/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 *
 * @author Baha
 */
public class Evenement {
    private int idevenement;
    private String titre;
    private String description;
    private Date date;
    private int etat;
    private int nbParticipantMax;
    private String lieu;
    private int frais;
    private Date datefin;
    private int categorie;
    private int club;
    private String image_name;
    private LocalDateTime updated_at;
   

    public Evenement(int idevenement) {
        this.idevenement = idevenement;
    }
    
    public Evenement(String titre, String description, Date date, int etat, int nbParticipantMax, String lieu,
            int frais, Date datefin, int categorie, int club) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
        this.club = club;
    }
    
    public Evenement(String titre, String description, Date date, int etat, int nbParticipantMax, String lieu,
            int frais, Date datefin, int categorie) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
    }

    public Evenement(int idevenement, String titre, String description, Date date, int etat, int nbParticipantMax,
            String lieu, int frais, Date datefin, int categorie,int club) {
        this.idevenement = idevenement;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
        this.club = club;
    }
    
    public Evenement(int idevenement, String titre, String description, Date date, int etat, int nbParticipantMax,
            String lieu, int frais, Date datefin, int categorie) {
        this.idevenement = idevenement;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
       
    }

    public Evenement(int idevenement, String titre, String description, Date date, int etat, int nbParticipantMax,
            String lieu, int frais, Date datefin, int categorie, String image_name, LocalDateTime updated_at) {
        this.idevenement = idevenement;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
        this.image_name = image_name;
        this.updated_at = updated_at;
    }
    
    public int getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getNbParticipantMax() {
        return nbParticipantMax;
    }

    public void setNbParticipantMax(int nbParticipantMax) {
        this.nbParticipantMax = nbParticipantMax;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getFrais() {
        return frais;
    }

    public void setFrais(int frais) {
        this.frais = frais;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getClub() {
        return club;
    }

    public void setClub(int club) {
        this.club = club;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idevenement=" + idevenement + ", titre=" + titre + ", description=" + description + ", date=" + date + ", etat=" + etat + ", nbParticipantMax=" + nbParticipantMax + ", lieu=" + lieu + ", frais=" + frais + ", datefin=" + datefin + ", categorie=" + categorie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.titre);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.date);
        hash = 23 * hash + this.etat;
        hash = 23 * hash + this.nbParticipantMax;
        hash = 23 * hash + Objects.hashCode(this.lieu);
        hash = 23 * hash + Float.floatToIntBits(this.frais);
        hash = 23 * hash + Objects.hashCode(this.datefin);
        hash = 23 * hash + this.categorie;
        hash = 23 * hash + this.club;
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
        final Evenement other = (Evenement) obj;
        if (this.idevenement != other.idevenement) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        if (this.nbParticipantMax != other.nbParticipantMax) {
            return false;
        }
        if (Float.floatToIntBits(this.frais) != Float.floatToIntBits(other.frais)) {
            return false;
        }
        if (this.categorie != other.categorie) {
            return false;
        }
        if (this.club != other.club) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.datefin, other.datefin)) {
            return false;
        }
        return true;
    }
}
