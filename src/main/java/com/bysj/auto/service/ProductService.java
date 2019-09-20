package com.bysj.auto.service;

import com.bysj.auto.bean.Product;
import com.bysj.auto.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> getProduct(HashMap params) {
        return productMapper.select(params);
    }

    public Product getProductById(int productId) {
        return productMapper.selectById(
                new HashMap<String, Object>() {{
                    put("id", productId);
                }}
        );
    }

    public int updateProduct(Product product) {
        return productMapper.update(product);
    }

    public int addProduct(Product product) {
        return productMapper.insert(product);
    }

}
