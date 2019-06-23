package com.sm.dao;

import com.sm.entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDAO {
    /**
     * 按照老师账号查找老师
     * @return
     * @throws SQLException
     */
    List<Teacher> selectAll(String phone) throws SQLException;


    int updateTeacher(Teacher teacher) throws SQLException;


}
