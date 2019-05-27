package com.sm.dao.impl;

import com.sm.dao.DepartmentDAO;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import org.junit.Test;

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

        try {
            int n = departmentDAO.insertDepartment(department);
            assertEquals(1, n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除院系
     */
    @Test
    public void deleteDepartmentById() {
        try {
            departmentDAO.deleteDepartmentById(8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}