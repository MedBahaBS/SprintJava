/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author acer
 */
public class Serie {
     int idserie;
    String lien,nomserie,description;
    int cour;

    public Serie(int idserie, String lien, String nomserie, String description, int cour) {
        this.idserie = idserie;
        this.lien = lien;
        this.nomserie = nomserie;
        this.description = description;
        this.cour = cour;
    }

    public Serie(String lien, String nomserie, String description, int cour) {
        this.lien = lien;
        this.nomserie = nomserie;
        this.description = description;
        this.cour = cour;
    }

    public int getIdserie() {
        return idserie;
    }

    public void setIdserie(int idserie) {
        this.idserie = idserie;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getNomserie() {
        return nomserie;
    }

    public void setNomserie(String nomserie) {
        this.nomserie = nomserie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCour() {
        return cour;
    }

    public void setCour(int cour) {
        this.cour = cour;
    }
    

}
