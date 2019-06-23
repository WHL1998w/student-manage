package com.sm.service;

import com.sm.entity.Course;
import com.sm.entity.CourseVO;

import java.sql.SQLException;
import java.util.List;

public interface CourseService {
    /**
     * 查询所有课程
     * @return
     */
    List<Course> selectAll();

    /**
     * 根据关键字查询课程
     * @param keywords
     * @return
     */
    List<Course> selectByKeywords(String keywords);

    /**
     * 修改成绩
     * @param course
     * @return
     */
    int updateGrade(Course course);

    int insertByStudentId(Course course);

    List<Course> selectTeacherAccount(String adminAccount);

    List<CourseVO> getSelectAll();
}
