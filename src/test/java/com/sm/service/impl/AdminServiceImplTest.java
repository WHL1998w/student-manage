package com.sm.service.impl;

import com.sm.factory.ServiceFacotry;
import com.sm.service.AdminServie;
import com.sm.utils.ResultEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminServiceImplTest {
    private AdminServie adminServie = ServiceFacotry.getAdminServiceInstance();

    @Test
    public void adminLogin() {
        ResultEntity resultEntity = adminServie.adminLogin("aaa@qq.com","123456");
        System.out.println(resultEntity);
    }
}