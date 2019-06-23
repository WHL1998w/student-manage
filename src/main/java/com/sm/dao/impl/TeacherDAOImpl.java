package com.sm.dao.impl;

import com.sm.dao.TeacherDAO;
import com.sm.entity.Department;
import com.sm.entity.Teacher;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public List<com.sm.entity.Teacher> selectAll(String phone) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_teacher WHERE phone = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,phone);
        ResultSet rs = pstmt.executeQuery();
        List<Teacher> teacherList = new ArrayList<>();
        while (rs.next()) {
            Teacher teacher = new Teacher();
            teacher.setTeacherName(rs.getString("teacher_name"));
            teacher.setHobby(rs.getString("hobby"));
            teacher.setAdress(rs.getString("address"));
            teacher.setCourseName(rs.getString("course_name"));
            teacher.setPhone(rs.getString("phone"));
            teacher.setAvatar(rs.getString("avatar"));
            teacher.setWorkTime(rs.getDate("work_time"));
            teacherList.add(teacher);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return teacherList;
    }

    @Override
    public int updateTeacher(Teacher teacher) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_teacher SET address = ? WHERE phone = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,teacher.getAdress());
        pstmt.setString(2,teacher.getPhone());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }
}
