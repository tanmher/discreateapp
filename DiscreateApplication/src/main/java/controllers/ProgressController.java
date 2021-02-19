/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

import main.java.models.Progress;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import static main.java.controllers.DashboardController.staticBorderPane;

/**
 *
 * @author Mher
 */
public class ProgressController  {
    @FXML private Label courseCode;
    @FXML private Text courseName;
    @FXML private Label progressData;
    
    private Progress prog;

    
    public void setData(Progress progress){
        this.prog = progress;
        courseCode.setText(prog.getCode());
        courseName.setText(prog.getName());
        progressData.setText(prog.getProgress());
    }
    
    public void continueCourse(MouseEvent event) {
        staticBorderPane.setCenter(prog.getCourse());
    }
}
