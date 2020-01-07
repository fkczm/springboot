package com.abc.demo.designMode.builder;

/**
 * Created on 2019/12/23 in springboot 项目 .
 */
public class Test {
    public static void main(String[] args) {
    House house = new House.HouseBuilder().setDoor("天心木门").setFloor("圣象地板").setWindow("天窗").setWoor("天花板").builder();
    System.out.println(house);
    }
}
