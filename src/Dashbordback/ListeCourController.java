/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashbordback;

import Entity.Cours;
import Service.ServiceCours;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ListeCourController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnPackages;
    @FXML
    private StackPane afficherPlatStackPane;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
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
    
   ServiceCours svr = new ServiceCours();
    @FXML
    private Button btncour;
    @FXML
    private TextField recherche;
    @FXML
    private MenuItem actualiser;
    @FXML
    private Pane ajouter;
    @FXML
    private Button supp;
    @FXML
    private Button consulter;
    @FXML
    private AnchorPane layer1;
    @FXML
    private Pane modifier;
    @FXML
    private Button update;
    @FXML
    private TableColumn<Cours, Integer> idcour;
 

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
            Logger.getLogger(Dashbordback.ListeCourController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void Gestioncour(ActionEvent event) {
    }

    @FXML
    private void recherche(ActionEvent event) {
    }

    @FXML
    private void cour(InputMethodEvent event) {
    }

    @FXML
    private void actualiser(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    private void ajouter(ActionEvent event) throws IOException {
          Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Addcour.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
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
    private void consulter(ActionEvent event) throws IOException {
         Cours p = tabcour.getSelectionModel().getSelectedItem();//get el ligne eli selectionéé
     FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsCour.fxml"));
            Parent root = loader.load();
           DetailsCourController scene2Controller = loader.getController();//bech ya9ouloo el 5idma fi controller apppl li cont mat3 page mofi
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
    private void update(ActionEvent event) throws IOException {
         Cours p = tabcour.getSelectionModel().getSelectedItem();//get el ligne eli selectionéé
     FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCour.fxml"));
            Parent root = loader.load();
           UpdateCourController scene2Controller = loader.getController();//bech ya9ouloo el 5idma fi controller apppl li cont mat3 page mofi
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
    private void add(ActionEvent event) throws IOException {
           Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Addcour.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    
     

    
   
    
}
