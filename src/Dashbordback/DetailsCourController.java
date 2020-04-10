/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashbordback;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class DetailsCourController extends Application implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private TextField niveau;
    @FXML
    private TextField matiere;
    @FXML
    private TextField nomchapitre;
    @FXML
    private TextField Email;
    @FXML
    private TextField lien;
    @FXML
    private Button retour;
    @FXML
    private TextArea idcour;
    @FXML
    private Label serie;
    @FXML
    private TextField lien1;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
    }


    @FXML
    private void retour(ActionEvent event) {
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

    @FXML
    private void pdf(ActionEvent event) {
        String str = lien.getText();
        String substr = str.substring(5,str.length());
        

        File file = new File(substr);
HostServices hostServices = getHostServices();
hostServices.showDocument(file.getAbsolutePath());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
