package com.bysj.auto.controller;

import com.bysj.auto.bean.Cart;
import com.bysj.auto.common.ListResponseEntity;
import com.bysj.auto.common.ResponseEntity;
import com.bysj.auto.service.RecordService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class RecordController {

    @Autowired
    RecordService recordService;


    @PostMapping("/buy")
    ResponseEntity tryBuy(
            @RequestBody tryBuyRequest request
    ) {
        ResponseEntity responseEntity = new ResponseEntity();
        List<Cart> failList = recordService.tryBuy(request.getUserId());
        if (failList != null) {
            StringBuilder sb = new StringBuilder();
            for (Cart cart : failList) {
                sb.append(" " + cart.getProductName());
                sb.append(" 库存不足!");
            }
            responseEntity.setCode(4396);
            responseEntity.setMessage(sb.toString());
        } else {
            Double price = recordService.countPrice(request.getUserId());
            // 跳转购买
            String result = recordService.goToAlipay(price);
            //减少库存
            recordService.buy(request.getUserId());
            responseEntity.setData(result);
        }
        return responseEntity;
    }


    @GetMapping("/record")
    ListResponseEntity getRrecord(
            @RequestParam(value = "userId", required = true) int userId
    ) {
        ListResponseEntity listResponseEntity = new ListResponseEntity();
        listResponseEntity.setList(recordService.getRecord(userId));
        return listResponseEntity;
    }


}

@Data
class tryBuyRequest {
    private Integer userId;

}
