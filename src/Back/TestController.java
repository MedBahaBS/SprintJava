/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class TestController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnPackages;
    @FXML
    private StackPane afficherPlatStackPane;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<?> listePlats;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nomPlat;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> status;
    @FXML
    private StackPane afficherMenuStackPane;
    @FXML
    private TableView<?> listeMenus;
    @FXML
    private TableColumn<?, ?> NomjourMenu;
    @FXML
    private TableColumn<?, ?> entree;
    @FXML
    private TableColumn<?, ?> plat_principal;
    @FXML
    private TableColumn<?, ?> dessert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterGUI(ActionEvent event) {
    }
    
}
