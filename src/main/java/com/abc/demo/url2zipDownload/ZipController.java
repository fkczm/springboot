package com.abc.demo.url2zipDownload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

/**
 * Created on 2019/12/31 in springboot 项目 .
 */
@Controller
public class ZipController {

    @RequestMapping("/zip")
    public  void  downZip(HttpServletResponse response ) throws IOException {
        String sessionId = UUID.randomUUID().toString();
        String strNow = "model";
        String tempDir = "F:/";
        List<String> downloadFileNames = new ArrayList<>();
        downloadFileNames.add("F:/downloadFileNames");
        String zipFileName = String.format("%s-%s.zip", sessionId, strNow);
        downLoadZipFile(response, zipFileName, tempDir, downloadFileNames);
         PrintWriter writer = response.getWriter();
         writer.write(1024);

    }
    /**
     * 下载 zip 文件
     * @param response
     * @param zipName   zip 文件名
     * @param filePath  需要压缩的文件路径
     * @param fileList  需要压缩的文件名列表
     * @throws IOException
     */
    public void downLoadZipFile(HttpServletResponse response, String zipName, String filePath, List<String> fileList) throws IOException {
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for (String fileName : fileList){
                ZipUtils.doCompress(filePath + File.separator + fileName, out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
}
