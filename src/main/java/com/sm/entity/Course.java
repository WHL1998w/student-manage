package com.sm.entity;

public class Course {
   private Integer id;
   private String departmentName;
   private String className;
   private String studentName;
   private String studentId;
   private String courseName;
   private String teacherName;
   private String otherCourse;
  private Double grade;
  private String courseNumber;
  private String teacherAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getOtherCourse() {
        return otherCourse;
    }

    public void setOtherCourse(String otherCourse) {
        this.otherCourse = otherCourse;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getTeacherAccount() {
        return teacherAccount;
    }

    public void setTeacherAccount(String teacherAccount) {
        this.teacherAccount = teacherAccount;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", className='" + className + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", otherCourse='" + otherCourse + '\'' +
                ", grade=" + grade +
                ", courseNumber='" + courseNumber + '\'' +
                ", teacherAccount='" + teacherAccount + '\'' +
                '}';
    }
}
