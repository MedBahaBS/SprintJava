/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Don;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 *
 * @author alaed
 */
public class AfficherDonController implements Initializable {
    @FXML
    private TableView<Don> donsTable;
    @FXML
    private TableColumn<Don, Number> ClubColonne;
    @FXML
    private TableColumn<Don, String> typeColonne;
    @FXML
    private TableColumn<Don, String> emailColonne;
    @FXML
    private TableColumn<Don, String> descriptionColonne;
    @FXML
    private TableColumn<Don, Number> etatColonne;
    @FXML
    private TableColumn<Don, Number> sommeColonne;
    
    private ListDataD listdatad = new ListDataD();
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_pie;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        donsTable.setItems(listdatad.getDons());
        
        ClubColonne.setCellValueFactory(cell -> cell.
                getValue().getClubProperty());
        typeColonne.setCellValueFactory(cell -> cell.
                getValue().getTypeProperty());
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
        emailColonne.setCellValueFactory(cell -> cell.
                getValue().getEmailProperty());
        etatColonne.setCellValueFactory(cell -> cell.
                getValue().getEtatProperty());
        sommeColonne.setCellValueFactory(cell -> cell.
                getValue().getSommeProperty());
        
        btn_pie.setOnAction(event->{
            try {
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/PieChartViewD.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherDonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_retour.setOnAction(event->{
            try {
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/Don.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherDonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        
    }
    
}
