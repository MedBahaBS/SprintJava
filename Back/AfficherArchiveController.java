/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CL.Back;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherArchiveController implements Initializable {
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, String> coldaterec;
    @FXML
    private TableColumn<Reclamation, String> colcontenurec;
    @FXML
    private TableColumn<Reclamation, String> coltyperec;
    @FXML
    private TableColumn<Reclamation, String> Etat;
    @FXML
    private TableColumn<Reclamation ,String> archiver;
    List<Reclamation> ListR=new ArrayList();
 Crud_Reclamation r = new Crud_Reclamation();
    @FXML
    private Button Retour1;
    @FXML
    private Button Supprimer;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {  
            
            
            
            loadReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

     public void loadReclamation() throws SQLException {
        /*Crud_Reclamation s = new Crud_Reclamation();
        ArrayList<Reclamation> stand = s.listerReclamation();
        ObservableList observableList = FXCollections.observableArrayList(stand);
        table.setItems(observableList);*/
        
        
         
        ListR =r.afficherReclamation("","","Archivée");
         
        ObservableList <Reclamation> listReclamation=FXCollections.observableArrayList(ListR);
     coldaterec.setCellValueFactory(new PropertyValueFactory<>("DateReclamation") );
         coltyperec.setCellValueFactory(new PropertyValueFactory<>("TypeReclamation") );
         colcontenurec.setCellValueFactory(new PropertyValueFactory<>("ContenuReclamation"));
         Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
             
    table.setItems(listReclamation);
    } 

    @FXML
    private void Retour1(ActionEvent event) {
    
     
     
      if ((event.getSource() == Retour1) )   {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/CL/Back/afficherReclamation.fxml"));
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