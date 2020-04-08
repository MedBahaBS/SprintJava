/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Categorie;
import Entities.Evenement;
import Entities.Participant;
import Service.CategorieService;
import Service.EvenementService;
import Service.ParticipantService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baha
 */
public class ModifierEvenementController implements Initializable {
    private CategorieService Cs = new CategorieService();
    private EvenementService Es = new EvenementService();
    
    private Evenement selectedEvent;
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
    private Button btnAnnuler;
    @FXML
    private Label idevenementlabel;
    
    @FXML
    private Button btnVoir;
    
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            ObservableList<Categorie> cats = Cs.listerCategorie();
            
            ObservableList<String> libelles = FXCollections.observableArrayList();
            cats.forEach((c) -> {
                libelles.add(c.getLibelle());
            });
            
            
            cbcategorie.setItems(libelles);
            //cbcategorie.getSelectionModel().select(cats.indexOf(cbcategorie.getSelectionModel().getSelectedItem()));
            
            cbclub.getItems().add("Club");
            cbclub.getItems().addAll(Es.getNomClubs());
            
            btn.setOnAction(event -> {
                String cat = cbcategorie.getSelectionModel().getSelectedItem();
                int catId = cats.stream()
                        .filter(c -> c.getLibelle().equals(cat))
                        .mapToInt(c -> c.getIdcategorie())
                        .sum();
                
                Date dateDeb = Date.valueOf(dDateDeb.getValue());
                Date dateFin = Date.valueOf(dDateFin.getValue());
                
                Evenement e = new Evenement(Integer.parseInt(idevenementlabel.getText()),tftitre.getText(),
                        tfdescription.getText(), dateDeb, 1, Integer.parseInt(tfnbMax.getText()), 
                        tflieu.getText(), Integer.parseInt(tfFrais.getText()), dateFin, catId);
                
                if (cbclub.getSelectionModel().getSelectedIndex() > 0){
                    try {
                        int clubId  = Es.getIdClub(cbclub.getSelectionModel().getSelectedItem());
                        e.setClub(clubId);
                        } catch (SQLException ex) {
                            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                
                try{
                    if ( Es.isValidTitre(e.getTitre()) && Es.isValidDescription(e.getDescription()) &&
                            Es.isValidDateDeb(e.getDate()) && Es.isValideDateFin(e.getDate(), e.getDatefin()) &&
                                Es.isValidFrais(e.getFrais()) && Es.isValidFrais(e.getFrais()) &&
                                    Es.isValidNbPart(e.getNbParticipantMax()) &&
                                        cbcategorie.getSelectionModel().getSelectedIndex() > 0){
                        Es.modifierEvenement(e);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Evénement modifié avec succés!");
                        alert.show();
                        try {
                            Parent modifEvent_page = FXMLLoader.load(getClass().getResource("/Views/AccueilEvenement.fxml"));
                            Scene scene = new Scene(modifEvent_page);
                            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            primaryStage.setScene(scene);
                            primaryStage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void initData(Evenement e) throws SQLException{
        ObservableList<Categorie> cats = Cs.listerCategorie();
        int index = cats.size();
        
        selectedEvent = e;
        idevenementlabel.setText(String.valueOf(selectedEvent.getIdevenement()));
        tftitre.setText(selectedEvent.getTitre());
        tfdescription.setText(selectedEvent.getDescription());
        dDateDeb.setValue(selectedEvent.getDate().toLocalDate());
        dDateFin.setValue(selectedEvent.getDatefin().toLocalDate());
        tflieu.setText(selectedEvent.getLieu());
        tfnbMax.setText(String.valueOf(selectedEvent.getNbParticipantMax()));
        tfFrais.setText(String.valueOf(selectedEvent.getNbParticipantMax()));
        
        
        for(Categorie c : cats)
          if (c.getIdcategorie() == e.getCategorie())
          {
              index = cats.indexOf(c);
              break;
          }
        cbcategorie.getSelectionModel().select(cats.get(index).getLibelle());
        
        String nomClub;
        if (selectedEvent.getClub() != 0){
           nomClub = Es.getNomClubs().stream().filter(c -> {
           boolean b = false;
                try {
                    b = Es.getIdClub(c) == selectedEvent.getClub();
                } catch (SQLException ex) {
                    Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return b;
            }).findFirst().get();
            cbclub.getSelectionModel().select(nomClub);
        }
        else 
            cbclub.getSelectionModel().select(0);
    }
    
    public void btnAnnulerHandle(ActionEvent event){
         try {
                Parent listEvents_page = FXMLLoader.load(getClass().getResource("/Views/AccueilEvenement.fxml"));
                Scene scene = new Scene(listEvents_page);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void btnVoirHandle(ActionEvent event) throws IOException, SQLException{
             
           String image_name = Es.getImageName(selectedEvent.getIdevenement());
           StackPane secondaryLayout = new StackPane();
           
           if (image_name != null)
           secondaryLayout.getChildren().add(new ImageView("file:///C:/wamp64/www/PIDEV_integration/public/images/events/" 
                   + image_name));
           else
               secondaryLayout.getChildren().add(new ImageView("file:///C:/wamp64/www/PIDEV_integration/public/" 
                   + "pas_image.png"));
           
            Scene secondScene = new Scene(secondaryLayout, 230, 100);
            
            Stage newWindow = new Stage();
            newWindow.setTitle("Image de l'événement");
            newWindow.setHeight(800);
            newWindow.setWidth(1000);
            newWindow.setScene(secondScene);
            newWindow.show();
    }
        
        @FXML
        TableView<String> participants = new TableView<>();
        @FXML
        TableColumn<String, String> NomCol = new TableColumn<>("Nom Participant");
        
    public void btnParticipantsHandle(ActionEvent event) throws SQLException, IOException{
        
        
        ParticipantService Ps = new ParticipantService();
        
        //StackPane secondaryLayout = new StackPane();
       
        
        NomCol.setCellValueFactory(new PropertyValueFactory<>("Nom Participant"));
        
        ObservableList<String> nomParticpants = FXCollections.observableArrayList();
        ObservableList<Participant> parts = Ps.listerParticipants(selectedEvent.getIdevenement());
        System.out.println(selectedEvent.getIdevenement());
        parts.stream().forEach(System.out::println);
        parts.stream().forEach((p) -> {
            try {
                nomParticpants.add(Ps.getNomParticipant(p));
                System.out.println(nomParticpants.get(nomParticpants.size()));
            } catch (SQLException ex) {
                Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        System.out.println(nomParticpants);
        participants.setItems(nomParticpants);
        
        //secondaryLayout.getChildren().add(participants);
        
        Scene secondScene = new Scene(FXMLLoader.load(getClass().getResource("/Views/Participants.fxml")), 230, 100);
            
            Stage newWindow = new Stage();
            newWindow.setTitle("Participants de l'événement");
            newWindow.setHeight(400);
            newWindow.setWidth(460);
            newWindow.setScene(secondScene);
            newWindow.show();
        
    }
}
