package com.sm.dao.impl;

import com.sm.dao.TeacherLoginDAO;
import com.sm.entity.TeacherLogin;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherLoginDAOImpl implements TeacherLoginDAO {

    @Override
    public TeacherLogin getTeacherByAccount(String teacherAccount) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_teacher_login WHERE teacher_account = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, teacherAccount);
        ResultSet rs = pstmt.executeQuery();
        TeacherLogin teacherLogin = null;
        while (rs.next()) {
            int id = rs.getInt("id");
            String account = rs.getString("teacher_account");
            String password = rs.getString("tearcher_password");
            String name = rs.getString("tearcher_name");
            teacherLogin = new TeacherLogin();
            teacherLogin.setId(id);
            teacherLogin.setTeacherAccount(account);
            teacherLogin.setTeacherPassword(password);
            teacherLogin.setTeacherName(name);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return teacherLogin;
    }
}
