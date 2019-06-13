package com.sm.dao.impl;

import com.sm.dao.StudentLoginDAO;
import com.sm.entity.StudentLogin;
import com.sm.entity.TeacherLogin;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentLoginDAOImpl implements StudentLoginDAO {
    @Override
    public StudentLogin getStudentAccountBy(String studentAccount) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_student_login WHERE student_account = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, studentAccount);
        ResultSet rs = pstmt.executeQuery();
        StudentLogin studentLogin = null;
        while (rs.next()) {
            int id = rs.getInt("id");
            String account = rs.getString("student_account");
            String password = rs.getString("student_password");
            String name = rs.getString("name");
            studentLogin = new StudentLogin();
            studentLogin.setId(id);
            studentLogin.setStudentAccount(account);
            studentLogin.setStudentPassword(password);
            studentLogin.setName(name);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentLogin;
    }
}
