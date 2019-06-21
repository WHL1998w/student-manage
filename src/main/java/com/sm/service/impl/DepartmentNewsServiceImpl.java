package com.sm.service.impl;

import com.sm.dao.DepartmentnNewsDAO;
import com.sm.entity.DepartmentNews;
import com.sm.entity.SchoolNews;
import com.sm.factory.DAOFactory;
import com.sm.service.DepartmentNewsService;

import java.sql.SQLException;
import java.util.List;

public class DepartmentNewsServiceImpl implements DepartmentNewsService {
    private DepartmentnNewsDAO departmentnNewsDAO = DAOFactory.getDepartmentNewsDAOInstance();
    @Override
    public List<DepartmentNews> selectAll() {
        List<DepartmentNews> departmentNewsList = null;
        try {
            departmentNewsList = departmentnNewsDAO.selectAll();
        } catch (SQLException e) {
            System.err.print("查询院系新闻出现异常");
        }
        return departmentNewsList;
    }
}
