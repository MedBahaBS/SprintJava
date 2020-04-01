/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;


import Entities.Classe;
import Entities.Reclamation;
import Service.Crud_Reclamation;
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
public class ReclamationController implements Initializable {
    @FXML
    private TextField typerec;
    @FXML
    private TextField contenurec;
    @FXML
    private Button Modifier;
    @FXML
    private ImageView image;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, String> coldaterec;
    @FXML
    private TableColumn<Reclamation, String> coltyperec;
    @FXML
    private TableColumn<Reclamation, String> colcontenurec;

   Crud_Reclamation r = new Crud_Reclamation();
    @FXML
    private Button ajouter;
    @FXML
    private Button Supprimer;
    List<Reclamation> ListR=new ArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadReclamation();
            /* coldaterec.setCellValueFactory(new PropertyValueFactory<>("DateReclamation") );
            coltyperec.setCellValueFactory(new PropertyValueFactory<>("TypeReclamation") );
            colcontenurec.setCellValueFactory(new PropertyValueFactory<>("ContenuReclamation") */
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    
    @FXML
  public void loadReclamation() throws SQLException {
        /*Crud_Reclamation s = new Crud_Reclamation();
        ArrayList<Reclamation> stand = s.listerReclamation();
        ObservableList observableList = FXCollections.observableArrayList(stand);
        table.setItems(observableList);*/
        
        ListR =r.afficherReclamation();
        ObservableList <Reclamation> listReclamation=FXCollections.observableArrayList(ListR);
     coldaterec.setCellValueFactory(new PropertyValueFactory<>("DateReclamation") );
         coltyperec.setCellValueFactory(new PropertyValueFactory<>("TypeReclamation") );
         colcontenurec.setCellValueFactory(new PropertyValueFactory<>("ContenuReclamation"));

    table.setItems(listReclamation);
    }
     
     
     
     
     
    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
          Reclamation r2 =table.getSelectionModel().getSelectedItem();
    System.out.println(r2.getTypeReclamation());
    r.modifier(r2.getId(),typerec.getText(),contenurec.getText());
    loadReclamation();
    }
    
    
    
    
    
    @FXML
    private void ModifDetail(MouseEvent event) {
        Reclamation r2 =table.getSelectionModel().getSelectedItem();
   typerec.setText(r2.getTypeReclamation());
   contenurec.setText(r2.getContenuReclamation());
    }

    
   



 /*private void afficher() throws SQLException
    {
    listR =r.afficherReclamation();
    ObservableList <Reclamation> listReclamation =FXCollections.observableArrayList(listR);
    coldaterec.setCellValueFactory(new PropertyValueFactory<>("DateReclamation") );
    coltyperec.setCellValueFactory(new PropertyValueFactory<>("TypeReclamation") );
    colcontenurec.setCellValueFactory(new PropertyValueFactory<>("ContenuReclamation") );

    table.setItems(listReclamation);
    }*/

    @FXML
      private void Ajouter(ActionEvent event) throws SQLException {
       /* Reclamation r1 =new Reclamation(typerec.getText(),String.valueOf(contenurec.getText()));
        r.ajouter(r1);
        loadReclamation();*/
        
        
         
         if ((event.getSource() == ajouter) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
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
    private void Supprimer(ActionEvent event) throws SQLException {
              ObservableList<Reclamation> SelectedRows, allpeople;
     
     allpeople = table.getItems();
    
     SelectedRows =table.getSelectionModel().getSelectedItems();
     
     for(Reclamation r1:SelectedRows){
       allpeople.remove(r1);
       r.delete(r1.getId());
     }
    }

      
      
      
      
    
    


    
   

    
    
}
