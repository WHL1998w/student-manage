package com.sm.dao.impl;

import com.sm.dao.StudnetDAO;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
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

    }

    /**
     * 根据班级id查询学生
     */
    @Test
    public void selectByCClassId() {

    }

    /**
     * 根据关键字查询学生
     */
    @Test
    public void selectByKeywords() {

    }

    /**
     * 新增学生
     */
    @Test
    public void insert() {
    }

    /**
     * 根据学生id删除学生
     */
    @Test
    public void deletStudentById() {
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
}