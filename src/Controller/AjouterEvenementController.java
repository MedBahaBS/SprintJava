/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Evenement;
import Entities.Categorie;
import Service.CategorieService;
import Service.EvenementService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baha
 */
public class AjouterEvenementController implements Initializable {
    private EvenementService Es = new EvenementService();
    @FXML
    private Button btn;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescription;
    @FXML
    private DatePicker dDateDeb = new DatePicker();
    @FXML
    private DatePicker dDateFin = new DatePicker();
    @FXML
    private TextField tflieu;
    @FXML
    private TextField tfnbMax;
    @FXML
    private TextField tfFrais;
    @FXML
    private ComboBox<String> cbcategorie;
    @FXML
    private ComboBox<String> cbclub;
    @FXML
    private Button btnParcourir;
    @FXML
    private Label fichierLabel;
    @FXML
    private Label erreurTitre;
    @FXML
    private Label erreurDescription;
    @FXML
    private Label erreurDateDeb;
    @FXML
    private Label erreurDateFin;
    @FXML
    private Label erreurLieu;
    @FXML
    private Label erreurNb;
    @FXML
    private Label erreurFrais;
    @FXML
    private Label erreurCategorie;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     *  save image
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            CategorieService Cs = new CategorieService();
            ObservableList<Categorie> cats = Cs.listerCategorie();
            
            ObservableList<String> libelles = FXCollections.observableArrayList();
            cats.stream().forEach((c) -> {
                libelles.add(c.getLibelle());
            });
            
            dDateDeb.setValue(LocalDate.now());
            dDateFin.setValue(LocalDate.now());
            /*
            ObservableList<Club> clubs = FXCollections.observableArrayList();
            ObservableList<String> nomClubs = FXCollections.observableArrayList();
            clubs.forEach((c)->{
                nomClubs.add(c.getNom());
            });
            cbclub.setItems(nomClubs);
            */
            cbclub.getItems().addAll(Es.getNomClubs());
            
            cbcategorie.setItems(libelles);
            
            btn.setOnAction(event -> {
                
                String cat = cbcategorie.getSelectionModel().getSelectedItem();
                int catId = cats.stream()
                        .filter(c -> c.getLibelle().equals(cat))
                        .mapToInt(c -> c.getIdcategorie())
                        .sum();
                
                Date dateDeb = Date.valueOf(dDateDeb.getValue());
                Date dateFin = Date.valueOf(dDateFin.getValue());
                
            //IL y a un probleme NullPointerException lorsque l'utilisateur supprime la date et clique sur ajouter
                Evenement e = new Evenement(tftitre.getText(), tfdescription.getText(),
                            dateDeb, 1, Integer.parseInt(tfnbMax.getText()), tflieu.getText(), 
                            Integer.parseInt(tfFrais.getText()), dateFin, catId);
                
                if (cbclub.getSelectionModel().getSelectedIndex() > 0){
                    try {
                        int clubId  = Es.getIdClub(cbclub.getSelectionModel().getSelectedItem());
                        e.setClub(clubId);
                        } catch (SQLException ex) {
                            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                
                try{
                   
                    //Si tous les champs sont correctes
                    if ( Es.isValidTitre(e.getTitre()) && Es.isValidDescription(e.getDescription()) &&
                            Es.isValidDateDeb(e.getDate()) && Es.isValideDateFin(e.getDate(), e.getDatefin()) &&
                                Es.isValidFrais(e.getFrais()) && Es.isValidFrais(e.getFrais()) &&
                                    Es.isValidNbPart(e.getNbParticipantMax()) &&
                                        cbcategorie.getSelectionModel().getSelectedIndex() > 0){
                        Es.ajouterEvenemenet(e);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Evénement insérée avec succés!");
                        alert.show();
                        
                        try {
                            Parent listEvents_page = FXMLLoader.load(getClass().getResource("/Views/AccueilEvenement.fxml"));
                            Scene scene = new Scene(listEvents_page);
                            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            primaryStage.setScene(scene);
                            primaryStage.show();
                         } catch (IOException ex) {
                                Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        
                    }
                    else{
                        if (!Es.isValidTitre(e.getTitre()))
                            erreurTitre.setVisible(true);
                        else
                            erreurTitre.setVisible(false);
                        
                        if (!Es.isValidDescription(e.getDescription()))
                            erreurDescription.setVisible(true);
                        else
                            erreurDescription.setVisible(false);
                        
                        if (!Es.isValidDateDeb(e.getDate()))
                            erreurDateDeb.setVisible(true);
                        else
                            erreurDateDeb.setVisible(false);
                        
                        if (!Es.isValideDateFin(e.getDate(),e.getDatefin()))
                            erreurDateFin.setVisible(true);
                        else
                            erreurDateFin.setVisible(false);
                        
                        if (!Es.isValidNbPart(e.getNbParticipantMax()))
                            erreurNb.setVisible(true);
                        else
                            erreurNb.setVisible(false);
                        
                        if (!Es.isValidFrais(e.getFrais()))
                            erreurFrais.setVisible(true);
                        else
                            erreurFrais.setVisible(false);
                        
                        if (!Es.isValidLieu(e.getLieu()))
                            erreurLieu.setVisible(true);
                        else
                            erreurLieu.setVisible(false);
                        
                        if (cbcategorie.getSelectionModel().getSelectedIndex() < 0)
                            erreurCategorie.setVisible(true);
                        else
                            erreurCategorie.setVisible(false);
                    }
                    
                }catch(SQLException ex)
                {
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void btnlisteEventsHandle(ActionEvent event){
         try {
                Parent ajoutCat_page = FXMLLoader.load(getClass().getResource("/Views/AccueilEvenement.fxml"));
                Scene scene = new Scene(ajoutCat_page);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void btnParcourirHandle(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //DirectoryChooser directoryChooser = new DirectoryChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg","*.png"));
        //directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null){
            fichierLabel.setText(file.getAbsolutePath());
            //image = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(),
              //          imageView.getFitHeight(), true, true);
            //ImageIO.write(SwingFXUtils.fromFXImage(new ImageView().getImage(), null) , "png", file);
            //Files.copy(file.getAbsolutePath(),);
        }
            
    }
}
