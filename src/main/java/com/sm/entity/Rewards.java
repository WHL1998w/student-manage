package com.sm.entity;

public class Rewards {
    private String studentId;
    private String studentName;
    private String departmentName;
    private String className;
    private String award;
    private String punishment;


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

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

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    @Override
    public String toString() {
        return "Rewards{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", className='" + className + '\'' +
                ", award='" + award + '\'' +
                ", punishment='" + punishment + '\'' +
                '}';
    }
}
