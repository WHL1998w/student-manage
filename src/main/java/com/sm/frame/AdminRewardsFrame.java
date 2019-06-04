package com.sm.frame;

import com.eltima.components.ui.DatePicker;
import com.sm.entity.*;
import com.sm.factory.ServiceFacotry;
import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdminRewardsFrame extends JFrame {
    private ImgPanel rootPanel;
    private JComboBox<StudentVO> studentComboBox;
    private String studentId;
    private JRadioButton 奖RadioButton;
    private JRadioButton 惩RadioButton;
    private JPanel rewardsPanel;
    private JTextField rewardsTextField;
    private JPanel timePanel;
    private JButton 新增Button;
    private JLabel 关闭Label;
    private JTextField idTextField;
    private AdminMainFrame adminMainFrame;

    public AdminRewardsFrame(AdminMainFrame adminMainFrame){
        this.adminMainFrame = adminMainFrame;
        setContentPane(rootPanel);
        rootPanel.setFileName("bgb.jpg");
        rootPanel.repaint();
        setTitle("新增奖惩");
        setSize(650,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonGroup group = new ButtonGroup();
        group.add(奖RadioButton);
        group.add(惩RadioButton);

        StudentVO tip4 = new StudentVO();
        tip4.setId("请选择学号");
        tip4.setStudentName("姓名");
        studentComboBox.addItem(tip4);
        List<StudentVO> studentVOList1 = ServiceFacotry.getStudnetServiceInstance().selectAll();
        for (StudentVO studentVo:studentVOList1) {
            studentComboBox.addItem(studentVo);
        }


        studentComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = studentComboBox.getSelectedIndex();
                    studentId  = studentComboBox.getItemAt(index).getId();
                }
            }
        });


        DatePicker datepick = getDatePicker();
        timePanel.add(datepick);
        timePanel.revalidate();

        新增Button.addActionListener(new ActionListener() {
            String kind = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (奖RadioButton.isSelected()){
                    kind = "奖";
                }
                if (奖RadioButton.isSelected()){
                    kind = "惩";
                }
                Rewards rewards = new Rewards();
                rewards.setId(idTextField.getColumns());
                rewards.setStudentId(studentId);
                rewards.setKind(kind);
                rewards.setAwardPunishment(rewardsTextField.getText());
                rewards.settDate((Date)datepick.getValue());
                int n = ServiceFacotry.getRewardsServiceInstance().insert(rewards);
                if (n == 1){
                    JOptionPane.showMessageDialog(rootPanel,"新增成功");
                    AdminRewardsFrame.this.dispose();
                    List<Rewards> rewardsList =  ServiceFacotry.getRewardsServiceInstance().selectAll();
                    adminMainFrame.showRewards(rewardsList);
                }
            }
        });

        关闭Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminRewardsFrame.this.dispose();
            }
        });
    }
    private static DatePicker getDatePicker() {
        final DatePicker datepick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        Dimension dimension = new Dimension(200, 30);
        int[] hilightDays = {1, 3, 5, 7};
        int[] disabledDays = {4, 6, 5, 9};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
        datepick.setHightlightdays(hilightDays, Color.red);
        datepick.setDisableddays(disabledDays);
        datepick.setLocale(Locale.CHINA);
        return datepick;
    }
}
