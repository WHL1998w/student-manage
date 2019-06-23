package com.sm.dao;

import com.sm.entity.Course;
import com.sm.entity.CourseVO;
import com.sm.entity.Rewards;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    /**
     * 查询所有课程
     * @return
     * @throws SQLException
     */

    List<Course> selectAll() throws SQLException;

    /**
     * 根据关键字查询
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<Course> selectByKeywords(String keywords) throws SQLException;


    /**
     * 根据学号增加课程
     * @param course
     * @return
     * @throws SQLException
     */
    int insertByStudentId(Course course) throws SQLException;

    /**
     * 修改成绩
     * @param course
     * @return
     * @throws SQLException
     */
    int updateGrade(Course course) throws SQLException;

    /**
     * 根据老师的账号查找成绩
     * @param adminAccount
     * @return
     * @throws SQLException
     */
    List<Course> selectTeacherAccount(String adminAccount) throws SQLException;

    List<CourseVO> getSelectAll() throws SQLException;

}
