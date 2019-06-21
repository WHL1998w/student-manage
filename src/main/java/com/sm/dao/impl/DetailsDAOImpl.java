package com.sm.dao.impl;

import com.sm.dao.DetailsDAO;
import com.sm.entity.Details;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailsDAOImpl implements DetailsDAO {

    @Override
    public List<Details> selectDepartmentById(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t.id,department_name,description FROM t_department WHERE t1.id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,departmentId);
        ResultSet rs = pstmt.executeQuery();
        List<Details> rewardsList = convert(rs);
        rs.close();
        jdbcUtil.closeConnection();
        pstmt.close();
        return rewardsList;
    }

    @Override
    public List<Details> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t.id,department_name,description FROM t_department ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Details> rewardsList = convert(rs);
        rs.close();
        jdbcUtil.closeConnection();
        pstmt.close();
        return rewardsList;
    }


    /**
     * 分装方法
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<Details> convert(ResultSet rs) throws SQLException{
        List<Details> detailsList = new ArrayList<>();
        while (rs.next()){
            Details details = new Details();
            details.setDepartmentId(rs.getInt("id"));
            details.setDepartmentName(rs.getString("department_name"));
            details.setDescription(rs.getString("description"));
            detailsList.add(details);
        }
        return detailsList;
    }
}
