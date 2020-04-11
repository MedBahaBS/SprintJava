/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUser;


import Entity.User;
import Front.AcceuilfrontController;
import Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SignInController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

   public  static int ID=0;
   ServiceUser us = new ServiceUser();
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im2;
    @FXML
    private Label erreur1;
    @FXML
    private Label erreur2;
    @FXML
    private Button signin;
    @FXML
    private Label oublier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signin(ActionEvent event) throws SQLException, IOException {
          User u = new User();
        u.setUsername(username.getText());
        u.setPassword(password.getText());
        String name=us.getUsername(u.getUsername());
       System.out.println(us.verifAdmin(u.getUsername()).equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"));
        if (us.verifpassword(u.getUsername(), u.getPassword()) ) {
        if(us.verifAdmin(u.getUsername()).equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
        {    Stage stage = new Stage();
       ID=us.getId(u.getUsername());

        Parent root = FXMLLoader.load(getClass().getResource("/Dashbordback/Acceuil.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        }
        else if(us.verifAdmin(u.getUsername()).equals("a:0:{}"))
        { FXMLLoader loader = new FXMLLoader();
                     loader.setLocation(getClass().getResource("/Front/Acceuilfront.fxml"));
ID=us.getId(u.getUsername());

              
              Parent detail=loader.load();
              Scene scene = new Scene(detail);
               AcceuilfrontController controller = loader.getController();
               controller.getUser(name);
               controller.getUsername(u.getUsername());
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
        
        }}
        else {
    
            if(us.existUsername(username.getText()))
            {              im2.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur2.setText("invalid password");
                        im1.setImage(null);
            erreur1.setText("");

            } else 
            { im1.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
            erreur1.setText("invalid username");
                im2.setImage(new Image(this.getClass().getResourceAsStream("croix.png")));
                        erreur2.setText("invalid password");
            ///
            }
            

    }
    
    }

    @FXML
    private void oublier(MouseEvent event) {
        

    }
}
