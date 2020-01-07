package com.abc.demo.url2zipDownload;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 2020/1/4 in springboot 项目 .
 */
@RestController
public class IOUtilTest {
    @RequestMapping("/id")
    public  String get(HttpServletResponse response) throws IOException {
        String file = "F:/李恒杰-java工程师-4年-15313786569.doc";
        InputStream in = new FileInputStream(file);
        IOUtils.copy(in, response.getOutputStream());
        return "io特殊";
    }
}
