package com.sm.frame;

import com.sm.entity.Admin;
import com.sm.entity.StudentLogin;
import com.sm.entity.TeacherLogin;
import com.sm.factory.ServiceFacotry;
import com.sm.ui.ImgPanel;
import com.sm.utils.ResultEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminLoginFrame extends JFrame {
    private ImgPanel rootPanel;
    private JTextField accountFiled;
    private JPasswordField passwordField;
    private JButton 登录Button;
    private JButton 重置Button;
    private JRadioButton 教务管理员RadioButton;
    private JRadioButton 班主任RadioButton;
    private JRadioButton 学生RadioButton;

    public AdminLoginFrame() {
        setTitle("学生信息管理系统");
        //设置背景图
        rootPanel.setFileName("bg.jpg");
        //组件重绘
        rootPanel.repaint();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(589, 526);
        setLocationRelativeTo(null);
        setVisible(true);

        登录Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (教务管理员RadioButton.isSelected()){
                    //获得输入的账号和密码，注意密码框组件的使用方法
                    String account = accountFiled.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    ResultEntity resultEntity = ServiceFacotry.getAdminServiceInstance().adminLogin(account, password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity.getMessage());
                    //登录成功，进入管理员主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new AdminMainFrame((Admin) resultEntity.getData());
                        AdminLoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountFiled.setText("");
                        passwordField.setText("");
                    }
                }
                if (学生RadioButton.isSelected()){
                    //获得输入的账号和密码，注意密码框组件的使用方法
                    String account = accountFiled.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    ResultEntity resultEntity = ServiceFacotry.getStudentLoginServiceInstance().studentLogin(account, password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity.getMessage());
                    //登录成功，进入管理员主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new StudentMainFrame((StudentLogin) resultEntity.getData());
                        AdminLoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountFiled.setText("");
                        passwordField.setText("");
                    }
                }
                if (班主任RadioButton.isSelected()){
                    //获得输入的账号和密码，注意密码框组件的使用方法
                    String account = accountFiled.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    ResultEntity resultEntity = ServiceFacotry.getTeacherServiceInstance().teacherLogin(account, password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity.getMessage());
                    //登录成功，进入管理员主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new TeacherMainFrame((TeacherLogin) resultEntity.getData());
                        AdminLoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountFiled.setText("");
                        passwordField.setText("");
                    }
                }
            }
        });
        重置Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountFiled.setText("");
                passwordField.setText("");
            }
        });
    }
    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AdminLoginFrame();
    }
}
