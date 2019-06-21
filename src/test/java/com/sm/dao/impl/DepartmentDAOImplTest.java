package com.sm.dao.impl;

import com.sm.dao.DepartmentDAO;
import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.entity.Student;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDAOImplTest {

    private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAOInstance();

    /**
     * 查询所有院系
     */
    @Test
    public void getAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        departmentList.forEach(department -> System.out.println(department));
    }

    /**
     * 新增院系
     */
    @Test
    public void insertDepartment() {
        Department department = new Department();
        department.setDepartmentName("测试院系");
        department.setLogo("https://wanghuanle.oss-cn-beijing.aliyuncs.com/jixie.jpg?Expires=1558454042&OSSAccessKeyId=TMP.AgFk0yZn7jk1lA0woqFo71rzQBZusiqrYFKmlXRHie0liswTwwSgbLhNBuwTMC4CFQC2TKczfw9TYzdVwVJoP04zOgu5bAIVAOysPHUjwar9AFFb4sCPbAJhaEAY&Signature=fvGxtJQ4tXHdwmCF6Bk2j1edMjE%3D ");
        department.setDescription("电气自动化技术专业，是培养具有一定的电子技术、微机控制技术和计算机网络技术的基础知识；熟悉常用电气设备的工作原理，掌握应用计算机技术实现电气控制的基本原理和方法。");
        try {
            int n = departmentDAO.insertDepartment(department);
            assertEquals(1, n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void updateDepartmentById() {
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("机械工程学院");
        int n = 0;
        try {
            n = departmentDAO.updateDepartmentById(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,n);
    }

    @Test
    public void selectDepartmentById() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDAO.selectDepartmentById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        departmentList.forEach(department -> System.out.println(department));
    }
}