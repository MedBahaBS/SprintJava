/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import Entity.Serie;
import Front.ListeCourfrontController;
import Service.ServiceSerie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SerielisteController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button cour;
    @FXML
    private Button serie;
    @FXML
    private Label LblUserName;
    @FXML
    private AnchorPane consulter;
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
   @FXML
    private TableView<Serie> tabcour;
    @FXML
    private TableColumn<Serie, String> lien;
    @FXML
    private TableColumn<Serie, String> nomserie;
    @FXML
    private TableColumn<Serie, String> description;
    @FXML
    private TableColumn<Serie, Integer> cour1;
 ServiceSerie ser = new ServiceSerie();
    @FXML
    private Button update;
    @FXML
    private Button supp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ObservableList<Serie> list  = FXCollections.observableArrayList();

         
        try {
            for(Serie S: ser.readAll())
                list.add(S);
        } catch (SQLException ex) {
            Logger.getLogger(ListeCourfrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         lien.setCellValueFactory(new PropertyValueFactory<>("lien"));
        nomserie.setCellValueFactory(new PropertyValueFactory<>("nomserie"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        cour1.setCellValueFactory(new PropertyValueFactory<>("cour"));
        

        //load dummy data
       
        tabcour.setItems(list);
    }    

    @FXML
    private void cour(ActionEvent event) {
    }

    @FXML
    private void serie(ActionEvent event) {
    }

    @FXML
    private void GetGestionPediatre(ActionEvent event) {
    }

    @FXML
    private void disconnect(MouseEvent event) {
    }


    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {
         ObservableList<Serie> SelectedRows, allpeople;
     
     allpeople = tabcour.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tabcour.getSelectionModel().getSelectedItems();
       
     for(Serie c:SelectedRows){
       allpeople.remove(c);
     ser.delete(c.getIdserie());
     }
    }

    
}
