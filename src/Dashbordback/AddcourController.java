/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashbordback;

import Entity.Cours;
import Service.ServiceCours;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AddcourController implements Initializable {

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
    private Button upload;
    @FXML
    private TextArea idcour;
   private FileChooser uploadPic;
 private File picPath;
  String imgUrl  ="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
                         String Niveau = niveau.getText();//tfnom el id
        String Matiere = matiere.getText();
        String Nomchapitre= nomchapitre.getText() ;
        String email= Email.getText() ;
        String Lien= imgUrl; 
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmer l'ajout");
        confirmation.setHeaderText("Confirmer");
        confirmation.setContentText("Voulez-vous ajouter cet cour ?");
        Optional<ButtonType> result = confirmation.showAndWait();  
        if (result.get() == ButtonType.OK) {

            if ("".equals(niveau.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("Le niveau est un champ obligatoire !");
                alert.showAndWait();
                } else if ("".equals(matiere.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention !!!");
                alert.setContentText("La matiere est un champ obligatoire !");
                alert.showAndWait();
                } else if ("".equals(nomchapitre.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText(" le nom cour  est un champ obligatoire  !");
                alert.showAndWait();
                }else if ("".equals(Email.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("L' e-mail est un champ obligatoire !");
                alert.showAndWait();
              }else if ("".equals(lien.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText(" l'ajout de cour est un champ obligatoire ! !");
                alert.showAndWait();
               } else {
          
        ServiceCours sp = new  ServiceCours();
        Cours C;
                C = new Cours (Niveau,Matiere,Nomchapitre,email,Lien);
       sp.ajouter(C);
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
                }else{
            System.out.println("pas d'ajout");
                        }
        }
    
    

    private void pdf(ActionEvent event) {
        
  
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void upload(ActionEvent event) {
       
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          uploadPic = new FileChooser();
            uploadPic.setTitle("Select the image you want to add");
            picPath = uploadPic.showOpenDialog(stage);
            System.out.println(picPath.toString());
            try {
                imgUrl = picPath.toURI().toURL().toExternalForm();
               
            lien.setText(imgUrl);
                
            } catch(IOException ex){
                System.err.println(ex.getMessage());
            }
        
        
    }
}
