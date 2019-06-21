package com.sm.frame;

import com.sm.entity.Department;
import com.sm.entity.Details;
import com.sm.entity.Profession;
import com.sm.factory.ServiceFacotry;
import com.sm.ui.ImgPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProfessionFrame extends JFrame {
    private AdminMainFrame adminMainFrame;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JComboBox<Department> departmentComboBox;
    private JTextField addTextField;
    private JButton 修改Button;
    private JButton 新增Button;
    private ImgPanel centerPanel;
    private JLabel 关闭Label;
    private JLabel departmentLabel;
    private Details details;
    private int departmentId;
    private int id;
    private JPopupMenu jPopupMenu;
    private JMenuItem item2;
    private JMenuItem item1;


    public ProfessionFrame(AdminMainFrame adminMainFrame){
        this.adminMainFrame = adminMainFrame;
        setContentPane(rootPanel);
        centerPanel.setFileName("bgb.jpg");
        centerPanel.repaint();
        setTitle("院系详情展示");
        setSize(1625,820);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showProessionPanel();

        //两个下拉框初始化显示数据，因为里面元素都是对象，所以这样进行了处理
        Department tip1 = new Department();
        tip1.setDepartmentName("请选择院系");
        departmentComboBox.addItem(tip1);
        List<Department> departmentList = ServiceFacotry.getDempartmentServiceInstance().selectAll();
        for (Department department :departmentList){
            departmentComboBox.addItem(department);
        }
        departmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //得到选中项的索引
                int index = departmentComboBox.getSelectedIndex();
                //按照索引取出项，就是一个Department对象，然后取出其中的id备用
                departmentId = departmentComboBox.getItemAt(index).getId();
            }
        });

        关闭Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProfessionFrame.this.dispose();
            }
        });
        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profession profession = new Profession();
                profession.setProfessionName(addTextField.getText().trim());
                profession.setDepartmentId(departmentId);
                int n = ServiceFacotry.getProfessionService().insetProfessionById(profession);
                if (n ==1){
                    JOptionPane.showMessageDialog(rootPanel,"新增专业成功");
                    showProessionPanel();
                    addTextField.setText("");
                }else {
                    JOptionPane.showMessageDialog(rootPanel,"新增专业失败");
                }
            }
        });
    }

    private void showProessionPanel(){
        List<Department> departmentList = ServiceFacotry.getDempartmentServiceInstance().selectAll();
        showProfession(departmentList);

    }
    private void showProfession(List<Department> departmentList){
        centerPanel.removeAll();
        GridLayout gridLayout = new GridLayout(0, 4, 20, 20);
        centerPanel.setLayout(gridLayout);
        for (Department department:departmentList) {
            JPanel depPanel = new JPanel();
            depPanel.setBackground(new Color(240, 240, 240));
            depPanel.setLayout(null);
            depPanel.setPreferredSize(new Dimension(370, 350));
            JTextField departmentNameField = new JTextField(department.getDepartmentName());
            //将院系名称设置给面板标题
            JLabel desLabel = new JLabel("院系简介");
            Font font = new Font("微软雅黑",Font.PLAIN,24);
            desLabel.setFont(font);
            departmentNameField.setFont(font);
            departmentNameField.setBounds(100,20,250,35);
            departmentNameField.setOpaque(true);
            departmentNameField.setEnabled(false);
            departmentNameField.setEditable(false);
            desLabel.setBounds(30,100,100,40);
            JTextArea desTextArea = new JTextArea(department.getDescription());
            desTextArea.setLineWrap(true);
            desTextArea.setBounds(150,20,210,140);
            JLabel proLabel = new JLabel("专业");
            proLabel.setFont(font);
            proLabel.setBounds(50,200,100,40);
            List<Profession> professionList = ServiceFacotry.getProfessionService().selectDepartmentId(department.getId());
            DefaultListModel listModel = new DefaultListModel();
            for (Profession p :professionList) {
                listModel.addElement(p);
            }
            JList<Profession> jList = new JList<>(listModel);
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
                        Profession profession = jList.getModel().getElementAt(index);
                        //对删除菜单添加监听
                        item2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int choice = JOptionPane.showConfirmDialog(depPanel,"确定删除吗？");
                                if (choice == 0){
                                    ServiceFacotry.getProfessionService().deletById(profession.getId());
                                    listModel.remove(index);
                                }
                            }
                        });
                        //对修改按钮添加监听
                        item1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int choice = JOptionPane.showConfirmDialog(depPanel,"确定修改吗？");
                                if (choice == 0){
                                    ServiceFacotry.getProfessionService().updateProfessionById(profession);
                                    listModel.set(index,profession);
                                }
                            }
                        });
                    }
                }
            });
            JScrollPane listScrollPane = new JScrollPane(jList);
            listScrollPane.setBounds(150,180,210,150);
            depPanel.add(listScrollPane);
            jPopupMenu = new JPopupMenu();
            item1 = new JMenuItem("修改");
            item2 = new JMenuItem("删除");
            jPopupMenu.add(item1);
            jPopupMenu.add(item2);
            jList.add(jPopupMenu);
            centerPanel.revalidate();
            centerPanel.add(depPanel);
            depPanel.add(desLabel);
            depPanel.add(desTextArea);
            depPanel.add(proLabel);
        }
    }
}
