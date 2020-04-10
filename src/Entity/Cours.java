/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.image.ImageView;


/**
 *
 * @author acer
 */
public class Cours {
    
    int idcour;
    String niveau,matiere,nomchapitre,email,lien;
    String date ;
    String cour;
   

    public Cours() {
    }
  
    
   
    

    public Cours(int idcour, String niveau, String matiere, String nomchapitre, String email, String lien, String date) {
        this.idcour = idcour;
        this.niveau = niveau;
        this.matiere = matiere;
        this.nomchapitre = nomchapitre;
        this.email = email;
        this.lien = lien;
        this.date = date;
    }

    public Cours(String niveau, String matiere, String nomchapitre, String email, String lien) {
        this.niveau = niveau;
        this.matiere = matiere;
        this.nomchapitre = nomchapitre;
        this.email = email;
        this.lien = lien;
    }

    public Cours(String Niveau, String Matiere, String Nomchapitre, String email, String Lien, String ImgToDb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public int getIdcour() {
        return idcour;
    }

    public void setIdcour(int idcour) {
        this.idcour = idcour;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getNomchapitre() {
        return nomchapitre;
    }

    public void setNomchapitre(String nomchapitre) {
        this.nomchapitre = nomchapitre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCour() {
        return cour;
    }

    public void setCour(String cour) {
        this.cour = cour;
    }

   
   
  
    

   

   

       
}
