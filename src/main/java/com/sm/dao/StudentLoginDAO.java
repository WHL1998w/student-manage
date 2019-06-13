package com.sm.dao;

import com.sm.entity.StudentLogin;

import java.sql.SQLException;

public interface StudentLoginDAO {
    /**
     * 根据学生账号查找
     * @param studentAccount
     * @return
     * @throws SQLException
     */
    StudentLogin getStudentAccountBy(String studentAccount)throws SQLException;
}
