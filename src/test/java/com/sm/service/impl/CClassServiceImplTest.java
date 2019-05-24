package com.sm.service.impl;

import com.sm.entity.CClass;
import com.sm.factory.ServiceFacotry;
import com.sm.service.CClassService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CClassServiceImplTest {
    private CClassService cClassService = ServiceFacotry.getCClassServiceInstance();

    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = cClassService.selectByDepartmentId(1);
        cClassList.forEach(cClass -> System.out.println(cClass));
    }

    @Test
    public void addCClass() {
        List<CClass> cClassList = cClassService.selectByDepartmentId(1);
        cClassList.forEach(cClass -> System.out.println(cClass));
    }
}