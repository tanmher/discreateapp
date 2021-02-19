/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.models;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Mher
 */
public class UserSession implements Initializable {

    private static String firstName;
    private static String lastName;
    private static String userName;
    private static String courseSection;
    private static Integer lesson01 = 0;
    private static Integer lesson02 = 0;
    private static Integer lesson03 = 0;
    private static Integer lesson04 = 0;
    private static Integer quiz01 = 0;
    private static Integer quiz02 = 0;
    private static Integer quiz03 = 0;
    private static Integer quiz04 = 0;
    private static Integer completedCourses = 0;
    private static Integer inProgress = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }

    public static void setUserInfo(String first, String last, String user, String course){
        UserSession.firstName = first;
        UserSession.lastName = last;
        UserSession.userName = user;
        UserSession.courseSection = course;
    }
    
    public static String getUserName() {
        return userName;
    }
    
    public static String getFullName(){
        String fullName = firstName + " " + lastName;
        return fullName;
    }
    
    public static String getInitials() {
        char fInitial = firstName.charAt(0);
        char lInitial =  lastName.charAt(0);
        String initials = String.valueOf(fInitial) + String.valueOf(lInitial);
        return initials.toUpperCase();
    }
    
    
    public static String getCourseSection() {
        return courseSection;
    }
    
    public static void setProgress(Integer lesson, Integer progress){
        
        if(lesson == 1){
            double prog = progress;
            prog = prog / 12.00 * 100;
            if(prog > lesson01) { 
                lesson01 = (int) prog;
            }
        } 
        
        if(lesson == 2){
            double prog = progress;
            prog = prog / 13.00 * 100;
            if(prog > lesson02) { 
                lesson02 = (int) prog;
            } 
        }
        
        if(lesson == 3){
            double prog = progress;
            prog = prog / 9.00 * 100;
            if(prog > lesson03) { 
                lesson03 = (int) prog;
            }
        } 
        
        if(lesson == 4){
            double prog = progress;
            prog = prog / 6.00 * 100;
            if(prog > lesson04) { 
                lesson04 = (int) prog;
            }
        }
    }
    
    public static void setAllProgress(Integer lesson1, Integer lesson2,Integer lesson3,Integer lesson4,
            Integer quiz1, Integer quiz2, Integer quiz3, Integer quiz4 ){
        UserSession.lesson01 = lesson1;
        UserSession.lesson02 = lesson2;
        UserSession.lesson03 = lesson3;
        UserSession.lesson04 = lesson4;
        UserSession.quiz01 = quiz1;
        UserSession.quiz02 = quiz2;
        UserSession.quiz03 = quiz3;
        UserSession.quiz04 = quiz4;
    }
    public static Integer getLesson1() {
        return lesson01;
    }
    public static Integer getLesson2() {
        return lesson02;
    }
    public static Integer getLesson3() {
        return lesson03;
    }
    public static Integer getLesson4() {
        return lesson04;
    }
    
    public static Integer getProgressLesson1() {
        if(lesson01 == null){
            return 0;
        }
        return lesson01;
    }
    
    public static Integer getProgressLesson2() {
        if(lesson02 == null){
            return 0;
        }
        return lesson02;
    }
    public static Integer getProgressLesson3() {
        if(lesson03 == null){
            return 0;
        }
        return lesson03;
    }
    
    public static Integer getProgressLesson4() {
        if(lesson04 == null){
            return 0;
        }
        return lesson04;
    }
    
    public static void setScoreQuiz01(Integer score){
        UserSession.quiz01 = score;
    }
    public static void setScoreQuiz02(Integer score){
        UserSession.quiz02 = score;
    }
    public static void setScoreQuiz03(Integer score){
        UserSession.quiz03 = score;
    }
    public static void setScoreQuiz04(Integer score){
        UserSession.quiz04 = score;
    }
    
    public static String getScoreQuiz01() {
        return String.valueOf(quiz01);
    }
    public static String getScoreQuiz02() {
        return String.valueOf(quiz02);
    }
    public static String getScoreQuiz03() {
        return String.valueOf(quiz03);
    }
    public static String getScoreQuiz04() {
        return String.valueOf(quiz04);
    }
    
    public static Integer getAverageQuizScore() {
        int average = (quiz01 + quiz02 + quiz03 + quiz04) / 4;
        return average;
    }
    
    public static Integer getCompletedCourses() {
        int complete = 0;
        if(lesson01 == 100) { complete++; }
        if(lesson02 == 100) { complete++; }
        if(lesson03 == 100) { complete++; }
        if(lesson04 == 100) { complete++; }
        completedCourses = complete;
        return completedCourses;
    }
    
    public static Integer getInProgress() {
        inProgress = 0;
        if(lesson01 > 0 && lesson01 < 100) { inProgress++; }
        if(lesson02 > 0 && lesson02 < 100) { inProgress++; }
        if(lesson03 > 0 && lesson03 < 100) { inProgress++; }
        if(lesson04 > 0 && lesson04 < 100) { inProgress++; }
        
        return inProgress;
    }
    
    
    public static void cleanUserSession() {
        firstName = "";
        lastName = "";
        userName = "";
        courseSection = "";
        lesson01 = 0;
        lesson02 = 0;
        lesson03 = 0;
        lesson04 = 0;
    }
    
    public static void closeUpdate(ActionEvent event) throws IOException  {
        if(courseSection != "Administrator") {
            if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int lesson1 = UserSession.getLesson1();
                int lesson2 = UserSession.getLesson2();
                int lesson3 = UserSession.getLesson3();
                int lesson4 = UserSession.getLesson4();
                String username = UserSession.getUserName();

                String updateProgress = "UPDATE `users` SET `lesson1`= '"+ lesson1+"',"
                        + "`lesson2` = '"+ lesson2 +"',"
                        + "`lesson3` = '"+ lesson3 +"',"
                        + "`lesson4` = '"+ lesson4 +"' WHERE `username`='"+ username +"'";

                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();
                try{
                    PreparedStatement statement;
                    statement = connectDB.prepareStatement(updateProgress);

                    if(statement.executeUpdate() != 0){                                
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.close();
                        UserSession.cleanUserSession();
                    }
                    statement.close();
                } catch(Exception e){
                    e.printStackTrace();
                    e.getCause();
                }
            } 
        }else{
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.close();
            UserSession.cleanUserSession();
        }
    }
}