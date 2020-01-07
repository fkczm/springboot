package com.abc.demo.designMode.factory.abstractFactory.impl;

import com.abc.demo.designMode.factory.abstractFactory.CourseFactroy;
import com.abc.demo.designMode.factory.abstractFactory.Note;
import com.abc.demo.designMode.factory.abstractFactory.Video;
import com.abc.demo.designMode.factory.abstractFactory.instance.PythonNote;
import com.abc.demo.designMode.factory.abstractFactory.instance.PythonVideo;

/**
 * Created on 2019/12/22 in springboot 项目 .
 */
public class PythonCourseFactory implements CourseFactroy {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Note getNote() {
        return new PythonNote();
    }
}
