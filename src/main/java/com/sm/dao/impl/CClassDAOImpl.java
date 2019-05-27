package com.sm.dao.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.utils.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CClassDAOImpl implements CClassDAO {
    /**
     * 根据院系id查班级
     * @param departmentId
     * @return List<CClass>
     * @throws SQLException
     */
    @Override
    public List<CClass> selectByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_class WHERE department_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,departmentId);
        ResultSet rs = pstmt.executeQuery();
        List<CClass> classList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return classList;
    }

    /**
     * 根据班级id删除班级
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteById(int id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_class WHERE  id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        return n;
    }

    @Override
    public List<CClass> getClassByName(String className) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_class WHERE class_name = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, className);
        ResultSet rs = pstmt.executeQuery();
        List<CClass> cClassList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return cClassList;
    }

    /**
     * 新增班级
     * @param cClass
     * @return int
     * @throws SQLException
     */
    @Override
    public int insertClass(CClass cClass) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_class (department_id,class_name) VALUES (?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, cClass.getDepartmentId());
        pstmt.setString(2, cClass.getClassName());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    /**
     * 查询所有班级
     * @return List<CClass>
     * @throws SQLException
     */
    @Override
    public List<CClass> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_class ORDER BY department_id ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<CClass> classList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return classList;
    }

    /**
     * 封装方法
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<CClass> convert(ResultSet rs) throws SQLException{
        List<CClass> classList = new ArrayList<>();
        while (rs.next()){
            CClass cClass = new CClass();
            cClass.setId(rs.getInt("id"));
            cClass.setDepartmentId(rs.getInt("department_id"));
            cClass.setClassName(rs.getString("class_name"));
            classList.add(cClass);
        }
        return classList;
    }
}
