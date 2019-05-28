package com.sm.frame;

import com.eltima.components.ui.DatePicker;
import com.sm.entity.CClass;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.ServiceFacotry;
import com.sm.ui.ImgPanel;
import com.sm.utils.AliOSSUtil;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdminAddFrame extends JFrame {
    private ImgPanel rootPanel;
    private JTextField studentIdTextField;
    private JTextField studentNameTextField;
    private JRadioButton 男RadioButton;
    private JRadioButton 女RadioButton;
    private JPanel brithPanel;
    private JTextField addressTextField;
    private JTextField phoneTextField;
    private JButton 新增Button;
    private JLabel closeLabel;
    private JLabel avatarLabel;
    private AdminManiFrame adminManiFrame;
    private File file;
    private JComboBox<CClass> banJiComboBox1;
    private int classId;
    private String uploadFileUrl;

    public AdminAddFrame(AdminManiFrame adminManiFrame){
        this.adminManiFrame = adminManiFrame;
        setContentPane(rootPanel);
        rootPanel.setFileName("bgb.jpg");
        rootPanel.repaint();
        setTitle("新增学生");
        setSize(640,1000);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CClass tip = new CClass();
        tip.setClassName("请选择班级");
        banJiComboBox1.addItem(tip);
        List<CClass> cClassList = ServiceFacotry.getCClassServiceInstance().selectAllClass();
        for (CClass cClass :cClassList){
            banJiComboBox1.addItem(cClass);
        }

        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminAddFrame.this.dispose();
            }
        });


        avatarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:\\image"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    icon.setImage(icon.getImage().getScaledInstance(165,165, Image.SCALE_DEFAULT));
                    avatarLabel.setText("");
                    avatarLabel.setIcon(icon);
                }
            }
        });
        banJiComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = banJiComboBox1.getSelectedIndex();
                    classId = banJiComboBox1.getItemAt(index).getId();
                }
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(男RadioButton);
        group.add(女RadioButton);


        DatePicker datepick = getDatePicker();
        brithPanel.add(datepick);
        brithPanel.revalidate();


        新增Button.addActionListener(new ActionListener() {
            String gender = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (男RadioButton.isSelected()){
                    gender = "男";
                }
                if (女RadioButton.isSelected()){
                    gender = "女";
                }
                Student student = new Student();student.setId(studentIdTextField.getText());
                student.setClassId(classId);
                student.setStudentName(studentNameTextField.getText());
                student.setAvatar(AliOSSUtil.ossUpload(file));
                student.setGender(gender);
                student.setBirthday((Date)datepick.getValue());
                student.setAddress(addressTextField.getText());
                student.setPhone(phoneTextField.getText());
                int n = ServiceFacotry.getStudnetServiceInstance().insert(student);
                if (n == 1){
                    JOptionPane.showMessageDialog(rootPanel,"新增成功");
                    AdminAddFrame.this.dispose();
                    List<StudentVO> studentList = (List<StudentVO>) ServiceFacotry.getStudnetServiceInstance().selectAll();
                    adminManiFrame.showStudentTable(studentList);
                }
                adminManiFrame.setEnabled(true);
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
//        datepick.setLocation(137, 83);//设置起始位置
        /*
        //也可用setBounds()直接设置大小与位置
        datepick.setBounds(137, 83, 177, 24);
        */
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        // 设置时钟面板可见
//        datepick.setTimePanleVisible(true);
        return datepick;
    }
}
