/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Club;
import dao.ClubDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 *
 * @author alaed
 */
public class ModifierClubController implements Initializable {
    @FXML
    private TableView<Club> clubsTable;
    @FXML
    private TableColumn<Club, String> NomColonne;
    @FXML
    private TableColumn<Club, String> ResponsableColonne;
    @FXML
    private TableColumn<Club, String> DescriptionColonne;
    
    @FXML
    private Label idLabel;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField ResponsableTextField;
    @FXML
    private TextField DescriptionTextField;
    

    private ListData listdata = new ListData();
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_modifier;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clubsTable.setItems(listdata.getClubs());
        NomColonne.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        ResponsableColonne.setCellValueFactory(cell -> cell.
                getValue().getResponsableProperty());
        DescriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getdescriptionProperty());
        
        clubsTable.setOnMouseClicked(event->{
            
        idLabel.setText(String.valueOf(listdata.getClubs()
                .get(clubsTable.getSelectionModel().getSelectedIndex())
                .getIdc()));
        
        
        nomTextField.setText(listdata.getClubs()
                .get(clubsTable.getSelectionModel().getSelectedIndex())
                .getNom());
        
        
        ResponsableTextField.setText(listdata.getClubs()
                .get(clubsTable.getSelectionModel().getSelectedIndex())
                .getResponsable());
        
        DescriptionTextField.setText(listdata.getClubs()
                .get(clubsTable.getSelectionModel().getSelectedIndex())
                .getDescription());
    });
        
        btn_modifier.setOnAction(event->{
            String a =String.valueOf(listdata.getClubs()
                .get(clubsTable.getSelectionModel().getSelectedIndex())
                .getIdc());
            int i = Integer.parseInt(a);
            Club c = new Club(i,nomTextField.getText(), DescriptionTextField.getText(),ResponsableTextField.getText());
            ClubDao cdao = ClubDao.getInstance();
            cdao.update(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Club Modifier avec succés!");
            alert.show();
        });
        

        btn_retour.setOnAction(event->{
            try {
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/club.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        
    }
    
}
