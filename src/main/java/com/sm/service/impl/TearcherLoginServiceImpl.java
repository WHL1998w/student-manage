package com.sm.service.impl;

import com.sm.dao.TeacherLoginDAO;
import com.sm.entity.Admin;
import com.sm.entity.TeacherLogin;
import com.sm.factory.DAOFactory;
import com.sm.service.TeacherLoginService;
import com.sm.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class TearcherLoginServiceImpl implements TeacherLoginService {
    private TeacherLoginDAO teacherLoginDAO = DAOFactory.getTeacherLoginDAOInstance();

    @Override
    public ResultEntity teacherLogin(String account, String password) {
        ResultEntity resultEntity = new ResultEntity();
        TeacherLogin teacherLogin = null;
        try {
            //根据账号查找管理员信息，捕获SQL异常
            teacherLogin = teacherLoginDAO.getTeacherByAccount(account);
        } catch (SQLException e) {
            System.err.println("根据账号查找老师信息出现SQL异常");
        }
        //根据账号查找到了记录
        if (teacherLogin != null) {
            //比较密码，此时需要将客户端传过来的密码进行MD5加密后才能比对
            if (DigestUtils.md5Hex(password).equals(teacherLogin.getTeacherPassword())) {
                resultEntity.setCode(0);
                resultEntity.setMessage("登录成功");
                resultEntity.setData(teacherLogin);
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
