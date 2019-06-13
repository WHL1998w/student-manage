package com.sm.entity;

public class StudentLogin {
    private  Integer id;
    private String studentAccount;
    private String studentPassword;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount(String studentAccount) {
        this.studentAccount = studentAccount;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentLoginDAO{" +
                "id=" + id +
                ", studentAccount='" + studentAccount + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
