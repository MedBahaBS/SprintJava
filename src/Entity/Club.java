/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author alaed
 */
public class Club {
    private SimpleIntegerProperty idc;
    private SimpleStringProperty nom;
    private SimpleStringProperty description;
    private SimpleStringProperty responsable;

    public Club() {
    }

    public Club(int idc, String nom, String description, String responsable) {
        this.idc = new SimpleIntegerProperty(idc);
        this.nom = new SimpleStringProperty(nom);
        this.description = new SimpleStringProperty(description);
        this.responsable = new SimpleStringProperty(responsable);
    }

    public Club(String nom, String description, String responsable) {
        this.nom = new SimpleStringProperty(nom);
        this.description = new SimpleStringProperty(description);
        this.responsable = new SimpleStringProperty(responsable);
    }

    public int getIdc() {
        return idc.get();
    }

    public void setIdc(int idc) {
        this.idc = new SimpleIntegerProperty(idc);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public String getResponsable() {
        return responsable.get();
    }

    public void setResponsable(String responsable) {
        this.responsable = new SimpleStringProperty(responsable);
    }
    
    public SimpleStringProperty getNomProperty(){
        return nom;
    }
    public SimpleStringProperty getResponsableProperty(){
        return responsable;
    }
    public SimpleStringProperty getdescriptionProperty(){
        return description;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Club other = (Club) obj;
        if (!Objects.equals(this.idc, other.idc)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
