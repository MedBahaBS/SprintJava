/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Front;

import Entity.Cours;
import Entity.Serie;
import Service.ServiceSerie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.S;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SeriefrontController implements Initializable {

    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;
    @FXML
    private ScrollPane SrollPaneMain;
    @FXML
    private TableView<Serie> tabcour;
    @FXML
    private TableColumn<Serie, String> lien;
    @FXML
    private TableColumn<Serie, String> nomserie;
    @FXML
    private TableColumn<Serie, String> description;
    @FXML
    private TableColumn<Serie, Integer> cour;
    @FXML
    private TextField Titre;
    @FXML
    private Button ChercherTitre;
    @FXML
    private CheckBox serie;
 ServiceSerie ser = new ServiceSerie();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Serie> list  = FXCollections.observableArrayList();

         
        try {
            for(Serie S: ser.readAll())
                list.add(S);
        } catch (SQLException ex) {
            Logger.getLogger(ListeCourfrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         lien.setCellValueFactory(new PropertyValueFactory<>("lien"));
        nomserie.setCellValueFactory(new PropertyValueFactory<>("nomserie"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        cour.setCellValueFactory(new PropertyValueFactory<>("cour"));
        

        //load dummy data
       
        tabcour.setItems(list);
    }    

    
}
