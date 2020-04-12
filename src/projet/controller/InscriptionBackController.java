/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
//import animatefx.animation.Pulse;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import projet.entities.menu;
import projet.entities.Inscription;
import projet.services.platService;
import projet.services.menuService;
import projet.services.InscriptionService;
/**
 * FXML Controller class
 *
 * @author hp
 */

public class InscriptionBackController implements Initializable {
@FXML
    private StackPane afficherTsEvenementStackPane;
    @FXML
    private Pane conteneurCategories;
    @FXML
    private Pane conteneurInscrip;
    @FXML
    private Pane conteneurClubs;
    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

   

    @FXML
    public TableView<Inscription> listeClubs;

    @FXML
    public TableColumn<?, ?> id;
    @FXML
    public TableColumn<?, ?> idUser;
    @FXML
    public TableColumn<?, ?> status;
    @FXML
    private TableColumn<?, ?> actionD;
    @FXML
    private TableColumn<Inscription, String> action;
    
    
    InscriptionService service = new InscriptionService();
    
    Boolean isIt = false;
    public static ObservableList<Inscription> observableList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
   
        
      afficherCategorieClub();
    }    
     @FXML
    public void afficherCategorieClub() {
        List<Inscription> myList = service.selectAllInscris();
        
        observableList = FXCollections.observableArrayList();
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
      idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        action.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));
        actionD.setCellValueFactory(new PropertyValueFactory<>("btn_confirmer"));
        myList.forEach(e -> {
            observableList.add(e);
            listeClubs.setItems(observableList);
        });
    }

  

    
    
}
