/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.util.Duration;
import org.controlsfx.control.PopOver;
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
public class AjoutPlatController implements Initializable {

    @FXML
    public JFXComboBox<String> entree;
    @FXML
    public JFXComboBox<String> plat_principal;
    @FXML
    public JFXComboBox<String> dessert;

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer2;
    @FXML
    private JFXButton signin;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
    @FXML
    private JFXButton signup;
    ObservableList<String> Type = FXCollections.observableArrayList("dessert", "plat principal", "entree");
    ObservableList<String> Jour = FXCollections.observableArrayList("lundi", "mardi", "mercredi", "jeudi", "vendredi");
    @FXML
    public JFXComboBox<String> type_plat;
    @FXML
    private JFXButton btnsignup;
    @FXML
    private JFXButton btnsignin;

    @FXML
    private ImageView imageView;
    @FXML
    private TextField nomPlat;

    @FXML
    public JFXComboBox<String> NomjourMenu;

    @FXML
    private AnchorPane layer1;
    private File file = null;

    private Image image;
    @FXML
    private Button btnBrowser;

    private FileChooser fileChooser;
    platService platService = new platService();
    menuService menuService = new menuService();
 private NewsLetterService newsLetter = new NewsLetterService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            type_plat.setEditable(false);
            NomjourMenu.setEditable(false);
            entree.setEditable(false);
            plat_principal.setEditable(false);
            dessert.setEditable(false);
            // pour le fileChooser
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                    new FileChooser.ExtensionFilter("Images", "*.*"),
                    new FileChooser.ExtensionFilter("Text File", "*.txt*"));
            PopOver popOver4 = platService.popNotification("le nom ne doit pas contenir aucun caractére spécial");
            nomPlat.setOnMouseEntered(mouseEvent -> {
                popOver4.show(nomPlat);
            });
            nomPlat.setOnMouseExited(mouseEvent -> {
                popOver4.hide();
            });
        } catch (Exception e) {
        }
        HashMap<String, Integer> mapTypeEntree = platService.getPlatTypeEntree();
        HashMap<String, Integer> mapTypePlatprincipal = platService.getPlatTypePlatPrincipal();
        HashMap<String, Integer> mapTypeDessert = platService.getPlatTypeDessert();
        for (String s : mapTypeEntree.keySet()) {
            entree.getItems().add(s);
        }
        for (String s : mapTypePlatprincipal.keySet()) {
            plat_principal.getItems().add(s);
        }

        for (String s : mapTypeDessert.keySet()) {
            dessert.getItems().add(s);
        }
        // TODO
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        btnsignin.setVisible(false);
        NomjourMenu.setVisible(false);
        try {
            NomjourMenu.setVisible(false);
            NomjourMenu.setItems(Jour);

        } catch (Exception e) {
        }
        entree.setVisible(false);
        plat_principal.setVisible(false);
        dessert.setVisible(false);

        nomPlat.setVisible(true);

        type_plat.setVisible(true);
        try {
            type_plat.setVisible(true);
            type_plat.setItems(Type);

        } catch (Exception e) {
        }
        btnBrowser.setVisible(true);
    }

    @FXML
    private void btn(MouseEvent even) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);

        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);

        signin.setVisible(false);
        btnsignup.setVisible(false);
        NomjourMenu.setVisible(true);

        entree.setVisible(true);
        plat_principal.setVisible(true);
        dessert.setVisible(true);

        nomPlat.setVisible(false);

        type_plat.setVisible(false);

        btnBrowser.setVisible(false);
        slide.setOnFinished((e -> {
            // HashMap<String, Integer> mapCategorie = categoriesClubService.getAllCategorie();

            nomPlat.setText("");
            type_plat.setValue("type plat");
//            image.setText("");

        }));
    }

    @FXML
    private void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);
        btnsignin.setVisible(false);

        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        signin.setVisible(true);
        btnsignup.setVisible(true);
        NomjourMenu.setVisible(false);
        entree.setVisible(false);
        plat_principal.setVisible(false);
        dessert.setVisible(false);
        btnBrowser.setVisible(true);
        nomPlat.setVisible(true);
