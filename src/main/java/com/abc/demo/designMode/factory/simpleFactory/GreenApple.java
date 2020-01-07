package com.abc.demo.designMode.factory.simpleFactory;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
public class GreenApple extends  Apple {
    @Override
    public void color() {
        System.out.println("我是青苹果");
    }
}
