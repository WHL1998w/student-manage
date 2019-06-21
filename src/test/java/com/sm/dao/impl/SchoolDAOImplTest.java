package com.sm.dao.impl;

import com.sm.dao.SchoolDAO;
import com.sm.entity.Department;
import com.sm.entity.SchoolNews;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class SchoolDAOImplTest {
    private SchoolDAO schoolDAO = DAOFactory.getSchoolDAOInstanche();

    @Test
    public void selsctAll() {
        List<SchoolNews> schoolNewsList = null;
        try {
            schoolNewsList = schoolDAO.selsctAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        schoolNewsList.forEach(schoolNews -> System.out.println(schoolNews));
    }
}