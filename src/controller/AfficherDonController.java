/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Don;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 *
 * @author alaed
 */
public class AfficherDonController implements Initializable {
    @FXML
    private TableView<Don> donsTable;
    @FXML
    private TableColumn<Don, Number> ClubColonne;
    @FXML
    private TableColumn<Don, String> typeColonne;
    @FXML
    private TableColumn<Don, String> emailColonne;
    @FXML
    private TableColumn<Don, String> descriptionColonne;
    @FXML
    private TableColumn<Don, Number> etatColonne;
    @FXML
    private TableColumn<Don, Number> sommeColonne;
    
    private ListDataD listdatad = new ListDataD();
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_pie;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        donsTable.setItems(listdatad.getDons());
        
        /*ClubColonne.setCellValueFactory(cell -> cell.
                getValue().getClubProperty());*/
        typeColonne.setCellValueFactory(cell -> cell.
                getValue().getTypeProperty());
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
        emailColonne.setCellValueFactory(cell -> cell.
                getValue().getEmailProperty());
       
        sommeColonne.setCellValueFactory(cell -> cell.
                getValue().getSommeProperty());
        
        
        
        
        
        
        
        
    }
    @FXML
   private void btn_supprimer(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //ajouterclub
        tableview = FXMLLoader.load(getClass().getResource("/view/SupprimerClubs.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }

    @FXML
   private void btn_modifier(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //ajouterclub
        tableview = FXMLLoader.load(getClass().getResource("/view/ModifierClubs.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   @FXML
   private void btnclub(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //club
        tableview = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   @FXML
   private void btndon(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //club
        tableview = FXMLLoader.load(getClass().getResource("/view/Acceuil_don.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   @FXML
   private void btn_retour(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //retour
        tableview = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
  @FXML
   private void btn_ajouter(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //ajouterclub
        tableview = FXMLLoader.load(getClass().getResource("/view/AjouterClub.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   @FXML
   private void btn_affichert(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //ajouterclub
        tableview = FXMLLoader.load(getClass().getResource("/view/AfficherDons.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   

    @FXML
    private void btnserie(ActionEvent event) throws IOException {
        
            javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeSerie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

  
    @FXML
    private void Gestioncoour(ActionEvent event) throws IOException {
           javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
          
    }

    @FXML
    private void boutonCour(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void gestioncour(MouseEvent event) {
    }

    @FXML
    private void cour(InputMethodEvent event) {
    }
    
}
