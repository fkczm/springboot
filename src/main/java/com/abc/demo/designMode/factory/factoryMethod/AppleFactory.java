package com.abc.demo.designMode.factory.factoryMethod;

import com.abc.demo.designMode.factory.simpleFactory.Apple;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
public interface AppleFactory {
    /**
     * 工厂的定义：定义一个创建对象的接口，但让实现这个接口的类来决定实例化哪个类，工厂方法让类的实例化推迟到子类中进行。
     *     类型：创建型。
     *     适用场景：（1）创建对象需要大量的重复代码。（2）应用层对于创建实例的细节并不关心。（3）一个类通过其子类来指定创建哪个对象。
     *     URLStreamHandlerFactory
     * @return
     */
    Apple getApple();
}
