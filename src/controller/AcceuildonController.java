/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import Dashbordback.*;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AcceuildonController implements Initializable {

    @FXML
    private StackPane afficherPlatStackPane;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btncour;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnPackages;
    @FXML
    private Button bntcour;
    @FXML
    private Button serie;
    @FXML
    private Label gestioncour;
    @FXML
    private Label gestioncour1;
    
    @FXML
    private Button btn_club;
    
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_affichert;
    @FXML
    private Button btn_supprimer;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    
    @FXML
   private void btn_supprimer(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        tableview = FXMLLoader.load(getClass().getResource("/view/SupprimerClubs.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }

    @FXML
   private void btn_modifier(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        
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
   
        //don
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
        tableview = FXMLLoader.load(getClass().getResource("/view/Acceuil_don.fxml"));
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
