package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import main.java.models.DatabaseConnection;
import main.java.models.UserSession;

public class HomeAdminController implements Initializable {
    @FXML private BorderPane borderPane;
    @FXML private AnchorPane homeScreen;
    @FXML private Label initialsName;
    @FXML private Label studentName;
    @FXML private Label studentCourse;
    @FXML private Label numberOfUsers;
    @FXML private Label numberOfCompleted;
    @FXML private Label numberOfInProgress;
    @FXML private BarChart<?, ?> chart01;
    @FXML private BarChart<?, ?> chart02;
    
    private static Integer totalUsers = 0;
    private static Integer completedLesson01 = 0;
    private static Integer completedLesson02 = 0;
    private static Integer completedLesson03 = 0;
    private static Integer completedLesson04 = 0;
    private static Integer totalCompleters = 0;
    
    private static Integer progressLesson01 = 0;
    private static Integer progressLesson02 = 0;
    private static Integer progressLesson03 = 0;
    private static Integer progressLesson04 = 0;
    private static Integer totalInProgress = 0;
    private static Integer averageLesson01 = 0;
    private static Integer averageLesson02 = 0;
    private static Integer averageLesson03 = 0;
    private static Integer averageLesson04 = 0;
    
    public static BorderPane staticBorderPane; 
    private static double xOffset = 0;
    private static double yOffset = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        getData();
        
        staticBorderPane = borderPane;
        
        XYChart.Series set1 = new XYChart.Series<>();
        set1.setName("Completed");
        set1.getData().add(new XYChart.Data("A1", completedLesson01));
        set1.getData().add(new XYChart.Data("A2", completedLesson02));
        set1.getData().add(new XYChart.Data("A3", completedLesson03));
        set1.getData().add(new XYChart.Data("A4", completedLesson04));
        
        XYChart.Series set2 = new XYChart.Series<>();
        set2.setName("Progress");
        set2.getData().add(new XYChart.Data("A1", progressLesson01));
        set2.getData().add(new XYChart.Data("A2", progressLesson02));
        set2.getData().add(new XYChart.Data("A3", progressLesson03));
        set2.getData().add(new XYChart.Data("A4", progressLesson04));
        
        chart01.getData().addAll(set1,set2);
        
        XYChart.Series set3 = new XYChart.Series<>();
        set3.setName("Score");
        set3.getData().add(new XYChart.Data("A1", averageLesson01));
        set3.getData().add(new XYChart.Data("A2", averageLesson02));
        set3.getData().add(new XYChart.Data("A3", averageLesson03));
        set3.getData().add(new XYChart.Data("A4", averageLesson04));
        
        chart02.getData().addAll(set3);
        
        staticBorderPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        staticBorderPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }
    
    public void getData() {
        String queryData = "SELECT " +
            "COUNT(*), " +
            "SUM(CASE WHEN lesson1 LIKE 100 THEN 1 ELSE 0 END) AS `clesson1`," +
            "SUM(CASE WHEN lesson2 LIKE 100 THEN 1 ELSE 0 END) AS `clesson2`," +
            "SUM(CASE WHEN lesson3 LIKE 100 THEN 1 ELSE 0 END) AS `clesson3`," +
            "SUM(CASE WHEN lesson4 LIKE 100 THEN 1 ELSE 0 END) AS `clesson4`," +
            "SUM(CASE WHEN lesson1 BETWEEN 1 AND 99 THEN 1 ELSE 0 END) AS `plesson1`," +
            "SUM(CASE WHEN lesson2 BETWEEN 1 AND 99 THEN 1 ELSE 0 END) AS `plesson2`," +
            "SUM(CASE WHEN lesson3 BETWEEN 1 AND 99 THEN 1 ELSE 0 END) AS `plesson3`," +
            "SUM(CASE WHEN lesson4 BETWEEN 1 AND 99 THEN 1 ELSE 0 END) AS `plesson4`," +
            "AVG(quiz1) as quiz1," +
            "AVG(quiz2) as quiz2," +
            "AVG(quiz3) as quiz3," +
            "AVG(quiz4) as quiz4 " +
            "from users";
        
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
            try{
                PreparedStatement statement;
                ResultSet queryResult;
                statement = connectDB.prepareStatement(queryData);
                queryResult = statement.executeQuery(); 
                if(queryResult.next()){
                    totalUsers = queryResult.getInt(1);
                    completedLesson01 = queryResult.getInt(2);
                    completedLesson02 = queryResult.getInt(3);
                    completedLesson03 = queryResult.getInt(4);
                    completedLesson04 = queryResult.getInt(5);
                    totalCompleters = completedLesson01 + completedLesson02 + completedLesson03 +completedLesson04;
                    progressLesson01 = queryResult.getInt(6);
                    progressLesson02 = queryResult.getInt(7);
                    progressLesson03 = queryResult.getInt(8);
                    progressLesson04 = queryResult.getInt(9);
                    totalInProgress = progressLesson01 + progressLesson02 + progressLesson03 + progressLesson04;
                    averageLesson01 = queryResult.getInt(10);
                    averageLesson02 = queryResult.getInt(11);
                    averageLesson03 = queryResult.getInt(12);
                    averageLesson04 = queryResult.getInt(13);
                    
                }
                queryResult.close();
                statement.close();
            } catch(Exception e){
                e.printStackTrace();
                e.getCause();
            } 
        String fullName = UserSession.getFullName();
        String course = UserSession.getCourseSection();
        String initials = UserSession.getInitials();
        Integer numberUsers = totalUsers;
        Integer numberCompleted = totalCompleters;
        Integer numberProgress = totalInProgress;
        studentName.setText(fullName);
        studentCourse.setText(course);
        initialsName.setText(initials);
        numberOfUsers.setText(String.valueOf(numberUsers));
        numberOfCompleted.setText(String.valueOf(numberCompleted));
        numberOfInProgress.setText(String.valueOf(numberProgress));
    }

    public void closeOnAction(ActionEvent event) throws IOException{
        UserSession.closeUpdate(event);
    }
    
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }  
   
    @FXML public void dashboard(MouseEvent event) {
        getData();
        staticBorderPane.setCenter(homeScreen);
    }
   
    @FXML public void accounts(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/accountsAdmin");
    }
    @FXML public void settings(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/settingsAdmin");
    }
    
    public void loadUI(String ui){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource( ui + ".fxml"));

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        staticBorderPane.setCenter(root);
    }
}
