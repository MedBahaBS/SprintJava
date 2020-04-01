/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Matiere;
import entities.Note;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Crud_Matiere;
import service.Crud_Note;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class NoteController implements Initializable {
    
    @FXML
    private TextField tfnote1;
    @FXML
    private TextField tfnote2;
    @FXML
    private TextField tfmoyenne;
    @FXML
    private ChoiceBox<String> tfmatiere;
    
    Crud_Note n = new Crud_Note();
    @FXML
    private Button ajouter;
    @FXML
    private TableColumn<Note, String> colmatiere;
    @FXML
    private TableColumn<Note, Float> colnote1;
    @FXML
    private TableColumn<Note, Float> colnote2;
    @FXML
    private TableColumn<Note, Float> colmoyenne;
    
    List<Note> listN=new ArrayList<>();
    @FXML
    private TableView<Note> table1;
    @FXML
    private Button supprimer;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Crud_Matiere m = new Crud_Matiere();
            List<Matiere> matieres = m.afficherMatiere();
            ObservableList<String> nom_matieres = FXCollections.observableArrayList();
            matieres.stream().forEach((ma) -> {
                nom_matieres.add(ma.getNom());
            });
            tfmatiere.setItems(nom_matieres);
        } catch (SQLException ex) {
            Logger.getLogger(NoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(NoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
         Crud_Note n = new Crud_Note(); 
           
         Crud_Matiere cm = new Crud_Matiere();
         List<Matiere> matieres = cm.afficherMatiere();
         String matiere = tfmatiere.getSelectionModel().getSelectedItem();
         int idmatiere = matieres.stream().filter(m-> m.getNom().equals(matiere))
                 .mapToInt(m -> m.getIdmatiere()).sum();
         
         Note new_note = new Note(idmatiere,Integer.parseInt(tfnote1.getText()),
                 Integer.parseInt(tfnote2.getText()),Integer.parseInt(tfmoyenne.getText()));
         n.ajouter(new_note);
         afficher();
         
    }

    private void afficher() throws SQLException {
         List<Note> listN=new ArrayList<>();
           listN =n.afficherNote2();
          // System.out.println(listN.get(0).getNote1());
    ObservableList <Note> listNote=FXCollections.observableArrayList(listN);
    colmatiere.setCellValueFactory(new PropertyValueFactory<>("nomMatiere") );
    colnote1.setCellValueFactory(new PropertyValueFactory<>("note1") );
    colnote2.setCellValueFactory(new PropertyValueFactory<>("note2") );
    colmoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne") );

    table1.setItems(listNote);

    }
//chniaa mochkol behyy chouf  fama=
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
          ObservableList<Note> SelectedRows, allpeople;
     
     allpeople = table1.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =table1.getSelectionModel().getSelectedItems();
     
     for(Note n1:SelectedRows){
       allpeople.remove(n1);
       n.delete(n1.getIdnote());
     }
    }
    
}
