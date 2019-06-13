package com.sm.service.impl;

import com.sm.entity.StudentVO;
import com.sm.factory.ServiceFacotry;
import com.sm.service.StudentService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceImplTest {
    private StudentService studentService = ServiceFacotry.getStudnetServiceInstance();

    @Test
    public void selectAll() {
        List<StudentVO> studentVOList = studentService.selectAll();
        studentVOList.forEach(studentVO -> System.out.println(studentVOList));
    }

    @Test
    public void updateStudent() {

    }

    @Test
    public void insert() {

    }

    @Test
    public void selectAdminAccount() {

    }
}