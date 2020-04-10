/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import Entity.Cours;
import Service.ServiceCours;
import java.io.IOException;
import static java.lang.String.format;
import static java.lang.String.format;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.util.Collections.list;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ListeCourController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button cour;
    @FXML
    private Button serie;
    @FXML
    private Label LblUserName;
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
    private TableColumn<Cours, String> date;
    private CheckBox Cinema;
    @FXML
    private Button consulter;
    @FXML
    private Button update;
    @FXML
    private Button supp;
ServiceCours svr = new ServiceCours();
    @FXML
    private TableColumn<Cours, Integer> idcour;
    @FXML
    private Button ajouter;
    @FXML
    private CheckBox idniveau;
    @FXML
    private CheckBox idmat;
    @FXML
    private CheckBox idchap;
    @FXML
    private Button filter;

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
            Logger.getLogger(Back.ListeCourController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void consulter(ActionEvent event) {
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
      Cours p = tabcour.getSelectionModel().getSelectedItem();//get el ligne eli selectionéé
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Courupdate.fxml"));
            Parent root = loader.load();
           CourupdateController scene2Controller = loader.getController();//bech ya9ouloo el 5idma fi controller apppl li cont mat3 page mofi
           scene2Controller.setIdcour(p.getIdcour()+"");
           scene2Controller.setNiveau(p.getNiveau());
           scene2Controller.setMatiere(p.getMatiere()); 
           scene2Controller.setNomchapitre(p.getNomchapitre()); 
           scene2Controller.setEmail(p.getEmail());
           scene2Controller.setLien(p.getLien());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hello World");
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
}

    

    @FXML
    private void supp(ActionEvent event) throws SQLException {
      
       ObservableList<Cours> SelectedRows, allpeople;
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("supprimer le cour ");
        confirmation.setHeaderText("Attention!!!");
        confirmation.setContentText(" êtes-vous sûr de  supprimer  ce cour?");
           
       Optional<ButtonType> result = confirmation.showAndWait();  
        if (result.get() == ButtonType.OK) {
             
     allpeople = tabcour.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tabcour.getSelectionModel().getSelectedItems();
       
     for(Cours c:SelectedRows){
       allpeople.remove(c);
     svr.supprimer2parID(c.getIdcour());
     }
    }
    }
   


    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("Couradd.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void filter(ActionEvent event) {
        int idcour = 0;
        if (idniveau.isSelected()) {
            idcour = 1;
        }
        if (idmat.isSelected()) {
            idcour = 2;
        }
        if (idchap.isSelected()) {
            idcour =3;
        }
        try {
            tabcour.setItems(svr.displayFiltreCour(idcour));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    }

    
  

    


