package com.sm.service.impl;

import com.sm.dao.CourseDAO;
import com.sm.entity.Course;
import com.sm.factory.DAOFactory;
import com.sm.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO = DAOFactory.getCourseDAOInstance();
    @Override
    public List<Course> selectAll() {
        List<Course> courseList = null;
        try {
            courseList = courseDAO.selectAll();
        } catch (SQLException e) {
            System.out.println("查询课程信息错误");
        }
        return courseList;
    }

    @Override
    public List<Course> selectByKeywords(String keywords) {
        List<Course> courseList = null;
        try {
            courseList = courseDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            System.out.println("根据关键字查询学生选修课程情况错误");
        }
        return courseList;
    }

    @Override
    public int updateGrade(Course course) {
        int n = 0;
        try {
            n = courseDAO.updateGrade(course);
        } catch (SQLException e) {
            System.out.println("修改学生成绩错误");
        }
        return n;
    }

    @Override
    public int insertByStudentId(Course course) {
        int n = 0;
        try {
            n = courseDAO.insertByStudentId(course);
        } catch (SQLException e) {
            System.out.println("新增学生成绩错误");
        }
        return n;
    }
}
