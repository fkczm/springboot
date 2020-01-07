package com.abc.demo.designMode.factory.simpleFactory;

import org.apache.commons.lang3.StringUtils;

/**
 *      简单工厂的定义：由一个工厂对象决定创建出哪一种产品类的实例。
 *     类型：创建型，但不属于GOF23种设计模式。
 *     适用场景：（1）工厂类负责创建的对象比较少。（2）应用层只知道传入的参数，对于创建实例的细节并不关心。
 * Created on 2019/12/18 in springboot 项目 .
 */

public class AppleFactory {
    /**
     * 简单工厂方法的优点：只需要传入一个正确的参数，就可以获取你需要的对象，不需要知道创建的细节。
     * 缺点：工厂类的职责过重，增加新的产品需要修改工厂的逻辑，违背了开闭原则，同时会新增类的个数，不利于维护。（
     * 这里还有一个缺点，就是无法形成继承等级的结构，在接下来的章节中鸡哥会提到）
     * @param color
     * @return
     */
    public static   Apple getAppleByName(String color){
        if(StringUtils.equals("green",color)){
            return new GreenApple();
        }else if(StringUtils.equals("red",color)){
            return new RedApple();
        }
        return null;
    }

    public static   Apple getAppleByReflect(Class<? extends Apple>  clz){
        Apple apple =null;
        try {
             apple = clz.newInstance();
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return apple;
    }
}
