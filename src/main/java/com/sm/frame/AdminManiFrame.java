package com.sm.frame;

import com.sm.entity.*;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFacotry;
import com.sm.thread.TimeThread;
import com.sm.ui.ImgPanel;
import com.sm.utils.AliOSSUtil;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * wanghuanle
 */
public class AdminManiFrame extends JFrame {

    private JButton 院系管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JButton 奖惩管理Button;
    private JPanel centerPanel;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPanel;
    private JPanel topPanel;
    private JButton 新增院系Button;
    private JButton 刷新数据Button;
    private JPanel leftPanel;
    private JTextField depNameField;
    private JButton 选择Logo图Button;
    private JButton 新增Button;
    private JScrollPane scrollPanel;
    private JPanel contentPanel;
    private JLabel adminNameLabel;
    private JLabel timeLabel;
    private ImgPanel rootPanel;
    private JLabel imgLabel;
    private JTextField textField1;
    private JPanel treePanel;
    private JPanel classContentPanel;
    private JButton 新增班级Button;
    private Admin admin;
    private String uploadFileUrl;
    private File file;
    private int departmentId = 0;
    private JPopupMenu jPopupMenu;
    private JMenuItem item2;
    private JComboBox<Department> comboBox1;
    private JPanel stuTopPanel;
    private JComboBox<Department> comboBox2;
    private JComboBox<CClass> comboBox3;
    private JTextField textField2;
    private JButton 搜索Button;
    private JButton 新增学生Button;
    private JButton 批量导入Button;
    private ImgPanel infoPanel;
    private JLabel avatarLabel;
    private JLabel xuehaoLabel;
    private JLabel yuanxiLabel;
    private JLabel banjiLabel;
    private JLabel xingmingLabel;
    private JLabel xingbie;
    private JTextField addressTextField;
    private JTextField phoneTextField;
    private JPanel tablePanel;
    private JLabel brithLabel;
    private JButton 初始化数据Button;
    private JButton 编辑Button;
    private JPanel rTopPanel;
    private JTextField textField3;
    private JButton 查询Button;
    private JPanel rightPanel;
    private JPanel rCenterPanel;
    private JTextArea jianglitextArea1;
    private JButton 新增奖励Button;
    private JTextArea chengfatextArea2;
    private JButton 记录处罚Button;
    private ImgPanel panel1;
    private ImgPanel panel2;
    private String keywords;
    private int row;
    private ImageIcon imageIcon;



