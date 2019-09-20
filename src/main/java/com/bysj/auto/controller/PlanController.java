package com.bysj.auto.controller;

import com.bysj.auto.bean.Plan;
import com.bysj.auto.common.ListResponseEntity;
import com.bysj.auto.common.ResponseEntity;
import com.bysj.auto.service.PlanService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {


    @Autowired
    PlanService planService;


    @GetMapping
    ListResponseEntity getPlan(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        ListResponseEntity<Plan> listResponseEntity = new ListResponseEntity<>();
        PageHelper.startPage(page, size);

        List<Plan> plans = planService.selectPlan();

        listResponseEntity.setList(plans);

        return listResponseEntity;
    }


    @PostMapping
    ResponseEntity addPlan(
//            @RequestParam(value = "name") String name,
//            @RequestParam(value = "userId") int userId,
//            @RequestParam(value = "userName") String userName
            @RequestBody Plan plan
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        planService.addPlan(plan.getName(), plan.getUserId(), plan.getMerchants());

        return responseEntity;
    }

}
