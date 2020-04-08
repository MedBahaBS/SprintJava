/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Categorie;
import Service.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baha
 */
public class ModifierCategorieController implements Initializable {
    private Categorie selectedCategorie;
    @FXML
    private Button btn;
    @FXML
    private TextField tflibelle;
    @FXML
    private TextField tfdescription;
    @FXML
    private Label idcategorielabel;
    @FXML
    private Label erreurLibelle;
    @FXML
    private Label erreurDescription;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn.setOnAction(event -> {
            
            Categorie c = new Categorie(Integer.parseInt(idcategorielabel.getText()),tflibelle.getText(), tfdescription.getText());
            CategorieService Cs = new CategorieService();
            try{
                if (Cs.isValidLibelle(tflibelle.getText()) && Cs.isValidDescription(tfdescription.getText()))
                { 
                    Cs.modifierCategorie(c);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Categorie modifiée avec succés!");
                    alert.show();
                    try {
                        Parent ajoutCat_page = FXMLLoader.load(getClass().getResource("/Views/AccueilCategorie.fxml"));
                        Scene scene = new Scene(ajoutCat_page);
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListeCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    if (!Cs.isValidLibelle(tflibelle.getText()))
                       erreurLibelle.setVisible(true);
                    else
                       erreurLibelle.setVisible(false);
                    
                    if (!Cs.isValidDescription(tfdescription.getText()))
                       erreurDescription.setVisible(true);
                    else
                       erreurDescription.setVisible(false);
                }
            }catch(SQLException ex)
            {
            }
        });
        
    }    
   
    public void initData(Categorie c){
        selectedCategorie = c;
        idcategorielabel.setText(String.valueOf(selectedCategorie.getIdcategorie()));
        tflibelle.setText(selectedCategorie.getLibelle());
        tfdescription.setText(selectedCategorie.getDescription());
    }
    
    public void btnAnnulerHandle(ActionEvent event){
         try {
                Parent ajoutCat_page = FXMLLoader.load(getClass().getResource("/Views/AccueilCategorie.fxml"));
                Scene scene = new Scene(ajoutCat_page);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
