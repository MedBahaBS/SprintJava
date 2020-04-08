/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Evenement;
import Service.EvenementService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jdk.nashorn.internal.parser.DateParser;

/**
 *
 * @author Baha
 */
public class AccueilEventFrontController implements Initializable {
    EvenementService Es = new EvenementService();
    @FXML
    private ScrollPane listEvents;
    @FXML
    private ImageView eventImage;
    @FXML
    private DatePicker eventDate;
    @FXML
    private Label eventTitre;
    @FXML
    private Button eventConsulter;
    @FXML 
    private Accordion accordion;
    @FXML 
    private TitledPane eventPane;
    
     /**
     * Initializes the controller class.
     *
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList<Evenement> events = Es.listerEvenement();
            events.stream().forEach((e) -> {
                
                try {
                    String image_name = Es.getImageName(e.getIdevenement());
                    if (image_name != null)
                        eventImage.setImage(new Image("file:///C:/wamp64/www/PIDEV_integration/public/images/events/"+ image_name)); 
                    else
                        eventImage.setImage(new Image("file:///C:/wamp64/www/PIDEV_integration/public/"+ "pas_image.png"));
                } catch (SQLException ex) {
                    Logger.getLogger(AccueilEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                eventTitre.setText(e.getTitre());
                eventPane = new TitledPane(eventTitre.getText(), eventDate);
                eventDate.setValue(e.getDate().toLocalDate());
                eventPane.setText(eventTitre.getText());
                accordion.getPanes().add(eventPane);
            });
        } catch (SQLException ex) {
            Logger.getLogger(AccueilEventFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
