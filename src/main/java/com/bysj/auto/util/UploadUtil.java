package com.bysj.auto.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtil {

    private static String path = "E:\\lixiaoyi\\img";

    public static String multipartFile2Local(MultipartFile multipartFile) {

        String contentType = multipartFile.getContentType();
        String suffixName = contentType.substring(contentType.indexOf("/")).replaceAll("/",".");

        String destName = UUID.randomUUID().toString().replaceAll("-", "") + suffixName;

        String destFullName = path + File.separator + destName;

        File dest = new File(destFullName);

        //判断文件所在路径是否存在
        if (!dest.exists()) {
            dest.getParentFile().mkdir();
            try {
                dest.createNewFile();
            } catch (IOException e) {
                // TODO: 2019/3/12 新建文件发生错误
                e.printStackTrace();
            }
        }

        try {
            multipartFile.transferTo(dest);
            System.out.println("upload success");
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 2019/3/12 接受文件发生错误
        } finally {

        }
        return destName;
    }

}
