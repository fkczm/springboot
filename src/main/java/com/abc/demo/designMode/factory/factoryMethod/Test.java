package com.abc.demo.designMode.factory.factoryMethod;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
public class Test {

    /**
     * 只需要知道对应的factory即可，对于这些factory内部实现细节并不关心，同时这里有一个好处，就是解决了简单工厂方法中违背开闭原则的缺点，
     * 试想一下，如果我们需要新增产品，比如还是黄苹果和黑苹果，那么我们只需要新增对应的工厂和苹果类型即可，
     * 不需要对原有的接口进行修改，也就是进行了扩展。但类的个数还是增加了。
     *
     *     到这里，我们再来总结一下，工厂方法的优点：用户只需关心所需产品对应的工厂，不关心创建细节。新增产品符合开闭原则，提高了扩展性。
     *     缺点：类的个数增多，不利于维护。同时增加了系统的抽象程度。
     *
     *     URLStreamHandlerFactory
     * @param args
     */
    public static void main(String[] args) {
        AppleFactory apple =  new RedAppleFactory();
        apple.getApple();
    }

}
