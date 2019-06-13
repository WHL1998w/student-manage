package com.sm.dao;

import com.sm.entity.TeacherLogin;

import java.sql.SQLException;

public interface TeacherLoginDAO {

    /**
     * 根据老师找好查找老师
     * @param teacherAccount
     * @return
     * @throws SQLException
     */
    TeacherLogin getTeacherByAccount(String teacherAccount) throws SQLException;
}
