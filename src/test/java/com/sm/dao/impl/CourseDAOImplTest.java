package com.sm.dao.impl;

import com.sm.dao.CourseDAO;
import com.sm.entity.Course;
import com.sm.entity.CourseVO;
import com.sm.entity.Rewards;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CourseDAOImplTest {
    private CourseDAO courseDAO = DAOFactory.getCourseDAOInstance();

    @Test
    public void selectAll() {
        List<Course> courseList = null;
        try {
            courseList = courseDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseList.forEach(course -> System.out.println(course));

    }


    @Test
    public void selectByKeywords() {
        List<Course> courseList = null;
        try {
            courseList = courseDAO.selectByKeywords("1802343334");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseList.forEach(course -> System.out.println(course));
    }

    @Test
    public void updateGrade() {
        Course course = new Course();
        course.setGrade((double) 86);
        course.setStudentId("1802343301");
        int n = 0;
        try {
            n = courseDAO.updateGrade(course);
        } catch (SQLException e) {
            System.out.println("修改成绩失败");
        }
        assertEquals(1,n);
    }

    @Test
    public void insertByStudentId() {
        Course course = new Course();
        course.setId(39);
        course.setStudentId("1802343301");
        //course.setStudentName("白建坤");
        //course.setCourseName("传统文化");
        course.setGrade(Double.valueOf(90));
        course.setCourseNumber("002");
        int n = 0;
        try {
            n = courseDAO.insertByStudentId(course);
        } catch (SQLException e) {
            System.out.println("新增失败");
        }
        assertEquals(1,n);
    }

    @Test
    public void selectTeacherAccount() {
        List<Course> courseList = null;
        try {
            courseList = courseDAO.selectTeacherAccount("13919532645");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseList.forEach(course -> System.out.println(course));
    }

    @Test
    public void getSelectAll() {
        List<CourseVO> courseList = null;
        try {
            courseList = courseDAO.getSelectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseList.forEach(courseVO -> System.out.println(courseVO));

    }
}