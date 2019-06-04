package com.sm.entity;

import java.util.Date;

public class Rewards {
    private Integer id;
   private String studentId;
   private String studentName;
   private String gender;
   private String departmentName;
   private String className;
   private String kind;
   private String awardPunishment;
   private Date tDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAwardPunishment() {
        return awardPunishment;
    }

    public void setAwardPunishment(String awardPunishment) {
        this.awardPunishment = awardPunishment;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    @Override
    public String toString() {
        return "Rewards{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender='" + gender + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", className='" + className + '\'' +
                ", kind='" + kind + '\'' +
                ", awardPunishment='" + awardPunishment + '\'' +
                ", tDate=" + tDate +
                '}';
    }
}
