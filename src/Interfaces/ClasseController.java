/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Entities.Classe;
import Service.Crud_Classe;
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

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ClasseController implements Initializable {
    @FXML
    private TextField libelle;
    @FXML
    private TextField nbeleve;
    @FXML
    private TextField numsalle; //hethom les inouts mte3ii 
    @FXML
    private Button ajouter;
    //awel haja declari service mte3ek
    Crud_Classe c = new Crud_Classe();
    @FXML
    private TableView<Classe> table;
    @FXML
    private TableColumn<Classe, String> colLib;
    @FXML
    private TableColumn<Classe, Integer> colNbeleve;
    @FXML
    private TableColumn<Classe, Integer> colSalle;
    List<Classe> listC=new ArrayList<>();
    @FXML
    private Button modifier;
    @FXML
    private Button Supprimer;
        @FXML
    private ImageView image;
@FXML
    private Button rechercher;
    @FXML
    private Button consulter;
//hetha controller eli bich tekhdem fih chnia bich ysir fil le button koll 
    ///rit mba3ed matbadel fi scen builder temchi lil claase.xml wou tecliqui droit make controller
    //sa3at mathebech teta3malik enzel build w a3malha sinon saker projet w3awed 7el
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        try {
            afficher();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
      /*  Classe c1 =new Classe(libelle.getText(),Integer.valueOf(nbeleve.getText()),Integer.valueOf(numsalle.getText()));
        c.ajouter(c1);
        afficher();*/
        
         if ((event.getSource() == ajouter) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AjouterClasse.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
         
         
    }
    private void afficher() throws SQLException
    {
    listC =c.afficherClass();
    ObservableList <Classe> listClasse=FXCollections.observableArrayList(listC);
    colLib.setCellValueFactory(new PropertyValueFactory<>("libelle") );
    colNbeleve.setCellValueFactory(new PropertyValueFactory<>("Nombreeleve") );
    colSalle.setCellValueFactory(new PropertyValueFactory<>("Numsalle") );

    table.setItems(listClasse);
    }
    
    
   

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
      Classe c2 =table.getSelectionModel().getSelectedItem();
    System.out.println(c2.getLibelle());
    c.modifier(c2.getIdclasse(),libelle.getText(),Integer.valueOf(nbeleve.getText()),Integer.valueOf(numsalle.getText()) );
    afficher();
     
        
    }
    
    
    

    @FXML
    private void modifDetail(MouseEvent event) {
   Classe c2 =table.getSelectionModel().getSelectedItem();
   libelle.setText(c2.getLibelle());
   nbeleve.setText(String.valueOf(c2.getNombreeleve()));
   numsalle.setText(String.valueOf(c2.getNumsalle()));
    
   
    }
    
    
                 
    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        
                   ObservableList<Classe> SelectedRows, allpeople;
     
     allpeople = table.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =table.getSelectionModel().getSelectedItems();
     
     for(Classe c1:SelectedRows){
       allpeople.remove(c1);
       c.delete(c1.getIdclasse());
     }
    }

   /* @FXML
    private void rechercher(ActionEvent event) {
    
       
      

        try {
            table.setItems(c.chercherParId(Integer.valueOf(rechercher.getText())));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
     
    }*/

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void consulter(ActionEvent event) {
         if ((event.getSource() == consulter) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Back/afficherReclamation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
   
}


   
