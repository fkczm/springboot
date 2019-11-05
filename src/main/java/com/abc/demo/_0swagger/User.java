package com.abc.demo._0swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lihj on 2019/10/27.
 */
@ApiModel(description = "UserModel 对象")
public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;
    @ApiModelProperty(value="参数中文名称:age",required=true,example = "10")
    private int age;
    @ApiModelProperty(value="参数中文名称:id",required=true,example = "0")
    private Long id;
}
