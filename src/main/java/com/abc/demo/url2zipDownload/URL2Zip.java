package com.abc.demo.url2zipDownload;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created on 2019/12/31 in springboot 项目 .
 */
@RestController
public class URL2Zip {


    public static void main(String[] args) {
        String sourceFile = "F:/downloadFileNames/";
        File fileToZip = new File(sourceFile);
        System.out.println(fileToZip.getAbsolutePath());
        System.out.println(fileToZip.getAbsoluteFile());
        System.out.println(fileToZip.getName());
    }
    @GetMapping("/zipFile")
    public void zipFile() throws IOException {
        //这个是文件夹的绝对路径，如果想要相对路径就自行了解写法
//        String sourceFile = "F:/李恒杰-java工程师-4年-15313786569.doc";
        String sourceFile = "F:/downloadFileNames";
        //这个是压缩之后的文件绝对路径
        FileOutputStream fos = new FileOutputStream( "F:/downloadFileNames.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }

    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    @GetMapping("/download")
    public String  download(HttpServletResponse response) throws IOException {
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition","attachment; filename=a.zip" );
        Files.copy(Paths.get("F:/downloadFileNames.zip"),response.getOutputStream());
//        response.setHeader("Content-Disposition", "attachment; filename=" + new String(zip_file.getName().getBytes(), "ISO-8859-1"));
//        IOUtils.copy(fis, sos);
        Configuration sourceConf = new Configuration();
        return "hhh";
    }

}
