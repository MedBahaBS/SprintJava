/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUser;

import projet.services.Mail;
import projet.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ResetPassController implements Initializable {

    @FXML
    private VBox rootPane;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView imgErreur;
    @FXML
    private PasswordField password1;
    @FXML
    private ImageView imgErreur2;
    @FXML
    private Button loginn;
    @FXML
    private Label lblErrors;
   String maile;
    boolean test=true;
    ServiceUser us = new ServiceUser();
    @FXML
    private TextField email;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setMail(String maile) {
        email.setText(maile);
        System.out.println(email.getText());
    }

    @FXML
    private void passwordControl(KeyEvent event) {
    }

    @FXML
    private void password1Control(KeyEvent event) {
    }

    @FXML
    private void inscrit(ActionEvent event) throws IOException {
        if (!password.getText().equals("") && !password1.getText().equals("")) {
        if (!password1.getText().equals(password.getText())) {
            lblErrors.setText("les deux mots de pass doit étre identiquent");
        }
        else{
            ServiceUser su = new ServiceUser();
            try {
                su.modifierPassword(email.getText(), password.getText());
                su.supprimerToken(maile);
                                    Mail m=new Mail();
                                     try {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	
                   
                        m.sendMail(email.getText(),"Bonjour, \n Votre mot de pass a été modifier a : "+dateFormat.format(date));
                    } catch (Exception ex) {
                        Logger.getLogger(ForgetPassController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } catch (SQLException ex) {
            }
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionUser/Main.fxml"));
            Parent root = loader.load();
            MainController scene2Controller = loader.getController();
              //  scene2Controller.setMail(username.getText());
            //Get controller of scene2
            //Pass whatever data you want. You can have multiple method calls here
          
                Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("");
            stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

       
        } }
        else {
                        lblErrors.setText("champs non valides");

        }
        
    
    }
    @FXML
    private void signup(MouseEvent event) {
    }
    
}
