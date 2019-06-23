package com.sm.dao.impl;

import com.sm.dao.TeacherLoginDAO;
import com.sm.entity.Admin;
import com.sm.entity.Department;
import com.sm.entity.TeacherLogin;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class TeacherLoginDAOImplTest {
    private TeacherLoginDAO teacherLoginDAO = DAOFactory.getTeacherLoginDAOInstance();

    @Test
    public void getTeacherByAccount() {
        try {
            TeacherLogin teacherLogin = teacherLoginDAO.getTeacherByAccount("18805162578");
            if (teacherLogin != null) {
                System.out.println(teacherLogin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {
        List<TeacherLogin> teacherLoginList = null;
        try {
            teacherLoginList = teacherLoginDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacherLoginList.forEach(teacherLogin -> System.out.println(teacherLogin));
    }
}