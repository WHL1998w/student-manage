package com.sm.service.impl;

import com.sm.entity.CClass;
import com.sm.entity.Course;
import com.sm.factory.ServiceFacotry;
import com.sm.service.CourseService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourseServiceImplTest {
    private CourseService courseService = ServiceFacotry.getCourseServiceInstance();

    @Test
    public void selectAll() {
        List<Course> courseList = courseService.selectAll();
        courseList.forEach(course -> System.out.println(course));
    }

    @Test
    public void selectByKeywords() {

    }

    @Test
    public void updateGrade() {
    }
}