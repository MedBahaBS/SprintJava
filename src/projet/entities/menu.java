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
public class menu {
    private String nomPlatDessert;
    private String nomPlatPrincipal;
    private String nomPlatEntree;
    private int id;
    private int entree;
    private int platprincipale;
    private int dessert;
    private int plat_id;
    private String jourMenu;
    private int nbrLike;
    private int nbrFoisLike;
    private float moyenneLike;

    public menu() {
    }

    public menu(int id, int entree, int platprincipale, int dessert, String jourMenu) {
        this.id = id;
        this.entree = entree;
        this.platprincipale = platprincipale;
        this.dessert = dessert;
      
        this.jourMenu = jourMenu;
    }
    public menu(int id,String jourMenu,String nomPlatEntree, String nomPlatPrincipal,String nomPlatDessert) {
        this.id = id;
        this.nomPlatDessert = nomPlatDessert;
        this.nomPlatPrincipal = nomPlatPrincipal;
        this.nomPlatEntree = nomPlatEntree;
       
        this.jourMenu = jourMenu;
    }
    public menu(int entree, int platprincipale, int dessert, String jourMenu ,int nbrLike ,int nbrFoisLike , float moyenneLike ) {
      
        this.entree = entree;
        this.platprincipale = platprincipale;
        this.dessert = dessert;
        this.jourMenu = jourMenu;
        this.nbrLike = nbrLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntree() {
        return entree;
    }

    public void setEntree(int entree) {
        this.entree = entree;
    }

    public int getPlatprincipale() {
        return platprincipale;
    }

    public void setPlatprincipale(int platprincipale) {
        this.platprincipale = platprincipale;
    }

    public int getDessert() {
        return dessert;
    }

    public void setDessert(int dessert) {
        this.dessert = dessert;
    }

    public int getPlat_id() {
        return plat_id;
    }

    public void setPlat_id(int plat_id) {
        this.plat_id = plat_id;
    }

    public String getJourMenu() {
        return jourMenu;
    }

    public void setJourMenu(String jourMenu) {
        this.jourMenu = jourMenu;
    }
public int getNbrLike() {
        return nbrLike;
    }

    public void setNbrLike(int nbrLike) {
        this.nbrLike = nbrLike;
    }
    public int getNbrFoisLike() {
        return nbrFoisLike;
    }

    public void setNbrFoisLike(int nbrFoisLike) {
        this.nbrFoisLike = nbrFoisLike;
    }
    public float getMoyenneLike() {
        return moyenneLike;
    }

    public void setMoyenneLike(float moyenneLike) {
        this.moyenneLike = moyenneLike;
    }
    
     public String getNomPlatDessert() {
        return nomPlatDessert;
    }

    public void setNomPlatDessert(String nomPlatDessert) {
        this.nomPlatDessert = nomPlatDessert;
    }

    public String getNomPlatPrincipal() {
        return nomPlatPrincipal;
    }

    public void setNomPlatPrincipal(String nomPlatPrincipal) {
        this.nomPlatPrincipal = nomPlatPrincipal;
    }

    public String getNomPlatEntree() {
        return nomPlatEntree;
    }

    public void setNomPlatEntree(String nomPlatEntree) {
        this.nomPlatEntree = nomPlatEntree;
    }
    
    @Override
    public String toString() {
        return "menu{" + "id=" + id + ", entree=" + entree + ", platprincipale=" + platprincipale + ", dessert=" + dessert + ", plat_id=" + plat_id + ", jourMenu=" + jourMenu + ", nbrLike=" + nbrLike +", nbrFoisLike=" + nbrFoisLike +", moyenneLike=" + moyenneLike + '}';
    }
   
}
