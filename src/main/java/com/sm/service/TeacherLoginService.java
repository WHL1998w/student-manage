package com.sm.service;

import com.sm.entity.TeacherLogin;
import com.sm.utils.ResultEntity;

public interface TeacherLoginService {
    /**
     *
     * @param account
     * @param password
     * @return
     */
    ResultEntity teacherLogin(String account, String password);
}
