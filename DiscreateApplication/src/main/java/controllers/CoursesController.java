/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

import static main.java.controllers.HomeController.staticBorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.java.models.Progress;
import main.java.models.UserSession;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mher
 */
public class CoursesController implements Initializable {
    @FXML private ImageView brandImage;
    @FXML private ImageView iconLesson01;
    @FXML private ImageView iconLesson02;
    @FXML private ImageView iconLesson03;
    @FXML private ImageView iconLesson04;
    @FXML private GridPane grid;
    private final List<Progress> progressData = new ArrayList<>();
    
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
        grid.getChildren().removeAll();
        int row = 0;
        try {
            for(int i = 0; i < progressData.size(); i++){
                FXMLLoader fxml = new FXMLLoader();
                fxml.setLocation(getClass().getResource("/main/resources/view/progress-courses.fxml"));
                AnchorPane anchorpane = fxml.load();

                ProgressController progressController = fxml.getController();
                progressController.setData(progressData.get(i));
               
                grid.add(anchorpane,0, row++);
            }
            
            for(int i = 0; i < 4 - progressData.size(); i++){
                FXMLLoader fxml = new FXMLLoader();
                fxml.setLocation(getClass().getResource("/main/resources/view/progress-empty.fxml"));
                AnchorPane anchorpane = fxml.load();
                grid.add(anchorpane,0, row++);
            }
            grid.setVgap(10);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String userFile = "/main/resources/images/courses-img.png";
        Image userImage = new Image(userFile);
        brandImage.setImage(userImage);
        
        String icon01 = "/main/resources/images/icon-lesson1-blue.png";
        Image icon01Image = new Image(icon01);
        iconLesson01.setImage(icon01Image);

        String icon02 = "/main/resources/images/icon-lesson2-blue.png";
        Image icon02Image = new Image(icon02);
        iconLesson02.setImage(icon02Image);
                
        String icon03 = "/main/resources/images/icon-lesson3-blue.png";
        Image icon03Image = new Image(icon03);
        iconLesson03.setImage(icon03Image);
        
        String icon04 = "/main/resources/images/icon-lesson4-blue.png";
        Image icon04Image = new Image(icon04);
        iconLesson04.setImage(icon04Image);

        addProgress();
    }
    
    public void closeOnAction(ActionEvent event) throws IOException{
        UserSession.closeUpdate(event);
    }
    
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    
    @FXML public void Lesson01(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/lesson01");
    }
    
    @FXML public void Lesson02(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/lesson02");
    }
    
    @FXML public void Lesson03(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/lesson03");
    }

    @FXML public void Lesson04(MouseEvent mouseEvent) {
        loadUI("/main/resources/view/lesson04");
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
