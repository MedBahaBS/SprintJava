/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Club;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author alaed
 */
public class ChartGraphiqueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private BarChart<String, Integer> chart;

    @FXML
    private CategoryAxis xaxis;

    private ObservableList<String> names = FXCollections.observableArrayList();

    private ListData listData = new ListData();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
}
