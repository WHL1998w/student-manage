package com.sm.dao;

import com.sm.entity.Student;
import com.sm.entity.StudentVO;

import java.sql.SQLException;
import java.util.List;

public interface StudnetDAO {

    /**
     * 查询所有学生
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectAll() throws SQLException;

    /**
     * 根据院系id查询学生
     * @param departmentId
     * @return int
     * @throws SQLException
     */
    List<StudentVO> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 根据班级id查询学生
     * @param classId
     * @return int
     * @throws SQLException
     */
    List<StudentVO> selectByCClassId(int classId) throws SQLException;

    /**
     * 根据关键字查询学生
     * @param keywords
     * @return String
     * @throws SQLException
     */
    List<StudentVO> selectByKeywords(String keywords) throws SQLException;

    /**
     * 新增学生
     * @param student
     * @return int
     * @throws SQLException
     */
    int insert(Student student) throws SQLException;


    /**
     * 更新学生信息
     * @param student
     * @return int
     * @throws SQLException
     */
    int updateStudent(Student student) throws SQLException;

    /**
     * 删除学生信息
     * @param id
     * @return int
     * @throws SQLException
     */
    int deletById(String id)throws SQLException;


    /**
     * 根据院系id统计学生人数
     * @param departmentId
     * @return
     * @throws SQLException
     */
    int countByDepartmentId(int departmentId)throws SQLException;

    /**
     * 根据班级统计学生人数
     * @param classId
     * @return
     * @throws SQLException
     */
    int countByClassId(int classId)throws SQLException;

    /**
     * 根据老师账号查找学生
     * @param account
     * @return
     * @throws SQLException
     */
    List<StudentVO> selectAdminAccount(String account) throws SQLException;
}
