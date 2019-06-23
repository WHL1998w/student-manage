package com.sm.service;

import com.sm.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> selectTeacherAccount(String phone);

}
