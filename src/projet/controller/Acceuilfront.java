/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class Acceuilfront implements Initializable {

   
    

    
    @FXML
    private Label nomUser;
String namee;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    @FXML
    public void acceuilGUI(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/projet/interfaces/front.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Acceuilfront.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
       // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void disconnect(MouseEvent event) {
    }

    public String getUser(String name) {
        String ch=name;
    
               nomUser.setText(ch);

        return ch ;
    }

    public String getUsername(String username) {
          String ch=username;
            namee=ch;  
                           //nomUser.setText("Bienvenue "+ch);

        return ch ;   
    }
    
}
