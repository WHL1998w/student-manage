package com.sm.entity;

import java.util.Date;

public class Teacher {
    private String teacherName;
    private String courseName;
    private String hobby;
    private String avatar;
    private String phone;
    private String adress;
    private Date workTime;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        return "TeacherDAOImpl{" +
                "teacherName='" + teacherName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", hobby='" + hobby + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                ", workTime=" + workTime +
                '}';
    }
}
