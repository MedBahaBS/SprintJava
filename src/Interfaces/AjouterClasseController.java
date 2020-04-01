/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Entities.Classe;
import Service.Crud_Classe;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterClasseController implements Initializable {
    @FXML
    private TextField ajtlib;
    @FXML
    private TextField ajtnbr;
    @FXML
    private TextField ajtsalle;
    @FXML
    private Button ok;
Crud_Classe c = new Crud_Classe();
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
    private void ok(ActionEvent event) throws SQLException {
           
       Classe c1 =new Classe(ajtlib.getText(),Integer.valueOf(ajtnbr.getText()),Integer.valueOf(ajtsalle.getText()));
        c.ajouter(c1);
        
        
         if ((event.getSource() == ok) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("classe.fxml"));
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
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("classe.fxml"));
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
    

