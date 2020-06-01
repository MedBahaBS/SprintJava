/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import projet.controller.InscriptionBackController;
import projet.services.InscriptionService;
import projet.services.NewsLetterService;
/**
 *
 * @author hp
 */
public class Inscription {
     private int id;
    
    private String status;
    
    private int idUser;
   
    private Button btn_delete;
    private Button btn_confirmer;

   
   

    public Inscription(int id,int idUser ,String status) {
        this.id = id;
      
        this.status = status;
       
        this.idUser = idUser;
    }

    public Inscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Button getBtn_delete() {
        return btn_delete;
    }

   

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setBtn_delete(Button btn_delete) {
        this.btn_delete = btn_delete;
        System.out.println("ahhhhh");
        this.btn_delete.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous voulez vraiment supprimer cette inscription?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                InscriptionService service = new InscriptionService();
                if (service.supprimerInscription(id)) {
                    
                    cantine c = service.retournerCapacite(id);
                    service.modifierCapacite(c.getCapacite()+1);
                    InscriptionBackController gestionInscription = new InscriptionBackController();
                    gestionInscription.observableList.remove(this);
                }
            } else {

            }
        });
    }

    public Button getBtn_confirmer() {
        return btn_confirmer;
    }

    public void setBtn_confirmer(Button btn_confirmer) {
        this.btn_confirmer = btn_confirmer;

        this.btn_confirmer.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous voulez vraiment confirmer cette inscription?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                InscriptionService service = new InscriptionService();
                int idUtillisateur=service.retournerIdUtilisateur(id);
                cantine c = service.retournerCapacite(1);
                NewsLetterService newsLetter = new NewsLetterService();
                if (c.getCapacite() > 0) {
                    String emailUtilisateur=service.retournerEmailUtilisateur(idUtillisateur);
                    newsLetter.sendMail("linda.guesmi@esprit.tn","samsung11",emailUtilisateur, c.getNom(), "on a approuvé votre inscription dans la cantine");
                    if (service.confirmerInscription(id)) {
                        service.modifierCapacite(c.getCapacite()-1);
                        InscriptionBackController gestionInscription = new InscriptionBackController();
                        ObservableList observableList = FXCollections.observableArrayList(service.selectAllInscris());
                        gestionInscription.observableList.clear();
                        gestionInscription.observableList.addAll(observableList);
                    }
                }
                else{
                     String emailUtilisateur=service.retournerEmailUtilisateur(idUtillisateur);
                    newsLetter.sendMail("linda.guesmi@esprit.tn","samsung11",emailUtilisateur, c.getNom(), "Nous sommes desoles. on a atteint la capacite maximale. Nous vous verrons tres prochainement");
                }
            } else {

            }
        });
    }
}
