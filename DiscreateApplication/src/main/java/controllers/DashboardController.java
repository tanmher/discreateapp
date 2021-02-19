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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.java.models.Progress;
import main.java.models.UserSession;


public class DashboardController implements Initializable {
    @FXML private BorderPane borderPane;
    @FXML private AnchorPane homeScreen;
    @FXML private Label initialsName;
    @FXML private Label studentName;
    @FXML private Label studentCourse;
    @FXML private Label coursesCompleted;
    @FXML private Label coursesInProgress;
    @FXML private Label averageQuizScore;
    @FXML private GridPane grid;
    
    public static BorderPane staticBorderPane; 
    private final List<Progress> progressData = new ArrayList<>();
    private static double xOffset = 0;
    private static double yOffset = 0;
    
    private List<Progress> getData() {
        List<Progress> progresses = new ArrayList<>();
        Progress progress;
        
        if(UserSession.getProgressLesson1() > 0) {
            progress = new Progress();
            progress.setCode("A1");
            progress.setName("Modular Arithmetic");
            progress.setProgress(String.valueOf(UserSession.getProgressLesson1()+ " %"));
            progress.setCourse("/main/resources/view/lesson01.fxml");
            
            progresses.add(progress);
        }
        if(UserSession.getProgressLesson2() > 0) {
            progress = new Progress();
            progress.setCode("A2");
            progress.setName("Application of \nModular Arithmetic");
            progress.setProgress(String.valueOf(UserSession.getProgressLesson2()+ " %"));
            progress.setCourse("/main/resources/view/lesson02.fxml");

            progresses.add(progress);
        }
        if(UserSession.getProgressLesson3() > 0) {
            progress = new Progress();
            progress.setCode("A3");
            progress.setName("Primes");
            progress.setProgress(String.valueOf(UserSession.getProgressLesson3()+ " %"));
            progress.setCourse("/main/resources/view/lesson03.fxml");

            progresses.add(progress);
        }
        if(UserSession.getProgressLesson4() > 0) {
            progress = new Progress();
            progress.setCode("A4");
            progress.setName("Application of \nPrimes");
            progress.setProgress(String.valueOf(UserSession.getProgressLesson4()+ " %"));
            progress.setCourse("/main/resources/view/lesson04.fxml");

            progresses.add(progress);
        }
        return progresses;
    }
    
    public void addProgress(){
        grid.getChildren().clear();
        progressData.clear();
        progressData.addAll(getData());
        
        Integer completed = UserSession.getCompletedCourses();
        Integer inProgress = UserSession.getInProgress();
        Integer average = UserSession.getAverageQuizScore();
        averageQuizScore.setText(String.valueOf(average));
        
        coursesInProgress.setText(String.valueOf(inProgress));
        coursesCompleted.setText(String.valueOf(completed));
        averageQuizScore.setText(String.valueOf(average));
        
        int row = 0;
        try {
            int progressSize = progressData.size();
            if(progressSize == 4) progressSize--;
            for(int i = 0; i < progressSize; i++){
                FXMLLoader fxml = new FXMLLoader();
                fxml.setLocation(getClass().getResource("/main/resources/view/progress-courses.fxml"));
                AnchorPane anchorpane = fxml.load();

                ProgressController progressController = fxml.getController();
                progressController.setData(progressData.get(i));
                
                grid.add(anchorpane,0, row++);
            }
            for(int i = 0; i < 3 - progressData.size(); i++){
                FXMLLoader fxml = new FXMLLoader();
                fxml.setLocation(getClass().getResource("/main/resources/view/progress-empty.fxml"));
                AnchorPane anchorpane = fxml.load();
                grid.add(anchorpane,0, row++);
            }
            grid.setVgap(5);

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        String fullName = UserSession.getFullName();
        String course = UserSession.getCourseSection();
        String initials = UserSession.getInitials();
        studentName.setText(fullName);
        studentCourse.setText(course);
        initialsName.setText(initials);
        
        staticBorderPane = borderPane;
        addProgress();
        draggable();
    }
    
    public static void draggable(){
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
    
    public void closeOnAction(ActionEvent event) throws IOException{
        UserSession.closeUpdate(event);
    }
    
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    
   
    @FXML public void home(MouseEvent event) {
        addProgress();
        staticBorderPane.setCenter(homeScreen);
    }
   
    @FXML public void courses(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/courses");
    }
    @FXML public void about(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/calculators");
    }
    @FXML public void settings(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/settings");
    }
    @FXML public void Lesson01(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/lesson01");
    }
    @FXML public void Lesson02(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/lesson02");
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
