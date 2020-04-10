/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashbordback;

import Back.SerieUpdateController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
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
public class ListeSerieController implements Initializable {

    @FXML
    private StackPane afficherPlatStackPane;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
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
    @FXML
    private TableColumn<Serie, Integer> idserie;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField recherche;
    @FXML
    private Button update;
    @FXML
    private Button supp;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btncour;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnPackages;
 ServiceSerie ser = new ServiceSerie();
 ServiceCours courr = new ServiceCours();
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
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Addserie.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        
    }

    @FXML
    private void recherche(ActionEvent event) {
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
          
         Serie p = tabcour.getSelectionModel().getSelectedItem();//get el ligne eli selectionéé
         FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateSerie.fxml"));
         Parent root = loader.load();
        UpdateSerieController scene2Controller = loader.getController();//bech ya9ouloo el 5idma fi controller apppl li cont mat3 page mofi
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
    private void supprimer(ActionEvent event) throws SQLException {
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
    private void cour(InputMethodEvent event) {
    }

    @FXML
    private void Gestioncour(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    
}
