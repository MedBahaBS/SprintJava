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
import javafx.event.ActionEvent;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author alaed
 */
public class ModifierClubController implements Initializable {
    
    @FXML
    private StackPane afficherPlatStackPane;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btncour;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnPackages;
    @FXML
    private Button bntcour;
    @FXML
    private Button serie;
    @FXML
    private Label gestioncour;
    @FXML
    private Label gestioncour1;
    
    @FXML
    private Button btn_club;
    
    @FXML
    private TableView<Club> clubsTable;
    @FXML
    private TableColumn<Club, String> NomColonne;
    @FXML
    private TableColumn<Club, String> ResponsableColonne;
    @FXML
    private TableColumn<Club, String> DescriptionColonne;
    
    
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
            if(nomTextField.getText().contentEquals("")||DescriptionTextField.getText().contentEquals("")||ResponsableTextField.getText().contentEquals(""))
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les champs!");
            alert.show();
            nomTextField.setText("");
            DescriptionTextField.setText("");
            ResponsableTextField.setText("");
            }
            else {
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
            alert.show();}
                
        });
 
                
    }
    @FXML
   private void btnclub(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //club
        tableview = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   
   @FXML
   private void btn_retour(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //retour
        tableview = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
  @FXML
   private void btn_ajouter(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //ajouterclub
        tableview = FXMLLoader.load(getClass().getResource("/view/AjouterClub.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   @FXML
   private void btn_affichert(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //ajouterclub
        tableview = FXMLLoader.load(getClass().getResource("/view/AfficherClubs.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   

    @FXML
    private void btnserie(ActionEvent event) throws IOException {
        
            javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeSerie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

  
    @FXML
    private void Gestioncoour(ActionEvent event) throws IOException {
           javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
          
    }

    @FXML
    private void boutonCour(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview;
        tableview = FXMLLoader.load(getClass().getResource("ListeCour.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void gestioncour(MouseEvent event) {
    }

    @FXML
    private void cour(InputMethodEvent event) {
    }
    @FXML
   private void btndon(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //club
        tableview = FXMLLoader.load(getClass().getResource("/view/Acceuil_don.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
    
}
