/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author alaed
 */
public class ClubController implements Initializable{
    private AnchorPane rootLayout;
    private Stage primaryStage;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_affichert;
    @FXML
    private Button btn_retour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_retour.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/back.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_ajouter.setOnAction(event -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/AjouterClub.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_affichert.setOnAction(event -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/AfficherClubs.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_modifier.setOnAction(event -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/ModifierClub.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_supprimer.setOnAction(event -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/SupprimerClub.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 
}
