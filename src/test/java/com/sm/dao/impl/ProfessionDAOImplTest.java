package com.sm.dao.impl;

import com.sm.dao.ProfessionDAO;
import com.sm.entity.CClass;
import com.sm.entity.Profession;
import com.sm.entity.Student;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ProfessionDAOImplTest {
    private ProfessionDAO professionDAO = DAOFactory.getProfessionDAOInstance();

    //根据院系id查询专业
    @Test
    public void selectDepartmentId() {
        List<Profession> professionList = null;
        try {
            professionList = professionDAO.selectDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        professionList.forEach(profession -> System.out.println(profession));
    }

    //新增专业
    @Test
    public void insetProfessionById() {
        Profession profession = new Profession();
        profession.setDepartmentId(1);
        profession.setProfessionName("测试专业");
        try {
            int n = professionDAO.insetProfessionById(profession);
            equals(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //修改专业
    @Test
    public void updateProfessionById() throws SQLException {
        Profession profession = new Profession();
        profession.setId(1);
        profession.setProfessionName("机械自动化制造");
        int n = professionDAO.updateProfessionById(profession);
        assertEquals(1,n);
    }

    //删除专业
    @Test
    public void deletById() {
        int n = 0;
        try {
            n = professionDAO.deletById(19);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,n);
    }

    @Test
    public void selectAll() {
        List<Profession> professionList = null;
        try {
            professionList = professionDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        professionList.forEach(profession -> System.out.println(profession));
    }
}