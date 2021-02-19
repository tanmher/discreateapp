/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import main.java.models.DatabaseConnection;
import main.java.models.UserSession;
import main.java.models.Users;

/**
 *
 * @author Mher
 */

public class AccountsAdminController implements Initializable{
    @FXML private TableView<Users> usersTable;
    @FXML private TableColumn<Users, String> username;
    @FXML private TableColumn<Users, String> firstname;
    @FXML private TableColumn<Users, String> lastname;
    @FXML private TableColumn<Users, String> courseSection;
    @FXML private TableColumn<Users, Integer> a1;
    @FXML private TableColumn<Users, Integer> a2;
    @FXML private TableColumn<Users, Integer> a3;
    @FXML private TableColumn<Users, Integer> a4;
    @FXML private TableColumn<Users, Integer> q1;
    @FXML private TableColumn<Users, Integer> q2;
    @FXML private TableColumn<Users, Integer> q3;
    @FXML private TableColumn<Users, Integer> q4;
    @FXML private TextField usernameTextField;
    @FXML private TextField firstnameTextField;
    @FXML private TextField lastnameTextField;
    @FXML private TextField courseSectionTextField;
    @FXML private Text warningLabel;
   
    private String unameSelected;
    ObservableList<Users> usersList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        updateTable();
    }
    
    public void getUsers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
            try{
                PreparedStatement statement;
                ResultSet queryResult;
                statement = connectDB.prepareStatement("SELECT * FROM users");
                queryResult = statement.executeQuery();
                
                while(queryResult.next()){
                    unameSelected = queryResult.getString("username");
                    usersList.add(new Users(
                        queryResult.getString("username"),
                        queryResult.getString("firstname"),
                        queryResult.getString("lastname"),
                        queryResult.getString("course_section"),
                        queryResult.getInt("lesson1"),
                        queryResult.getInt("lesson2"),
                        queryResult.getInt("lesson3"),
                        queryResult.getInt("lesson4"),
                        queryResult.getInt("quiz1"),
                        queryResult.getInt("quiz2"),
                        queryResult.getInt("quiz3"),
                        queryResult.getInt("quiz4")                          
                    ));
                }
            } catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
    }
    
    @FXML void getSelected (MouseEvent event){
    int index = usersTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        unameSelected = username.getCellData(index).toString();
    }

    @FXML public void updateTable () {
        usersList.clear();
        getUsers();
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        courseSection.setCellValueFactory(new PropertyValueFactory<>("courseSection"));
        a1.setCellValueFactory(new PropertyValueFactory<>("a1"));
        a2.setCellValueFactory(new PropertyValueFactory<>("a2"));
        a3.setCellValueFactory(new PropertyValueFactory<>("a3"));
        a4.setCellValueFactory(new PropertyValueFactory<>("a4"));
        q1.setCellValueFactory(new PropertyValueFactory<>("q1"));
        q2.setCellValueFactory(new PropertyValueFactory<>("q2"));
        q3.setCellValueFactory(new PropertyValueFactory<>("q3"));
        q4.setCellValueFactory(new PropertyValueFactory<>("q4"));

        usersTable.setItems(usersList);
    }
    
    
    @FXML public void deleteUser(MouseEvent event) {
        if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String delete = "DELETE from users WHERE username = ? ";
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
                try{
                    PreparedStatement statement;
                    statement = connectDB.prepareStatement(delete);
                    statement.setString(1, unameSelected);
                    statement.execute();
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Account Deleted");
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                    e.printStackTrace();
                    e.getCause();
            }
        }
    }
    
    public void editUser (MouseEvent event){
        String value1 = usernameTextField.getText();
        String value2 = firstnameTextField.getText();
        String value3 = lastnameTextField.getText();
        String value4 = courseSectionTextField.getText();
        
        if(value1 == "" && value2 == "" && value3 == "" && value4 == ""){
            try {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();
                
                String edit = "UPDATE users set username= '"+value1+"',firstname= '"+value2+"',lastname= '"+
                        value3+"',course_section= '"+value4+"'WHERE username='"+ unameSelected +"'";
                PreparedStatement statement;
                statement = connectDB.prepareStatement(edit);
                statement.execute();
                JOptionPane.showMessageDialog(null, "Account Updated");
                updateTable();
                usernameTextField.clear();
                firstnameTextField.clear();
                lastnameTextField.clear();
                courseSectionTextField.clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            warningLabel.setText("Enter user details");
        }
    }
    
    public void closeOnAction(ActionEvent event) throws IOException{
        UserSession.closeUpdate(event);
    }
    
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
}
   

