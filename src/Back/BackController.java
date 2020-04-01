/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Back;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class BackController implements Initializable {
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Label LblUserName;
    @FXML
    private Button btncour;
    @FXML
    private Button btnserie;
    @FXML
    private Button btncountine;
    @FXML
    private Button classe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void disconnect(MouseEvent event) {
    }

    @FXML
    private void btncour(ActionEvent event) {
    }

    @FXML
    private void btnserie(ActionEvent event) {
    }

    @FXML
    private void btncountine(ActionEvent event) {
    }

    @FXML
    private void classe(ActionEvent event) {
        if ((event.getSource() == classe) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Interfaces/classe.fxml"));
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
