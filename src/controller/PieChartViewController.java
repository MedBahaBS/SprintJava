/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClubDao;
import Entity.Club;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alaed
 */
public class PieChartViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    ObservableList<Data> list=FXCollections.
            observableArrayList();
    @FXML
    private Button btn_retour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ClubDao cdao=ClubDao.getInstance();
        List<Club> clubs=cdao.displayAllList();
        for(Club c:clubs) {
            list.addAll(
                new Data(c.getNom(), 12.0)
        );
        }
        pieChart.setAnimated(true);
        pieChart.setData(list);
        
        btn_retour.setOnAction(event->{
            try {
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/AfficherClubs.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherClubsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
