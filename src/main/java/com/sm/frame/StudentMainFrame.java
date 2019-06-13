package com.sm.frame;

import com.sm.entity.Admin;
import com.sm.entity.StudentLogin;
import com.sm.factory.DAOFactory;
import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentMainFrame extends JFrame {
    private ImgPanel rootPanel;
    private JPanel centerPanel;
    private JPanel fristPanel;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel oneselfPanel;
    private JLabel firstLabel;
    private JLabel departmentLabel;
    private JLabel classLabel;
    private JLabel studentLabel;
    private JLabel oneselfLabel;
    private JLabel studentName;
    private JLabel timeLabel;
    private JLabel imageLabel;
    private StudentLogin studentLogin;

    public StudentMainFrame(StudentLogin studentLogin){
        this.studentLogin = studentLogin;
        //设置背景图
        rootPanel.setFileName("bg1.jpg");
        //组件重绘
        rootPanel.repaint();
        setTitle("学生主界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //获取centerPanel的布局,会的是LayoutManger,向下转型为CardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
        firstLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card1");
            }
        });
        departmentLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card2");
            }
        });
        classLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card3");
            }
        });
        studentLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card4");
            }
        });
        oneselfLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card5");
            }
        });
    }

    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new StudentMainFrame(DAOFactory.getStudnetLoginDAOInstance().getStudentAccountBy("1802343334"));
    }
}
