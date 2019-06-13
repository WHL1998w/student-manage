package com.sm.dao.impl;

import com.sm.dao.StudnetDAO;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.utils.JDBCUtil;

import java.sql.*;
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

    @Override
    public int insert(Student student) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_student VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement prstm = connection.prepareStatement(sql);
        prstm.setString(1,student.getId());
        prstm.setInt(2,student.getClassId());
        prstm.setString(3,student.getStudentName());
        prstm.setString(4,student.getAvatar());
        prstm.setString(5,student.getGender());
        prstm.setDate(6,new Date(student.getBirthday().getTime()));
        prstm.setString(7,student.getAddress());
        prstm.setString(8,student.getPhone());
        int n = prstm.executeUpdate();
        prstm.close();
        connection.close();
        return n;
    }


    /**
     * 更新学生信息
     * @param student
     * @return
     * @throws SQLException
     */
    @Override
    public int updateStudent(Student student) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_student SET address = ?,phone = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,student.getAddress());
        pstmt.setString(2,student.getPhone());
        pstmt.setString(3,student.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }


    @Override
    public int deletById(String id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_student WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        int n = pstm.executeUpdate();
        pstm.close();
        connection.close();
        return n;
    }

    @Override
    public int countByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql ="SELECT COUNT(*) FROM t_student t1 LEFT JOIN t_class t2 ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3 ON t2.department_id =t3.id\n" +
                "WHERE t3.id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,departmentId);
        ResultSet rs = pstmt.executeQuery();
        int rowcount =0 ;
        if (rs.next()){
            rowcount = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rowcount;
    }

    @Override
    public int countByClassId(int classId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT COUNT(*) FROM t_student WHERE class_id =?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,classId);
        ResultSet rs = pstmt.executeQuery();
        int rowcount =0 ;
        if (rs.next()){
            rowcount = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rowcount;
    }

    /**
     * 根据老师的账号查对应的学生
     * @param account
     * @return
     * @throws SQLException
     */
    @Override
    public List<StudentVO> selectAdminAccount(String account) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id\n"+
                "WHERE t1.admin_account = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,account);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentVOList;
    }

    /**
     *分装
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
            studentVO.setAdminAccount(rs.getString("admin_account"));
            studentVOList.add(studentVO);
        }
        return studentVOList;
    }
}
