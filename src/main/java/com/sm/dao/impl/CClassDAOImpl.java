package com.sm.dao.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CClassDAOImpl implements CClassDAO {
    //根据院系id查询班级
    @Override
    public List<CClass> selectByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_class WHERE department_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, String.valueOf(departmentId));
        ResultSet rs = pstmt.executeQuery();
        List<CClass> classList = new ArrayList<>();
        while (rs.next()){
            CClass cClass = new CClass();
            cClass.setId(rs.getInt("id"));
            cClass.setDepartmentId(rs.getInt("department_id"));
            cClass.setClassName(rs.getString("class_name"));
            classList.add(cClass);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return classList;

    }
    //根据id删除班级
    @Override
    public int deleteById(int id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_class WHERE id= "+id;
        PreparedStatement pstmt =connection.prepareStatement(sql);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }
    //新增班级
    @Override
    public int insertCClass(CClass cClass) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_class(department_id,class_name) VALUES (?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(2,cClass.getClassName());
        pstmt.setString(1, String.valueOf(cClass.getDepartmentId()));
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }
}
