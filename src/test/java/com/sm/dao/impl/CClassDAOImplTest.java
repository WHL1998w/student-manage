package com.sm.dao.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



public class CClassDAOImplTest {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();

    /**
     * 根据院系id查询班级
     */
    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));
    }

    /**
     * 根据班级id删除班级
     */
    @Test
    public void deleteById() {

    }

    /**
     * 新增班级
     */
    @Test
    public void insertCClass() {
       CClass cClass = new CClass();
       cClass.setDepartmentId(1);
       cClass.setClassName("测试班级");
        try {
            int n = cClassDAO.insertClass(cClass);
            equals(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有班级
     */
    @Test
    public void selectAll() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));

    }
}