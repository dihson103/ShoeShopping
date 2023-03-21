/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Feedback {
    private int id;
    private String email;
    private String name;
    private String subject;
    private String mess;
    private String date;

    public Feedback() {
    }

    public Feedback(int id, String email, String name, String subject, String mess, String date) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.mess = mess;
        this.date = date;
    }
    
    

    public Feedback(String email, String name, String subject, String mess, String date) {
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.mess = mess;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
