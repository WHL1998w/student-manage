package com.sm.dao.impl;

import com.sm.dao.DepartmentnNewsDAO;
import com.sm.entity.DepartmentNews;
import com.sm.entity.SchoolNews;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentNewsDAOImplTest {
    private DepartmentnNewsDAO departmentnNewsDAO = DAOFactory.getDepartmentNewsDAOInstance();

    @Test
    public void selectAll() {
        List<DepartmentNews> departmentNewsList = null;
        try {
            departmentNewsList = departmentnNewsDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        departmentNewsList.forEach(departmentNews -> System.out.println(departmentNews));
    }
}