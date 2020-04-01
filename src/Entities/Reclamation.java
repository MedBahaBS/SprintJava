/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author alaed
 */
public class Reclamation {
    private int id;
    private String DateReclamation;
    private String TypeReclamation;
    private String ContenuReclamation;
    private String etat;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    

    public Reclamation() {
    }

    public Reclamation(int id, String DateReclamation, String TypeReclamation, String ContenuReclamation,String etat) {
        this.id = id;
        this.DateReclamation = DateReclamation;
        this.TypeReclamation = TypeReclamation;
        this.ContenuReclamation = ContenuReclamation;
        this.etat = etat;
    }
    public Reclamation(int id , String TypeReclamation, String ContenuReclamation) {
        this.id = id;
        
        this.TypeReclamation = TypeReclamation;
        this.ContenuReclamation = ContenuReclamation;
    }
    
    
    public Reclamation( /*LocalDate  DateReclamation,*/ String TypeReclamation, String ContenuReclamation) {
       
       /* this.DateReclamation = DateReclamation;*/
        this.TypeReclamation = TypeReclamation;
        this.ContenuReclamation= ContenuReclamation;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateReclamation() {
        return DateReclamation;
    }
    

    public void setDateReclamation(String DateReclamation) {
        this.DateReclamation = DateReclamation;
    }

    public String getTypeReclamation() {
        return TypeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.TypeReclamation = typeReclamation;
    }

    public String getContenuReclamation() {
        return ContenuReclamation;
    }

    public void setContenuReclamation(String ContenuReclamation) {
        this.ContenuReclamation = ContenuReclamation;
    }

    
    
    
    
    
}
