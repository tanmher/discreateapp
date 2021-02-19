package main.java.controllers;

import main.java.models.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javax.swing.JOptionPane;
import main.java.models.UserSession;

public class RegisterController implements Initializable {
    @FXML private TextField firstnameTextField;
    @FXML private TextField lastnameTextField;
    @FXML private TextField courseTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField setPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label warningLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    public void closeOnAction(ActionEvent event){
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
    }
    
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    
    public void loginOnAction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/login.fxml"));
        Stage loginStage = new Stage();
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setScene(new Scene(root, 1280, 720));
        loginStage.show();
        loginStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));
    }
    
    public void registerOnAction(ActionEvent event) throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
        String username = usernameTextField.getText();

        //create a select query to chek if username and pass exist in the database
        if(verifyFields()){
            if(!checkUsername(username))
            {
                String registerUserQuery = "INSERT INTO `users`(`firstname`, `lastname`, `username`, `password`, `course_section`) "
                        + "VALUES ("
                        + "'" + firstnameTextField.getText() + "',"
                        + "'" + lastnameTextField.getText() + "',"
                        + "'" + usernameTextField.getText() + "',"
                        + "'" + setPasswordField.getText() + "',"
                        + "'" + courseTextField.getText() + "')";
                try {
                    PreparedStatement statement;
                    statement = connectDB.prepareStatement(registerUserQuery);
                    
                    
                    if(statement.executeUpdate() != 0)
                    {   
                        JOptionPane.showMessageDialog(null, "Your Account Has Been Successfully Created");
                        // show a new form
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.hide();
                        
                        String fname = firstnameTextField.getText();
                        String lname = lastnameTextField.getText();
                        String uname = usernameTextField.getText();
                        String courseSection = courseTextField.getText();
                        UserSession.setUserInfo(fname, lname, uname, courseSection);
                        
                        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/dashboardStudent.fxml"));
                        Stage homeStage = new Stage();

                        homeStage.initStyle(StageStyle.UNDECORATED);
                        homeStage.setScene(new Scene(root, 1280, 720));
                        homeStage.show();
                        homeStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));
                        // close the current form(login form)
                        //this.close();

                    }else{
                        // error message
                        JOptionPane.showMessageDialog(null, "Error: Check Your Information");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }
    
    public boolean verifyFields()
    {
        String fname = firstnameTextField.getText();
        String lname = lastnameTextField.getText();
        String uname = usernameTextField.getText();
        String pass1 = setPasswordField.getText();
        String pass2 = confirmPasswordField.getText();
        
        int letterCount = 0;
        int numberCount = 0;
        int letterCountP = 0;
        int numberCountP = 0;

        /*
         * Loop through the password and count all the letters and characters
         */
        for (int i = 0; i < uname.length(); i++) {
            char c = uname.charAt(i);

            if (Character.toString(c).matches("[a-zA-Z]")) {
                letterCount++;
            }

            if (Character.toString(c).matches("[0-9]")) {
                numberCount++;
            }
        }
        for (int i = 0; i < pass1.length(); i++) {
            char c = pass1.charAt(i);

            if (Character.toString(c).matches("[a-zA-Z]")) {
                letterCountP++;
            }

            if (Character.toString(c).matches("[0-9]")) {
                numberCountP++;
            }
        }
        
        // check empty fields
        if(fname.trim().equals("") || lname.trim().equals("") || uname.trim().equals("")
           || pass1.trim().equals("") || pass2.trim().equals(""))
        {
            warningLabel.setText("One Or More Fields Are Empty");
            return false;
        }
        
        else if(uname.length() < 8 || pass1.length() < 8){
            warningLabel.setText("Username and Password should atleast 8 characters long,\n must have atleast of 6 letters,and 2 numbers, no symbols");
            return false;       
        }
        else if(letterCount < 6 || numberCount < 2){
            warningLabel.setText("Username should atleast 8 characters long, must have atleast of 6 letters,\n and 2 numbers, no symbols");
            return false;
        }
        else if(letterCountP < 6 || numberCountP < 2){
            warningLabel.setText("Username should atleast 8 characters long, must have atleast of 6 letters,\n and 2 numbers, no symbols");
            return false;
        }
        // check if the two password are equals or not
        else if(!pass1.equals(pass2))
        {
           warningLabel.setText("Password does not match"); 
           return false;
        }
        
        // if everything is ok
        else{
            return true;
        }
    }
    
    public boolean checkUsername(String username)
    {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
        boolean username_exist = false;
        
        String query = "SELECT * FROM `users` WHERE `username` = '" + usernameTextField.getText() + "'";
        
       
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            
            if(queryResult.next())
            {
                username_exist = true;
                warningLabel.setText("This Username is Already Taken");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username_exist;
    }


}
