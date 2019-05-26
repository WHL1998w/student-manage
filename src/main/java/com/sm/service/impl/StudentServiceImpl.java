package com.sm.service.impl;

import com.sm.dao.StudnetDAO;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import com.sm.service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudnetDAO studnetDAO = DAOFactory.getStudentDAOInstance();
    @Override
    public List<StudentVO> selectAll() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectAll();
        } catch (SQLException e) {
            System.out.println("查询学生信息错误");
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByDepartmentId(int departmentId) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            System.out.println("根据院系id查询错误");
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByCClassId(int classId) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectByCClassId(classId);
        } catch (SQLException e) {
            System.out.println("根据班级id查询错误");
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByKeywords(String keywords) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studnetDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            System.out.println("根据关键字查询错误");
        }
        return studentVOList;
    }
}
