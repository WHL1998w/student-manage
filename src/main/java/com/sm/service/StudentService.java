package com.sm.service;

import com.sm.entity.Student;
import com.sm.entity.StudentVO;

import java.util.List;

public interface StudentService {
    /**
     * 查询学生信息
     * @return  List<StudentVO>
     */
    List<StudentVO> selectAll();

    /**
     * 根据院系id查询学生
     * @param departmentId
     * @return
     */
    List<StudentVO> selectByDepartmentId(int departmentId);

    /**
     * 根据班级id查询学生
     * @param classId
     * @return
     */
    List<StudentVO> selectByCClassId(int classId);

    /**
     * 根据关键字查询学生
     * @param keywords
     * @return
     */
    List<StudentVO> selectByKeywords(String keywords);

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    int updateStudent(Student student);

    /**
     * 删除学生信息
     * @param id
     * @return
     */
    int deletById(String id);
}
