/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CL.Back;

import CL.Entities.Reclamation;
import CL.Service.Crud_Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherReclamationController implements Initializable {
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, String> coldaterec;
    @FXML
    private TableColumn<Reclamation, String> coltyperec;
    @FXML
    private TableColumn<Reclamation, String> colcontenurec;
List<Reclamation> ListR=new ArrayList();
List<Reclamation> ListR1=new ArrayList();
 Crud_Reclamation r = new Crud_Reclamation();
    @FXML
    private Button Retour;
    @FXML
    private TableColumn<Reclamation, String> Etat;
    @FXML
    private Button Traiter;
    @FXML
    private Button Archiver;
    @FXML
    private Button Bloquer;
    @FXML
    private Button Stat;
    @FXML
    private TableColumn<Reclamation, String> archiver;
    @FXML
    private Button afficher;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {  
            
            
            
            loadReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

     public void loadReclamation() throws SQLException {
        /*Crud_Reclamation s = new Crud_Reclamation();
        ArrayList<Reclamation> stand = s.listerReclamation();
        ObservableList observableList = FXCollections.observableArrayList(stand);
        table.setItems(observableList);*/
        
       
         
        ListR =r.afficherReclamation("En attente","Traitée","");
         
         
        ObservableList <Reclamation> listReclamation=FXCollections.observableArrayList(ListR);
       
     coldaterec.setCellValueFactory(new PropertyValueFactory<>("DateReclamation") );
         coltyperec.setCellValueFactory(new PropertyValueFactory<>("TypeReclamation") );
         colcontenurec.setCellValueFactory(new PropertyValueFactory<>("ContenuReclamation"));
         Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
    table.setItems(listReclamation);
   
    } 

    @FXML
    private void Retour(ActionEvent event) {
         if ((event.getSource() == Retour) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/CL/Interfaces/classe.fxml"));
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
    private void Traiter(ActionEvent event) throws SQLException { 
        Reclamation r2 =table.getSelectionModel().getSelectedItem();
        if ((event.getSource() == Traiter) ) {
  
       r.modifier3(r2.getId(),r2.getNewValue()); 
    loadReclamation();
       }
    }

    
  

    @FXML
    private void Traiter2(MouseEvent event) {
           Reclamation r2 =table.getSelectionModel().getSelectedItem();
  r2.getEtat();
  
    }

    @FXML
    private void Archiver(ActionEvent event) throws SQLException {
        
   Reclamation r2 =table.getSelectionModel().getSelectedItem();
   if ((event.getSource() == Archiver && "En attente".equals(r2.getEtat())) ) {
       Alert alert= new Alert (Alert.AlertType.ERROR);
       alert.setTitle("ATTENTION!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("La réclamation n'est pas encore Traité");
         alert.showAndWait();
   }
   
   else if ((event.getSource() == Archiver && "Traitée".equals(r2.getEtat()) ) ){
       
       r.cacher(r2.getId(),r2.getArchiver2()); 
    loadReclamation();
    
    }
    }

    @FXML
    private void Bloquer(ActionEvent event) {
    }

    @FXML
    private void Stat(ActionEvent event) {
            if ((event.getSource() == Stat) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/CL/Back/Statistique.fxml"));
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
    private void afficher(ActionEvent event) {
          if ((event.getSource() == afficher) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/CL/Back/afficherArchive.fxml"));
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
