/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Entities.Reclamation;
import Service.Crud_Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterReclamationController implements Initializable {
    @FXML
    private Button Ajouterr;
    @FXML
    private TextArea ajtctn;
    @FXML
    private TextField ajttype;
    
    Crud_Reclamation r = new Crud_Reclamation();
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouterr(ActionEvent event) throws SQLException {
         
         Reclamation r1 =new Reclamation(ajtctn.getText(),String.valueOf(ajttype.getText()));
        r.ajouter(r1);
        
         if ((event.getSource() == Ajouterr) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
          
    }
    }

    @FXML
    private void Retour(ActionEvent event) {
         if ((event.getSource() == Retour) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
          
    }
    }
    
}
