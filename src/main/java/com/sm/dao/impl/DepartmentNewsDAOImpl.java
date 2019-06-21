package com.sm.dao.impl;

import com.sm.dao.DepartmentDAO;
import com.sm.dao.DepartmentnNewsDAO;
import com.sm.entity.DepartmentNews;
import com.sm.entity.SchoolNews;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentNewsDAOImpl implements DepartmentnNewsDAO {
    @Override
    public List<DepartmentNews> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_department_news ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<DepartmentNews> departmentNewsList = new ArrayList<>();
        while (rs.next()) {
            DepartmentNews departmentNews = new DepartmentNews();
            departmentNews.setId(rs.getInt("id"));
            departmentNews.setNews(rs.getString("news"));
            departmentNews.setDate(rs.getDate("date"));
            departmentNewsList.add(departmentNews);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return departmentNewsList;
    }
}
