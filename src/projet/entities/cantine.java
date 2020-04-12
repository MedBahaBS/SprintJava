/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

/**
 *
 * @author hp
 */
public class cantine {
   
      private int id;
    
    private String nom;

    public cantine() {
    }
    
    private int capacite;

    public cantine(int id, String nom, int capacite) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
   
}
