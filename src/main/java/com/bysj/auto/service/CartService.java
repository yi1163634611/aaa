package com.bysj.auto.service;

import com.bysj.auto.bean.Cart;
import com.bysj.auto.bean.Plan;
import com.bysj.auto.bean.Product;
import com.bysj.auto.dao.CartMapper;
import com.bysj.auto.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    public List<Cart> getCartByUserId(int userId) {
        return cartMapper.select(userId);
    }

    public int addCart(Cart cart) {
        int userId = cart.getUserId();
        int productId = cart.getProductId();

        Cart cartInTable = cartMapper.selectByUserIdAndProductId(new HashMap<String,Object>(){{
            put("userId",userId);
            put("productId",productId);
        }});
        if (cartInTable != null) {
            cartInTable.setNum(cartInTable.getNum() + cart.getNum());
            return updateCart(cartInTable);
        } else {
            return cartMapper.insert(cart);
        }
    }


    @Transactional
    public void planToCart(int planId, int userId, int num) {
        List<Product> products = productMapper.select(
                new HashMap() {{
                    put("planId", planId);
                }}
        );
        for (Product product : products) {
            Cart cart = new Cart() {{
                setUserId(userId);
                setNum(num);
                setProductId(product.getId());
            }};
            addCart(cart);
        }
    }

    public int updateCart(Cart cart) {
        return cartMapper.update(cart);
    }

    public int deleteCart(int id) {
        return cartMapper.delete(id);
    }


}
