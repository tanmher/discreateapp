/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the ripeditor.
 */
package main.java.controllers;

import static main.java.controllers.QuizController.score;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.java.models.UserSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static main.java.controllers.QuizController.finalScore;
import main.java.models.DatabaseConnection;


/**
 * FXML Controller class
 *
 * @author Mher
 */
public class Lesson03Controller implements Initializable {

    @FXML private AnchorPane content01;
    @FXML private AnchorPane content02;
    @FXML private AnchorPane content03;
    @FXML private AnchorPane content04;
    @FXML private AnchorPane content05;
    @FXML private AnchorPane content06;
    @FXML private AnchorPane content07;
    @FXML private AnchorPane quizReady;
    @FXML private AnchorPane quizContent;
    @FXML private AnchorPane quizScore;
    @FXML private ScrollPane quizScroll;
    @FXML private Text quizFinalScore;
    @FXML private Button btnNext;
    @FXML private Button btnBack;
    @FXML private ImageView L3_01;
    @FXML private ImageView L3_02;
    @FXML private ImageView L3_03;
    @FXML private ImageView L3_05;
    @FXML private ImageView L3_06;
    @FXML private Text warningLabel;
    
    @FXML private MediaView mv;
    @FXML private MediaPlayer player;
    @FXML private Media me;
    @FXML private ImageView playpause;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ImageView btn_stop;

    private Integer pageNum = 0;
    private final AnchorPane[] contents = new AnchorPane[10];
    
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
        contents[7] = quizReady;
        contents[8] = quizContent;
        contents[9] = quizScore;
        
        me = new Media(getClass().getResource("/main/resources/videos/lesson03-vid.mp4").toExternalForm());
	player = new MediaPlayer(me);
	mv.setFitHeight(200);
	mv.setFitWidth(400);
	mv.setMediaPlayer(player);
	player.setAutoPlay(false);
        
        Parent quiz = null;
        try {
            quiz = FXMLLoader.load(getClass().getResource("/main/resources/view/quiz03.fxml"));
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
        
        if(pageNum < 9){
            btnNext.setText("Next");
            warningLabel.setText("");
            pageNum = pageNum + 1;
            
            if(pageNum == 7){
                btnNext.setText("Take Quiz");
            }
            if(pageNum == 8){
                btnNext.setText("Submit");
            }
            if(pageNum == 9){
                finalScore = 0;
                for(int i : score){
                    finalScore += i;
                }
                if(finalScore == 0 ){
                    JOptionPane.showMessageDialog(null, "You don't have any answers, Please complete the test");
                    pageNum--;
                }else{
                    UserSession.setScoreQuiz03(finalScore);
                    quizFinalScore.setText(UserSession.getScoreQuiz03() + " / 5");
                    btnNext.setText("Back to Home"); 
                }
                String username = UserSession.getUserName();
                String queryTotalUsers = "UPDATE `users` SET `quiz3`= '"+ finalScore +"' WHERE `username`='"+ username +"'";
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
            UserSession.setProgress(3, pageNum);
        }else warningLabel.setText("This is the Last Page");
    }
    
    public void backClick(MouseEvent event){
        if(pageNum > 0){
            warningLabel.setText("");
            pageNum = pageNum - 1;
            contents[pageNum].toFront();
        }else warningLabel.setText("This is the First Page");
    }

}
