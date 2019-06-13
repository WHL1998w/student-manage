package com.sm.frame;

import com.sm.entity.TeacherLogin;
import com.sm.factory.DAOFactory;

import javax.swing.*;
import java.sql.SQLException;

public class TeacherMainFrame extends JFrame {
    private JPanel rootPanel;
    private JButton 首页Button;
    private JButton 学生Button;
    private JButton 个人中心Button;
    private TeacherLogin teacherLogin;

    public TeacherMainFrame(TeacherLogin teacherLogin){
        this.teacherLogin = teacherLogin;
        setTitle("班主任主界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new TeacherMainFrame(DAOFactory.getTeacherLoginDAOInstance().getTeacherByAccount("1880516578"));
    }
}
