package com.abc.demo.designMode.factory.factoryMethod;

import com.abc.demo.designMode.factory.simpleFactory.Apple;
import com.abc.demo.designMode.factory.simpleFactory.RedApple;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
public class RedAppleFactory implements  AppleFactory {
    @Override
    public Apple getApple() {
        return new RedApple();
    }
}
