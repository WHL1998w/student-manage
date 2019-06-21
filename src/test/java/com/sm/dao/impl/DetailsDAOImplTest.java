package com.sm.dao.impl;

import com.sm.dao.DetailsDAO;
import com.sm.entity.CClass;
import com.sm.entity.Details;
import com.sm.entity.Profession;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DetailsDAOImplTest {
    private DetailsDAO detailsDAO = DAOFactory.getDetailsDAOInstance();

    @Test
    public void selectDepartmentById() {
        List<Details> detailsList = null;
        try {
            detailsList = detailsDAO.selectDepartmentById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        detailsList.forEach(details -> System.out.println(details));
    }

    @Test
    public void selectAll() {
        List<Details> detailsList = null;
        try {
            detailsList = detailsDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        detailsList.forEach(details -> System.out.println(details));
    }
}