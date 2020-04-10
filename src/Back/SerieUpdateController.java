/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import Entity.Cours;
import Entity.Serie;
import Service.ServiceCours;
import Service.ServiceSerie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SerieUpdateController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button cour;
    @FXML
    private Button serie;
    @FXML
    private Label LblUserName;
    @FXML
    private ImageView img;
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
    private Button Update;
    @FXML
    private Button annuler;
    ServiceCours s = new ServiceCours();
    @FXML
    private TextArea idserie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            List<Cours> cour = s.readAll();
            for(Cours c: cour)
            {
                cours.getItems().add(c.getNomchapitre());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddserieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
     private void cour(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void serie(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("Serieliste.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void GetGestionPediatre(ActionEvent event) {
    }

    @FXML
    private void disconnect(MouseEvent event) {
    }


    @FXML
    private void pdf(ActionEvent event) {
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
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
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Serieliste.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        
         Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmer l'annulation ");
        confirmation.setHeaderText("Confirmer");
        confirmation.setContentText("Voulez-vous annuler la modification  de cette serie ?");
        Optional<ButtonType> result = confirmation.showAndWait();  
        if (result.get() == ButtonType.OK) { 
         javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("Serieliste.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
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

