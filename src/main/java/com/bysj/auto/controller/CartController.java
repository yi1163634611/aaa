package com.bysj.auto.controller;

import com.bysj.auto.bean.Cart;
import com.bysj.auto.common.ListResponseEntity;
import com.bysj.auto.common.ResponseEntity;
import com.bysj.auto.service.CartService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/plan")
    ResponseEntity planToCart(
            @RequestBody CartPlan cartPlan
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        int userId = cartPlan.getUserId();
        int planId = cartPlan.getPlanId();
        int num = cartPlan.getNum();

        cartService.planToCart(planId, userId, num);

        return responseEntity;
    }

    @GetMapping
    ListResponseEntity getCart(
            @RequestParam(value = "userId", required = true) int userId
    ) {
        ListResponseEntity listResponseEntity = new ListResponseEntity();

        listResponseEntity.setList(cartService.getCartByUserId(userId));

        return listResponseEntity;
    }

    @PostMapping
    ResponseEntity addCart(
            @RequestBody Cart cart
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        cartService.addCart(cart);

        return responseEntity;
    }

    @PutMapping
    ResponseEntity updateCart(
            @RequestBody Cart inputCart
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        cartService.updateCart(inputCart);

        return responseEntity;

    }

    @DeleteMapping
    ResponseEntity deleteCart(
            @RequestParam(value = "id", required = true) int id
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        cartService.deleteCart(id);

        return responseEntity;
    }

}

@Data
class CartPlan {

    private int userId;
    private int planId;
    private int num;
}
