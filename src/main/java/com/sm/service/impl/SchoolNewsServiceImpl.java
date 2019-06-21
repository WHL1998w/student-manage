package com.sm.service.impl;

import com.sm.dao.SchoolDAO;
import com.sm.entity.Department;
import com.sm.entity.SchoolNews;
import com.sm.factory.DAOFactory;
import com.sm.service.SchoolNewsService;

import java.sql.SQLException;
import java.util.List;

public class SchoolNewsServiceImpl implements SchoolNewsService {
    private SchoolDAO schoolDAO = DAOFactory.getSchoolDAOInstanche();
    @Override
    public List<SchoolNews> selectAll() {
        List<SchoolNews> schoolNewsList = null;
        try {
            schoolNewsList = schoolDAO.selsctAll();
        } catch (SQLException e) {
            System.err.print("查询学校新闻出现异常");
        }
        return schoolNewsList;
    }
}
