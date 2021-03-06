package com.abc.demo.test;

import com.abc.demo.url2zipDownload.ZipUtils;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

/**
 * Created by lihj on 2019/10/26.
 */

public class MyTest {
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
