/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CL.Interfaces;

import CL.Entities.Classe;
import CL.Service.Crud_Classe;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterClasseController implements Initializable {
    @FXML
    private TextField ajtlib;
    @FXML
    private TextField ajtnbr;
    @FXML
    private TextField ajtsalle;
    @FXML
    private Button ok;
Crud_Classe c = new Crud_Classe();
    @FXML
    private Button Retour;
        
     
    @FXML
    private ComboBox<String> COMBO1;
    @FXML
    private ComboBox<String> COMBO2;

    

     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        COMBO1.setItems(list1);
        COMBO2.setItems(list2);
    }    

    
  ObservableList <String> list1 =FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9",
    "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25");
    ObservableList <String> list2 =FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11");
    
    
    @FXML
    private void ok(ActionEvent event) throws SQLException {
           
       Classe c1 =new Classe(ajtlib.getText()/*,Integer.valueOf(ajtnbr.getText()),Integer.valueOf(ajtsalle.getText())*/
               
               
       ,Integer.valueOf(COMBO1.getValue())
       ,Integer.valueOf( COMBO2.getValue()));
       
       
       
        c.ajouter(c1);
        
        
         if ((event.getSource() == ok) )   {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("classe.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          
        }
          Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("AJOUT!!!!");
       alert.setHeaderText("Information");
         alert.setContentText("La classe est bien ajoutée");
         alert.showAndWait();
    }
         
         
         
    }

    @FXML
    private void Retour(ActionEvent event) {
        if ((event.getSource() == Retour) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("classe.fxml"));
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
    

