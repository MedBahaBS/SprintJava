/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CL.Interfaces;


import CL.Entities.Classe;
import CL.Entities.Reclamation;
import CL.Service.Crud_Reclamation;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox<String> COMBO;
    
     @FXML
    private Button modifier;
    
@FXML
    private Button rechercher;
    @FXML
    private Button consulter;
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
        COMBO.setItems(list);
    }    
    
    ObservableList <String> list =FXCollections.observableArrayList("Retard","Absence","cantine","club");
    
    
    @FXML
  public void loadReclamation() throws SQLException {
        /*Crud_Reclamation s = new Crud_Reclamation();
        ArrayList<Reclamation> stand = s.listerReclamation();
        ObservableList observableList = FXCollections.observableArrayList(stand);
        table.setItems(observableList);*/
        
        ListR =r.afficherReclamation("En attente","Traitée","Archivée");
        ObservableList <Reclamation> listReclamation=FXCollections.observableArrayList(ListR);
     coldaterec.setCellValueFactory(new PropertyValueFactory<>("DateReclamation") );
         coltyperec.setCellValueFactory(new PropertyValueFactory<>("TypeReclamation") );
         colcontenurec.setCellValueFactory(new PropertyValueFactory<>("ContenuReclamation"));

    table.setItems(listReclamation);
    }
     
     
     
     
     
    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
          Reclamation r2 =table.getSelectionModel().getSelectedItem();
    
           if("Traitée".equals(r2.getEtat())) {
     
         Alert alert= new Alert (Alert.AlertType.ERROR);
       alert.setTitle("ERROR!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("La réclamation est déja Traité, vous ne pouvez pas la modifier , veuillez entrez une aure révlamation");
         alert.showAndWait();
     
     }
         else
         {
    /*r.modifier(r2.getId(),typerec.getText(),contenurec.getText());*/
    r.modifier(r2.getId(),COMBO.getValue(),contenurec.getText()); 
    loadReclamation();
         }
    }
    
    
    
    
    
    @FXML
    private void ModifDetail(MouseEvent event) {
        Reclamation r2 =table.getSelectionModel().getSelectedItem();
   /*typerec.setText(r2.getTypeReclamation());*/
   if (r2==null){
    Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("ATTENTION!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("Rien n'est selectionné");
         alert.showAndWait();
    }
   else
   {
   contenurec.setText(r2.getContenuReclamation());
   COMBO.setValue(r2.getTypeReclamation());
   }
   
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
         
         if("Traitée".equals(r1.getEtat())) {
     
         Alert alert= new Alert (Alert.AlertType.ERROR);
       alert.setTitle("ERROR!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("La réclamation est déja Traité, vous ne pouvez pas la supprimer , veuillez entrez une aure révlamation");
         alert.showAndWait();
     
     }
         else
         {
          
         
       allpeople.remove(r1);
       r.delete(r1.getId());
       
       Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Suppression!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("La reclamation est bien supprimé");
         alert.showAndWait();
                 }
     }
    }

      
      
      
      
    
    


    
   

    
    
}
