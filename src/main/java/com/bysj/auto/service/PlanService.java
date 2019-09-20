package com.bysj.auto.service;

import com.bysj.auto.bean.Cart;
import com.bysj.auto.bean.Plan;
import com.bysj.auto.bean.Product;
import com.bysj.auto.bean.Relation;
import com.bysj.auto.dao.CartMapper;
import com.bysj.auto.dao.PlanMapper;
import com.bysj.auto.dao.ProductMapper;
import com.bysj.auto.dao.RelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class PlanService {
    @Autowired
    PlanMapper planMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    RelationMapper relationMapper;

    public List<Plan> selectPlan() {
        List<Plan> list = planMapper.select();
        for (Plan plan :
                list) {
            int planId = plan.getId();
            List<Product> products = productMapper.select(
                    new HashMap() {{
                        put("planId", planId);
                    }}
            );
            double price = 0;
            for (Product product :
                    products) {
                price += product.getPrice() * product.getNum();
            }
            plan.setPrice(price);
        }
        return list;
    }

    @Transactional
    public void addPlan(String name, int userId, String userName) {
        // 插入方案
        Plan plan = new Plan() {{
            setMerchants(userName);
            setName(name);
        }};
        planMapper.insertPlan(plan);
        int planId = plan.getId();

        // 插入方案和组件的关系
        List<Cart> products = cartMapper.select(userId);
        for (Cart cart : products) {
            Relation relation = new Relation() {{
                setPlanId(planId);
                setProductId(cart.getProductId());
                setNum(cart.getNum());
            }};
            relationMapper.insert(relation);
        }
    }

}
