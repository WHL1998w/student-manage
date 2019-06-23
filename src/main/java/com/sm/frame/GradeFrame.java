package com.sm.frame;

import com.sm.entity.Course;
import com.sm.entity.CourseVO;
import com.sm.entity.StudentVO;
import com.sm.factory.ServiceFacotry;
import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class GradeFrame extends JFrame {
    private ImgPanel rootPanel;
    private JTextField idTextField;
    private JComboBox<StudentVO> studentNameComboBox;
    private JComboBox<CourseVO> courseComboBox;
    private JTextField gradetextField;
    private JButton 新增Button;
    private JLabel 关闭Label;
    private AdminMainFrame adminMainFrame;
    private String studentId;
    private String courseNumber;

    public GradeFrame(AdminMainFrame adminMainFrame){
        this.adminMainFrame = adminMainFrame;
        setContentPane(rootPanel);
        rootPanel.setFileName("bgb.jpg");
        rootPanel.repaint();
        setTitle("新增奖惩");
        setSize(650,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //初始学生下拉框
        StudentVO tip4 = new StudentVO();
        tip4.setId("请选择学号");
        tip4.setStudentName("姓名");
        studentNameComboBox.addItem(tip4);
        List<StudentVO> studentVOList1 = ServiceFacotry.getStudnetServiceInstance().selectAll();
        for (StudentVO studentVo:studentVOList1) {
            studentNameComboBox.addItem(studentVo);
        }
        //初始课程下拉框
        CourseVO tip5 = new CourseVO();
        tip5.setCourseName("请选择课程");
        tip5.setCourseName("课程编号");
        courseComboBox.addItem(tip5);
        List<CourseVO> courseList1 = ServiceFacotry.getCourseServiceInstance().getSelectAll();
        for (CourseVO courseVO:courseList1) {
            courseComboBox.addItem(courseVO);
        }

        studentNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = studentNameComboBox.getSelectedIndex();
                    studentId  = studentNameComboBox.getItemAt(index).getId();
                }
            }
        });
        courseComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = courseComboBox.getSelectedIndex();
                    courseNumber  = courseComboBox.getItemAt(index).getCourseNumber();
                }
            }
        });

        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course course = new Course();
                course.setId(idTextField.getColumns());
                course.setStudentId(studentId);
                course.setCourseNumber(courseNumber);
                course.setCourseName(courseNumber);
                course.setGrade(Double.valueOf(gradetextField.getText()));
                int n = ServiceFacotry.getCourseServiceInstance().insertByStudentId(course);
                if (n == 1){
                    JOptionPane.showMessageDialog(rootPanel,"新增成功");
                    List<Course> courseList =  ServiceFacotry.getCourseServiceInstance().selectAll();
                    adminMainFrame.showCourse(courseList);
                    idTextField.setText("");
                    gradetextField.setText("");
                }else {
                    JOptionPane.showMessageDialog(rootPanel,"新增失败");
                }
            }
        });
        关闭Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GradeFrame.this.dispose();
            }
        });

    }


}
