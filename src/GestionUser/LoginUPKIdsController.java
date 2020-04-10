/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class LoginUPKIdsController implements Initializable {

    @FXML
    private Button btnsignup;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Pass;
    @FXML
    private ImageView img;
    @FXML
    private PasswordField RepeterPass;
    @FXML
    private RadioButton homme;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femme;
    @FXML
    private Button Reset;
    @FXML
    private Button Choose;
    @FXML
    private ImageView img1;
    @FXML
    private TextField Nom1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Register(ActionEvent event) {
    }

    @FXML
    private void Reset(ActionEvent event) {
    }

    @FXML
    private void Choisir(ActionEvent event) {
    }
    
}
