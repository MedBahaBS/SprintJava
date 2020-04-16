/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static projet.controller.ModifierPlatController.id_plat;
import static projet.controller.ModifierPlatController.nomAfficher;
import projet.entities.menu;
import projet.entities.plat;
import projet.services.NewsLetterService;
import projet.services.menuService;
import projet.services.platService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierMenuController implements Initializable {
    
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private AnchorPane layer1;

    @FXML
    public AnchorPane GUI2;
    @FXML
    public StackPane modifierMenuStackPane;
    public static int id_menu;
    public static int id_entree;
    public static int id_platprincipal;
    public static int id_dessert;
    @FXML
    public JFXComboBox<String> jourMenu_fx;
    @FXML
    public JFXComboBox<String> Entree_fx;
    @FXML
    public JFXComboBox<String> PLat_Principal_fx;
    @FXML
    public JFXComboBox<String> Dessert_fx;

    ObservableList<String> Jour = FXCollections.observableArrayList("lundi", "mardi", "mercredi", "jeudi", "vendredi");

    menuService menuService = new menuService();
    platService platService = new platService();
    private NewsLetterService newsLetter = new NewsLetterService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            jourMenu_fx.setValue("Choisissez");
            jourMenu_fx.setItems(Jour);
            HashMap<String, Integer> mapTypeEntree = platService.getPlatTypeEntree();
            HashMap<String, Integer> mapTypePlatprincipal = platService.getPlatTypePlatPrincipal();
            HashMap<String, Integer> mapTypeDessert = platService.getPlatTypeDessert();
            for (String s : mapTypeEntree.keySet()) {
                Entree_fx.getItems().add(s);
            }
            for (String s : mapTypePlatprincipal.keySet()) {
                PLat_Principal_fx.getItems().add(s);
            }

            for (String s : mapTypeDessert.keySet()) {
                Dessert_fx.getItems().add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void modifierMenu() throws SQLException {
        HashMap<String, Integer> mapTypeEntree = platService.getPlatTypeEntree();
        HashMap<String, Integer> mapTypePlatprincipal = platService.getPlatTypePlatPrincipal();
        HashMap<String, Integer> mapTypeDessert = platService.getPlatTypeDessert();
        int idMenu = id_menu;
        String jourMenu = jourMenu_fx.getValue();
        /* String Entree = Entree_fx.getValue();
        String PLat_Principal = PLat_Principal_fx.getValue();
        String Dessert = Dessert_fx.getValue();*/
        BoxBlur blur = new BoxBlur(2, 2, 2);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OKAY");
        button.getStyleClass().add("dialog-button");
        JFXDialog dialog = new JFXDialog(modifierMenuStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {

            dialog.close();
        });
        menu m = new menu();
        m.setId(idMenu);
        m.setJourMenu(jourMenu);
        System.out.println(id_entree);
        System.out.println(id_platprincipal);
        System.out.println(id_dessert);
        if (mapTypeEntree.get(Entree_fx.getValue()) != null) {
            id_entree = mapTypeEntree.get(Entree_fx.getValue());
        }
        if (mapTypePlatprincipal.get(PLat_Principal_fx.getValue()) != null) {
            id_platprincipal = mapTypePlatprincipal.get(PLat_Principal_fx.getValue());
        }
        if (mapTypeDessert.get(Dessert_fx.getValue()) != null) {
            id_dessert = mapTypeDessert.get(Dessert_fx.getValue());
        }
        // 
        System.out.println(id_entree);
        m.setEntree(id_entree);

        m.setPlatprincipale(id_platprincipal);

        m.setDessert(id_dessert);

        if (Entree_fx.getValue().isEmpty()) {
            String tire = "Champ Vide";
            String message = "tous les champs doivent être remplis";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tire);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));

        } else {

            menuService.modifierMenu(m);
            List<String> listeEmail = newsLetter.retournerListeEmails();
            for (String i : listeEmail) {
                System.out.println(i);
                newsLetter.sendMail("linda.guesmi@esprit.tn", "samsung11", i, "nouveau menu", "Notre nouveau menu est :" + Entree_fx.getValue() + " " + PLat_Principal_fx.getValue() + " " + Dessert_fx.getValue());
            }
            String message = "votre modification a été bien enregistré.";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            //  tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        }
        //boolean status = menuService.modifierMenu(m);
    }

    @FXML
    private void Annuler(ActionEvent event) {

        Stage stage = (Stage) layer1.getScene().getWindow();
        stage.close();
    }
}
