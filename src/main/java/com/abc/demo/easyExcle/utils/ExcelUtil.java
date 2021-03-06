package com.abc.demo.easyExcle.utils;

import com.abc.demo.easyExcle.domain.Employee;
import com.abc.demo.easyExcle.mapper.EmployeeMapper;
import com.abc.demo.easyExcle.service.EmployeeService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author wyy
 * @version 2.0
 * @date 2019/7/30 16:27
 * @deprecated 版本升级为对应的2.0.0以上
 * 性能更加高效  导出数据更加稳定
 * 支持 64M内存1分钟内读取75M(46W行25列)
 **/
@Component
public class ExcelUtil {
    /**
     * 导出 Excel ：一个 sheet，带表头.
     *
     * @param response  HttpServletResponse
     * @param data      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param model     映射实体类，Excel 模型
     * @throws Exception 异常
     */
    private  final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    public  void writeExcel(HttpServletResponse response, List<? extends Object> data,
                                  String fileName, String sheetName, Class model) throws Exception {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(getOutputStream(fileName,response), model)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet(sheetName)
                .registerWriteHandler(horizontalCellStyleStrategy)
                //最大长度自适应 目前没有对应算法优化 建议注释不用 会出bug
                // .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(data);

    }


    /**
     * 导出文件时为Writer生成OutputStream.
     * @param fileName 文件名
     * @param response
     * @return
     * @throws Exception
     */
    private  OutputStream getOutputStream(String fileName,
                                                HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }


    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    public   Map<String, Object> simpleRead(String fileName)  {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
//        // 写法1：
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
        DataListener dataListener = new DataListener(employeeMapper);

        try {
            EasyExcel.read(getInputStream(fileName),Employee.class,dataListener).headRowNumber(1).sheet().doRead();
        } catch (Exception e) {
            LOGGER.info("读取excel异常={}",e);

        }

        Map<String, Object> data = dataListener.getData();
        String exception = data.get("exception") + "";
        int exceptionCount = Integer.parseInt(exception);
        if(exceptionCount>0){
            String filename = "F:/upload/easy.xlsx";
            // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            EasyExcel.write(filename, Employee.class).sheet("模板").doWrite(dataListener.getExceptionList());
            data.put("filename",filename);
        }





        return data;
        // 写法2：
//        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//        excelReader.finish();
    }

    private  InputStream getInputStream(String fileName) throws Exception {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
            return inputStream;
        } catch (IOException e) {
            throw new Exception("写入表格失败！", e);
        }
    }

    public class DataListener extends AnalysisEventListener<Employee> {
        /**
         * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
         */
        private static final int BATCH_COUNT = 100;
        private int successCount = 0; // 成功量

        private List<Object> exceptionList = new ArrayList<>(); // 异常数据
        private int exceptionCount = 0; // 异常量
        List<Employee> list = new ArrayList<Employee>();
        public List<Employee> getList() {
            return list;
        }
        /**
         * 这个每一条数据解析都会来调用
         *
         * @param data
         *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
         * @param context
         */
        @Override
        public void invoke(Employee data, AnalysisContext context) {
            LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
            successCount++;
            list.add(data);
            // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
            if (list.size() >= BATCH_COUNT) {
                saveData();
                // 存储完成清理 list
                list.clear();
                LOGGER.info("存储完成清理 list:{}", list);
            }
        }

        /**
         * 所有数据解析完成了 都会来调用
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
            LOGGER.info("所有数据解析完成！");
            saveData();
        }

        /**
         * 加上存储数据库
         */
        private List<Employee> saveData() {
            LOGGER.info("{}条数据，开始存储数据库！", list.size());
            for (Employee employee : list) {
                 employeeMapper.insert(employee);
            }
            return list;
        }
        private List<Employee> saveBatchData() {
            LOGGER.info("{}条数据，开始批量插入数据库！", list.size());
            employeeMapper.insertBatchEmployee(list);
            return list;
        }
        /**
         * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
         */
        private EmployeeMapper employeeMapper;
        /**
         * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
         *
         * @param employeeMapper
         */
        public DataListener(EmployeeMapper employeeMapper) {
            this.employeeMapper = employeeMapper;
        }

        public DataListener() {
            // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        }
        /**
         * 这里会一行行的返回头
         *
         * @param headMap
         * @param context
         */
        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
            LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
        }
        /**
         * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
         *
         * @param exception
         * @param context
         * @throws Exception
         */
//        @Override
//        public void onException(Exception exception, AnalysisContext context) {
//            LOGGER.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
//            // 如果是某一个单元格的转换异常 能获取到具体行号
//            // 如果要获取头的信息 配合invokeHeadMap使用
//            if (exception instanceof ExcelDataConvertException) {
//                ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
//                LOGGER.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
//                        excelDataConvertException.getColumnIndex());
//            }
//        }
        @Override
        public void onException(Exception exception, AnalysisContext context) {
            LOGGER.error("解析失败，但是继续解析下一行exception:【{}】", exception);
            Object employee  =  context.readRowHolder().getCurrentRowAnalysisResult();
            exceptionList.add(employee);
            exceptionCount++;
        }
        /**
         * 插入结果返回
         * @return
         */
        public Map<String,Object> getData(){

            Map<String,Object> map = new HashMap<>();
            map.put("success",successCount);
            map.put("exception",exceptionCount);
            return map;
        }

        /**
         * 失败数据返回
         * @return
         */
        public List<Object> getExceptionList(){

            return exceptionList;
        }
    }

}
