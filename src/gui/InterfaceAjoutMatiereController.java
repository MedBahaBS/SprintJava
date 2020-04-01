/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Matiere;
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
import service.Crud_Matiere;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class InterfaceAjoutMatiereController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField nbseance;
    @FXML
    private TextField description;
    @FXML
    private TextField coefficient;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        Crud_Matiere m = new Crud_Matiere(); //hethi button shiha heka controlleur ghalet heka 3leh makhedmetlekich lezemek t3awed projet jdid mnathemm okaay emmemm yaatik sa7a :* ,maghir mzye sahbi <3

        Matiere m1 = new Matiere(nom.getText(),Integer.valueOf(nbseance.getText()),description.getText(),Integer.valueOf(coefficient.getText()));
       m.ajouter(m1);
            if ((event.getSource() == ajouter) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Matiere.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}}

    @FXML
    private void annuler(ActionEvent event) {
            try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Matiere.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}}
    

       
    
    

