/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import Entity.Cours;
import Service.ServiceCours;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class CouraddController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button cour;
    @FXML
    private Button serie;
    @FXML
    private Label LblUserName;
    @FXML
    private TextField Email;
    @FXML
    private ImageView img;
    @FXML
    private TextField nomchapitre;
    @FXML
    private TextField matiere;
    @FXML
    private TextField niveau;
    @FXML
    private TextField lien;
    @FXML
    private Button pdf;
    @FXML
    private Button ajouter;
    private ListView<?> aa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void ajouter(ActionEvent event)throws IOException,SQLException {
       
        String Niveau = niveau.getText();//tfnom el id
        String Matiere = matiere.getText();
        String Nomchapitre= nomchapitre.getText() ;
        String email= Email.getText() ;
        String Lien= lien.getText() ;
        
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
    
        
    @FXML
    private void pdf(ActionEvent event) {
         FileChooser fc = new FileChooser();
         fc.setInitialDirectory(new File("C:\\Users\\acer\\Documents\\NetBeansProjects\\Ecolepi\\src\\pdf"));
         fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files"));
         
        File selectfile = fc.showOpenDialog(null);
        if (selectfile !=null)
        {
            aa.getItems();
        } else {
            System.out.println ("file not valid");
            
        }
    }


   
    
}
