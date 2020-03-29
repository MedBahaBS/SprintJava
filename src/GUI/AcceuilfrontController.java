/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AcceuilfrontController implements Initializable {

    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;
    @FXML
    private Button BtnAcceuil;
    @FXML
    private ScrollPane SrollPaneMain;
    @FXML
    private Button cour;
    @FXML
    private Button serie;
    @FXML
    private Button club;
    @FXML
    private AnchorPane image;
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cour(ActionEvent event) throws IOException {
         
    }

    @FXML
    private void serie(ActionEvent event) {
    }

    @FXML
    private void club(ActionEvent event) {
    }

   
     public void setLabelUserName(String username, int Id) {
        this.UserName.setText(username);
    }
}
