package com.abc.demo.designMode.factory.abstractFactory.impl;

import com.abc.demo.designMode.factory.abstractFactory.CourseFactroy;
import com.abc.demo.designMode.factory.abstractFactory.Note;
import com.abc.demo.designMode.factory.abstractFactory.Video;
import com.abc.demo.designMode.factory.abstractFactory.instance.JavaNote;
import com.abc.demo.designMode.factory.abstractFactory.instance.JavaVideo;

/**
 * Created on 2019/12/22 in springboot 项目 .
 */
public class JavaCourseFactory implements CourseFactroy {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Note getNote() {
        return new JavaNote();
    }
}
