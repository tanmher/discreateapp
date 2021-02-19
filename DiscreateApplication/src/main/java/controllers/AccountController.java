/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.models.UserSession;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import main.java.models.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Mher
 */
public class AccountController implements Initializable {
    @FXML private ImageView userPhoto;
    @FXML private Label studentUserName;
    @FXML private Label studentName;
    @FXML private Label studentCourse;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String uname = UserSession.getUserName();
        String fullName = UserSession.getFullName();
        String course = UserSession.getCourseSection();
        
        studentUserName.setText(uname);
        studentName.setText(fullName);
        studentCourse.setText(course);
        
    }
    
    public void closeOnAction(ActionEvent event) throws IOException{
        UserSession.closeUpdate(event);
    }
    
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    
    public void deleteAccount(ActionEvent event) throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String unameCurrent = UserSession.getUserName();
        String uname = JOptionPane.showInputDialog(null,"Enter username","Confirm User", JOptionPane.INFORMATION_MESSAGE);
        
        String deleteQuery = "DELETE FROM users WHERE username = "+ unameCurrent +"";
        
        try{
            PreparedStatement statement;
            statement = connectDB.prepareStatement(deleteQuery);
        
            if(!unameCurrent.equals(uname)){
                JOptionPane.showMessageDialog(null, "Username does not match, Please enter your username");
            }else{
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "You're account is Deleted");
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.hide();
                Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/login.fxml"));
                Stage homeStage = new Stage();
                homeStage.initStyle(StageStyle.UNDECORATED);
                homeStage.setScene(new Scene(root, 1280, 720));
                homeStage.show();
                homeStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));
            }
            
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    
    
    public void changePassword(ActionEvent event){
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/changePassword.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 1280, 720));
            registerStage.show();
            registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void logoutOnAction(MouseEvent event) throws IOException  {
        if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int lesson01 = UserSession.getLesson1();
            int lesson02 = UserSession.getLesson2();
            int lesson03 = UserSession.getLesson3();
            int lesson04 = UserSession.getLesson4();
            String username = UserSession.getUserName();
            
            String updateProgress = "UPDATE `users` SET `lesson1`= '"+ lesson01+"',"
                    + "`lesson2` = '"+ lesson02 +"',"
                    + "`lesson3` = '"+ lesson03 +"',"
                    + "`lesson4` = '"+ lesson04 +"' WHERE `username`='"+ username +"'";
            
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            try{
                PreparedStatement statement;
                statement = connectDB.prepareStatement(updateProgress);
                
                if(statement.executeUpdate() != 0){                                
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    primaryStage.hide();
                        
                    UserSession.cleanUserSession();
                    Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/login.fxml"));
                    Stage loginStage = new Stage();
                    loginStage.initStyle(StageStyle.UNDECORATED);
                    loginStage.setScene(new Scene(root, 1280, 720));
                    loginStage.show();
                    loginStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));
                }
            } catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        } 

    }
    
}
