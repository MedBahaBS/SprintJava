/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Club;
import Entity.Don;
import dao.ClubDao;
import dao.DonDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author alaed
 */

public class AjouterDonController implements Initializable {
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
    private Button btn_retour;
    @FXML
    private Button btn_valider;
    @FXML
    private TextField description;
    @FXML
    private TextField somme;
        private ListData listdata = new ListData();
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
                .getIdc()));});

        
        btn_retour.setOnAction(event -> {

            try {
                Parent page1= FXMLLoader.load(getClass().getResource("/view/Don.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       btn_valider.setOnAction(event -> {
            String a =String.valueOf(listdata.getClubs()
                .get(clubsTable.getSelectionModel().getSelectedIndex())
                .getIdc());
            int i = Integer.parseInt(a);
            float s= Float.parseFloat(somme.getText());
            Don c = new Don(i, "Argent",description.getText(),"alaeddinneg92@gmail.com"
                    ,0,s);
            
            DonDao cdao = DonDao.getInstance();
            cdao.insert(c);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
        
        description.setText("");
        somme.setText("");
        });
    }
}
