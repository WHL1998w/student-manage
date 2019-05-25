package com.sm.dao.impl;

import com.sm.dao.StudnetDAO;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOImplTest {
    private StudnetDAO studnetDAO = DAOFactory.getStudentDAOInstance();
    @Test
    public void selectAll() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));

    }
}