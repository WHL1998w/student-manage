package com.sm.dao.impl;

import com.sm.dao.RewardsDAO;
import com.sm.entity.Rewards;
import com.sm.entity.StudentVO;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RewardsDAOImpl implements RewardsDAO {
    /**
     * 查询所有学生
     * @return List<Rewards>
     * @throws SQLException
     */
    @Override
    public List<Rewards> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_rewards t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id";
        PreparedStatement prstm = connection.prepareStatement(sql);
        ResultSet rs = prstm.executeQuery();
        List<Rewards> rewardsList = convert(rs);
        rs.close();
        jdbcUtil.closeConnection();
        prstm.close();
        return rewardsList;
    }

    /**
     * 根据关键字查询学生奖惩情况
     * @param keywords
     * @return List<Rewards>
     * @throws SQLException
     */
    @Override
    public List<Rewards> selectByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_rewards t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id\n"+
                "WHERE t1.student_name LIKE ? OR t2.class_name LIKE ? OR t3.department_name LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        pstmt.setString(3,"%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<Rewards> rewardsList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rewardsList;
    }

    /**
     * 分装方法
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<Rewards> convert(ResultSet rs) throws SQLException{
        List<Rewards> rewardsList = new ArrayList<>();
        while (rs.next()){
            Rewards rewards = new Rewards();
            rewards.setDepartmentName(rs.getString("department_name"));
            rewards.setClassName(rs.getString("class_name"));
            rewards.setStudentId(rs.getString("student_id"));
            rewards.setStudentName(rs.getString("student_name"));
            rewards.setAward(rs.getString("award"));
            rewards.setPunishment(rs.getString("punishment"));
            rewardsList.add(rewards);
        }
        return rewardsList;
    }

}
