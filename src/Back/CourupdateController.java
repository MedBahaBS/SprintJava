/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import Entity.Cours;
import Service.ServiceCours;
import java.io.IOException;
import java.net.URL;
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
public class CourupdateController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button cour;
    @FXML
    private Button serie;
    @FXML
    private Label LblUserName;
    @FXML
    private Button update;
    @FXML
    private TextField Email;
    @FXML
    private ImageView img;
    @FXML
    private Button pdf;
    @FXML
    private TextField niveau;
    @FXML
    private TextField matiere;
    @FXML
    private TextField nomchapitre;
    @FXML
    private TextField lien;
    @FXML
    private Button annuler;
    @FXML
    private TextArea idcour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cour(ActionEvent event) {
    }

    @FXML
    private void serie(ActionEvent event) {
    }

    @FXML
    private void GetGestionPediatre(ActionEvent event) {
    }

    @FXML
    private void disconnect(MouseEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("modifier le cour ");
        confirmation.setHeaderText("Attention!!!");
        confirmation.setContentText(" êtes-vous sûr de  modifier ce cour?");
           
       Optional<ButtonType> result = confirmation.showAndWait();  
        if (result.get() == ButtonType.OK) {
         ServiceCours sv = new ServiceCours() ;
    int x =  (Integer)   Integer.parseInt(idcour.getText()) ;
    Cours p = new Cours();
    p.setIdcour(x);
    p.setNiveau(niveau.getText());
    p.setMatiere(matiere.getText());
    p.setNomchapitre(nomchapitre.getText());
    p.setEmail(Email.getText());
    p.setLien(lien.getText());
    sv.modifier(x,niveau.getText(),matiere.getText(),nomchapitre.getText(),Email.getText(),lien.getText());
      try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
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
    private void pdf(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    void setIdcour(String string) {
        this.idcour.setText(string);
     }

    void setNiveau(String niveau) {
         this.niveau.setText(niveau);
    }

    void setMatiere(String matiere) {
         this.matiere.setText(matiere);
    }

    void setNomchapitre(String nomchapitre) {
         this.nomchapitre.setText(nomchapitre);
    }

    void setEmail(String email) {
           this.Email.setText(email);
    }
    

    void setLien(String lien) {
         this.lien.setText(lien);
    }
    
}
