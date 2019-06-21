package com.sm.dao.impl;

import com.sm.dao.SchoolDAO;
import com.sm.entity.SchoolNews;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAOImpl implements SchoolDAO {
    @Override
    public List<SchoolNews> selsctAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_school_news ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<SchoolNews> schoolNewsList = new ArrayList<>();
        while (rs.next()) {
            SchoolNews schoolNews = new SchoolNews();
            schoolNews.setId(rs.getInt("id"));
            schoolNews.setNews(rs.getString("news"));
            schoolNews.setDate(rs.getDate("date"));
            schoolNewsList.add(schoolNews);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return schoolNewsList;
    }
}
