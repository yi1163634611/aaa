package com.bysj.auto.controller;

import com.bysj.auto.bean.Product;
import com.bysj.auto.common.ListResponseEntity;
import com.bysj.auto.common.ResponseEntity;
import com.bysj.auto.service.ProductService;
import org.apache.tomcat.util.threads.ResizableExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping
    ListResponseEntity getProducts(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "planId", required = false) Integer planId
    ) {
        ListResponseEntity listResponseEntity = new ListResponseEntity();

        HashMap params = new HashMap() {{
            if (type != null && !type.isEmpty())
                put("type", type);
            if (id != null)
                put("id", id);
            if (planId != null)
                put("planId", planId);
        }};
        listResponseEntity.setList(productService.getProduct(params));
        return listResponseEntity;
    }


    @PostMapping
    ResponseEntity addProdcut(@RequestBody Product product) {
        ResponseEntity responseEntity = new ResponseEntity();

        productService.addProduct(product);

        return responseEntity;
    }


    @PutMapping
    ResponseEntity updateProduct(
            @RequestBody Product inputProduct
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        int productId = inputProduct.getId();

        Product product = productService.getProductById(productId);

        String name = inputProduct.getName();
        if (name != null) {
            product.setName(name);
        }
        Double price = inputProduct.getPrice();
        if (price != null) {
            product.setPrice(price);
        }
        String merchants = inputProduct.getMerchants();
        if (merchants != null) {
            product.setMerchants(merchants);
        }
        Integer stock = inputProduct.getStock();
        if (stock != null) {
            product.setStock(stock);
        }
        String img = inputProduct.getImg();
        if (img != null) {
            product.setImg(img);
        }

        productService.updateProduct(product);

        return responseEntity;
    }

}
