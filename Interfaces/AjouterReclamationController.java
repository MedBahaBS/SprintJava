/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CL.Interfaces;

import CL.Entities.Reclamation;
import CL.Service.Crud_Reclamation;
import GestionUser.SignInController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
   
    
    Crud_Reclamation r = new Crud_Reclamation();
    @FXML
    private Button Retour;
    @FXML
    private ComboBox<String> Comb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Comb.setItems(list); 
    }    
ObservableList <String> list =FXCollections.observableArrayList("Retard","Absence","cantine","club");
    @FXML
    private void Ajouterr(ActionEvent event) throws SQLException, IOException {
         
        
        
        
        
        
       
         Reclamation r1 =new Reclamation(/*,String.valueOf(ajttype.getText())*/Comb.getValue(),ajtctn.getText());
         r1.setUser(SignInController.ID);
         r1.setTarget_id(SignInController.ID);
           /* if (ajtctn.getText()==null && Comb.getValue() ==null) {
    Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("ATTENTION!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("Rien n'est selectionné");
         alert.showAndWait();}
            else
            {*/
        
        
           /* }*/ 
          
        
         if ((  (Comb.getValue() == null) && event.getSource() == Ajouterr) ||
                 ( ajtctn.getText() ==null && event.getSource() == Ajouterr)){
          
        
           Alert alert= new Alert (Alert.AlertType.ERROR);
       alert.setTitle("ATENTION!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("Merci d'entrez votre données ");
         alert.showAndWait();
         }
         
         
         
         else if ((    (Comb.getValue() !=null) &&  event.getSource() == Ajouterr ) &&
                 ( ajtctn.getText() !=null && event.getSource() == Ajouterr)) {
         
        
         r.ajouter(r1);
       
         javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        
         Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("REMERCIEMENTS!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("Merci d'ajouter votre réclamation , Nous allons  la traiter le plutot possible");
         alert.showAndWait();
        
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
