/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import projet.entities.menu;
import projet.services.menuService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FrontController implements Initializable {
    @FXML
    private Label menu1;
    @FXML
    private Label menu2;
    @FXML
    private Label menu3;

    @FXML
    private Label entree1;
    @FXML
    private Label entree2;
    @FXML
    private Label entree3;
    @FXML
    private Label platP1;
    @FXML
    private Label platP2;
    @FXML
    private Label platP3;
    @FXML
    private Label dessert1;
    @FXML
    private Label dessert2;
     @FXML
    private Label dessert3;
menuService menuService = new menuService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        List<menu> myList = menuService.getListMenu();
        for(int i=0;i<myList.size();i++){
            if(i==0){
        menu1.setText(myList.get(i).getJourMenu());
        entree1.setText(myList.get(i).getNomPlatDessert());
        platP1.setText(myList.get(i).getNomPlatPrincipal());
        dessert1.setText(myList.get(i).getNomPlatDessert());}
    
        else if(i==1)
                {
                 
       
        menu2.setText(myList.get(i).getJourMenu());
        entree2.setText(myList.get(i).getNomPlatDessert());
        platP2.setText(myList.get(i).getNomPlatPrincipal());
        dessert2.setText(myList.get(i).getNomPlatDessert());
    }
            else if(i==2)
                {
                 
       
        menu3.setText(myList.get(i).getJourMenu());
        entree3.setText(myList.get(i).getNomPlatDessert());
        platP3.setText(myList.get(i).getNomPlatPrincipal());
        dessert3.setText(myList.get(i).getNomPlatDessert());
    }
                }
       
    } catch (SQLException ex) {
        Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
    }    
    
}
