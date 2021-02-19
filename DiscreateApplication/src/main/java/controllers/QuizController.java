/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Mher
 */
public class QuizController implements Initializable {

    @FXML private RadioButton question01_1;
    @FXML private RadioButton question01_2;
    @FXML private RadioButton question01_3;
    @FXML private RadioButton question01_4;
    @FXML private RadioButton question02_1;
    @FXML private RadioButton question02_2;
    @FXML private RadioButton question02_3;
    @FXML private RadioButton question02_4;
    @FXML private RadioButton question03_1;
    @FXML private RadioButton question03_2;
    @FXML private RadioButton question03_3;
    @FXML private RadioButton question03_4;
    @FXML private RadioButton question04_1;
    @FXML private RadioButton question04_2;
    @FXML private RadioButton question04_3;
    @FXML private RadioButton question04_4;
    @FXML private RadioButton question05_1;
    @FXML private RadioButton question05_2;
    @FXML private RadioButton question05_3;
    @FXML private RadioButton question05_4;

    public static Integer finalScore = 0;
    public static int[] score = new int[5];

    ToggleGroup toggleChoices01 = new ToggleGroup();
    ToggleGroup toggleChoices02 = new ToggleGroup();
    ToggleGroup toggleChoices03 = new ToggleGroup();
    ToggleGroup toggleChoices04 = new ToggleGroup();
    ToggleGroup toggleChoices05 = new ToggleGroup();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        score = new int[] {0,0,0,0,0};
        question01_1.setToggleGroup(toggleChoices01);
        question01_2.setToggleGroup(toggleChoices01);
        question01_3.setToggleGroup(toggleChoices01);
        question01_4.setToggleGroup(toggleChoices01);
        question02_1.setToggleGroup(toggleChoices02);
        question02_2.setToggleGroup(toggleChoices02);
        question02_3.setToggleGroup(toggleChoices02);
        question02_4.setToggleGroup(toggleChoices02);
        question03_1.setToggleGroup(toggleChoices03);
        question03_2.setToggleGroup(toggleChoices03);
        question03_3.setToggleGroup(toggleChoices03);
        question03_4.setToggleGroup(toggleChoices03);
        question04_1.setToggleGroup(toggleChoices04);
        question04_2.setToggleGroup(toggleChoices04);
        question04_3.setToggleGroup(toggleChoices04);
        question04_4.setToggleGroup(toggleChoices04);
        question05_1.setToggleGroup(toggleChoices05);
        question05_2.setToggleGroup(toggleChoices05);
        question05_3.setToggleGroup(toggleChoices05);
        question05_4.setToggleGroup(toggleChoices05);
        
        toggleChoices01.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
            @Override
            public void changed(ObservableValue<? extends Toggle> ob,Toggle o, Toggle n) 
            {
                if (toggleChoices01.getSelectedToggle() != null) {
                    if (question01_1.isSelected()) { 
                        score[0] = 1;
                    }else{
                        score[0] = 0;
                    }
                    
                }
            } 
        });
        toggleChoices02.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
            public void changed(ObservableValue<? extends Toggle> ob,Toggle o, Toggle n) 
            { 
                if (toggleChoices02.getSelectedToggle() != null ) {
                    if (question02_1.isSelected()) { 
                        score[1] = 1;
                    } 
                }
            } 
        });
        toggleChoices03.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
            public void changed(ObservableValue<? extends Toggle> ob,Toggle o, Toggle n) 
            { 
                if (toggleChoices03.getSelectedToggle() != null ) {
                    if (question03_1.isSelected()) { 
                        score[2] = 1;
                    } 
                }
            } 
        });
        toggleChoices04.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
            public void changed(ObservableValue<? extends Toggle> ob,Toggle o, Toggle n) 
            { 
                if (toggleChoices04.getSelectedToggle() != null ) {
                    if (question04_1.isSelected()) { 
                        score[3] = 1;
                    } 
                }
            } 
        });
        toggleChoices05.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
            public void changed(ObservableValue<? extends Toggle> ob,Toggle o, Toggle n) 
            { 
                if (toggleChoices05.getSelectedToggle() != null ) {
                    if (question05_1.isSelected()) { 
                        score[4] = 1;
                    }
                }
            } 
        });
    }
    
}
