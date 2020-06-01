/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.Pulse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
//import animatefx.animation.Pulse;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private HBox conteneurMenu;
@FXML
    private HBox conteneurPlat;
@FXML
    private HBox conteneurInscription;
    @FXML
    private HBox compteur;
    Boolean isIt = false;
    int counter = 0;
    int counterMenu = 0;
    int counterPlat = 0;
    int counterInscrits = 0;
    @FXML
    private Label countMenu;
    @FXML
    private Label countPlat;
    @FXML
    private Label countInscrits;
    @FXML
    private StackPane afficherTsEvenementStackPane;

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

    public static ObservableList<Inscription> observableList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficherCategorieClub();
        try {
            compteurInscriptions();
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            compteurMenu();
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            compteurPlat();
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    public void compteurMenu() throws SQLException {
        Timer timer = new Timer();
        menuService ms = new menuService();
        if (ms.getListMenu().isEmpty()) {
            countMenu.setText(String.valueOf(0));
        } else {
            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                counterMenu++;
                new Pulse(conteneurMenu).play();
                countMenu.setText(String.valueOf(counterMenu));
                int nbrClub = 0;
                try {
                    nbrClub = ms.getListMenu().size();
                } catch (SQLException ex) {
                    Logger.getLogger(afficherplatController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (counterMenu == nbrClub) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(ms.getListMenu().size());
            timelineCat.play();
        }

    }

    public void compteurPlat() throws SQLException {
        Timer timer = new Timer();
        platService ps = new platService();
        if (ps.getListPlats().isEmpty()) {
            countPlat.setText(String.valueOf(0));
        } else {
            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                counterPlat++;
                new Pulse(conteneurPlat).play();
                countPlat.setText(String.valueOf(counterPlat));
                int nbrPlats = 0;
                try {
                    nbrPlats = ps.getListPlats().size();
                } catch (SQLException ex) {
                    Logger.getLogger(afficherplatController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (counterPlat == nbrPlats) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(ps.getListPlats().size());
            timelineCat.play();
        }
    }

    public void compteurInscriptions() throws SQLException {
        Timer timer = new Timer();
        InscriptionService ps = new InscriptionService();
        if (ps.selectAllInscris().isEmpty()) {
            countInscrits.setText(String.valueOf(0));
        } else {
            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                counterInscrits++;
                new Pulse(conteneurInscription).play();
                countInscrits.setText(String.valueOf(counterInscrits));
                int nbrInscrits = 0;
                nbrInscrits = ps.selectAllInscris().size();
                if (counterInscrits == nbrInscrits) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(ps.selectAllInscris().size());
            timelineCat.play();
        }
    }

}
