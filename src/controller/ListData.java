/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClubDao;
import Entity.Club;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author alaed
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Club> clubs=FXCollections.observableArrayList();
    

    public ListData() {
        
        ClubDao cdao=ClubDao.getInstance();
        clubs= cdao.displayAll();
        //System.out.println(clubs);
    }
    
        public ObservableList<Club> getClubs(){
            return clubs;
        }
   
}
