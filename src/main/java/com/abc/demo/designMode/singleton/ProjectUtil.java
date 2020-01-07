package com.abc.demo.designMode.singleton;

/**
 * Created on 2019/12/23 in springboot 项目 .
 */
public class ProjectUtil {

    private static final ProjectUtil instance = new ProjectUtil();
    private ProjectUtil (){};
    public static ProjectUtil getInstance(){
        return instance;
    }

}
