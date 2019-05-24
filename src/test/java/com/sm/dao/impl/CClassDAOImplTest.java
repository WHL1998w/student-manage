package com.sm.dao.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;



public class CClassDAOImplTest {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();
    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));
    }

    @Test
    public void deleteById() {

    }

    @Test
    public void insertCClass() {
       CClass cClass = new CClass();
       cClass.setDepartmentId(1);
       cClass.setClassName("测试班级");
        try {
            int n = cClassDAO.insertCClass(cClass);
            equals(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}