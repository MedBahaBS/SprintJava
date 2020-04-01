/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author asus
 */
public class Matiere {
    int idmatiere;
    String nom;
    int nbrseance;
    String description;
    int coefficient;

    public Matiere(int idmatiere, String nom, int nbrseance, String description, int coefficient) {
        this.idmatiere = idmatiere;
        this.nom = nom;
        this.nbrseance = nbrseance;
        this.description = description;
        this.coefficient = coefficient;
    }

    public Matiere(String nom, int nbrseance, String description, int coefficient) {
        this.nom = nom;
        this.nbrseance = nbrseance;
        this.description = description;
        this.coefficient = coefficient;
    }
    

    public Matiere() {
    }

    public int getIdmatiere() {
        return idmatiere;
    }

    public void setIdmatiere(int idmatiere) {
        this.idmatiere = idmatiere;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbrseance() {
        return nbrseance;
    }

    public void setNbrseance(int nbrseance) {
        this.nbrseance = nbrseance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.idmatiere;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matiere other = (Matiere) obj;
        if (this.idmatiere != other.idmatiere) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (this.nbrseance != other.nbrseance) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
