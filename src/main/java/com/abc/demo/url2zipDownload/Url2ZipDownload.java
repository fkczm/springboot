package com.abc.demo.url2zipDownload;

import java.io.*;
import java.util.zip.ZipOutputStream;

/**
 * Created on 2019/12/31 in springboot 项目 .
 */
public class Url2ZipDownload  {
    /**
     * 使用GBK编码可以避免压缩中文文件名乱码
     */
    private static final String CHINESE_CHARSET = "GBK";

    /**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;
    /**
     * <p>
     * 压缩文件
     * </p>
     *
     * @param sourceFolder 需压缩文件 或者 文件夹 路径
     * @param zipFilePath 压缩文件输出路径
     * @throws Exception
     */
    public static void zip(String sourceFolder, String zipFilePath) throws Exception {
        OutputStream out = new FileOutputStream(zipFilePath);
        BufferedOutputStream bos = new BufferedOutputStream(out);
        ZipOutputStream zos = new ZipOutputStream(bos);

        // 解决中文文件名乱码
//        zos.setEncoding(CHINESE_CHARSET);
        File file = new File(sourceFolder);
        String basePath = null;
        if (file.isDirectory()) {
            basePath = file.getPath();
        } else {
            basePath = file.getParent();
        }
        zipFile(file, basePath, zos);
        zos.closeEntry();
        zos.close();
        bos.close();
        out.close();
    }

    /**
     * <p>
     * 递归压缩文件
     * </p>
     *
     * @param parentFile
     * @param basePath
     * @param zos
     * @throws Exception
     */
    private static void zipFile(File parentFile, String basePath, ZipOutputStream zos) throws Exception {
        File[] files = new File[0];
        if (parentFile.isDirectory()) {
            files = parentFile.listFiles();
        } else {
            files = new File[1];
            files[0] = parentFile;
        }
        String pathName;
        InputStream is;
        BufferedInputStream bis;
        byte[] cache = new byte[CACHE_SIZE];
        for (File file : files) {
            if (file.isDirectory()) {
                basePath = basePath.replace('\\', '/');
                if(basePath.substring(basePath.length()-1).equals("/")){
                    pathName = file.getPath().substring(basePath.length()) + "/";
                }else{
                    pathName = file.getPath().substring(basePath.length() + 1) + "/";
                }

//                zos.putNextEntry(new org.apache.tools.zip.ZipEntry(pathName));
                zipFile(file, basePath, zos);
            } else {
                pathName = file.getPath().substring(basePath.length()) ;
                pathName = pathName.replace('\\', '/');
                if(pathName.substring(0,1).equals("/")){
                    pathName = pathName.substring(1);
                }

                is = new FileInputStream(file);
                bis = new BufferedInputStream(is);
//                zos.putNextEntry(new org.apache.tools.zip.ZipEntry(pathName));
                int nRead = 0;
                while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                    zos.write(cache, 0, nRead);
                }
                bis.close();
                is.close();
            }
        }
    }
}
