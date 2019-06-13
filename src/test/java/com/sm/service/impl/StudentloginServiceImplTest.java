package com.sm.service.impl;

import com.sm.factory.ServiceFacotry;
import com.sm.service.StudentLoginService;
import com.sm.utils.ResultEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentloginServiceImplTest {
    private StudentLoginService studentLoginService = ServiceFacotry.getStudentLoginServiceInstance();

    @Test
    public void teacherLogin() {
        ResultEntity resultEntity =studentLoginService .studentLogin("1802343334","1802343334");
        System.out.println(resultEntity);
    }
}