/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.models;

/**
 *
 * @author Mher
 */
public class Users {
    private String username;
    private String firstname;
    private String lastname;
    private String courseSection;
    private Integer a1;
    private Integer a2;
    private Integer a3;
    private Integer a4;
    private Integer q1;
    private Integer q2;
    private Integer q3;
    private Integer q4;
    
    
    public Users(String uname, String fname, String lname, String cSection, 
            Integer a1, Integer a2, Integer a3,Integer a4,
            Integer q1, Integer q2, Integer q3, Integer q4){
        this.username = uname;
        this.firstname = fname;
        this.lastname = lname;
        this.courseSection = cSection;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
    }
    
    public String getUsername() {
        return this.username;
    }
    public String getFirstname() {
        return this.firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    public String getCourseSection() {
        return this.courseSection;
    }
    
    public Integer getA1() {
        return a1;
    }

    public Integer getA2() {
        return a2;
    }

    public Integer getA3() {
        return a3;
    }

    public Integer getA4() {
        return a4;
    }

    public Integer getQ1() {
        return q1;
    }

    public Integer getQ2() {
        return q2;
    }

    public Integer getQ3() {
        return q3;
    }

    public Integer getQ4() {
        return q4;
    }
}
