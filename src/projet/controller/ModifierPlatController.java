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
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import projet.entities.plat;
import projet.services.platService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierPlatController implements Initializable {
 @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
     @FXML
    private AnchorPane layer1;
     
    @FXML
    public AnchorPane GUI;
    @FXML
    public StackPane modifierEvenementStackPane;
    public static int id_plat;
    
    public static String nomAfficher;
    @FXML
    public JFXTextField nom_plat_fx;
    @FXML
    public Stage stage;
    @FXML
    public JFXComboBox<String> type_plat_fx;

    public Image image;
    public File file = null;
    public FileChooser fileChooser;
    @FXML
    public ImageView imageView;
    ObservableList<String> dataTypeEvenement = FXCollections.observableArrayList("Entree", "Plat principal", "Dessert");
    platService platService = new platService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // pour le fileChooser
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                    new FileChooser.ExtensionFilter("Images", "*.*"),
                    new FileChooser.ExtensionFilter("Text File", "*.txt*"));
            
             
            PopOver popOver4 = platService.popNotification("le nom ne doit pas contenir aucun caractére spécial");
            nom_plat_fx.setOnMouseEntered(mouseEvent -> {
                popOver4.show(nom_plat_fx);
            });
            nom_plat_fx.setOnMouseExited(mouseEvent -> {
                popOver4.hide();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            type_plat_fx.setValue("Choisissez");
            type_plat_fx.setItems(dataTypeEvenement);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBrowser(ActionEvent event) {
        stage = (Stage) GUI.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);

        // try { deskTOP.open(file); } catch (IOException e) { e.printStackTrace(); }
        if (file != null) {
            System.out.println("" + file.getAbsolutePath());
            image = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(),
                    imageView.getFitHeight(), true, true);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {

        Stage stage = (Stage) layer1.getScene().getWindow();
        stage.close();
    }

    private String generateFileName() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return "Plat" + saltStr;
    }

    private String replaceFile(String file) {
        String extension = file.substring(file.lastIndexOf("."), file.length());
        String filename = generateFileName() + extension;
        Path sourceDirectory = Paths.get(file);
        Path targetDirectory = Paths.get("C:\\wamp64\\www\\pidev\\web\\" + filename);
        try {
            Files.copy(sourceDirectory, targetDirectory);
        } catch (IOException ex) {
            Logger.getLogger(ModifierPlatController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filename;
    }

    @FXML
    private void modifierEvenement() {
     
        
        try {
            int idPlat = id_plat;
            String nomPlat = nom_plat_fx.getText();
            String typePlat = type_plat_fx.getValue();
            String afficheEvenement;

            if (file == null) {
                afficheEvenement = nomAfficher;
            } else {
                afficheEvenement = replaceFile(file.getAbsolutePath());
            }

            BoxBlur blur = new BoxBlur(2, 2, 2);
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            JFXButton button = new JFXButton("OKAY");
            button.getStyleClass().add("dialog-button");
            JFXDialog dialog = new JFXDialog(modifierEvenementStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {

                dialog.close();
            });

            plat evenement = new plat();
            evenement.setId(idPlat);
            evenement.setNomPlat(nomPlat);
            evenement.setType(typePlat);
            evenement.setImage(afficheEvenement);
           // boolean status = platService.modifierPlat(evenement);
              if (nomPlat.isEmpty() && (typePlat.isEmpty())) {
            String tire = "Champ Vide";
            String message = "tous les champs doivent être remplis";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tire);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else if (!platService.validationChaineSimpleSansEspace(nomPlat)) {
            String t = "Nom Catgeorie";
            String message = "le nom de la catégorie est non autorisé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(t);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            String extension = afficheEvenement.substring(afficheEvenement.lastIndexOf("."), afficheEvenement.length());
            if (extension.equals(".jpg") || extension.equals(".png")) {
                platService.modifierPlat(evenement);
                String message = "votre modification a été bien enregistré.";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
               // tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            } else {
                String ilte = "image uploadé";
                String message = "le fichier doit être de type jpg ou png";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(ilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(3000));
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierPlatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
