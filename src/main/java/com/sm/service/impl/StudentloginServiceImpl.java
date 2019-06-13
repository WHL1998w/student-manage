package com.sm.service.impl;

import com.sm.dao.StudentLoginDAO;
import com.sm.entity.StudentLogin;
import com.sm.entity.TeacherLogin;
import com.sm.factory.DAOFactory;
import com.sm.service.StudentLoginService;
import com.sm.service.StudentService;
import com.sm.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class StudentloginServiceImpl implements StudentLoginService {
    private StudentLoginDAO studentLoginDAO = DAOFactory.getStudnetLoginDAOInstance();
    @Override
    public ResultEntity studentLogin(String account, String password) {
        ResultEntity resultEntity = new ResultEntity();
        StudentLogin studentLogin = null;
        try {
            //根据账号查找管理员信息，捕获SQL异常
            studentLogin = studentLoginDAO.getStudentAccountBy(account);
        } catch (SQLException e) {
            System.err.println("根据账号查找老师信息出现SQL异常");
        }
        //根据账号查找到了记录
        if (studentLogin != null) {
            //比较密码，此时需要将客户端传过来的密码进行MD5加密后才能比对
            if (DigestUtils.md5Hex(password).equals(studentLogin.getStudentPassword())) {
                resultEntity.setCode(0);
                resultEntity.setMessage("登录成功");
                resultEntity.setData(studentLogin);
            } else {  //账号存在，密码输入错误
                resultEntity.setCode(1);
                resultEntity.setMessage("密码错误");
            }
        } else {  //账号不存在
            resultEntity.setCode(2);
            resultEntity.setMessage("账号不存在");
        }
        return resultEntity;
    }
}
