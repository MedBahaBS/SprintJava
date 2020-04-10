/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import Entity.Serie;
import Front.ListeCourfrontController;
import Service.ServiceCours;
import Service.ServiceSerie;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
 ServiceCours courr = new ServiceCours();
    @FXML
    private Button update;
    @FXML
    private Button supp;
    @FXML
    private Button Ajouter;
    @FXML
    private TableColumn<Serie, Integer> idserie;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ObservableList<Serie> list  = FXCollections.observableArrayList();

         
        try {
            for(Serie S: ser.readAll()){
               String sa = courr.getCourbyid(S.getCour());
               S.setCours(sa);
                 list.add(S);
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(ListeCourfrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         lien.setCellValueFactory(new PropertyValueFactory<>("lien"));
        nomserie.setCellValueFactory(new PropertyValueFactory<>("nomserie"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        cour1.setCellValueFactory(new PropertyValueFactory<>("cours"));
        

        //load dummy data
       
        tabcour.setItems(list);
    }    

    @FXML
    private void cour(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void serie(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("Serieliste.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void GetGestionPediatre(ActionEvent event) {
    }

    @FXML
    private void disconnect(MouseEvent event) {
    }


    @FXML
    private void update(ActionEvent event) throws IOException, SQLException {
        
         Serie p = tabcour.getSelectionModel().getSelectedItem();//get el ligne eli selectionéé
         FXMLLoader loader = new FXMLLoader(getClass().getResource("SerieUpdate.fxml"));
         Parent root = loader.load();
         SerieUpdateController scene2Controller = loader.getController();//bech ya9ouloo el 5idma fi controller apppl li cont mat3 page mofi
           scene2Controller.setIdserie(p.getIdserie()+"");
           scene2Controller.setLien(p.getLien());
           scene2Controller.setNomserie(p.getNomserie()); 
           scene2Controller.setDescription(p.getDescription()); 

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hello World");
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
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

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("Addserie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    
}
