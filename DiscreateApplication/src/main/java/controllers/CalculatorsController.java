/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.models.UserSession;

/**
 *
 * @author Mher
 */
public class CalculatorsController implements Initializable {
    @FXML private AnchorPane calculators;
    @FXML private AnchorPane calculator01;
    @FXML private AnchorPane calculator02;
    @FXML private AnchorPane calculator03;
    @FXML private AnchorPane calculator04;
    @FXML private AnchorPane calculator05;
    @FXML private AnchorPane calculator06;
    @FXML private AnchorPane calculator07;
    @FXML private AnchorPane calculator08;
    
    @FXML private TextField numA;
    @FXML private TextField numB;
    @FXML private TextField numN;
    @FXML private TextField numN1;
    @FXML private TextField checkprime;

    @FXML private TextField num1;            
    @FXML private TextField num2;
    
    @FXML private TextField numA1;
    @FXML private TextField numB1;
    @FXML private TextField numN2;
    
    @FXML private TextField numA2;
    @FXML private TextField numB2;
    @FXML private TextField numN3;
    @FXML private TextField exponent;
    
    @FXML private TextField encrypt;
    @FXML private TextField key;
    @FXML private Text encrypted; 
    
    @FXML private TextField firstInteger;
    @FXML private TextField secondInteger;
    @FXML private Text solution01;
    @FXML private Text solution03;
    @FXML private Text solution04;
    @FXML private Text solution05;
    @FXML private Text solution06;
    @FXML private Text solution07;
    @FXML private Text solution08;
    
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
    
    
    private AnchorPane[] contents = new AnchorPane[13];
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
         
        calculators.toFront();
        contents[0] = calculators;
        contents[1] = calculator01;
        contents[2] = calculator02;
        contents[3] = calculator03;
        contents[4] = calculator04;
        contents[5] = calculator05;
        contents[6] = calculator06;
        contents[7] = calculator07;
        contents[8] = calculator08;
    }
    
    public void closeOnAction(ActionEvent event) throws IOException{
        UserSession.closeUpdate(event);
    }
    public void minimizeOnAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }
    @FXML public void backButton(MouseEvent mouseEvent) {
        contents[0].toFront();
    }
    
    @FXML public void Calculator01(MouseEvent mouseEvent) {
        contents[1].toFront();
    }
    
    @FXML public void Calculator02(MouseEvent mouseEvent) {
        contents[2].toFront();
    }
    
    @FXML public void Calculator03(MouseEvent mouseEvent) {
        contents[3].toFront();
    }
    @FXML public void Calculator04(MouseEvent mouseEvent) {
        contents[4].toFront();
    }
    
    @FXML public void Calculator05(MouseEvent mouseEvent) {
        contents[5].toFront();
    }
    
    @FXML public void Calculator06(MouseEvent mouseEvent) {
        contents[6].toFront();
    }
    
    @FXML public void Calculator07(MouseEvent mouseEvent) {
        contents[7].toFront();
    }
    
    @FXML public void Calculator08(MouseEvent mouseEvent) {
        contents[8].toFront();
    }
     
    public void showSolution01(MouseEvent event) {
        int fInteger = Integer.parseInt(firstInteger.getText());
        int sInteger = Integer.parseInt(secondInteger.getText());
        int q = fInteger / sInteger;
        int r = fInteger % sInteger;
        
        solution01.setText("a = qb + r \n" 
                + fInteger + " = " + q + " x " + sInteger + " + " + r + "\n"
                + "q = " + q + ", r = " + r); 
    }
    
    public void showSolution02(MouseEvent event) {
        long userISBN =  Long.parseLong(ISBNNumber.getText()); 
        int lengthISBN = 1;
        int total = 0;
        long temp = 0;
        StringBuilder multiplied = new StringBuilder(); 
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
    
    public void showSolution03(MouseEvent event) {
            double num1 = Double.parseDouble(numA.getText());
            double num2 = Double.parseDouble(numB.getText());
            double num3 = Double.parseDouble(numN.getText());
            double num4 = num1-num2;
            double num5 = num4%num3;
             solution03.setText( num1 + "-" + num2 + "=" + "\n" 
                     + num4 + "mod" + num3 + "=" + num5 );          
    }
    
    public void showSolution04(MouseEvent event) {
         int ctr=0;
         
            double num1 = Double.parseDouble(numN1.getText());
            for(int i=1;i<=num1;++i){
                if(num1==0)
                solution04.setText("The number is 0");
                else
                if(num1%i==0){
                    ctr++;
            }
                if(ctr==2)
            solution04.setText("The number of factors: " + ctr + 
                    "\nthus the factor is" + "Prime");
                else if(ctr==1||ctr==-1)
            solution04.setText("The number of factors: " + ctr + " "
                    + "\nthus the factor is" + "is either -1, or 1");
                else
            solution04.setText("The number of factors: " + ctr + 
                    " \nthus the factor is" + "Not Prime");
            }
    }

    public void showSolution05(MouseEvent event) {
         int greatest = 0;
       
            double first = Double.parseDouble(num1.getText());
            double second = Double.parseDouble(num2.getText());
            for(int i=1;i<=first && i<=second;++i){
                if(first%i==0 && second%i==0)
                    greatest = i;
            }
            solution05.setText("The GCD of " + first + " and " + second + " are : " + greatest);
         }
         
    public void showSolution06(MouseEvent event) {
          double congruence,i=1,ctr=0;
            double num1 = Double.parseDouble(numA1.getText());
            double num2 = Double.parseDouble(numB1.getText());
            double num3 = Double.parseDouble(numN2.getText());
            while(i<=num3){
                congruence=((num1*i)-num2)%num3;
                i++;
                if(congruence==0){
                    ctr++;  
                    solution06.setText("The possible solutions : " + ctr + "\n" + num1 + " - " + num2 + " mod " + num3 + " = " + congruence );
                }
            }
    }
         
    public void showSolution07(MouseEvent event) {
            String text = encrypt.getText();
            char[] chars = text.toCharArray();
            String back;
            int i=0;
            double k = Double.parseDouble(key.getText());
            
            for(char c: chars){
                c += k;
                chars[i]=c;
                i++;
            }
            back = Arrays.toString(chars);
            encrypted.setText(back);
    }
                             
    public void showSolution08(MouseEvent event) {
        int ctr=1;
        double num1 = Double.parseDouble(numA2.getText());
        double num2 = Double.parseDouble(numB2.getText());
        double exp = Double.parseDouble(exponent.getText());
        double num3 = Double.parseDouble(numN3.getText());
        while(ctr<exp){
            num1=num1*num1;
            exp--;
            double num4 = num1-num2;
            double num5 = num4%num3;
            solution08.setText( "The solution :\n" + num1 + " - " + num2  + " = " + num4 + "mod" + num3 + " = " + num5 );
        }
    }
    
}
