/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Matiere;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Crud_Matiere;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterMatiereController implements Initializable {
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfNbrseance;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfCoefficient;
    @FXML
    private Button ajouter;
    
    Crud_Matiere m =new Crud_Matiere();
    @FXML
    private TableView<Matiere> table;
    @FXML
    private TableColumn<Matiere, String> colNom;
    @FXML
    private TableColumn<Matiere, Integer> colNbseance;
    @FXML
    private TableColumn<Matiere, String> colDescription;
    @FXML
    private TableColumn<Matiere, Integer> colCoefficient;
      List<Matiere> listM=new ArrayList<>();
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private ImageView image;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button search;
    @FXML
    private ImageView image1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterMatiereController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnValider(ActionEvent event) throws SQLException, IOException {
       //Crud_Matiere m = new Crud_Matiere(); //hethi button shiha heka controlleur ghalet heka 3leh makhedmetlekich lezemek t3awed projet jdid mnathemm okaay emmemm yaatik sa7a :* ,maghir mzye sahbi <3

        //Matiere m1 = new Matiere(tfNom.getText(),Integer.valueOf(tfNbrseance.getText()),tfDescription.getText(),Integer.valueOf(tfCoefficient.getText()));
      // m.ajouter(m1);
       
       if ((event.getSource() == ajouter) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("interfaceAjoutMatiere.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
          //afficher();
    }}

    private void afficher() throws SQLException {
          List<Matiere> listM=new ArrayList<>();
           listM =m.afficherMatiere();
    ObservableList <Matiere> listMatiere=FXCollections.observableArrayList(listM);
    colNom.setCellValueFactory(new PropertyValueFactory<>("nom") );
    colNbseance.setCellValueFactory(new PropertyValueFactory<>("nbrseance") );
    colDescription.setCellValueFactory(new PropertyValueFactory<>("description") );
    colCoefficient.setCellValueFactory(new PropertyValueFactory<>("coefficient") );

    table.setItems(listMatiere);

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         ObservableList<Matiere> SelectedRows, allpeople;
     
     allpeople = table.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =table.getSelectionModel().getSelectedItems();
     
     for(Matiere m1:SelectedRows){
       allpeople.remove(m1);
       m.delete(m1.getIdmatiere());
     }
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
            Matiere m2 =table.getSelectionModel().getSelectedItem();
//egtrth imen moch hedhy normalement integer.valueoff
    m.modifier(m2.getIdmatiere(),tfNom.getText(),Integer.valueOf(tfNbrseance.getText()),tfDescription.getText(),Integer.valueOf(tfCoefficient.getText()));
    afficher();
    }

    @FXML
    private void modifRaf(MouseEvent event) {
            Matiere m2 =table.getSelectionModel().getSelectedItem();
tfNom.setText(m2.getNom());//nom khater text field lezem string okk
tfNbrseance.setText(String.valueOf(m2.getNbrseance()));
tfDescription.setText(m2.getDescription());
tfCoefficient.setText(String.valueOf(m2.getCoefficient()));
    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
        String text = search.getText();
              ObservableList<Matiere> listu  = FXCollections.observableArrayList();
    
         
         for(Matiere bb: m.RechercherMatiere(text))
             listu.add(bb);
            table.setItems(listu); 
     
      
         
    }
    
    
}
    

