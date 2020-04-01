/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author alaed
 */
public class Classe {
    private int idclasse;
    private String libelle;
    private int Nombreeleve;
    private int Numsalle;

    public Classe() {
    }

    public Classe(int idclasse, String libelle, int Nombreeleve, int Numsalle) {
        this.idclasse = idclasse;
        this.libelle = libelle;
        this.Nombreeleve = Nombreeleve;
        this.Numsalle = Numsalle;
    }
    
    
    public Classe( String libelle, int Nbreeleve, int Numsalle) {
       
        this.libelle = libelle;
        this.Nombreeleve = Nbreeleve;
        this.Numsalle = Numsalle;
    
    }

    
    
    public int getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(int idclasse) {
        this.idclasse = idclasse;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String nom) {
        this.libelle = nom;
    }

    public int getNombreeleve() {
        return Nombreeleve;
    }

    public void setNombreeleve(int Nombreeleve) {
        this.Nombreeleve = Nombreeleve;
    }

    public int getNumsalle() {
        return Numsalle;
    }

    public void setNumsalle(int Numsalle) {
        this.Numsalle = Numsalle;
    }
    
    
    
    
}
