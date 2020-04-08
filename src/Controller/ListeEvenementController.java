/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Categorie;
import Entities.Evenement;
import Service.CategorieService;
import Service.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class ListeEvenementController implements Initializable {
    EvenementService Es = new EvenementService();
    CategorieService Cs = new CategorieService();
    
    @FXML
    private TableView<Evenement> eventsTable = new TableView<Evenement>();
    @FXML
    private TableColumn<Evenement, Integer> idevenementColonne = new TableColumn<Evenement, Integer>("idevenement");
    @FXML
    private TableColumn<Evenement, String> titreColonne = new TableColumn<Evenement, String>("titre");
    @FXML
    private TableColumn<Evenement, Date> dateDebColonne = new TableColumn<Evenement, Date>("date");
    @FXML
    private TableColumn<Evenement, Date> dateFinColonne = new TableColumn<Evenement, Date>("datefin");
    @FXML
    private TableColumn<Evenement, String> lieuColonne = new TableColumn<Evenement, String>("lieu");
    @FXML
    private TableColumn<Evenement, Integer> fraisColonne = new TableColumn<Evenement, Integer>("frais");
    @FXML
    private TableColumn<Evenement, Integer> nbParticipantsColonne = new TableColumn<Evenement, Integer>("nbParticipantMax");
    @FXML
    private TableColumn<Evenement, String> categorieColonne = new TableColumn<Evenement, String>("categorie");
    
    @FXML
    private Button btnajoutEvent;
    
    @FXML
    private TextField Rclef;
    @FXML
    private DatePicker Rdate;
    @FXML
    private ComboBox<String> Rcategorie;
    @FXML
    private ComboBox<String> Rclub;
    
    
    @FXML
    private Button btnRecherche;
    @FXML
    private Button btnAfficher;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnCategorie;
    
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idevenementColonne.setCellValueFactory(new PropertyValueFactory<>("idevenement"));
        titreColonne.setCellValueFactory(new PropertyValueFactory<>("titre"));
        dateDebColonne.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateFinColonne.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        lieuColonne.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        fraisColonne.setCellValueFactory(new PropertyValueFactory<>("frais"));
        nbParticipantsColonne.setCellValueFactory(new PropertyValueFactory<>("nbParticipantMax"));
        categorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        try {
            ObservableList<Categorie> cats = Cs.listerCategorie();
            
            ObservableList<String> libelles = FXCollections.observableArrayList();
            ObservableList<Evenement> events = FXCollections.observableArrayList();
            
            cats.forEach((c) -> {
                libelles.add(c.getLibelle());
            });
            
            Rclub.getItems().add("Club");
            Rclub.getItems().addAll(Es.getNomClubs());
            
            Rcategorie.getItems().add("Catégorie");
            Rcategorie.getItems().addAll(libelles);
            
            events = Es.listerEvenement();
            eventsTable.setItems(events);
            
            
            eventsTable.getSelectionModel().selectedItemProperty().addListener((event)->
                            {   btnAfficher.setDisable(false);
                                btnSupprimer.setDisable(false);
                            });
            
        } catch (SQLException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnajoutEvenementtHandle(ActionEvent event){
        try {
                Parent ajoutEvent_page = FXMLLoader.load(getClass().getResource("/Views/AjouterEvenement.fxml"));
                Scene scene = new Scene(ajoutEvent_page);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void btnEditerHandle(ActionEvent event) throws SQLException{
       
         try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Views/ModifierEvenement.fxml"));
                Parent modifCat_page = loader.load();
                
                Scene scene = new Scene(modifCat_page);
                ModifierEvenementController controller = loader.getController();
                controller.initData(eventsTable.getSelectionModel().getSelectedItem());
                
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void btnSupprimerHandle(ActionEvent event) throws SQLException{
        
        Evenement e = new Evenement(eventsTable.getSelectionModel().getSelectedItem().getIdevenement());
        Alert alertSup = new Alert(Alert.AlertType.CONFIRMATION);
        alertSup.setTitle("Suppression d'un événement");
        alertSup.setHeaderText("Etes-vous sûr de vouloir supprimer cet événement?");
        
        Optional<ButtonType> option = alertSup.showAndWait();
        if (option.get() == ButtonType.OK)
        {   Es.supprimerEvenement(e);
            eventsTable.getItems().clear();
            eventsTable.setItems(Es.listerEvenement());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Evénement supprimé avec succés!");
            alert.show();
        }
    }
    
    public void btnRechercheHandle(ActionEvent event) throws SQLException{
        ObservableList<Categorie> cats = Cs.listerCategorie();
        String cat = Rcategorie.getSelectionModel().getSelectedItem();
        int catId = cats.stream()
                        .filter(c -> c.getLibelle().equals(cat))
                        .mapToInt(c -> c.getIdcategorie()).sum();
        
        
        int clubId  = Es.getIdClub(Rclub.getSelectionModel().getSelectedItem());
        
        Evenement e;
        if (Rclub.getSelectionModel().getSelectedIndex() < 0 )
            e = new Evenement(Rclef.getText(), Rclef.getText(), null, 0, 0, Rclef.getText(),
                0, null, catId);
        else
            e = new Evenement(Rclef.getText(), Rclef.getText(), null, 0, 0, Rclef.getText(),
                0, null, catId, clubId);
        if (Rdate.getValue()!= null) e.setDate(Date.valueOf(Rdate.getValue()));
        else e.setDate(null);
        
        
        idevenementColonne.setCellValueFactory(new PropertyValueFactory<>("idevenement"));
        titreColonne.setCellValueFactory(new PropertyValueFactory<>("titre"));
        dateDebColonne.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateFinColonne.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        lieuColonne.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        fraisColonne.setCellValueFactory(new PropertyValueFactory<>("frais"));
        nbParticipantsColonne.setCellValueFactory(new PropertyValueFactory<>("nbParticipantMax"));
        categorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        
            if ("".equals(Rclef.getText()) && Rdate.getValue() == null 
                    && Rcategorie.getSelectionModel().getSelectedIndex() < 1
                    && Rclub.getSelectionModel().getSelectedIndex() < 1)
            {
                eventsTable.getItems().clear();
                eventsTable.setItems(Es.listerEvenement());
            }    
            else{
                eventsTable.getItems().clear();
                eventsTable.setItems(Es.rechercheEvenement(e));
            }
            
        btnAfficher.setDisable(true);
        btnSupprimer.setDisable(true);    
    }
    
    public void btnCategorieHandle(ActionEvent event){
        try {
                Parent listCat_page = FXMLLoader.load(getClass().getResource("/Views/AccueilCategorie.fxml"));
                Scene scene = new Scene(listCat_page);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
