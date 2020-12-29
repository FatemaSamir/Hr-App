package com.example.hrapp_project;

public class User {

    String full_name,Email,Department,Age,phone,password;

    public User(String full_name, String email, String password) {
        this.full_name = full_name;
        Email = email;

    }
    public User(String full_name, String email, String department, String age, String phone, String password) {
        this.full_name = full_name;
        Email = email;
        Department = department;
        Age = age;
        this.phone = phone;
    }
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }


}
