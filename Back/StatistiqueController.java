/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CL.Back;

import CL.Entities.Reclamation;

import CL.Service.Statistique;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class StatistiqueController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private AnchorPane table;

    float  S ;
    
    
    
    float B;
    float N;
    @FXML
    private Button Retour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            statistique();
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     private void  statistique () throws SQLException {
  
       Statistique apr = new Statistique();
       //*****************LineChaart************************
        Statistique gs1=new Statistique();
     
        gs1.calculatereportspermonth();
        
        
    pane.getChildren().clear();
 PieChart series = new PieChart(); 
      
/*list.add (new PieChart.Data("en attente",Integer.valueOf( Reclamation.getEtat())));*/

 N= (  float)Math.round ( (Statistique.totaleNT+Statistique.totaleT));
 S=(  float)Math.round ((Statistique.totaleNT/N)*100);
 B=(  float)Math.round (100-S);
 
 
series.getData()
        .add (new PieChart.Data("En attente"  + S +  "%",Statistique.totaleNT));

series.getData()
        .add (new PieChart.Data("Traité" + B +" %", Statistique.totaleT));

pane.getChildren().add(series); 




}

    @FXML
    private void Retour(ActionEvent event) {
        if ((event.getSource() == Retour) ) {
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
}
