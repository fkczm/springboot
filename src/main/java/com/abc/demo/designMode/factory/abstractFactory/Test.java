package com.abc.demo.designMode.factory.abstractFactory;

import com.abc.demo.designMode.factory.abstractFactory.impl.JavaCourseFactory;
import com.abc.demo.designMode.factory.factoryMethod.AppleFactory;
import com.abc.demo.designMode.factory.factoryMethod.RedAppleFactory;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
public class Test {


    public static void main(String[] args) {
       CourseFactroy javaFactory =  new JavaCourseFactory();
        javaFactory.getNote().process();
    }

}
