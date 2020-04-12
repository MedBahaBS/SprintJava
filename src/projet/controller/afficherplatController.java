/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import projet.entities.plat;
import projet.services.platService;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.entities.menu;
import projet.entities.plat;
import projet.services.menuService;
import projet.services.platService;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import static projet.controller.ModifierMenuController.id_menu;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author hp
 */
public class afficherplatController implements Initializable {
   @FXML
    private JFXTextField rechercheBar;
    @FXML
    private HBox compteur;
      Boolean isIt = false;
int counter = 0;
    int counterMenu = 0;
    int counterPlat = 0;
    @FXML
    private Label countMenu;
    @FXML
    private Label countPlat;
    @FXML
    private Label c;
    @FXML
    private StackPane afficherPlatStackPane;
    @FXML
    private StackPane afficherMenuStackPane;

    @FXML
    private TableView<plat> listePlats;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nomPlat;
    @FXML
    private TableColumn<plat, ImageView> image;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> status;

    @FXML
    private TableView<menu> listeMenus;
    @FXML
    private TableColumn<?, ?> nbrLike;
     @FXML
    private TableColumn<?, ?> nbrFoisLike;
      @FXML
    private TableColumn<?, ?> moyenneLike;
    @FXML
    private TableColumn<?, ?> NomjourMenu;
    @FXML
    private TableColumn<?, ?> entree;
    @FXML
    private TableColumn<?, ?> plat_principal;
    @FXML
    private TableColumn<?, ?> dessert;
    platService platService = new platService();
    menuService menuService = new menuService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            afficherPlat();
            afficherMenu();
            compteurMenu();
            compteurPlat();
     
        } catch (SQLException ex) {
            Logger.getLogger(afficherplatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherPlat() throws SQLException {

        List<plat> myList = platService.getListPlats();
        ObservableList<plat> myObservableList = FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomPlat.setCellValueFactory(new PropertyValueFactory<>("nomPlat"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
       

        myList.forEach(e -> {
            myObservableList.add(e);
            listePlats.setItems(myObservableList);
        });
    }

    @FXML
    private void afficherMenu() throws SQLException {

        List<menu> myList = menuService.getListMenu();
        for (menu m : myList) {
            System.out.println(m.getJourMenu());
        }
        ObservableList<menu> myObservableList = FXCollections.observableArrayList();

        NomjourMenu.setCellValueFactory(new PropertyValueFactory<>("jourMenu"));

        entree.setCellValueFactory(new PropertyValueFactory<>("nomPlatEntree"));
        plat_principal.setCellValueFactory(new PropertyValueFactory<>("nomPlatPrincipal"));
        dessert.setCellValueFactory(new PropertyValueFactory<>("nomPlatDessert"));
         nbrLike.setCellValueFactory(new PropertyValueFactory<>("nbrLike"));
          nbrFoisLike.setCellValueFactory(new PropertyValueFactory<>("nbrFoisLike"));
          moyenneLike.setCellValueFactory(new PropertyValueFactory<>("moyenneLike"));


        myList.forEach(e -> {
            myObservableList.add(e);
            listeMenus.setItems(myObservableList);
        });
    }

    private int index() {
        int selectedItem = listePlats.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = listePlats.getSelectionModel().getSelectedIndex();

        // System.out.println(selectedItem);
        return selectedItem;
    }

    private int index2() {
        int selectedItem = listeMenus.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = listeMenus.getSelectionModel().getSelectedIndex();
        return selectedItem;
    }

    @FXML
    private void supprimerPlat(ActionEvent event) {
        try {

            int x = index();
            plat cat = listePlats.getSelectionModel().getSelectedItem();
            Alert a1 = new Alert(Alert.AlertType.WARNING);
            a1.setWidth(750);
            a1.setHeight(400);
            a1.setTitle("Supprimer plat");
            String message = "Attention vous allez supprimer ce plat\n";

            a1.setContentText(message);
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == ButtonType.OK) {
                platService.supprimerPlat(x);
                Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                a2.setTitle("Supprimer categorie");
                a2.setContentText("Plat supprimé avec succés!");
                a2.show();
                System.out.println("plat supprime");
                listePlats.getItems().clear();
                listePlats.getItems().addAll(platService.getListPlats());

            } else {
                a1.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void supprimerMenu(ActionEvent event) {
        try {

            int x = index2();
            System.out.println(x);
            menu cat = listeMenus.getSelectionModel().getSelectedItem();
            System.out.println(cat);
            Alert a1 = new Alert(Alert.AlertType.WARNING);
            a1.setWidth(750);
            a1.setHeight(400);
            a1.setTitle("Supprimer menu");
            String message = "Attention vous allez supprimer ce menu\n";

            a1.setContentText(message);
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == ButtonType.OK) {
                menuService.supprimerMenu(x);
                Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                a2.setTitle("Supprimer menu");
                a2.setContentText("Menu supprimé avec succés!");
                a2.show();
                System.out.println("menu supprime");
                listeMenus.getItems().clear();
                listeMenus.getItems().addAll(menuService.getListMenu());

            } else {
                a1.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void ajouterGUI(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/interfaces/ajoutPlat.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void modifierPlatAction(ActionEvent event) {

        plat listeselect = listePlats.getSelectionModel().getSelectedItem();
        if (listeselect == null) {
            System.out.println(listeselect);
          
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            JFXButton button = new JFXButton("OKAY");
            button.getStyleClass().add("dialog-button");
            JFXDialog dialog = new JFXDialog(afficherPlatStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
            dialogLayout.setHeading(new Label("Modification du plat "));
            dialogLayout.setBody(new Label("il faut selectionner un plat"));
            dialogLayout.setActions(button);
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
                afficherPlatStackPane.setEffect(null);
            });
            return;
        }

        try {
            /*Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/interfaces/ajoutPlat.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();*/
            Stage primaryStage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/interfaces/modifierPlat.fxml"));
            Parent root = loader.load();

            ModifierPlatController controller = (ModifierPlatController) loader.getController();
            controller.id_plat = listeselect.getId();
            controller.nom_plat_fx.setText(listeselect.getNomPlat());
            controller.type_plat_fx.setValue(listeselect.getType());
            controller.image = new Image("file:///C://wamp64//www//pidev//web//"+listeselect.getImage(), controller.imageView.getFitWidth(),
           controller.imageView.getFitHeight(), true, true);
            controller.imageView.setImage(controller.image);
            controller.imageView.setPreserveRatio(true);
            primaryStage.setTitle("Modifier Palt");
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            scene.getStylesheets().add(getClass().getResource("/projet/style/EvenementCss.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setMaxWidth(670);
            primaryStage.setMaxHeight(750);

            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void modifierMenuAction(ActionEvent event) {

        menu listeselect = listeMenus.getSelectionModel().getSelectedItem();
        if (listeselect == null) {
            System.out.println(listeselect);

            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            JFXButton button = new JFXButton("OKAY");
            button.getStyleClass().add("dialog-button");
            JFXDialog dialog = new JFXDialog(afficherMenuStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
            dialogLayout.setHeading(new Label("Modification du menu "));
            dialogLayout.setBody(new Label("il faut selectionner un menu"));
            dialogLayout.setActions(button);
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
                afficherMenuStackPane.setEffect(null);
            });
            return;
        }

        try {
            //pour que les champs soient rempli à la place de  // Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenementGUI.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/interfaces/modifierMenu.fxml"));
            Parent root = loader.load();
            ModifierMenuController controller = (ModifierMenuController) loader.getController();
            controller.id_menu = listeselect.getId();
            // System.out.println(listeselect.getEntree());

            controller.id_entree = listeselect.getEntree();
            // System.out.println(listeselect.getPlatprincipale());

            controller.id_platprincipal = listeselect.getPlatprincipale();
            // System.out.println(listeselect.getDessert());

            controller.id_dessert = listeselect.getDessert();
            controller.jourMenu_fx.setValue(listeselect.getJourMenu());
            controller.Entree_fx.setValue(listeselect.getNomPlatEntree());
            controller.PLat_Principal_fx.setValue(listeselect.getNomPlatPrincipal());
            controller.Dessert_fx.setValue(listeselect.getNomPlatDessert());
            Stage primaryStage = new Stage();

            primaryStage.setTitle("Modifier Menu");
            Scene scene = new Scene(root);
             scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
          //  scene.getStylesheets().add(getClass().getResource("/projet/style/EvenementCss.css").toExternalForm());
           // primaryStage.setScene(scene);
            primaryStage.setMaxWidth(670);
            primaryStage.setMaxHeight(750);
           
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex);
        }
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
                countMenu.setText(String.valueOf(counterMenu));
                int nbrClub=0;
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
                countPlat.setText(String.valueOf(counterPlat));
                int nbrPlats=0;
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
     
    @FXML
    void afficherMesPlatsGUI(ActionEvent event) throws SQLException {
     platService service =new platService();
        try {
            service.exportXLS();
             String tilte = "fichier pret";
                String message = "le fichier est telecharge";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
        } catch (WriteException ex) {
            Logger.getLogger(afficherplatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void rechercher(KeyEvent event) throws SQLException {

        if (!rechercheBar.getText().isEmpty()) {
            listePlats.setVisible(true);
            List<plat> myList = platService.rechercheCategories(rechercheBar.getText());
            ObservableList<plat> observableList = FXCollections.observableArrayList();

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomPlat.setCellValueFactory(new PropertyValueFactory<>("nomPlat"));
             image.setCellValueFactory(new PropertyValueFactory<>("image"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
            myList.forEach(e -> {

                observableList.add(e);
                // System.out.println(observableList);
            });
            listePlats.setItems(observableList);
        } else {
            if (rechercheBar.getText().isEmpty()) {
                listePlats.getItems().clear();
                listePlats.getItems().addAll(platService.getListPlats());
            }

        }
    }
}
