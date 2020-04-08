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
 *
 * @author Baha
 */
public class AjouterCategorieController implements Initializable {
    @FXML
    private Button btn;
    @FXML
    private TextField tflibelle;
    @FXML
    private TextField tfdescription;
    @FXML
    private Button listeCat;
    @FXML
    private Label erreurLibelle;
    @FXML
    private Label erreurDescription;
    
     /**
     * Initializes the controller class.
     *
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        btn.setOnAction(event -> {
            
            Categorie c = new Categorie(tflibelle.getText(), tfdescription.getText());
            CategorieService Cs = new CategorieService();
            try{
                
                if (Cs.isValidLibelle(c.getLibelle()) && Cs.isValidDescription(c.getDescription()))
                { 
                    Cs.ajouterCategorie(c);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Categorie insérée avec succés!");
                    alert.show();
                    tflibelle.setText("");
                    tfdescription.setText("");
                    erreurLibelle.setVisible(false);
                    erreurDescription.setVisible(false);
                }else
                {
                    if (!Cs.isValidLibelle(c.getLibelle()))
                       erreurLibelle.setVisible(true);
                    else
                       erreurLibelle.setVisible(false);
                    
                    if (!Cs.isValidDescription(c.getDescription()))
                       erreurDescription.setVisible(true);
                    else
                       erreurDescription.setVisible(false);
                }
                
                
            }catch(SQLException ex)
            {    
            }   
        });
    }
    
    public void btnlisteCatHandle(ActionEvent event){
         try {
                Parent ajoutCat_page = FXMLLoader.load(getClass().getResource("/Views/AccueilCategorie.fxml"));
                Scene scene = new Scene(ajoutCat_page);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
