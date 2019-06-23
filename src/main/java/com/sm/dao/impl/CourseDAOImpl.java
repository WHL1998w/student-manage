package com.sm.dao.impl;

import com.sm.dao.CourseDAO;
import com.sm.entity.Course;
import com.sm.entity.CourseVO;
import com.sm.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    //查询所有课程情况
    @Override
    public List<Course> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.department_name,t2.class_name,t3.student_name,admin_account,t4.id,student_id,course_name,grade,course_number,t5.teacher_name\n" +
                "FROM t_department t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.id=t2.department_id\n" +
                "LEFT JOIN t_student t3\n" +
                "ON t2.id=t3.class_id\n" +
                "LEFT JOIN t_course t4\n" +
                "ON t3.id=t4.student_id\n" +
                "LEFT JOIN t_teacher t5\n" +
                "ON t4.course_number=t5.course_id";
        PreparedStatement prstm = connection.prepareStatement(sql);
        ResultSet rs = prstm.executeQuery();
        List<Course> courseList = convert(rs);
        rs.close();
        jdbcUtil.closeConnection();
        prstm.close();
        return courseList;
    }

    //根据关键字查询
    @Override
    public List<Course> selectByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.department_name,t2.class_name,t3.student_name,t4.id,student_id,grade,course_name,t5.teacher_name,other_course\n" +
                "FROM t_department t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.id=t2.department_id\n" +
                "LEFT JOIN t_student t3\n" +
                "ON t2.id=t3.class_id\n"+
                "LEFT JOIN t_course t4\n" +
                "ON t3.id=t4.student_id\n" +
                "LEFT JOIN t_teacher t5\n" +
                "ON t4.course_number=t5.course_id\n" +
                "WHERE t1.department_name LIKE ? OR t2.class_name LIKE ? OR t3.student_name LIKE ? OR t3.id LIKE ? OR t4.course_name LIKE ? OR t5.teacher_name LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        pstmt.setString(3,"%" + keywords + "%");
        pstmt.setString(4,"%" + keywords + "%");
        pstmt.setString(5,"%" + keywords + "%");
        pstmt.setString(6,"%" + keywords + "%");

        ResultSet rs = pstmt.executeQuery();
        List<Course> courseList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return courseList;
    }


    //新增课程
    @Override
    public int insertByStudentId(Course course) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_course VALUES (?,?,?,?)";
        PreparedStatement prstm = connection.prepareStatement(sql);
        prstm.setInt(1,course.getId());
        prstm.setString(2,course.getStudentId());
        //prstm.setString(3,course.getCourseName());
        prstm.setDouble(3,course.getGrade());
        prstm.setString(4,course.getCourseNumber());
        int n = prstm.executeUpdate();
        prstm.close();
        connection.close();
        return n;
    }

    @Override
    public int updateGrade(Course course) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_course SET grade = ? WHERE student_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setDouble(1,course.getGrade());
        pstmt.setString(2,course.getStudentId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public List<Course> selectTeacherAccount(String adminAccount) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.department_name,t2.class_name,t3.student_name,admin_account,t4.id,student_id,grade,course_number,t5.teacher_name,course_name\n" +
                "FROM t_department t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.id=t2.department_id\n" +
                "LEFT JOIN t_student t3\n" +
                "ON t2.id=t3.class_id\n" +
                "LEFT JOIN t_course t4\n" +
                "ON t3.id=t4.student_id\n" +
                "LEFT JOIN t_teacher t5\n" +
                "ON t4.course_number=t5.course_id\n" +
                "WHERE t3.admin_account = ?";
        PreparedStatement prstm = connection.prepareStatement(sql);
        prstm.setString(1,adminAccount);
        ResultSet rs = prstm.executeQuery();
        List<Course> courseList = convert(rs);
        rs.close();
        jdbcUtil.closeConnection();
        prstm.close();
        return courseList;
    }

    @Override
    public List<CourseVO> getSelectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_teacher ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<CourseVO> courseVOList = new ArrayList<>();
        while (rs.next()) {
            CourseVO courseVO = new CourseVO();
            courseVO.setCourseName(rs.getString("course_name"));
            courseVO.setCourseNumber(rs.getString("course_id"));
            courseVOList.add(courseVO);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return courseVOList;
    }

    //分装
    private List<Course> convert(ResultSet rs) throws SQLException{
        List<Course> courseList = new ArrayList<>();
        while (rs.next()){
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setCourseName(rs.getString("course_name"));
            course.setGrade(rs.getDouble("grade"));
            course.setStudentName(rs.getString("student_name"));
            course.setClassName(rs.getString("class_name"));
            course.setDepartmentName(rs.getString("department_name"));
            course.setTeacherName(rs.getString("teacher_name"));
            course.setStudentId(rs.getString("student_id"));
            course.setCourseNumber(rs.getString("course_number"));
            course.setTeacherAccount(rs.getString("admin_account"));
            courseList.add(course);
        }
        return courseList;
    }

}