//        image.setVisible(true);
        type_plat.setVisible(true);
        slide.setOnFinished((e -> {
            HashMap<String, Integer> mapTypeEntree = platService.getPlatTypeEntree();
            HashMap<String, Integer> mapTypePlatprincipal = platService.getPlatTypePlatPrincipal();
            HashMap<String, Integer> mapTypeDessert = platService.getPlatTypeDessert();
            for (String s : mapTypeEntree.keySet()) {
                entree.getItems().add(s);
            }
            for (String s : mapTypePlatprincipal.keySet()) {
                plat_principal.getItems().add(s);
            }

            for (String s : mapTypeDessert.keySet()) {
                dessert.getItems().add(s);
            }
        }));
    }

    @FXML
    private void quitter() {
        // get a handle to the stage
        Stage stage = (Stage) layer1.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void btnsignup(MouseEvent event) throws SQLException {
        System.out.println("a ajoute");
        plat p = new plat();
        p.setNomPlat(nomPlat.getText());
//     
        String afficherimage;
        afficherimage = replaceFile(file.getAbsolutePath());
        p.setStatus("non reserve");
        p.setType(type_plat.getValue());

        p.setImage(afficherimage);
        //   p.setPath(afficherimage);  

        String tilte = "Ajout validé";

        if (nomPlat.getText().isEmpty() && (type_plat.getValue().isEmpty())) {
            String tire = "Champ Vide";
            String message = "tous les champs doivent être remplis";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tire);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else if (!platService.validationChaineSimpleSansEspace(nomPlat.getText())) {
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
            String extension = afficherimage.substring(afficherimage.lastIndexOf("."), afficherimage.length());
            if (extension.equals(".jpg") || extension.equals(".png")) {
                platService.ajouterPlat(p);
                String message = "votre ajout a été bien enregistré.";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(tilte);
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

    }

    @FXML
    private void btnsignin(MouseEvent event) throws SQLException {
        HashMap<String, Integer> mapTypeEntree = platService.getPlatTypeEntree();
        HashMap<String, Integer> mapTypePlatprincipal = platService.getPlatTypePlatPrincipal();
        HashMap<String, Integer> mapTypeDessert = platService.getPlatTypeDessert();
        
        System.out.println("a ajoute");
        menu m = new menu();
        
        m.setJourMenu(NomjourMenu.getValue());
        
        int id_entree = mapTypeEntree.get(entree.getValue());
        m.setEntree(id_entree);
        
        int id_platprincipal = mapTypePlatprincipal.get(plat_principal.getValue());
        m.setPlatprincipale(id_platprincipal);
        
        int id_dessert = mapTypeDessert.get(dessert.getValue());
         m.setDessert(id_dessert);
        
        m.setNbrLike(0);
        m.setNbrFoisLike(0);
        m.setMoyenneLike(0);
        m.setPlat_id(0);

        platService.modifierStatus(id_entree);
        platService.modifierStatus(id_platprincipal);
        platService.modifierStatus(id_dessert);
       
        
        String tilte = "Ajout validé";
        
         if (entree.getValue().isEmpty()) {
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
            
                menuService.ajouterMenu(m);
                String message = "votre ajout a été bien enregistré.";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                
                List<String> listeEmail = newsLetter.retournerListeEmails();
                for (String i : listeEmail) {
                    System.out.println(i);
                    newsLetter.sendMail("linda.guesmi@esprit.tn","samsung11", i, "nouveau menu","Notre nouveau menu est :"+ entree.getValue()+" "+plat_principal.getValue() +" "+dessert.getValue());
                }
          
        }
    }

    @FXML
    private void handleBrowser(ActionEvent event) {

        Stage stage = (Stage) layer1.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            //System.out.println("" + file.getAbsolutePath());
            try {
                image = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(),
                        imageView.getFitHeight(), true, true);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
            } catch (Exception e) {
                System.out.println("lenna");
            }

        }
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
        return "plat_" + saltStr;
    }

    private String replaceFile(String file) {

        String extension = file.substring(file.lastIndexOf("."), file.length());
        //System.out.println(extension);

        String filename = generateFileName() + extension;

        Path sourceDirectory = Paths.get(file);
        Path targetDirectory = Paths.get("C:\\wamp64\\www\\pidev\\web\\" + filename);

        try {
            //copy source to target using Files Class
            Files.copy(sourceDirectory, targetDirectory);
        } catch (IOException ex) {
            //Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ahawa");
        }

        return filename;
    }
}
