/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.effects.JFXDepthManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import projet.entities.menu;
import projet.services.menuService;
import projet.services.platService;
import projet.controller.Acceuilfront;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import projet.entities.Inscription;
import projet.services.InscriptionService;
import projet.services.NewsLetterService;
import projet.entities.CommentaireEvenement;
import projet.services.ServiceCommentaireEvenement;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FrontController implements Initializable {

    /* commentaire */
    public CommentaireEvenement commentaire;
    @FXML
    private StackPane DetailsEvenementStackPane;

    @FXML
    private AnchorPane GUI;
    @FXML
    public ListView<String> ListView_commentaire;
    @FXML
    private TextArea commentaire_text_fx;

    @FXML
    private MenuItem modifier_commentaire_fx;

    @FXML
    private MenuItem supprimer_commentaire_fx;
    private ServiceCommentaireEvenement sc = new ServiceCommentaireEvenement();
    @FXML
    private VBox content;
    @FXML
    private VBox content2;

    public List<CommentaireEvenement> listCommentaireController;
    /*end commentaire */
    @FXML
    private StackPane afficherTsEvenementStackPane;

    @FXML
    private ScrollPane scrollPaneCommentaire;

    InscriptionService service = new InscriptionService();
    menu exp;
    private menu experience;
    @FXML
    private HBox content_product;
    @FXML
    private HBox content_top3;
    @FXML
    private HBox hb;
    @FXML
    private HBox meilleursProduit;

    menuService menuService = new menuService();
    @FXML
    private TextField emailField;
    private NewsLetterService newsLetter = new NewsLetterService();
    @FXML
    private Rating rating;
    @FXML
    private Label msg;
    public int valeurEtoile;
    private HBox row;
    private HBox row2;
    public static int id;

    public void setDataCommentaire() {

        content.getChildren().clear();
        commentaires();

    }
    ObservableList<CommentaireEvenement> dataComment = FXCollections.observableArrayList();

    public void commentaires() {
        dataComment.clear();
        try {
            dataComment.addAll(sc.afficherCommentaire());

            HBox hbox = new HBox();
            content.getChildren().add(hbox);
            int index = 0;

            JFXDepthManager.setDepth(hbox, 0);

            for (CommentaireEvenement commentaire : dataComment) {

                if (index % 1 == 0) {
                    hbox = new HBox();
                    content.getChildren().add(hbox);
                }

                Label c = new Label();
                c.setText(commentaire.getId_user() + " " + commentaire.getMeessage());

                HBox hb = new HBox();
                hb.getChildren().addAll(c);
                hb.setMargin(c, new Insets(1, 1, 1, 1));

                hbox.getChildren().add(hb);
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
public void setId(int id){
    this.id=id;
}
public int getId(){
    return this.id;
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("ahhhhhhhhhhhhhhhhh"+id);
        try {
            List<menu> myList = menuService.getListMenu();
            List<menu> myList1 = menuService.TopMenu();

            content_product.getChildren().clear();
            content_top3.getChildren().clear();
            int index = 0;

            for (menu produit : myList) {

                if (index % 5 == 0) {
                    row = new HBox();
                    row.getStyleClass().add("content-item");
                    content_product.getChildren().add(row);
                }

                VBox content = new VBox();

                Label title = new Label(produit.getJourMenu());
                title.getStyleClass().add("title_prod");

                title.setStyle("-fx-font-weight: bold");
                Label prix = new Label(produit.getNomPlatEntree());
                Label platp = new Label(produit.getNomPlatPrincipal());
                Label dessert = new Label(produit.getNomPlatDessert());
                Rating r = new Rating();
                r.setRating(0);
                r.ratingProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
                    r.setDisable(true);
                    System.out.println(t1.toString());
                    valeurEtoile = t1.intValue();

                    String tilte = "Merci pour votre avis";
                    String message = "On a approuvé votre avis.";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                    //  System.out.println(exp.getId());
                    System.out.println(valeurEtoile);
                    menu ClubDeBase = new menu();
                    int nbrFoisLike = produit.getNbrFoisLike();
                    System.out.println("nombre precedent: " + nbrFoisLike);
                    nbrFoisLike++;
                    System.out.println(nbrFoisLike);
                    int nbrLike = produit.getNbrLike();
                    nbrLike += valeurEtoile;
                    produit.setNbrFoisLike(nbrFoisLike);
                    float moyenneLike = (nbrLike / nbrFoisLike);
                    produit.setMoyenneLike(moyenneLike);
                    produit.setNbrLike(nbrLike);
                    produit.setId(produit.getId());
                    menuService.modifierLike(produit);
                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));
                });
                content.getChildren().addAll(title, prix, platp, dessert, r);
                //Button item = new Button("", content);

                row.getChildren().add(content);

                index++;
            }
            int i = 0;
            for (menu produit : myList1) {

                if (i % 3 == 0) {
                    row2 = new HBox();
                    row2.getStyleClass().add("content-item");
                    content_top3.getChildren().add(row2);
                }
                VBox content2 = new VBox();

                Label title = new Label(produit.getJourMenu());
                title.getStyleClass().add("title_prod");

                title.setStyle("-fx-font-weight: bold");
                Label label1 = new Label(produit.getNomPlatEntree());
                Label label2 = new Label(produit.getNomPlatPrincipal());
                Label label3 = new Label(produit.getNomPlatDessert());

                content2.getChildren().addAll(title, label1, label2, label3);
                System.out.println("lllllll");
                row2.getChildren().add(content2);

                i++;
            }
            sc.afficherCommentaire();
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void EnregitrerEmail() {

        if (!platService.validationEmail(emailField.getText())) {
            String tilte = "Email";
            String message = "votre Email n'est correcte";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            newsLetter.ajouterEmail(emailField.getText());
            String tilte = "Merci pour votre Confiance";
            String message = "votre email a été bien enregistré.";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

    @FXML
    private void SeDesabonner() {

        newsLetter.desabonner(emailField.getText());
        String tilte = "Nous sommes tristes";
        String message = "votre email a été bien supprimé.";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }

    @FXML
    public void ajouterInscrip(ActionEvent even) {

        Inscription insc = new Inscription();

        insc.setStatus("non traitée");
        // System.out.println(id);
        System.out.println(id);
        insc.setIdUser(id);
        System.out.println("hjbjkbjkb");
        service.ajouterInscription(insc);

        String tilte = "Inscription enregistre";
        String message = "votre inscription a été bien enregistrée.";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }

    @FXML
    public void ajouterCommentaireEvenement() throws SQLException {
        //String contenueCommentaireEvenement = commentaire_text_fx.getText();
        BoxBlur blur = new BoxBlur(2, 2, 2);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OKAY");
        button.getStyleClass().add("dialog-button");
        JFXDialog dialog = new JFXDialog(DetailsEvenementStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            dialog.close();
        });
        if ((commentaire_text_fx.getText()).isEmpty()) {
            dialogLayout.setHeading(new Label("Champ commentaire est vide"));
            dialogLayout.setBody(new Label("vous devez ecrir un commentaire"));
            dialogLayout.setActions(button);
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
                GUI.setEffect(null);
            });
            GUI.setEffect(blur);
            return;
        }
        /* ListView_commentaire.getItems().add(LocalDate.now() + " Ajouter par " + recupererUtilisateurConnecte.getNom_Utilisateur() +
                ": \"" + contenueCommentaireEvenement + "\"");*/
        //recupererUtilisateurConnecte.getNom_Utilisateur()+" : "+contenueCommentaireEvenement a la place de requette qui n'a pas marché 
        //commentaire.setContenu_commentaire(contenueCommentaireEvenement);
       
        sc.ajouterCommentaireEvenement(commentaire_text_fx.getText(),id);
         setDataCommentaire();
        String tilte = "Commentaire Accepté";
        String message = " Merci pour votre avis ";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

        commentaire_text_fx.clear();
    }
    /*@FXML
     public void Top3(){
         List<menu> myList1 = menuService.TopMenu();
         content_top3.getChildren().clear();
         int i = 0;
         for (menu produit : myList1) {
             
             if (i % 3 == 0) {
                 row2 = new HBox();
                 row2.getStyleClass().add("content-item");
                 content_top3.getChildren().add(row2);
             }
             VBox content2 = new VBox();
             
             Label title = new Label(produit.getJourMenu());
             title.getStyleClass().add("title_prod");
             
             title.setStyle("-fx-font-weight: bold");
             Label label1 = new Label(produit.getNomPlatDessert());
             Label label2 = new Label(produit.getNomPlatPrincipal());
             Label label3 = new Label(produit.getNomPlatEntree());
             
             content2.getChildren().addAll(label1,label2, label3);
             System.out.println("lllllll");
             row2.getChildren().add(content2);
             
             i++;
         }
            
           
     
     }*/

}
