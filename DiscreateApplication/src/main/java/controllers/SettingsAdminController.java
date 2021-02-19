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
import main.java.models.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Mher
 */
public class SettingsAdminController implements Initializable {
    @FXML private ImageView userPhoto;
    @FXML private Label adminUsername;
    @FXML private Label adminName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String userFile = "/main/resources/images/user_photo.png";
        Image userImage = new Image(userFile);
        userPhoto.setImage(userImage);
        
        String uname = UserSession.getUserName();
        String fullName = UserSession.getFullName();
        
        adminUsername.setText(uname);
        adminName.setText(fullName);
    }
    
    public void closeOnAction(ActionEvent event) throws IOException{
        UserSession.closeUpdate(event);
    }
    
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    
    public void changePassword(ActionEvent event){
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/forgotPassword.fxml"));
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
    }
    
}
