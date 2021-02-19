package main.java.controllers;

import main.java.models.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import main.java.models.UserSession;

public class LoginController implements Initializable {
    @FXML private ChoiceBox userLevel;
    @FXML private Label loginMessageLabel;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField enterPasswordField;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){   
        userLevel.getItems().add("Student");
        userLevel.getItems().add("Administrator");
    }

    public void closeOnAction(ActionEvent event){
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
    }

    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }

    public void validateLogin(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String user = (String) userLevel.getSelectionModel().getSelectedItem();
        String verifyLogin;
        Parent root;
        
        if (usernameTextField.getText() != null && enterPasswordField.getText() != null ) {
            if("Student".equals(user)){
                verifyLogin = "SELECT * FROM `users` WHERE `username` = '" + usernameTextField.getText() + "' AND `password` = '"+ enterPasswordField.getText() +"'";
            }else if("Administrator".equals(user)){
                verifyLogin = "SELECT * FROM `admin` WHERE `username` = '" + usernameTextField.getText() + "' AND `password` = '"+ enterPasswordField.getText() +"'";
            }else{
                verifyLogin = null;
                loginMessageLabel.setText("Please select user level");
            }
            try {                
                if(verifyLogin != null) {
                    Statement statement = connectDB.createStatement();
                    ResultSet queryResult = statement.executeQuery(verifyLogin);
                    if(queryResult.next()) {
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.hide();
                        String uname = queryResult.getString(2);
                        String fname = queryResult.getString(4);
                        String lname = queryResult.getString(5);
                        String courseSection;
                        
                        if("Student".equals(user)) {
                            courseSection = queryResult.getString(6);
                            Integer l1 = queryResult.getInt(7);
                            Integer l2 = queryResult.getInt(8);
                            Integer l3 = queryResult.getInt(9);
                            Integer l4 = queryResult.getInt(10);
                            Integer q1 = queryResult.getInt(11);
                            Integer q2 = queryResult.getInt(12);
                            Integer q3 = queryResult.getInt(13);
                            Integer q4 = queryResult.getInt(14);

                            UserSession.setAllProgress(l1, l2, l3, l4,q1,q2,q3,q4);
                            UserSession.setUserInfo(fname, lname, uname, courseSection);
                            
                            root = FXMLLoader.load(getClass().getResource("/main/resources/view/dashboardStudent.fxml"));
                        }else{
                            UserSession.setUserInfo(fname, lname, uname, "Administrator");
                            root = FXMLLoader.load(getClass().getResource("/main/resources/view/dashboardAdmin.fxml"));
                        }
                        
                        Stage homeStage = new Stage();
                        homeStage.initStyle(StageStyle.UNDECORATED);
                        homeStage.setScene(new Scene(root, 1280, 720));
                        homeStage.show();
                        homeStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));
                    } else {
                            loginMessageLabel.setText("Invalid Login. Please Try Again");
                    }
                    queryResult.close();
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            } 
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void createAccountForm(ActionEvent event){
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/register.fxml"));
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
    
    public void changePassword(ActionEvent event){
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/forgotPassword.fxml"));
            Stage Stage = new Stage();
            Stage.initStyle(StageStyle.UNDECORATED);
            Stage.setScene(new Scene(root, 1280, 720));
            Stage.show();
            Stage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
