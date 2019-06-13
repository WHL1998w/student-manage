package com.sm.dao.impl;

import com.sm.dao.StudnetDAO;
import com.sm.entity.CClass;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOImplTest {
    private StudnetDAO studnetDAO = DAOFactory.getStudentDAOInstance();

    /**
     * 查询所有学生
     */
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

    /**
     * 根据院系查询学生
     */
    @Test
    public void selectByDepartmentId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));

    }

    /**
     * 根据班级id查询学生
     */
    @Test
    public void selectByCClassId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectByCClassId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));

    }

    /**
     * 根据关键字查询学生
     */
    @Test
    public void selectByKeywords() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectByKeywords("江");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));

    }

    /**
     * 新增学生
     */
    @Test
    public void insert() throws SQLException {
        Student student = new Student();
        student.setId("111");
        student.setClassId(1);
        student.setStudentName("曾月");
        student.setAvatar("1.jpg");
        student.setBirthday(new Date());
        student.setGender("女");
        student.setAddress("江苏南京");
        student.setPhone("1880516278");
        int n = studnetDAO.insert(student);
        assertEquals(1,n);
    }


    @Test
    public void updateStudent() throws SQLException {
        Student student = new Student();
        student.setId("1802343307");
        student.setAddress("江苏南京");
        student.setPhone("13919532645");
        int n = studnetDAO.updateStudent(student);
        assertEquals(1,n);
    }

    @Test
    public void deletById() throws SQLException {
        int n = studnetDAO.deletById("1802343334");
        assertEquals(1,n);
    }

    @Test
    public void selectAdminAccount() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectAdminAccount("13919532645");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }
}