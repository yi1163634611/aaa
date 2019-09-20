package com.bysj.auto.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.bysj.auto.bean.Cart;
import com.bysj.auto.bean.Product;
import com.bysj.auto.bean.Record;
import com.bysj.auto.config.AlipayConfig;
import com.bysj.auto.dao.CartMapper;
import com.bysj.auto.dao.ProductMapper;
import com.bysj.auto.dao.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecordService {

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    public List<Record> getRecord(int userId) {
        return recordMapper.select(userId);
    }

    @Transactional
    public List<Cart> tryBuy(int userId) {
        /**
         * 检查库存
         **/
        List<Cart> carts = cartMapper.select(userId);
        // 将库存不足的购买记录添加到 res
        List<Cart> res = new ArrayList<Cart>();
        for (Cart cart : carts) {
            if (cart.getNum() > cart.getProductStock()) {
                res.add(cart);
            }
        }
        if (res.size() > 0) {
            return res;
        } else {

            return null;
        }
    }

    public void buy(int userId) {
        List<Cart> carts = cartMapper.select(userId);
        for (Cart cart : carts) {
            //减少 库存
            Product product = new Product() {{
                setImg(cart.getProductImg());
                setStock(cart.getProductStock() - cart.getNum());
                setMerchants(cart.getProductMerchants());
                setType(cart.getProductType());
                setPrice(cart.getProductPrice());
                setId(cart.getId());
                setName(cart.getProductName());
            }};
            productMapper.update(product);

            //新增购买记录
            Record record = new Record() {{
                setProductId(cart.getProductId());
                setUserId(cart.getUserId());
                setNum(cart.getNum());
            }};
            recordMapper.insert(record);
            //从购物车删除记录
            cartMapper.delete(cart.getId());
        }
    }

    public Double countPrice(int userId) {
        List<Cart> carts = cartMapper.select(userId);
        Double price = 0.0;
        for (Cart cart : carts) {
            price += cart.getNum() * cart.getProductPrice();
        }
        return price;
    }

    public String goToAlipay(Double price) {


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
//        String out_trade_no = UUID.randomUUID().toString().replaceAll("-", "");
        String out_trade_no = "1";
        //付款金额，必填
//        String total_amount = "0.01";
        String total_amount = price.toString();
        //订单名称，必填
        String subject = "测试付款";
        //商品描述，可空
        String body = "";

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = null;
        try {
            AlipayTradePagePayResponse response = alipayClient.pageExecute(alipayRequest);
            result = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;
    }

}
