/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Entity.Club;
import Entity.membre;
import dao.membreDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.InputMethodRequests;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author alaed
 */
public class AfficherClubsFrontController implements Initializable {
    
    
    @FXML
    private TableView<Club> clubsTable;
    @FXML
    private TableColumn<Club, String> NomColonne;
    @FXML
    private TableColumn<Club, String> ResponsableColonne;
    @FXML
    private TableColumn<Club, String> DescriptionColonne;
    
    

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
    
    private ListData listdata = new ListData();
    private ObservableList<Club> dataList = FXCollections.observableArrayList();
    
    @FXML
    private Button btn_retour;
    @FXML
    private TextField filterField;
    @FXML
    private Label id;
    @FXML
    private TextField ideleve;
    @FXML
    private Button btn_valider;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        clubsTable.setItems(listdata.getClubs());
        NomColonne.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        ResponsableColonne.setCellValueFactory(cell -> cell.
                getValue().getResponsableProperty());
        DescriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getdescriptionProperty());
        
       clubsTable.setOnMouseClicked(event->{ id.setText(String.valueOf(listdata.getClubs()
                .get(clubsTable.getSelectionModel().getSelectedIndex())
                .getIdc()));});
        id.setVisible(false);
        
        
    }
    
   @FXML
    private void btn_valider(ActionEvent event) throws IOException {
        if(id.getText().contentEquals(""))
        {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("vous devez choisir a quel club vous voulez s'inscrire!");
            alert.show();
            
            }
            else {
                
               
            String a =String.valueOf(listdata.getClubs()
                .get(clubsTable.getSelectionModel().getSelectedIndex())
                .getIdc());
                
        int idclub = Integer.parseInt(a);
        /*int idel= Integer.parseInt(b);*/
        
        membre c = new membre(idclub,1);
            membreDao cdao = membreDao.getInstance();
            cdao.insert(c);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous êtes maintenant un membre dans ce Club!");
            alert.show();
            }
    }
   @FXML
   private void btnclub(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //club
        tableview = FXMLLoader.load(getClass().getResource("/view/AfficherClubFront.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   @FXML
   private void btn_retour(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;
   
        //retour
        tableview = FXMLLoader.load(getClass().getResource("/view/Acceuilfront.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
   @FXML
   private void btndon(ActionEvent event) throws IOException {
   
   javafx.scene.Parent tableview;

        tableview = FXMLLoader.load(getClass().getResource("/view/Ajouterdon.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
   }
}
