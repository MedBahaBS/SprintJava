/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUser;

import projet.controller.Acceuilfront;
import static GestionUser.SignInController.ID;
import projet.services.Mail;
import projet.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ForgetPassController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private ImageView im1;
    @FXML
    private Label erreur1;
    @FXML
    private Button forget;
    @FXML
    private TextField code;
    @FXML
    private ImageView im11;
    @FXML
    private Label erreur11;
    @FXML
    private Button singup;
    
    ServiceUser us = new ServiceUser();
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();
    String token= generateRandomString(7);
    @FXML
    private ImageView imgErreur;
    @FXML
    private Label erreur;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // debug
            System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

            sb.append(rndChar);

        }

        return sb.toString();

    }

    @FXML
    private void forget(ActionEvent event) throws IOException {
        try {
            String mail= username.getText();
            String cod = code.getText();
            
            if(us.existMail(mail) )
            {
                if ( cod.equals("")) {
                    us.creerToken(token,username.getText());
                    Mail m=new Mail();
                   
       
     
                    try {
                        m.sendMail(username.getText(),"Bonjour, \n Votre token de reset password est :"+token+"");
                         erreur.setText(" token envoyer a votre mail ");
                    } catch (Exception ex) {
                        Logger.getLogger(ForgetPassController.class.getName()).log(Level.SEVERE, null, ex);
                    }
      
    }
                    
                
                else {
                    if( us.verifToken(cod , username.getText())) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionUser/resetPass.fxml"));
            Parent root = loader.load();
            ResetPassController scene2Controller = loader.getController();
                scene2Controller.setMail(username.getText());
            //Get controller of scene2
            //Pass whatever data you want. You can have multiple method calls here
          
                Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("");
            stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

        
        
                    }
                    else {
                        imgErreur.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur.setText("invalid token code");
                    }
                }
                
                
            }else
            { imgErreur.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
            erreur.setText("invalid mail");
            }
        } catch (SQLException ex) {
System.out.print(ex.getMessage());        }
                }

   


    @FXML
    private void singup(ActionEvent event) {
    }

  

    @FXML
    private void forgetCode(KeyEvent event) {
                         String cod = code.getText();
if (cod.equals("")) {
                forget.setText("Envoyer"); }
if (!cod.equals("") && forget.getText().equals("Envoyer")) {
                    forget.setText("Valider");}

    }
    }
    
  
