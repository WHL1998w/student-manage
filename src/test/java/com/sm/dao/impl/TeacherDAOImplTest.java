package com.sm.dao.impl;

import com.sm.dao.TeacherDAO;
import com.sm.entity.Course;
import com.sm.entity.Student;
import com.sm.entity.Teacher;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class TeacherDAOImplTest {
    private TeacherDAO teacherDAO = DAOFactory.getTeacherDAOInstance();

    @Test
    public void selectAll() {
        List<Teacher> teacherList = null;
        try {
            teacherList = teacherDAO.selectAll("13919532645");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacherList.forEach(teacher -> System.out.println(teacher));
    }

    @Test
    public void updateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setPhone("13919532645");
        teacher.setAdress("江苏南京");
        int n = 0;
        try {
            n = teacherDAO.updateTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,n);
    }
}