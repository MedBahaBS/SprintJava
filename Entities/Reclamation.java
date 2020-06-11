/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CL.Entities;

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
    private String NewValue="Traitée";
    private String archiver;
    private String archiver2="Archivée";
     private Integer user;
     private Integer target_id;

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
    

    public String getArchiver2() {
        return archiver2;
    }

    public void setArchiver2(String archiver2) {
        this.archiver2 = archiver2;
    }

    public String getArchiver() {
        return archiver;
    }

    public void setArchiver(String archiver) {
        this.archiver = archiver;
    }

    public String getNewValue() {
        return NewValue;
    }

    public void setNewValue(String NewValue) {
        this.NewValue = NewValue;
    }
    
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
    
    
       public Reclamation(int id, String DateReclamation, String TypeReclamation, String ContenuReclamation,String etat,Integer user) {
        this.id = id;
        this.DateReclamation = DateReclamation;
        this.TypeReclamation = TypeReclamation;
        this.ContenuReclamation = ContenuReclamation;
        this.etat = etat;
         this.user = user;
        
    }
       
       
       
        public Reclamation(int id, String DateReclamation, String TypeReclamation, String ContenuReclamation,String etat,Integer user,Integer target_id) {
        this.id = id;
        this.DateReclamation = DateReclamation;
        this.TypeReclamation = TypeReclamation;
        this.ContenuReclamation = ContenuReclamation;
        this.etat = etat;
         this.user = user;
         this.target_id = target_id;
    }
    
    
    public Reclamation(int id , String TypeReclamation, String ContenuReclamation) {
        this.id = id;
        
        this.TypeReclamation = TypeReclamation;
        this.ContenuReclamation = ContenuReclamation;
    }
    
    public Reclamation( String TypeReclamation) {
       
        
        this.TypeReclamation = TypeReclamation;
      
    }
    
    
    public Reclamation(int id, String NewValue) {
        this.id = id;
        this.NewValue = NewValue;
        
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
