package com.sm.dao.impl;

import com.sm.dao.StudnetDAO;
import com.sm.entity.StudentVO;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudnetDAO {
    @Override
    /**
     *查询所有学生
     */
    public List<StudentVO> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentVOList;
    }

    /**
     * 根据院系id查询
     * @param departmentId
     * @return
     * @throws SQLException
     */
    @Override
    public List<StudentVO> selectByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id\n"+
                "WHERE t3.id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,departmentId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentVOList;
    }

    /**
     * 根据班级id查询学生
     * @param classId
     * @return
     * @throws SQLException
     */
    @Override
    public List<StudentVO> selectByCClassId(int classId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id\n"+
                "WHERE t1.class_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,classId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentVOList;
    }

    /**
     * 根据关键字查询学生
     * @param keywords
     * @return
     * @throws SQLException
     */
    @Override
    public List<StudentVO> selectByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id\n"+
                "WHERE t1.student_name LIKE ? OR t1.address LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentVOList;
    }

    //新增学生
    @Override
    public int insert(StudentVO studentVO) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_student (id,student_name,gender,avatar,phone,address," +
                "birthday,department_name,class_name) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,studentVO.getId());
        pstmt.setString(2,studentVO.getClassName());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int deletStudentById(String id) throws SQLException {
        return 0;
    }

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<StudentVO> convert(ResultSet rs) throws SQLException{
        List<StudentVO> studentVOList = new ArrayList<>();
        while (rs.next()){
            StudentVO studentVO = new StudentVO();
            studentVO.setId(rs.getString("id"));
            studentVO.setDepartmentName(rs.getString("department_name"));
            studentVO.setClassName(rs.getString("class_name"));
            studentVO.setStudentName(rs.getString("student_name"));
            studentVO.setAvatar(rs.getString("avatar"));
            studentVO.setGender(rs.getString("gender"));
            studentVO.setBirthday(rs.getDate("birthday"));
            studentVO.setAddress(rs.getString("address"));
            studentVO.setPhone(rs.getString("phone"));
            studentVOList.add(studentVO);
        }
        return studentVOList;
    }
}
