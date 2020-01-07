package com.abc.demo.url2zipDownload;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;//注意引用的是apache的
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created on 2020/1/4 in springboot 项目 .
 */
public class ApacheZip {
    @RequestMapping(value = "/zip1", method = RequestMethod.GET)
    public void downloadZip(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
        String fileName = sdf.format(dt);//压缩包名字，已经过 "中文测试";
        System.out.println(fileName);
        //李 1 创建 目标文件 zip File
        File zip_file = new File(fileName + ".zip");
        ZipOutputStream out = null;
        FileInputStream in = null;
        List<File> srcList = new ArrayList<File>();
        // 李 2 创建 源文件 File
        srcList.add(new File(java.net.URLDecoder.decode("F:\\李恒杰-java工程师-4年-15313786569.doc", "UTF-8")));//修改成真实的文件地址，下同
//        srcList.add(new File(java.net.URLDecoder.decode("D:\\work\\qr.png", "UTF-8")));
        try {
            // 李 3  创建 目标 zipOut 流（目标File）
            out = new ZipOutputStream(new FileOutputStream(zip_file));
            out.setEncoding("UTF-8");
            byte[] buf = new byte[1024];
            Iterator it = srcList.iterator();
            while (it.hasNext()) {
                File file = (File) it.next();
                // 李 4 创建 原文件 in 流
                in = new FileInputStream(file);
                // 李 5 创建zip里 entry内容
                out.putNextEntry(new ZipEntry(file.getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    // 李6 往 目标流 写 数据
                    out.write(buf, 0, len);
                }
                // 李 7 关 entry in
                out.closeEntry();
                in.close();
            }
            // 李 8 关 out
            out.flush();
            out.close();
            // 李 9 设置 请求 参数
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(zip_file.getName().getBytes(), "ISO-8859-1"));
            // 李 10 创建 zip 源流 input 获取 output流
            ServletOutputStream sos = response.getOutputStream();
            FileInputStream fis = new FileInputStream(fileName + ".zip");
            // 李11 拷贝
            IOUtils.copy(fis, sos);
            // 李12 关流
            sos.flush();
            sos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 李13 删除临时文件
            zip_file.delete();//删掉临时生成，供用户下载的zip文件
        }

    }

    public static void main(String[] args) throws IOException {
        String src = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(new File(src));
        //这个再Windows下是可以的，但是在Linux （centos7）下是不行的，正确的获取方法：
        Resource resource = new ClassPathResource("");
        System.out.println(resource.getFile().getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
    }



    /**
     * 提供给用户使用的基本压缩类
     * @param srcPath
     * @param outPath
     * @throws IOException
     */
    public static void compressFile(String srcPath, String outPath) throws IOException {
        //读取源文件
        File srcFile = new File(srcPath);
        //判断输出路径是否正确
        File outFile = new File(outPath);
        //如果只是路劲加入对应的压缩名称
        if (outFile.isDirectory()) {
            //用"/"作文判断标准
            if (outPath.endsWith(File.separator)) {
                outPath += srcFile.getName().split("\\.")[0] + ".zip";
            } else {
                outPath += File.separator + srcFile.getName().split("\\.")[0] + ".zip";
            }
        }
        //读取文件流
        FileOutputStream fileOutputStream = new FileOutputStream(outPath);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        //压缩文件
        compressFile(srcFile, srcFile.getName(),zipOutputStream);
        //关闭流
        zipOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * 迭代方式进行文件压缩
     * @param file
     * @param fileName
     * @param outputStream
     * @throws IOException
     */
    private static void compressFile(File file, String fileName, final ZipOutputStream outputStream) throws IOException {
        //如果是目录
        if (file.isDirectory()) {
            //创建文件夹
            outputStream.putNextEntry(new ZipEntry(fileName+"/"));
            //迭代判断，并且加入对应文件路径
            File[] files = file.listFiles();
            Iterator<File> iterator = Arrays.asList(files).iterator();
            while (iterator.hasNext()) {
                File f = iterator.next();
                compressFile(f, fileName+"/"+f.getName(), outputStream);
            }
        } else {
            //创建文件
            outputStream.putNextEntry(new ZipEntry(fileName));
            //读取文件并写出
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] bytes = new byte[1024];
            int n;
            while ((n = bufferedInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, n);
            }
            //关闭流
            fileInputStream.close();
            bufferedInputStream.close();
        }
    }
}
