package com.sm.dao.impl;

import com.sm.dao.StudentLoginDAO;
import com.sm.entity.StudentLogin;
import com.sm.entity.TeacherLogin;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class StudentLoginDAOImplTest {
    private StudentLoginDAO studentLoginDAO = DAOFactory.getStudnetLoginDAOInstance();

    @Test
    public void getStudentAccountBy() {
        try {
            StudentLogin studentLogin = studentLoginDAO.getStudentAccountBy("1802343334");
            if (studentLogin != null) {
                System.out.println(studentLogin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}