    public AdminManiFrame(Admin admin) {
        this.admin = admin;
        adminNameLabel.setText("管理员：" + admin.getAdminName());
        showDepartments();
        setTitle("管理员主界面");
        //设置背景图
        rootPanel.setFileName("bg1.jpg");
        //组件重绘
        rootPanel.repaint();
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        //获取centerPanel的布局,会的是LayoutManger,向下转型为CardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();

        院系管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               cardLayout.show(centerPanel,"Card1");
            }
        });
        班级管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card2");
                showClassPanel();
            }
        });
        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card3");
                infoPanel.setFileName("a.png");
                infoPanel.repaint();
                List<StudentVO> studentVOList = ServiceFacotry.getStudnetServiceInstance().selectAll();
                showStudentTable(studentVOList);

                //两个下拉框初始化显示数据，因为里面元素都是对象，所以这样进行了处理
                Department tip1 = new Department();
                tip1.setDepartmentName("请选择院系");
                comboBox2.addItem(tip1);
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                comboBox3.addItem(tip2);
                //初始化院系下拉框数据
                List<Department> departmentList = ServiceFacotry.getDempartmentServiceInstance().selectAll();
                for (Department department:departmentList) {
                    comboBox2.addItem(department);
                }

                //初始化班级下拉框数据
                List<CClass> cClassList = ServiceFacotry.getCClassServiceInstance().selectAllClass();
                for (CClass cClass:cClassList) {
                    comboBox3.addItem(cClass);
                }

                //院系下拉框的监听，选中哪项，表格中显示该院系所有学生，
                comboBox2.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED){
                            int index = comboBox2.getSelectedIndex();
                            if (index != 0){
                                departmentId = comboBox2.getItemAt(index).getId();
                                List<StudentVO> studentVOList = ServiceFacotry.getStudnetServiceInstance().selectByDepartmentId(departmentId);
                                showStudentTable(studentVOList);
                                List<CClass> cClassList = ServiceFacotry.getCClassServiceInstance().selectByDepartmentId(departmentId);
                                comboBox3.removeAllItems();
                                CClass tip = new CClass();
                                tip.setClassName("请选择班级");
                                comboBox3.addItem(tip);
                                for (CClass cClass:cClassList) {
                                    comboBox3.addItem(cClass);
                                }
                            }
                        }
                    }
                });
                comboBox3.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED){
                            int index = comboBox3.getSelectedIndex();
                            if (index != 0){
                                int classId = comboBox3.getItemAt(index).getId();
                                List<StudentVO> studentVOList = ServiceFacotry.getStudnetServiceInstance().selectByCClassId(classId);
                                showStudentTable(studentVOList);
                            }
                        }
                    }
                });
            }
        });
        奖惩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card4");
                List<Rewards> rewardsList = ServiceFacotry.getRewardsServiceInstance().selectAll();
                showRewards(rewardsList);
                //设置背景图
                panel1.setFileName("bgc1.png");
                panel2.setFileName("bgc1.png");
                //组件重绘
                panel1.repaint();
                panel2.repaint();
            }
        });
        新增院系Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = leftPanel.isVisible();
                if (flag == true){
                    leftPanel.setVisible(false);
                }else {
                    leftPanel.setVisible(true);
                }
            }
        });

        选择Logo图Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory( new File("D:\\image"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION){
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建Icon对象
                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    ((ImageIcon) icon).setImage(((ImageIcon) icon).getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
                    //通过标签显示图片
                    imgLabel.setIcon(icon);
                    //设置标签可见
                    imgLabel.setVisible(true);
                    //将按钮隐藏
                    选择Logo图Button.setVisible(false);
                }
            }
        });
        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //上传文件到OSS并返回外链接
                uploadFileUrl = AliOSSUtil.ossUpload(file);
                //创建Department对象，并设置相应属性
                Department department = new Department();
                department.setDepartmentName(depNameField.getText().trim());
                department.setLogo(uploadFileUrl);
                //调用service实现新增功能
                int n = ServiceFacotry.getDempartmentServiceInstance().addDepartment(department);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系成功");
                    //新增成功后，将侧边栏隐藏
                    leftPanel.setVisible(false);
                    //刷新界面数据
                    showDepartments();
                    //将图片预览标签隐藏
                    imgLabel.setVisible(false);
                    //将选择图片的按钮可见
                    选择Logo图Button.setVisible(true);
                    //清空文本框
                    depNameField.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系失败");
                }
            }
        });
        depNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                depNameField.setText("");
            }
        });
        imgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory( new File("D:\\image"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建Icon对象
                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    ((ImageIcon) icon).setImage(((ImageIcon) icon).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                    //通过标签显示图片
                    imgLabel.setIcon(icon);
                    //设置标签可见
                    imgLabel.setVisible(true);
                    //将按钮隐藏
                    选择Logo图Button.setVisible(false);
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //得到选中项的索引
                int index = comboBox1.getSelectedIndex();
                //按照索引取出项，就是一个Department对象，然后取出其中的id备用
              departmentId = comboBox1.getItemAt(index).getId();
            }
        });
        新增班级Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CClass cClass = new CClass();
                cClass.setClassName(textField1.getText().trim());
                cClass.setDepartmentId(departmentId);
                int n = ServiceFacotry.getCClassServiceInstance().addCClass(cClass);
                if (n ==1){
                    JOptionPane.showMessageDialog(rootPanel,"新增班级成功");
                    showClassPanel();
                    textField1.setText("");
                }else {
                    JOptionPane.showMessageDialog(rootPanel,"新增班级失败");
                }
            }
        });
        初始化数据Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //还原表格数据
                List<StudentVO> studentVOList = ServiceFacotry.getStudnetServiceInstance().selectAll();
                showStudentTable(studentVOList);
                //院系下拉框还原
                comboBox2.setSelectedIndex(0);
                //班级下拉框数据还原
                comboBox3.removeAllItems();
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                comboBox3.addItem(tip2);
                List<CClass> cClassList = ServiceFacotry.getCClassServiceInstance().selectAllClass();
                for (CClass cClass:cClassList) {
                    comboBox3.addItem(cClass);
                }
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
        搜索Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = textField2.getText().trim();
                List<StudentVO> studentVOList = ServiceFacotry.getStudnetServiceInstance().selectByKeywords(keywords);
                if (studentVOList != null){
                    showStudentTable(studentVOList);
                }
            }
        });
        新增学生Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminAddFrame(AdminManiFrame.this);
            }
        });
        AdminManiFrame.this.setEnabled(true);

        刷新数据Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepartments();
            }
        });
        查询Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = textField3.getText().trim();
                List<Rewards> rewardsList = ServiceFacotry.getRewardsServiceInstance().selectByKeywords(keywords);
                if (rewardsList != null){
                    showRewards(rewardsList);
                }
            }
        });
    }

    private void showDepartments(){
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到所有院系列表
        List<Map> departmentList = ServiceFacotry.getDempartmentServiceInstance().selectDepartmentInfo();
        GridLayout gridLayout = new GridLayout(0, 4, 20, 20);
        contentPanel.setLayout(gridLayout);
        TimeThread timeThread = new TimeThread();
        timeThread.setTimeLabel(timeLabel);
        timeThread.start();
        for (Map map : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            Department department = (Department) map.get("department");
            int classCount = (int) map.get("classCount");
            int studentCount = (int )map.get("studentCount");
            depPanel.setBackground(new Color(240, 240, 240));
            depPanel.setLayout(null);
            depPanel.setPreferredSize(new Dimension(300, 350));
            //将院系名称设置给面板标题
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            //新建一个Label用来放置院系logo，并指定大小
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() +"'width=230 height=230 /></html>");
            logoLabel.setBackground(new Color(255,255,255));
            logoLabel.setBounds(35,20,250,250);
            JLabel infoLabel = new JLabel("班级" + classCount + "个，学生" + studentCount + "人");
            JButton delBtn=new JButton("删除");
            delBtn.setBackground(new Color(37,61,242));
            Font font = new Font("微软雅黑",Font.PLAIN,20);
            delBtn.setFont(font);
            delBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked (MouseEvent e) {
                    int n=JOptionPane.showConfirmDialog(null,"确定要删除这行数据吗？","删除警告",
                            JOptionPane.YES_OPTION);
                    if (n==JOptionPane.YES_OPTION) {
                        contentPanel.remove(depPanel);
                        contentPanel.repaint();
                        ServiceFacotry.getDempartmentServiceInstance().deleteDepartment(department.getId());
                    }
                }
            });
            delBtn.setBounds(120,280,100,40);
            infoLabel.setBounds(70,340,250,40);
            infoLabel.setFont(font);
            //图标标签加入院系面板
            depPanel.add(logoLabel);
            depPanel.add(infoLabel);
            depPanel.add(delBtn);
            //院系面板加入主体内容面板
            contentPanel.add(depPanel);
            //刷新主体内容面板
            contentPanel.revalidate();
        }
    }
    private void showClassPanel(){
        List<Department> departmentList = ServiceFacotry.getDempartmentServiceInstance().selectAll();
        showCombobox(departmentList);
        showTree(departmentList);
        showClass(departmentList);

    }
    private void showCombobox(List<Department> departmentList){
        for (Department department:departmentList) {
            comboBox1.addItem(department);
        }
    }
    private void showTree(List<Department> departmentList){
        treePanel.removeAll();
        //左侧树形结构根节点
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("南工院");
        for (Department department : departmentList) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            top.add(group);
            List<CClass> cClassList = ServiceFacotry.getCClassServiceInstance().selectByDepartmentId(department.getId());
            for (CClass cClass : cClassList) {
                int num = ServiceFacotry.getStudnetServiceInstance().countStudentByClassId(cClass.getId());
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(cClass.getClassName() + "（" + num + "人)");
                group.add(node);
            }
        }
        final JTree tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        treePanel.add(tree);
        treePanel.revalidate();
    }
    private void showClass(List<Department> departmentList){
        classContentPanel.removeAll();
        //右侧流式布局显示
        Font titleFont = new Font("微软雅黑",Font.PLAIN,22);
        for (Department department :departmentList) {
            ImgPanel depPanel = new ImgPanel();
            depPanel.setFileName("bg2.jpg");
            depPanel.setBackground(new Color(244,244,253));
            depPanel.repaint();
            depPanel.setPreferredSize(new Dimension(380,450));
            depPanel.setLayout(null);
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titleFont);
            depNameLabel.setBounds(125,15,200,30);
            List<CClass> cClassList = ServiceFacotry.getCClassServiceInstance().selectByDepartmentId(department.getId());
            DefaultListModel listModel = new DefaultListModel();
            for (CClass cClass:cClassList) {
                listModel.addElement(cClass);
            }
            JList<CClass> jList = new JList<>(listModel);
            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //选中项的下标
                    int index = jList.getSelectedIndex();
                    //点击鼠标右键
                    if (e.getButton() == 3){
                        //在鼠标位置弹出菜单
                        jPopupMenu.show(jList,e.getX(),e.getY());
                        //取出选中项数据
                        CClass cClass = jList.getModel().getElementAt(index);
                        //对删除菜单添加监听
                        item2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int choice = JOptionPane.showConfirmDialog(depPanel,"确定删除吗？");
                                if (choice == 0){
                                    ServiceFacotry.getCClassServiceInstance().deleteClassById(cClass.getId());
                                    listModel.remove(index);
                                    showTree(ServiceFacotry.getDempartmentServiceInstance().selectAll());
                                }
                            }
                        });
                    }
                }
            });
            JScrollPane listScrollPane = new JScrollPane(jList);
            listScrollPane.setBounds(90,90,200,260);
            depPanel.add(depNameLabel);
            depPanel.add(listScrollPane);
            classContentPanel.add(depPanel);
            jPopupMenu = new JPopupMenu();
            JMenuItem item1 = new JMenuItem("修改");
            item2 = new JMenuItem("删除");
            jPopupMenu.add(item1);
            jPopupMenu.add(item2);
            jList.add(jPopupMenu);
        }
    }
    public void showStudentTable(List<StudentVO> studentVOList){
        tablePanel.removeAll();
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
        tablePanel.add(scrollPane);
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("删除");
        jPopupMenu.add(item);
        table.add(jPopupMenu);
        //刷新数据
        tablePanel.revalidate();
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
                编辑Button.setVisible(true);
                编辑Button.setText("编辑");
                编辑Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("编辑")){
                            addressTextField.setEditable(true);
                            addressTextField.setEnabled(true);
                            phoneTextField.setEditable(true);
                            phoneTextField.setEnabled(true);
                            编辑Button.setText("保存");
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
                                编辑Button.setText("编辑");
                            }
                        }
                    }
                });
                if (e.getButton()==3){
                    jPopupMenu.show(table,e.getX(),e.getY());
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String id= (String) table.getValueAt(row,0);
                int choice=JOptionPane.showConfirmDialog(tablePanel,"确定删除吗？");
                if (choice==0) {
                    if (row != -1) {
                        model.removeRow(row);
                    }
                    ServiceFacotry.getStudnetServiceInstance().deletById(id);
                }
            }
        });

    }
    public void showRewards(List<Rewards> rewardsList){
        rCenterPanel.removeAll();
        //创建表格对象
        JTable table = new JTable();
        //表格数据模型
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        //表头内容
        model.setColumnIdentifiers(new String[]{"学号","姓名","院系","班级","奖励情况","惩处情况"});
        //遍历list 转成object输出
        for (Rewards rewards:rewardsList) {
            Object[] objects = new Object[]{rewards.getStudentId(),rewards.getStudentName(),rewards.getDepartmentName(),rewards.getClassName(),
                    rewards.getAward(),rewards.getPunishment()};
            model.addRow(objects);
        }
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
        table.setBackground(new Color(161, 250, 220));
        //表格内容居中
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        //表格加入滚动面板
        JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        rCenterPanel.add(scrollPane);
        //刷新数据
        rCenterPanel.revalidate();
        //点击哪一行中的哪一列就显示在右边
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                jianglitextArea1.setText(table.getValueAt(row,4).toString());
                chengfatextArea2.setText(table.getValueAt(row,5).toString());
            }
        });
    }
    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AdminManiFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("aaa@qq.com"));
    }
}
