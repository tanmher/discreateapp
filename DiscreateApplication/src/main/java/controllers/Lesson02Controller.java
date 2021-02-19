/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

import java.io.File;
import static main.java.controllers.QuizController.finalScore;
import static main.java.controllers.QuizController.score;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
public class Lesson02Controller implements Initializable {

    @FXML private ScrollPane scrollPane;
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
    @FXML private AnchorPane content11;
    @FXML private AnchorPane quizReady;
    @FXML private AnchorPane quizContent;
    @FXML private AnchorPane quizScore;
    @FXML private ScrollPane quizScroll;
    @FXML private Text quizFinalScore;
    @FXML private Button btnNext;
    @FXML private Button btnBack;
    @FXML private ImageView L2_01;
    @FXML private ImageView L2_02;
    @FXML private ImageView L2_03;
    @FXML private ImageView L2_04;
    @FXML private ImageView L2_05;
    @FXML private ImageView L2_06;
    @FXML private ImageView L2_07;
    @FXML private ImageView L2_07_2;
    @FXML private ImageView L2_08;
    @FXML private ImageView L2_08_2;
    @FXML private Text warningLabel;
    @FXML private TextField ISBNNumber;
    @FXML private TextField totalNum;
    @FXML private CheckBox valid;
    @FXML private CheckBox notValid;
    @FXML private Text numberMultiplied;
    @FXML private Text totalNumber;
    @FXML private Text divideNum;
    @FXML private Text ISBNLabel;
    @FXML private Text validOrNot;
    @FXML private Text invalidISBN;
    
    @FXML private MediaView mv;
    @FXML private MediaPlayer player;
    @FXML private Media me;
    @FXML private ImageView playpause;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ImageView btn_stop;
    

    private Integer pageNum = 0;
    private final AnchorPane[] contents = new AnchorPane[14];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String L2_01File = "/main/resources/images/L2_01.jpg";
        Image L2_01Image = new Image(L2_01File);
        L2_01.setImage(L2_01Image);
        
        String L2_02File = "/main/resources/images/L2_02.jpg";
        Image L2_02Image = new Image(L2_02File);
        L2_02.setImage(L2_02Image);
        
        String L2_03File = "/main/resources/images/L2_03.jpg";
        Image L2_03Image = new Image(L2_03File);
        L2_03.setImage(L2_03Image);
        
        String L2_04File = "/main/resources/images/L2_04.jpg";
        Image L2_04Image = new Image(L2_04File);
        L2_04.setImage(L2_04Image);
        
        String L2_05File = "/main/resources/images/L2_05.jpg";
        Image L2_05Image = new Image(L2_05File);
        L2_05.setImage(L2_05Image);
        
        String L2_06File = "/main/resources/images/L2_06.jpg";
        Image L2_06Image = new Image(L2_06File);
        L2_06.setImage(L2_06Image);
        
        String L2_07File = "/main/resources/images/L2_07.jpg";
        Image L2_07Image = new Image(L2_07File);
        L2_07.setImage(L2_07Image);
        
        String L2_07_2File = "/main/resources/images/L2_07.jpg";
        Image L2_07_2Image = new Image(L2_07_2File);
        L2_07_2.setImage(L2_07_2Image);
        
        String L2_08File = "/main/resources/images/L2_08.jpg";
        Image L2_08Image = new Image(L2_08File);
        L2_08.setImage(L2_08Image);
        
        String L2_08_2File = "/main/resources/images/L2_08.jpg";
        Image L2_08_2Image = new Image(L2_08_2File);
        L2_08_2.setImage(L2_08_2Image);
        
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
        contents[10] = content11;
        contents[11] = quizReady;
        contents[12] = quizContent;
        contents[13] = quizScore;
        
        me = new Media(getClass().getResource("/main/resources/videos/lesson02-vid.mp4").toExternalForm());
	player = new MediaPlayer(me);
	mv.setFitHeight(200);
	mv.setFitWidth(400);
	mv.setMediaPlayer(player);
	player.setAutoPlay(false);
        
        Parent quiz = null;
        try {
            quiz = FXMLLoader.load(getClass().getResource("/main/resources/view/quiz02.fxml"));
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
    
    public void showSolution(MouseEvent event) {
        long userISBN =  Long.parseLong(ISBNNumber.getText()); 
        int lengthISBN = 1;
        int total = 0;
        long temp = 0;
        StringBuilder multiplied = new StringBuilder(""); 
        ArrayList<Long> numbersISBN = new ArrayList<>();
        
        temp = userISBN;
        while(temp >= 10) { temp = temp / 10; lengthISBN++; }
        
        if(lengthISBN == 10 || lengthISBN == 13){
            temp = userISBN;
            for(int i = 0; i < lengthISBN; i++){
                numbersISBN.add(temp % 10);
                temp = temp / 10;
            }

            for(int i = 1; i <= lengthISBN; i++) {
                int divisor = 1;
                if(i % 2 == 0 ) divisor = 3;
                else divisor = 1;
                temp = numbersISBN.get(i-1) * (divisor);
                multiplied.insert(0,"\n" + numbersISBN.get(i-1) + " x " + divisor + " = " + temp);          
                total += (int)temp;
            }
            invalidISBN.setText("");
            numberMultiplied.setText(multiplied.toString());
            totalNumber.setText("TOTAL = "+ total);
            divideNum.setText(total + " (mod10) = " + total%10);
            ISBNLabel.setText(String.valueOf(userISBN));

            if (total%10 == 0) validOrNot.setText("Valid");
            else validOrNot.setText("Not Valid");
        }else {
            invalidISBN.setText("Input 10 or 13 digits ISBN");
            numberMultiplied.setText("");
            totalNumber.setText("");
            divideNum.setText("");
            ISBNLabel.setText("");
            validOrNot.setText("");
        }

    }
    
    public void nextClick(MouseEvent event){
        btnNext.setText("Next");
        if(pageNum < 13){
            warningLabel.setText("");
            pageNum = pageNum + 1;     
            if(pageNum == 11){
                btnNext.setText("Take Quiz");
            }
            if(pageNum == 12){
                btnNext.setText("Submit");
            }
            if(pageNum == 13){
                finalScore = 0;
                for(int i : score){
                    finalScore += i;
                }
                if(finalScore == 0 ){
                    JOptionPane.showMessageDialog(null, "You don't have any answers, Please complete the test");
                    pageNum--;
                }else{
                    UserSession.setScoreQuiz02(finalScore);
                    quizFinalScore.setText(UserSession.getScoreQuiz02() + " / 5");
                    btnNext.setText("Back to Home"); 
                }
                Integer integer = Integer.parseInt(UserSession.getUserName());
                String username = UserSession.getUserName();
                String queryTotalUsers = "UPDATE `users` SET `quiz2`= '"+ finalScore +"' WHERE `username`='"+ username +"'";
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
            UserSession.setProgress(2, pageNum);
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
