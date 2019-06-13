package com.sm.service.impl;

import com.sm.factory.ServiceFacotry;
import com.sm.service.TeacherLoginService;
import com.sm.utils.ResultEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class TearcherLoginServiceImplTest {

    private TeacherLoginService teacherLoginService = ServiceFacotry.getTeacherServiceInstance();
    @Test
    public void teacherLogin() {
        ResultEntity resultEntity =teacherLoginService .teacherLogin("18805162578","18805162578");
        System.out.println(resultEntity);
    }
}