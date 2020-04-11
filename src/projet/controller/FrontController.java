/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import projet.entities.menu;
import projet.services.menuService;
import projet.services.platService;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import projet.services.NewsLetterService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FrontController implements Initializable {

    menu exp;
    private menu experience;
    @FXML
    private HBox content_product;
    @FXML
    private Label id1;
    @FXML
    private Label menu1;
    @FXML
    private Label menu2;
    @FXML
    private Label menu3;
    @FXML
    private Label menu4;
    @FXML
    private Label entree1;
    @FXML
    private Label entree2;
    @FXML
    private Label entree3;
    @FXML
    private Label entree4;
    @FXML
    private Label platP1;
    @FXML
    private Label platP2;
    @FXML
    private HBox hb;
    @FXML
    private HBox meilleursProduit;
    @FXML
    private Label platP3;
    @FXML
    private Label platP4;
    @FXML
    private Label dessert1;
    @FXML
    private Label dessert2;
    @FXML
    private Label dessert3;
    @FXML
    private Label dessert4;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<menu> myList = menuService.getListMenu();

            content_product.getChildren().clear();

            int index = 0;

            for (menu produit : myList) {
                /* try {
                    rating(produit);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                if (index % 5 == 0) {
                    row = new HBox();
                    row.getStyleClass().add("content-item");
                    content_product.getChildren().add(row);
                }

                VBox content = new VBox();

                Label title = new Label(produit.getJourMenu());
                title.getStyleClass().add("title_prod");

                title.setStyle("-fx-font-weight: bold");
                Label prix = new Label(produit.getNomPlatDessert());
                Label platp = new Label(produit.getNomPlatPrincipal());
                Label dessert = new Label(produit.getNomPlatEntree());
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

            /*rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              msg.setText("Rating : "+ t1.toString());
              }});*/

 /* for(int i=0;i<myList.size();i++){
           if(i==0){
        menu1.setText(myList.get(i).getJourMenu());
        entree1.setText(myList.get(i).getNomPlatEntree());
        platP1.setText(myList.get(i).getNomPlatPrincipal());
        dessert1.setText(myList.get(i).getNomPlatDessert());
        rating(myList.get(0));
  //                     System.out.println(myList.get(0).getNbrFoisLike());

           }
        else if(i==1)
                {
                 
       
        menu2.setText(myList.get(i).getJourMenu());
        entree2.setText(myList.get(i).getNomPlatEntree());
        platP2.setText(myList.get(i).getNomPlatPrincipal());
        dessert2.setText(myList.get(i).getNomPlatDessert());
        rating(myList.get(1));
    }
            else if(i==2)
                {
                 
       
        menu3.setText(myList.get(i).getJourMenu());
        entree3.setText(myList.get(i).getNomPlatEntree());
        platP3.setText(myList.get(i).getNomPlatPrincipal());
        dessert3.setText(myList.get(i).getNomPlatDessert());
    }
           
           else if(i==3)
                {
                 
       
        menu4.setText(myList.get(i).getJourMenu());
        entree4.setText(myList.get(i).getNomPlatEntree());
        platP4.setText(myList.get(i).getNomPlatPrincipal());
        dessert4.setText(myList.get(i).getNomPlatDessert());
    }
                }
       
         Label menu=new Label(myList.get(i).getJourMenu());
         Label platE=new Label(myList.get(i).getNomPlatEntree());
          Label platT=new Label(myList.get(i).getNomPlatPrincipal());
         hb.getChildren().addAll(menu,platE,platT);*/
 /*int index = 0;
            for (menu produit : myList) {

                if (index % 5 == 0) {
                    meilleursProduit = new HBox();
                    meilleursProduit.getStyleClass().add("content-item");
                }
                VBox content = new VBox();

                Label title = new Label(produit.getJourMenu());
                title.getStyleClass().add("title_prod");
                Label Entree = new Label(produit.getNomPlatEntree());
                Entree.getStyleClass().add("title_sub");
                Label PlatP = new Label(produit.getNomPlatPrincipal());
                PlatP.getStyleClass().add("title_sub");
                Label Dessert = new Label(produit.getNomPlatDessert());
                Dessert.getStyleClass().add("title_sub");
                Rating r = new Rating();

                content.getChildren().addAll(title, Entree, PlatP, Dessert, r);
                /* Button item = new Button("", content);
            item.setOnAction(event -> {
                detailExperience(produit);
            });

                meilleursProduit.getChildren().add(content);

                content_product.getChildren().add(meilleursProduit);

            }*/
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

}

   /* @FXML
    public void rating(menu exp) throws FileNotFoundException {
        rating.ratingProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
//            System.out.println("ahhhhhhhhhhhh:"+exp.getNbrFoisLike());
            msg.setText("Rating : " + t1.toString());
            //rating.setDisable(true);
            valeurEtoile = t1.intValue();

            String tilte = "Merci pour votre avis";
            String message = "On a approuvé votre avis.";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            //  System.out.println(exp.getId());
            System.out.println(valeurEtoile);
            menu ClubDeBase = new menu();
            int nbrFoisLike = exp.getNbrFoisLike();
            System.out.println("nombre precedent: " + nbrFoisLike);
            nbrFoisLike++;
            System.out.println(nbrFoisLike);
            int nbrLike = exp.getNbrLike();
            nbrLike += valeurEtoile;
            ClubDeBase.setNbrFoisLike(nbrFoisLike);
            float moyenneLike = (nbrLike / nbrFoisLike);
            ClubDeBase.setMoyenneLike(moyenneLike);
            ClubDeBase.setNbrLike(nbrLike);
            ClubDeBase.setId(exp.getId());
            menuService.modifierLike(ClubDeBase);
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        });*/

       

