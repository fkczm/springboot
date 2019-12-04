package com.abc.demo.easyExcle.exclemodel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;



/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author wyy
 **/
@Data
@ColumnWidth(22)
@ContentRowHeight(15)
public class DemoData {

    @ExcelProperty(value = "姓名",index = 0)
    private String name;

    @ExcelProperty(value = "出生日期",index = 1)
    private String birthday;

    @ExcelProperty(value = "工资",index = 2)
    private Double salary;

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;

    public DemoData(String string, String date, Double doubleData) {
        this.name = string;
        this.birthday = date;
        this.salary = doubleData;
    }

    public DemoData() {
    }
}