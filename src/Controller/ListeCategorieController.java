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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baha
 */
public class ListeCategorieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    CategorieService Cs = new CategorieService();
    
    @FXML
    private TableView<Categorie> categorieTable = new TableView<Categorie>();
    @FXML
    private TableColumn<Categorie, Integer> idColonne = new TableColumn<Categorie, Integer>();
    
    @FXML
    private TableColumn<Categorie, String> libelleColonne = new TableColumn<Categorie, String>();
    @FXML
    private TableColumn<Categorie, String> descriptionColonne = new TableColumn<Categorie, String>();
    @FXML
    private Button ajoutCat;
    @FXML
    private TextField tflibelle;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField Rclef;
    
    @FXML
    private Button btnRecherche;
    @FXML
    private Button btnEditer;
    @FXML
    private Button btnSupprimer;
     /**
     * Initializes the controller class.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        idColonne.setCellValueFactory(new PropertyValueFactory<>("idcategorie"));
        libelleColonne.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionColonne.setCellValueFactory(new PropertyValueFactory<>("description"));
        try {
                categorieTable.setItems(Cs.listerCategorie());              
                categorieTable.getSelectionModel().selectedItemProperty().addListener((event)->
                            {   btnEditer.setDisable(false);
                                btnSupprimer.setDisable(false);
                                //if (categorieTable.getSelectionModel().getSelectedIndex() > 0){
                                tflibelle.setText(categorieTable.getSelectionModel().getSelectedItem().getLibelle());
                                tfdescription.setText(categorieTable.getSelectionModel().getSelectedItem().getDescription());
                        //        }
                        });
                
        } catch (SQLException ex) {
            Logger.getLogger(ListeCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void btnajoutCatHandle(ActionEvent event){
        try {
                Parent ajoutCat_page = FXMLLoader.load(getClass().getResource("/Views/AjouterCategorie.fxml"));
                Scene scene = new Scene(ajoutCat_page);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void btnRechercheHandle(ActionEvent event) throws SQLException{
        Categorie c = new Categorie(Rclef.getText(), Rclef.getText());
            tfdescription.setText("");
            tflibelle.setText("");
            btnEditer.setDisable(true);
            btnSupprimer.setDisable(true);
            
            idColonne.setCellValueFactory(new PropertyValueFactory<>("idcategorie"));
            libelleColonne.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            descriptionColonne.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            if ("".equals(Rclef.getText()) )
                categorieTable.setItems(Cs.listerCategorie());
            else{
                categorieTable.getItems().clear();
                categorieTable.setItems(Cs.rechercheCategorie(c));
            }
    }
    
    public void btnEditerHandle(ActionEvent event) throws SQLException{
       
         try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Views/ModifierCategorie.fxml"));
                Parent modifCat_page = loader.load();
                
                Scene scene = new Scene(modifCat_page);
                ModifierCategorieController controller = loader.getController();
                controller.initData(categorieTable.getSelectionModel().getSelectedItem());
                
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void btnSupprimerHandle(ActionEvent event) throws SQLException{
        
        Categorie c = new Categorie(categorieTable.getSelectionModel().getSelectedItem().getIdcategorie(),
                categorieTable.getSelectionModel().getSelectedItem().getLibelle(),
                categorieTable.getSelectionModel().getSelectedItem().getDescription());
        Alert alertSup = new Alert(Alert.AlertType.CONFIRMATION);
        alertSup.setTitle("Suppression d'une catégorie");
        alertSup.setHeaderText("Etes-vous sûr de vouloir supprimer cette catégorie ?");
        
        Optional<ButtonType> option = alertSup.showAndWait();
        if (option.get() == ButtonType.OK)
        {
            Cs.supprimerCategorie(c);
            categorieTable.getItems().clear();
            categorieTable.setItems(Cs.listerCategorie());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie supprimée avec succés!");
            alert.show();
            tflibelle.setText("");
            tfdescription.setText("");
        }   
    }
    
    public void btnEventsHandle(ActionEvent event){
        try {
                Parent listEvent_page = FXMLLoader.load(getClass().getResource("/Views/AccueilEvenement.fxml"));
                Scene scene = new Scene(listEvent_page);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
