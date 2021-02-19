/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.models.UserSession;
import static main.java.controllers.QuizController.finalScore;
import static main.java.controllers.QuizController.score;
import javax.swing.JOptionPane;
import main.java.models.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Mher
 */
public class Lesson01Controller implements Initializable {

    @FXML private AnchorPane content01;
    @FXML private AnchorPane content02;
    @FXML private AnchorPane content03;
    @FXML private AnchorPane content04;
    @FXML private AnchorPane content05;
    @FXML private AnchorPane content06;
    @FXML private AnchorPane content07;
    @FXML private AnchorPane content08;
    @FXML private AnchorPane content09;
    @FXML private AnchorPane content10;
    @FXML private AnchorPane quizReady;
    @FXML private AnchorPane quizContent;
    @FXML private AnchorPane quizScore;
    @FXML private ScrollPane quizScroll;
    @FXML private Button btnNext;
    @FXML private Text warningLabel;
    @FXML private Text quizFinalScore;
    @FXML private MediaView mv;
    @FXML private MediaPlayer player;
    @FXML private Media me;
    @FXML private ImageView playpause;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ImageView btn_stop;

    private Integer pageNum = 0;
    private AnchorPane[] contents = new AnchorPane[13];
    ToggleGroup toggleChoices01 = new ToggleGroup();
    ToggleGroup toggleChoices02 = new ToggleGroup();
    ToggleGroup toggleChoices03 = new ToggleGroup();
    ToggleGroup toggleChoices04 = new ToggleGroup();
    ToggleGroup toggleChoices05 = new ToggleGroup();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        content01.toFront();
        contents[0] = content01;
        contents[1] = content02;
        contents[2] = content03;
        contents[3] = content04;
        contents[4] = content05;
        contents[5] = content06;
        contents[6] = content07;
        contents[7] = content08;
        contents[8] = content09;
        contents[9] = content10;
        contents[10] = quizReady;
        contents[11] = quizContent;
        contents[12] = quizScore;

	me = new Media(getClass().getResource("/main/resources/videos/lesson01-vid.mp4").toExternalForm());
	player = new MediaPlayer(me);
	mv.setFitHeight(200);
	mv.setFitWidth(400);
	mv.setMediaPlayer(player);
	player.setAutoPlay(false);
       
//        String userFile = "/main/resources/images/courses-img.png";
//        Image userImage = new Image(userFile);
//        brandImage.setImage(userImage);
        
        Parent quiz = null;
        try {
            quiz = FXMLLoader.load(getClass().getResource("/main/resources/view/quiz01.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Lesson01Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        quizScroll.setContent(quiz);
    }
    
    @FXML private void onClick_btn_pause(){
        if(player.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            player.pause();
	}
	else {
            player.play();
	}
    }
    @FXML private void onClick_btn_stop(){
	player.stop();
    }
    
    @FXML private void btplayChange1(MouseEvent event) {
	if (player.getStatus().equals(MediaPlayer.Status.PLAYING)) {
	    playpause.setImage(new Image("/main/resources/images/pause_circle.png"));
	} else {
	    playpause.setImage(new Image("/main/resources/images/play_circle.png"));
	}
    }
	
    @FXML private void btplayChange2(MouseEvent event) {
	if (player.getStatus().equals(MediaPlayer.Status.PLAYING)) {
	    playpause.setImage(new Image("/main/resources/images/pause-button.png"));
	} else {
	    playpause.setImage(new Image("/main/resources/images/play-button.png"));
	}
    }
    
    //hover
    @FXML private void btstopChange1(MouseEvent event) {
	btn_stop.setImage(new Image("/main/resources/images/stop-button.png"));
    }
    @FXML private void btstopChange2(MouseEvent event) {
	btn_stop.setImage(new Image("/main/resources/images/stop-circle.png"));
    }
    
    public void closeOnAction(ActionEvent event) throws IOException{
        UserSession.closeUpdate(event);
    }
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    public void nextClick(MouseEvent event){
        
        if(pageNum < 12){
            btnNext.setText("Next");
            warningLabel.setText("");
            pageNum = pageNum + 1;
            
            if(pageNum == 10){
                btnNext.setText("Take Quiz");
            }
            if(pageNum == 11){
                btnNext.setText("Submit");
            }
            if(pageNum == 12){ 
                finalScore = 0;
                for(int i : score){
                    finalScore += i;
                }
                if(finalScore == 0 ){
                    JOptionPane.showMessageDialog(null, "You don't have any answers, Please complete the test");
                    pageNum--;
                }else{
                    UserSession.setScoreQuiz01(finalScore);
                    quizFinalScore.setText(UserSession.getScoreQuiz01() + " / 5");
                    btnNext.setText("Back to Home"); 
                }
                String username = UserSession.getUserName();

                String queryTotalUsers = "UPDATE `users` SET `quiz1`= '"+ finalScore +"' WHERE `username`='"+ username +"'";
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();
                    try{
                        PreparedStatement statement;
                        statement = connectDB.prepareStatement(queryTotalUsers);
                        statement.executeUpdate(queryTotalUsers);

                    } catch(Exception e){
                        e.printStackTrace();
                        e.getCause();
                }
            }
            contents[pageNum].toFront();
            UserSession.setProgress(1, pageNum);
        }else warningLabel.setText("This is the Last Page");
    }
    
    public void backClick(MouseEvent event){
        if(pageNum > 0){
            warningLabel.setText("");
            pageNum = pageNum - 1;
            if(pageNum == 10){
                btnNext.setText("Take Quiz");
            }
            if(pageNum == 11){
                btnNext.setText("Submit");
            }
            if(pageNum == 12){
                btnNext.setText("Back to Home");
            }
            contents[pageNum].toFront();
        }else warningLabel.setText("This is the First Page");
    }
    
    
}





    