package com.sm.frame;

import com.sm.entity.DepartmentNews;
import com.sm.entity.SchoolNews;
import com.sm.entity.StudentVO;
import com.sm.entity.TeacherLogin;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFacotry;
import com.sm.service.SchoolNewsService;
import com.sm.thread.ImageThread;
import com.sm.thread.TimeThread;
import com.sm.ui.ImgPanel;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TeacherMainFrame extends JFrame {
    private ImgPanel rootPanel;
    private JButton 首页Button;
    private JButton 学生管理Button;
    private JButton 成绩管理Button;
    private JButton 个人中心Button;
    private JPanel centerPanel;
    private JPanel firstPanel;
    private JPanel studentPanel;
    private JPanel gradePanel;
    private JPanel mePanel;
    private JLabel imgLabel;
    private JPanel newsPanel;
    private TeacherLogin teacherLogin;

    public TeacherMainFrame(TeacherLogin teacherLogin){
        this.teacherLogin = teacherLogin;
        rootPanel.setFileName("bg1.jpg");
        rootPanel.repaint();
        setTitle("班主任主界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        showFristPanel();

        ImageThread imageThread = new ImageThread();
        imageThread.setImgLabel(imgLabel);
        imageThread.start();
        //获取centerPanel的布局,会的是LayoutManger,向下转型为CardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
        首页Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card1");

            }
        });
        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card2");

            }
        });
        成绩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card3");
            }
        });
        个人中心Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card4");
            }
        });
    }
    public void showFristPanel(){
        List<SchoolNews> schoolNewsList = ServiceFacotry.getSchoolNewsServiceInstance().selectAll();
        List<DepartmentNews> departmentNewsList = ServiceFacotry.getDepartmentNewsServiceInstance().selectAll();
        showSchoolPanel(schoolNewsList);
        showDepartmentNews(departmentNewsList);
    }
    public void showSchoolPanel(List<SchoolNews> schoolNewsList){
        ImageThread imageThread = new ImageThread();
        imageThread.setImgLabel(imgLabel);
        imageThread.start();
        newsPanel.setLayout(null);
        JPanel leftPanel = new JPanel();
        JTable table = new JTable();
        JLabel schoolLabel = new JLabel("重点新闻");
        Font titleFont = new Font("微软雅黑",Font.PLAIN,30);
        schoolLabel.setFont(titleFont);
        schoolLabel.setForeground(new Color(56,159,214));
        leftPanel.setBounds(30,30,670,340);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(table,BorderLayout.CENTER);
        leftPanel.add(schoolLabel,BorderLayout.NORTH);
        newsPanel.add(leftPanel);
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        model.setColumnIdentifiers(new String[]{"内容","日期"});
        for (SchoolNews schoolNews:schoolNewsList) {
            Object[] objects = new Object[]{schoolNews.getNews(),schoolNews.getDate()};
            model.addRow(objects);
        }
        JTableHeader head = table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        head.setDefaultRenderer(hr);
        head.setPreferredSize(new Dimension(head.getWidth(),40));
        head.setFont(new Font("楷体",Font.PLAIN,30));
        table.setRowHeight(35);
        table.setBackground(new Color(255,255,255));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
    }
    public void showDepartmentNews(List<DepartmentNews> departmentNewsList){
        newsPanel.setLayout(null);
        JPanel rightPanel = new JPanel();
        JTable table = new JTable();
        JLabel schoolLabel = new JLabel("校内新闻");
        Font titleFont = new Font("微软雅黑",Font.PLAIN,30);
        schoolLabel.setFont(titleFont);
        schoolLabel.setForeground(new Color(56,159,214));
        rightPanel.setBounds(720,30,670,340);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(table,BorderLayout.CENTER);
        rightPanel.add(schoolLabel,BorderLayout.NORTH);
        newsPanel.add(rightPanel);
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        model.setColumnIdentifiers(new String[]{"内容","日期"});
        for (DepartmentNews departmentNews:departmentNewsList) {
            Object[] objects = new Object[]{departmentNews.getNews(),departmentNews.getDate()};
            model.addRow(objects);
        }
        JTableHeader head = table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        head.setDefaultRenderer(hr);
        head.setPreferredSize(new Dimension(head.getWidth(),40));
        head.setFont(new Font("楷体",Font.PLAIN,30));
        table.setRowHeight(35);
        table.setBackground(new Color(255,255,255));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
    }

    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new TeacherMainFrame(DAOFactory.getTeacherLoginDAOInstance().getTeacherByAccount("1880516578"));
    }
}
