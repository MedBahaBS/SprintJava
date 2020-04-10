/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Front;

import Entity.Cours;

import Service.ServiceCours;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ListeCourfrontController implements Initializable {

    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;
    @FXML
    private ScrollPane SrollPaneMain;
    @FXML
    private TableView<Cours> tabcour;
    @FXML
    private TableColumn<Cours, String> niveau;
    @FXML
    private TableColumn<Cours, String> matiere;
    @FXML
    private TableColumn<Cours, String> nomchap;
    @FXML
    private TableColumn<Cours, String> email;
    @FXML
    private TableColumn<Cours, String> lien;
    @FXML
    private TableColumn<Cours, Date> date;
    @FXML
    private TextField Titre;
    @FXML
    private Button ChercherTitre;

    @FXML
    private CheckBox Cultiver;
    @FXML
    private CheckBox Distraire;
    @FXML
    private CheckBox Cinema;
    ServiceCours svr = new ServiceCours();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Cours> list  = FXCollections.observableArrayList();

         
        try {
            for(Cours P: svr.readAll())
                list.add(P);
        } catch (SQLException ex) {
            Logger.getLogger(ListeCourfrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
         matiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
         nomchap.setCellValueFactory(new PropertyValueFactory<>("nomchapitre"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
         lien.setCellValueFactory(new PropertyValueFactory<>("lien"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        //load dummy data
       
        tabcour.setItems(list);
    }    


   
}
