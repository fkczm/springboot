package com.abc.demo.designMode.factory.simpleFactory;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
public class Test {

    public static void main(String[] args) {
       Apple apple = AppleFactory.getAppleByName("red");
       apple.color();
         apple = AppleFactory.getAppleByReflect(RedApple.class);
        apple.color();
    }
}
