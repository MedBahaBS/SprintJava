/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DonDao;
import Entity.Don;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListDataD {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Don> dons=FXCollections.observableArrayList();

    public ListDataD() {
        
        DonDao cdao=DonDao.getInstance();
        dons= cdao.displayAll();
        //System.out.println(clubs);
    }
    
        public ObservableList<Don> getDons(){
            return dons;
        }
   
}
