/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author solta
 */
public class CommentaireEvenement {

    private int id_commentaire;
    private int id_user;
    private String meessage;

    public CommentaireEvenement() {

    }

    public CommentaireEvenement(int id_commentaire, int id_user, String meessage) {
        this.id_commentaire = id_commentaire;
        this.id_user = id_user;
        this.meessage = meessage;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getMeessage() {
        return meessage;
    }

    public void setMeessage(String meessage) {
        this.meessage = meessage;
    }

    
        
        
    
}
