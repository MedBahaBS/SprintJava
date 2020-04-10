/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUser;

import Entity.Cours;
import Entity.User;
import Service.ServiceUser;

import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SignUPController implements Initializable {
 
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
   
    
  
   Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Label lblErrors;
    @FXML
    private Button loginn;
    @FXML
    private VBox rootPane;
    @FXML
    private PasswordField password1;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }   

    @FXML
    private void signup(MouseEvent event) {
    }

    @FXML
    private void inscrit(ActionEvent event) throws IOException {
        User u = new User();
        ServiceUser us = new  ServiceUser();
         if(username.getText().equals("")&& email.getText().equals("") && password.getText().equals("") )
        {Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalide inscription");
        alert.setHeaderText("Veuillez remplir tous les champs");
        alert.setContentText("Ooops!");
        alert.showAndWait(); 
    }else
        { u.setUsername(username.getText());
            u.setEmail(email.getText());
            u.setPassword(password.getText());
            u.setEnable(1);
u.setUsername(username.getText());
u.setRoles(null);

 us.ajoutUser(u, u.getPassword());

  Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        }
    }

    @FXML
    private void usernameControl(KeyEvent event) {
        Pattern pattern = Pattern.compile("[^A-Z&&[^a-z&&[^ ]]]");
                   Matcher matcher = pattern.matcher(username.getText()); 

      if(! matcher.find())
      {if(!"".equals(username.getText())){
     img1.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));
      }else  img1.setImage(null);
    }
else  img1.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));   
      if("".equals(username.getText())) 
        img1.setImage(null);
    }

    @FXML
    private void emailControl(KeyEvent event) {
          boolean controlMaile = controlMail(email.getText());
    if("".equals(email.getText())) 
        img2.setImage(null);
    }

    @FXML
    private void passwordControl(KeyEvent event) {
          if(password.getText().length()>0)
        {if(((controlPassword(password.getText())==false)|| password.getText().charAt(0)<'A' || password.getText().charAt(0)>'Z')||(password.getText().length()<4))
        {img3.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
        }
        else
        { img3.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));
                

        }
   
    }
           if("".equals(password.getText())) 
        img3.setImage(null);
    
    }
    
    @FXML
    private void password1Control(KeyEvent event) {
    if(password1.getText().length()>0)
    { for(int i=0;i<password1.getText().length();i++)
    {if (password1.getText().charAt(i)!= password.getText().charAt(i))
    {img4.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                       

    } 
    else{img4.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));} 
     

    }
    
    
    }if("".equals(password1.getText())) 
        img4.setImage(null);
    }
    
    
    
 


    
    
    
    
    
boolean controlMail (String mail){
boolean pass =true;

if (mail.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) {
    img2.setImage(new Image(this.getClass().getResourceAsStream("tic.png")));//a finir
}else{
img2.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
}

return pass;
}
private boolean controlPassword(String p )
  {

       Pattern pattern = Pattern.compile("[^A-Z&&[^a-z&&[^0-9&&[^ ]]]]");
                   Matcher matcher = pattern.matcher(password.getText()); 

      if(!matcher.find())
      {
      return true;
      }return false;
   
  }


   

























}