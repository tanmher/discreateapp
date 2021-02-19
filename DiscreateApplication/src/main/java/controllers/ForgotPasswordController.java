package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
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
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import main.java.models.DatabaseConnection;

public class ForgotPasswordController implements Initializable {
    @FXML private TextField firstnameTextField;
    @FXML private TextField lastnameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Text warningLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    public void closeOnAction(ActionEvent event){
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    
    public void loginOnAction(ActionEvent event){
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/main/resources/view/login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage homeStage = new Stage();
        homeStage.initStyle(StageStyle.UNDECORATED);
        homeStage.setScene(new Scene(root, 1280, 720));
        homeStage.show();
        homeStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));
    }

    public void changePasswordOnAction(ActionEvent event) throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        
        String fname = firstnameTextField.getText();
        String lname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String newpass = newPasswordField.getText();
        String cpass = confirmPasswordField.getText();
        

        //create a select query to chek if username and pass exist in the database
        if(verifyFields()){
                String verify = "SELECT * FROM `users` WHERE `username`='" + username + "'";
                String updatePassword = "UPDATE `users` SET `password`= '"+ newpass +"' WHERE `username`='"+ username +"'";
                
                try {
                    PreparedStatement statement;
                    statement = connectDB.prepareStatement(verify);
                    ResultSet queryResult = statement.executeQuery();
                    
                    if(queryResult.next()){
                        if(queryResult.getString("firstname").equals(fname)){
                            if(queryResult.getString("lastname").equals(lname)){
                                if(newpass.equals(cpass))
                                {
                                    statement.executeUpdate(updatePassword);
                                    statement.executeQuery();
                                    JOptionPane.showMessageDialog(null, "Password Changed Successfully");
                                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    primaryStage.hide();
                                    Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/login.fxml"));
                                    Stage homeStage = new Stage();
                                    homeStage.initStyle(StageStyle.UNDECORATED);
                                    homeStage.setScene(new Scene(root, 1280, 720));
                                    homeStage.show();
                                    homeStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/images/Discreate.png")));

                                }else{
                                    warningLabel.setText("Password does not match");
                                }
                            }else{
                                warningLabel.setText("Invalid Last Name");
                            }                            
                        }else{
                            warningLabel.setText("Invalid First Name");
                        }
                    }else{
                        warningLabel.setText("Invalid Username");
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

    }
    
    public boolean verifyFields()
    {
        String fname = firstnameTextField.getText();
        String lname = lastnameTextField.getText();
        String uname = usernameTextField.getText();
        String pass1 = newPasswordField.getText();
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


}
