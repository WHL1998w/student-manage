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
}
