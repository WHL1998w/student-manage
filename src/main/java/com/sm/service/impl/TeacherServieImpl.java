package com.sm.service.impl;

import com.sm.dao.TeacherDAO;
import com.sm.entity.Rewards;
import com.sm.entity.Teacher;
import com.sm.factory.DAOFactory;
import com.sm.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

public class TeacherServieImpl implements TeacherService {
    private TeacherDAO teacherDAO = DAOFactory.getTeacherDAOInstance();
    @Override
    public List<Teacher> selectTeacherAccount(String phone) {
        List<Teacher> teacherList = null;
        try {
            teacherList = teacherDAO.selectAll(phone);
        } catch (SQLException e) {
            System.out.println("根据老师账号查询错误");
        }
        return teacherList;
    }
}
