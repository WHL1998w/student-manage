package com.sm.dao.impl;

import com.sm.dao.RewardsDAO;
import com.sm.entity.Rewards;
import com.sm.utils.JDBCUtil;

import java.sql.*;
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
        String sql = "SELECT t1.student_name,gender,admin_account,t2.department_name,t3.class_name,t4.id,student_id,kind,award_punishment,t_date\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t3\n" +
                "ON t1.class_id=t3.id\n" +
                "LEFT JOIN t_department t2\n" +
                "ON t3.department_id=t2.id\n" +
                "LEFT JOIN t_rewards t4\n" +
                "ON t4.student_id=t1.id";
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
        String sql = "SELECT t1.*,t2.student_name,gender,admin_account,t3.class_name,t4.department_name\n" +
                "FROM t_rewards t1\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t1.student_id=t2.id\n" +
                "LEFT JOIN t_class t3\n" +
                "ON t2.class_id=t3.id\n"+
                "LEFT JOIN t_department t4\n" +
                "ON t3.department_id=t4.id\n" +
                "WHERE t1.kind LIKE ? OR t2.student_name LIKE ? OR t3.class_name LIKE ? OR t4.department_name LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        pstmt.setString(3,"%" + keywords + "%");
        pstmt.setString(4,"%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<Rewards> rewardsList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rewardsList;
    }

    /**
     * 修改学生奖惩情况
     * @param rewards
     * @return int
     * @throws SQLException
     */
    @Override
    public int updateRewards(Rewards rewards) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_rewards SET award_punishment = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,rewards.getAwardPunishment());
        pstmt.setString(2,rewards.getStudentId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int insert(Rewards rewards) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_rewards VALUES (?,?,?,?,?)";
        PreparedStatement prstm = connection.prepareStatement(sql);
        prstm.setInt(1,rewards.getId());
        prstm.setString(2,rewards.getStudentId());
        prstm.setString(3,rewards.getKind());
        prstm.setString(4,rewards.getAwardPunishment());
        prstm.setDate(5,new Date(rewards.gettDate().getTime()));
        int n = prstm.executeUpdate();
        prstm.close();
        connection.close();
        return n;
    }

    @Override
    public List<Rewards> selectTeacherAccount(String adminAccount) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.student_name,gender,admin_account,t2.department_name,t3.class_name,t4.id,student_id,kind,award_punishment,t_date\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t3\n" +
                "ON t1.class_id=t3.id\n" +
                "LEFT JOIN t_department t2\n" +
                "ON t3.department_id=t2.id\n" +
                "LEFT JOIN t_rewards t4\n" +
                "ON t4.student_id=t1.id\n"+
                "WHERE t1.admin_account = ?";
        PreparedStatement prstm = connection.prepareStatement(sql);
        prstm.setString(1,adminAccount);
        ResultSet rs = prstm.executeQuery();
        List<Rewards> rewardsList = convert(rs);
        rs.close();
        jdbcUtil.closeConnection();
        prstm.close();
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
            rewards.setId(rs.getInt("id"));
            rewards.setDepartmentName(rs.getString("department_name"));
            rewards.setClassName(rs.getString("class_name"));
            rewards.setStudentId(rs.getString("student_id"));
            rewards.setStudentName(rs.getString("student_name"));
            rewards.setAwardPunishment(rs.getString("award_punishment"));
            rewards.setGender(rs.getString("gender"));
            rewards.setKind(rs.getString("kind"));
            rewards.settDate(rs.getDate("t_date"));
            rewards.setAdminAccount(rs.getString("admin_account"));
            rewardsList.add(rewards);
        }
        return rewardsList;
    }

}
