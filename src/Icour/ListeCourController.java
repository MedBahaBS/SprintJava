/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Icour;

import Entity.Cours;
import Service.ServiceCours;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ListeCourController implements Initializable {

    @FXML
    private TableView<Cours> tabcour;
  
    @FXML
    private TableColumn<Cours, String> niveau;
    @FXML
    private TableColumn<Cours, String> matiere;
    @FXML
    private TableColumn<Cours, String> nomchap;
   @FXML
    private TableColumn<Cours, String> email;
    @FXML
    private TableColumn<Cours, String> lien;
    @FXML
    private TableColumn<Cours, Date> date;
    @FXML
    private Button retour;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
ServiceCours svr = new ServiceCours();
    @FXML
    private Button add;
    @FXML
    private Button liste;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      ObservableList<Cours> list  = FXCollections.observableArrayList();

         
        try {
            for(Cours P: svr.readAll())
                list.add(P);
        } catch (SQLException ex) {
            Logger.getLogger(ListecourController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
         matiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
         nomchap.setCellValueFactory(new PropertyValueFactory<>("nomchapitre"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
         lien.setCellValueFactory(new PropertyValueFactory<>("lien"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        //load dummy data
       
        tabcour.setItems(list);
    }    

    @FXML
    private void consulter(ActionEvent event) throws IOException {
          javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Consultercour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
                   ObservableList<Cours> SelectedRows, allpeople;
     
     allpeople = tabcour.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tabcour.getSelectionModel().getSelectedItems();
     
     for(Cours c:SelectedRows){
       allpeople.remove(c);
     svr.delete(c.getIdcour());
     }
    }
    

 
    @FXML
    private void modifier(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("UpdateCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
         javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("AjouterCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();

    }

    @FXML
    private void liste(ActionEvent event) throws IOException {
         javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();

    }

    private static class ListecourController {

        public ListecourController() {
        }}}
    
    

