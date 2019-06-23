package com.sm.frame;

import com.sm.entity.*;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFacotry;
import com.sm.service.SchoolNewsService;
import com.sm.thread.ImageThread;
import com.sm.thread.TimeThread;
import com.sm.ui.ImgPanel;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class TeacherMainFrame extends JFrame {
    private ImgPanel rootPanel;
    private JButton 首页Button;
    private JButton 学生信息Button;
    private JButton 成绩管理Button;
    private JButton 个人中心Button;
    private JPanel centerPanel;
    private JPanel firstPanel;
    private JPanel studentPanel;
    private JPanel gradePanel;
    private JPanel mePanel;
    private JLabel imgLabel;
    private JPanel newsPanel;
    private JLabel teacherLabel;
    private JPanel rewPanel;
    private JButton 学生奖惩Button;
    private JLabel messageLabel;
    private JLabel rewardsLabel;
    private JLabel informLabel;
    private JPanel centerStudentPanel;
    private JPanel rightPanel;
    private JLabel avatarLabel;
    private JButton 修改Button;
    private JTextField addressTextField;
    private JTextField phoneTextField;
    private JLabel xuehaoLabel;
    private JLabel yuanxiLabel;
    private JLabel banjiLabel;
    private JLabel xingmingLabel;
    private JLabel xingbie;
    private JLabel brithLabel;
    private JButton 初始数据Button;
    private JPanel topPanel;
    private JTextField gradeTextField;
    private JButton 查询Button;
    private JPanel centerRewsPanel;
    private JPanel topRewPanel;
    private JTextField rewtextField;
    private JButton 查询Button1;
    private JPanel recenterPanel;
    private JButton 新增奖惩Button;
    private JComboBox<StudentVO> studentcomboBox;
    private JComboBox<CourseVO> coursecomboBox;
    private JTextField gardtextField;
    private JButton 成绩录入Button;
    private JPanel rightgradPanel;
    private JTextField gradetextField;
    private JButton 修改成绩Button;
    private JLabel idLabel;
    private JLabel courseLabel;
    private JTextField idTextField;
    private JPanel meInforPanel;
    private JLabel imageLabel;
    private JTextField ardessTextField1;
    private JLabel nameLabel;
    private JLabel courseNameLabel;
    private JLabel hobbyLabel;
    private JLabel workTimeLabel;
    private JTextField phone;
    private JButton 修改Button1;
    private JButton 修改密码Button;
    private JButton 信息管理Button;
    private JButton 成绩管理Button1;
    private JButton 发送通知Button;
    private TeacherLogin teacherLogin;
    private String adminAccount;
    private int row;
    private String studentId;
    private String courseNumber;

    public TeacherMainFrame(TeacherLogin teacherLogin){
        this.teacherLogin = teacherLogin;
        teacherLabel.setText("班主任：" + teacherLogin.getTeacherName());
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
        学生信息Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card2");
                List<StudentVO> studentVOList = ServiceFacotry.getStudnetServiceInstance().selectAdminAccount("13919532645");
                showStudentMessage(studentVOList);

            }
        });
        学生奖惩Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card5");
                List<Rewards> rewardsList = ServiceFacotry.getRewardsServiceInstance().selectTeacherAccount("13919532645");
                showStudentRewards(rewardsList);
            }
        });
        成绩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card3");
                List<Course> courseList = ServiceFacotry.getCourseServiceInstance().selectTeacherAccount("13919532645");
                showStudentGrade(courseList);

                //初始学生下拉框
                StudentVO tip4 = new StudentVO();
                tip4.setId("请选择学号");
                tip4.setStudentName("姓名");
                studentcomboBox.addItem(tip4);
                List<StudentVO> studentVOList1 = ServiceFacotry.getStudnetServiceInstance().selectAll();
                for (StudentVO studentVo:studentVOList1) {
                    studentcomboBox.addItem(studentVo);
                }
                //初始课程下拉框
                CourseVO tip5 = new CourseVO();
                tip5.setCourseName("请选择课程");
                coursecomboBox.addItem(tip5);
                List<CourseVO> courseList1 = ServiceFacotry.getCourseServiceInstance().getSelectAll();
                for (CourseVO courseVO:courseList1) {
                    coursecomboBox.addItem(courseVO);
                }

                studentcomboBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED){
                            int index = studentcomboBox.getSelectedIndex();
                            studentId  = studentcomboBox.getItemAt(index).getId();
                        }
                    }
                });
                coursecomboBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED){
                            int index = coursecomboBox.getSelectedIndex();
                            courseNumber  = coursecomboBox.getItemAt(index).getCourseNumber();
                        }
                    }
                });

                成绩录入Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Course course = new Course();
                        course.setId(idTextField.getColumns());
                        course.setStudentId(studentId);
                        course.setCourseNumber(courseNumber);
                        course.setGrade(Double.valueOf(gardtextField.getText()));
                        int n = ServiceFacotry.getCourseServiceInstance().insertByStudentId(course);
                        if (n == 1){
                            JOptionPane.showMessageDialog(rootPanel,"新增成功");
                            List<Course> courseList =  ServiceFacotry.getCourseServiceInstance().selectTeacherAccount("13919532645");
                            showStudentGrade(courseList);
                            gradetextField.setText("");
                        }else {
                            JOptionPane.showMessageDialog(rootPanel,"新增失败");
                        }
                    }
                });
            }
        });
        个人中心Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card4");

            }
        });
        初始数据Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //右侧个人信息显示去数据还原
                avatarLabel.setText("<html><img src ='https://student-manage-whl.oss-cn-beijing.aliyuncs.com/logo/a.jpg'g/><html>");
                xuehaoLabel.setText("未选择");
                yuanxiLabel.setText("未选择");
                banjiLabel.setText("未选择");
                xingmingLabel.setText("未选择");
                xingbie.setText("未选择");
                brithLabel.setText("未选择");
                phoneTextField.setText("");
                addressTextField.setText("");
            }
        });
        新增奖惩Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRewardsFrame(TeacherMainFrame.this);
            }
        });
        查询Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = rewtextField.getText().trim();
                List<Rewards> rewardsList = ServiceFacotry.getRewardsServiceInstance().selectByKeywords(keywords);
                if (rewardsList != null){
                    showStudentRewards(rewardsList);
                }
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
        table.setFont(new Font("楷体",Font.PLAIN,20));
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
        table.setFont(new Font("楷体",Font.PLAIN,20));
        table.setBackground(new Color(255,255,255));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
    }
    public void showStudentMessage(List<StudentVO> studentVOList){
        centerStudentPanel.removeAll();
        //创建表格对象
        JTable table = new JTable();
        //表格数据模型
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        //表头内容
        model.setColumnIdentifiers(new String[]{"学号","院系","班级","姓名","性别","地址","手机号","出生日期","头像"});
        //遍历list 转成object输出
        for (StudentVO student:studentVOList) {
            Object[] objects = new Object[]{student.getId(),student.getDepartmentName(),student.getClassName(),student.getStudentName(),
                    student.getGender(),student.getAddress(),student.getPhone(),student.getBirthday(),
                    student.getAvatar()};
            model.addRow(objects);
        }
        //将最后一列隐藏头像地址不显示在表格中
        TableColumn tc = table.getColumnModel().getColumn(8);
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
        //获得表头
        JTableHeader head = table.getTableHeader();
        //表头居中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        head.setDefaultRenderer(hr);
        //设置表头大小
        head.setPreferredSize(new Dimension(head.getWidth(),40));
        //设置表头字体
        head.setFont(new Font("楷体",Font.PLAIN,22));
        table.setRowHeight(35);
        table.setBackground(new Color(255,255,255));
        //表格内容居中
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        //表格加入滚动面板
        JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        centerStudentPanel.add(scrollPane);
        //刷新数据
        centerStudentPanel.revalidate();
        //点击一行在右边显示学生信息
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                xuehaoLabel.setText(table.getValueAt(row,0).toString());
                yuanxiLabel.setText(table.getValueAt(row,1).toString());
                banjiLabel.setText(table.getValueAt(row,2).toString());
                xingmingLabel.setText(table.getValueAt(row,3).toString());
                xingbie.setText(table.getValueAt(row,4).toString());
                addressTextField.setText(table.getValueAt(row,5).toString());
                phoneTextField.setText(table.getValueAt(row,6).toString());
                brithLabel.setText(table.getValueAt(row,7).toString());
                avatarLabel.setText("<html><img src ='"+table.getValueAt(row,8).toString()+"'width=230 height=230/><html>");
                //显示电话和地址的文本框处于不可编辑状态
                addressTextField.setEditable(false);
                addressTextField.setEnabled(false);
                phoneTextField.setEditable(false);
                phoneTextField.setEnabled(false);
                //编辑按钮在获得学生信息后可见
                修改Button.setVisible(true);
                修改Button.setText("修改");
                //编辑按钮的监听事件
                修改Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("修改")){
                            addressTextField.setEditable(true);
                            addressTextField.setEnabled(true);
                            phoneTextField.setEditable(true);
                            phoneTextField.setEnabled(true);
                            修改Button.setText("保存");
                        }
                        if (e.getActionCommand().equals("保存")) {
                            Student student = new Student();
                            student.setId(xuehaoLabel.getText());
                            student.setAddress(addressTextField.getText());
                            student.setPhone(phoneTextField.getText());
                            int n = ServiceFacotry.getStudnetServiceInstance().updateStudent(student);
                            if (n == 1) {
                                model.setValueAt(addressTextField.getText(), row, 5);
                                model.setValueAt(phoneTextField.getText(), row, 6);
                                addressTextField.setEditable(false);
                                addressTextField.setEnabled(false);
                                phoneTextField.setEditable(false);
                                phoneTextField.setEnabled(false);
                                修改Button.setText("修改");
                            }
                        }
                    }
                });
            }
        });
    }
    public void showStudentRewards(List<Rewards> rewardsList){
        recenterPanel.removeAll();
        GridLayout gridLayout = new GridLayout(0, 4, 20, 20);
        recenterPanel.setLayout(gridLayout);
        for (Rewards r:rewardsList) {
            ImgPanel rewsPanel = new ImgPanel();
            rewsPanel.setFileName("e.png");
            rewsPanel.repaint();
            rewsPanel.setLayout(null);
            rewsPanel.setBackground(new Color(255, 255, 255));
            rewsPanel.setPreferredSize(new Dimension(358, 381));
            Font font = new Font("微软雅黑",Font.BOLD,20);

            JLabel kindLabel = new JLabel(r.getKind());
            kindLabel.setFont(font);
            kindLabel.setBounds(140,30,50,50);
            rewsPanel.add(kindLabel);

            JTextField rewTextField = new JTextField(r.getAwardPunishment());
            rewTextField.setOpaque(false);
            rewTextField.setFont(font);
            rewTextField.setBounds(60,85,270,50);
            rewTextField.setBorder(new EmptyBorder(0,0,0,0));
            rewsPanel.add(rewTextField);

            JLabel nameLabell = new JLabel(r.getStudentName());
            nameLabell.setFont(font);
            nameLabell.setBounds(165,135,150,50);
            rewsPanel.add(nameLabell);

            JLabel timeLabel = new JLabel(String.valueOf(r.gettDate()));
            timeLabel.setFont(font);
            timeLabel.setBounds(165,190,150,50);
            rewsPanel.add(timeLabel);
            recenterPanel.add(rewsPanel);
        }
    }
    public void showStudentGrade(List<Course> courseList){
        centerRewsPanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.setColumnIdentifiers(new String[]{"院系","班级","学号","姓名","科目","成绩","授课教师"});
        for (Course course:courseList) {
            Object[] objects = new Object[]{course.getDepartmentName(),course.getClassName(),course.getStudentId(),
                    course.getStudentName(),course.getCourseName(),course.getGrade(),course.getTeacherName()};
            model.addRow(objects);
        }
        JTableHeader head = table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        head.setDefaultRenderer(hr);
        head.setPreferredSize(new Dimension(head.getWidth(),40));
        head.setFont(new Font("楷体",Font.PLAIN,22));
        table.setRowHeight(35);
        table.setBackground(new Color(161, 250, 220));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
       table.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {
               if (model.isCellEditable(table.getSelectedRow(),table.getSelectedColumn())){
                   Course course = new Course();
                   int n = ServiceFacotry.getCourseServiceInstance().updateGrade(course);
                   if (n == 1){
                       model.setValueAt(course.getGrade(),table.getSelectedRow(),table.getSelectedColumn());
                   }
               }
           }
       });
        JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        centerRewsPanel.add(scrollPane);
        centerRewsPanel.revalidate();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                idLabel.setText(table.getValueAt(row,2).toString());
                courseLabel.setText(table.getValueAt(row,4).toString());
                gradetextField.setText(table.getValueAt(row,5).toString());
                gradetextField.setEditable(false);
                gradetextField.setEnabled(false);
                rightgradPanel.setVisible(true);
                修改成绩Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("修改成绩")){
                            gradetextField.setEditable(true);
                            gradetextField.setEnabled(true);
                            修改成绩Button.setText("保存");
                        }
                        if (e.getActionCommand().equals("保存")) {
                            Course course = new Course();
                            course.setCourseName(courseLabel.getText());
                            course.setGrade(Double.valueOf(gradetextField.getText()));
                            course.setStudentId(idLabel.getText());
                            int n = ServiceFacotry.getCourseServiceInstance().updateGrade(course);
                            if (n == 1) {
                                model.setValueAt(idLabel.getText(), row, 2);
                                model.setValueAt(courseLabel.getText(), row, 4);
                                model.setValueAt(gradetextField.getText(), row, 5);
                                idLabel.setText("");
                                courseLabel.setText("");
                                gradetextField.setText("");
                                gradetextField.setEditable(false);
                                gradetextField.setEnabled(false);
                                修改成绩Button.setText("修改成绩");
                                rightgradPanel.setVisible(false);
                            }
                        }
                    }
                });
            }
        });
    }
    public void showTeacher(List<Teacher> teacherList){
        Teacher teacher = new Teacher();
        avatarLabel.setText("<html><img src ='"+teacher.getAvatar() +"'/><html>");
        nameLabel.setText(teacher.getTeacherName());
        courseNameLabel.setText(teacher.getCourseName());
        hobbyLabel.setText(teacher.getHobby());
        workTimeLabel.setText(String.valueOf(teacher.getWorkTime()));
        ardessTextField1.setText(teacher.getAdress());
        phone.setText(teacher.getPhone());
        ardessTextField1.setEditable(false);
        ardessTextField1.setEnabled(false);
        phone.setEditable(false);
        phone.setEnabled(false);
//        修改Button1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getActionCommand().equals("修改")){
//                    ardessTextField1.setEditable(true);
//                    ardessTextField1.setEnabled(true);
//                    修改Button1.setText("保存");
//                }
//                if (e.getActionCommand().equals("保存")) {
//                    Teacher teacher1 = new Teacher();
//                    teacher1.setPhone(xuehaoLabel.getText());
//                    teacher1.setAdress(ardessTextField1.getText());
//                    teacher1.setPhone(phone.getText());
//                    int n = ServiceFacotry.getStudnetServiceInstance().updateStudent(student);
//                    if (n == 1) {
//                        model.setValueAt(ardessTextField1.getText(), row, 5);
//                        model.setValueAt(phoneTextField.getText(), row, 6);
//                        ardessTextField1.setEditable(false);
//                        ardessTextField1.setEnabled(false);
//                        phone.setEditable(false);
//                        phone.setEnabled(false);
//                        修改Button1.setText("修改");
//                    }
//                }
//            }
//        });
    }
    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new TeacherMainFrame(DAOFactory.getTeacherLoginDAOInstance().getTeacherByAccount("18805162578"));
    }
}
