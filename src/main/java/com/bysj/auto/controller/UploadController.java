package com.bysj.auto.controller;


import com.bysj.auto.common.ResponseEntity;
import com.bysj.auto.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin("*")
public class UploadController {

    @ResponseBody
    @PostMapping(value = "/uploads")
    public ResponseEntity upload(HttpServletRequest httpServletRequest) {
        ResponseEntity responseEntity = new ResponseEntity();
        List<MultipartFile> files = ((MultipartHttpServletRequest) httpServletRequest).getFiles("files");
        if (files.isEmpty()) {
            responseEntity.setCode(10002);
        } else {
            StringBuilder filesName = new StringBuilder();

            for (MultipartFile file : files) {
                filesName.append(UploadUtil.multipartFile2Local(file));
            }
            responseEntity.setData(filesName.toString());
        }
        return responseEntity;
    }

}
