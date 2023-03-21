/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.Dao;

/**
 *
 * @author admin
 */
public class User {
    
    private int id;
    private int roleId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private boolean isDeleted;
    private String checkCode;

    public User() {
    }

    public User(int id, int roleId, String email, String fullName, String phone) {
        this.id = id;
        this.roleId = roleId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }
    
    public boolean getIsAdmin(){
        if(Dao.isAdmin(email) != null){
            return true;
        }
        return false;
    }

    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        roleId = 3;
        isDeleted = false;
        this.email = email;
        this.password = password;
    }
    
    

    public User(int id, int roleId, String fullName, String email, String phone, String address, String password, boolean isDeleted) {
        this.id = id;
        this.roleId = roleId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
    
    
}
