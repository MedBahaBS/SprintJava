/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashbordback;

import Service.ServiceCours;
import Service.ServiceSerie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class UpdateSerieController implements Initializable {

    @FXML
    private AnchorPane updatecour;
    @FXML
    private Button pdf;
    @FXML
    private TextField nomserie;
    @FXML
    private TextField lien;
    @FXML
    private ChoiceBox<String> cours;
    @FXML
    private TextField description;
    @FXML
    private TextField nomserie1;
    @FXML
    private Button retour;
    @FXML
    private TextArea idserie;
    @FXML
    private Button update;
     ServiceCours s = new ServiceCours();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void pdf(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
         String cc = cours.getValue();
       System.err.println(cc);
       int Id = s.getCourbNom(cc);
         Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("modifier serie ");
        confirmation.setHeaderText("Attention!!!");
        confirmation.setContentText(" êtes-vous sûr de  modifier cette serie?");
           
       Optional<ButtonType> result = confirmation.showAndWait();  
        if (result.get() == ButtonType.OK) {
         ServiceSerie sv = new ServiceSerie () ;
    int x =  (Integer)   Integer.parseInt(idserie.getText()) ;
   
 

    sv.modifier(x,lien.getText(),nomserie.getText(),description.getText());
      try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListeSerie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }

   

    void setIdserie(String string) {
        this.idserie.setText(string);
    }

    void setNomserie(String nomserie) {
        this.nomserie.setText(nomserie);
    }

    void setLien(String lien) {
        this.lien.setText(lien);
    }

    void setDescription(String description) {
        this.description.setText(description);
    }
    }
    

