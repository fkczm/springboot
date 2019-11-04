package com.abc.demo._18fileupload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.awt.font.ShapeGraphicAttribute;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by lihj on 2019/10/27.
 */
@Controller
public class FileuploadController {
    @Value("${file.path}")
    private String filePath;
    @RequestMapping("/file")
    @ResponseBody
    public String fileupload(MultipartFile file) throws  Exception {
        System.out.println( "file 是："+file.getName());
        System.out.println("filePath 是："+ filePath);
        String extName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName= UUID.randomUUID().toString()+extName;
        FileCopyUtils.copy(  file.getInputStream(),new FileOutputStream(new File(filePath+fileName)));
        return fileName;
    }
}
