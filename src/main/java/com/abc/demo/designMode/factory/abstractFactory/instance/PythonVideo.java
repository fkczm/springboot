package com.abc.demo.designMode.factory.abstractFactory.instance;

import com.abc.demo.designMode.factory.abstractFactory.Video;

/**
 * Created on 2019/12/22 in springboot 项目 .
 */
public class PythonVideo extends Video {
    @Override
    public void process() {
        System.out.println("学习：PythonVideo");
    }
}
