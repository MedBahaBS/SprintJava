/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashbordback;

import Entity.Cours;
import Entity.Serie;
import Service.ServiceCours;
import Service.ServiceSerie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AddserieController implements Initializable {

    @FXML
    private AnchorPane updatecour;
    @FXML
    private Button ajouter;
    @FXML
    private Button pdf;
    @FXML
    private TextField nomserie;
    @FXML
    private TextField lien;
    @FXML
    private ChoiceBox<String> cours;
    @FXML
    private TextField description;
    @FXML
    private TextField nomserie1;
    @FXML
    private Button retour;
   ServiceCours s = new ServiceCours();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        try {
            List<Cours> cour = s.readAll();
            for(Cours c: cour)
            {
                cours.getItems().add(c.getNomchapitre());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Back.AddserieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
           ServiceCours sc = new ServiceCours();
        String cc = cours.getValue();
        System.err.println(cc);
       int Id = sc.getCourbNom(cc);
        String Lien= lien.getText() ;
        String Nomserie = nomserie.getText();
        String Description= description.getText() ;
      
         Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmer l'ajout");
        confirmation.setHeaderText("Confirmer");
        confirmation.setContentText("Voulez-vous ajouter cet serie ?");
        Optional<ButtonType> result = confirmation.showAndWait();  
        if (result.get() == ButtonType.OK) { 
            
            if ("".equals(lien.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("Le niveau est un champ obligatoire !");
                alert.showAndWait();
                } else if ("".equals(nomserie.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention !!!");
                alert.setContentText("La matiere est un champ obligatoire !");
                alert.showAndWait();
                }else if ("".equals(description.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention !!!");
                alert.setContentText("La matiere est un champ obligatoire !");
                alert.showAndWait();
                }
            else if ((cours.getSelectionModel().getSelectedItem()) == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("Il faut choisir un cour   !");
                alert.showAndWait();
        } else {
         ServiceSerie sp = new  ServiceSerie();
         
        Serie c ;
                c = new Serie (Lien,Nomserie,Description,Id);
       sp.ajouter(c); 
      
     try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListeSerie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
        }
    }

    @FXML
    private void pdf(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeSerie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
}
