package com.sm.dao.impl;

import com.sm.dao.ProfessionDAO;
import com.sm.entity.Profession;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessionDAOImpl implements ProfessionDAO {


    @Override
    public List<Profession> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_profession ORDER BY id ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Profession> professionList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return professionList;
    }

    //根据院系id查询专业
    @Override
    public List<Profession> selectDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_profession WHERE department_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,departmentId);
        ResultSet rs = pstmt.executeQuery();
        List<Profession> professionList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return professionList;
    }

    /**
     * 新增专业
     * @param profession
     * @return
     * @throws SQLException
     */
    @Override
    public int insetProfessionById(Profession profession) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_profession (department_id,profession_name) VALUES (?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, profession.getDepartmentId());
        pstmt.setString(2, profession.getProfessionName());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int updateProfessionById(Profession profession) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_profession SET profession_name = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,profession.getProfessionName());
        pstmt.setInt(2,profession.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    //根据专业Id删除专业
    @Override
    public int deletById(int id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_profession WHERE  id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        return n;
    }

    /**
     * 分装
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<Profession> convert(ResultSet rs) throws SQLException{
        List<Profession> professionList = new ArrayList<>();
        while (rs.next()){
            Profession profession = new Profession();
            profession.setId(rs.getInt("id"));
            profession.setDepartmentId(rs.getInt("department_id"));
            profession.setProfessionName(rs.getString("profession_name"));
            professionList.add(profession);
        }
        return professionList;
    }
}
