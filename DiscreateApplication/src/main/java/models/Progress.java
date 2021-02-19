/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.models;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 *
 * @author Mher
 */
public class Progress {
    private String courseCode;
    private String courseName;
    private String progressPercent;
    private Parent course;
    
    public String getCode() {
        return courseCode;
    }
    
    public void setCode(String code) {
        this.courseCode = code;
    }
    
    public String getName() {
        return courseName;
    }
    
    public void setName(String name) {
        this.courseName = name;
    }
    
    public String getProgress() {
        return progressPercent;
    }
    
    public void setProgress(String progress) {
        this.progressPercent = progress;
    }
    
    public Parent getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(course));
        } catch (IOException ex) {
            Logger.getLogger(Progress.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.course = root;
    }
    
    
}
