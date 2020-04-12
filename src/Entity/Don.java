/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
/**
 *
 * @author alaed
 */

public class Don {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty club;
    private SimpleStringProperty type;
    private SimpleStringProperty description;
    private SimpleStringProperty email;
    private SimpleIntegerProperty etat;
    private SimpleFloatProperty somme;


    public Don(int club, String type, String email, float somme) {
        this.club = new SimpleIntegerProperty(club);
        this.type = new SimpleStringProperty(type);
        this.email = new SimpleStringProperty(email);
        this.somme = new SimpleFloatProperty(somme);
    }

    public Don(int club, String type, String description, String email, float somme) {
        this.club = new SimpleIntegerProperty(club);
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(description);
        this.email = new SimpleStringProperty(email);
        this.somme = new SimpleFloatProperty(somme);
    }

    public Don(int id, int etat) {
        this.id = new SimpleIntegerProperty(id);
        this.etat =new SimpleIntegerProperty(etat);
    }

    public Don(int id, int club, String type, String description, String email, int etat, float somme) {
        this.id = new SimpleIntegerProperty(id);
        this.club = new SimpleIntegerProperty(club);
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(description);
        this.email = new SimpleStringProperty(email);
        this.somme = new SimpleFloatProperty(somme);
        this.etat= new SimpleIntegerProperty(etat);
    }

    public Don(int club, String type, String description, String email, int etat, float somme) {
        this.club = new SimpleIntegerProperty(club);
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(description);
        this.email = new SimpleStringProperty(email);
        this.somme = new SimpleFloatProperty(somme);
        this.etat= new SimpleIntegerProperty(etat);
    }

    public Don() {
    }
    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public int getClub() {
        return club.get();
    }

    public void setClub(int club) {
        this.club = new SimpleIntegerProperty(club);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public int getEtat() {
        return etat.get();
    }

    public void setEtat(int etat) {
        this.etat =new SimpleIntegerProperty(etat);
    }

    public float getSomme() {
        return somme.get();
    }

    public void setSomme(float somme) {
        this.somme = new SimpleFloatProperty(somme);
    }
    
    public SimpleIntegerProperty getClubProperty(){
        return club;
    }
    public SimpleStringProperty getTypeProperty(){
        return type;
    }
    public SimpleStringProperty getDescriptionProperty(){
        return description;
    }
    public SimpleStringProperty getEmailProperty(){
        return email;
    }
    public SimpleIntegerProperty getEtatProperty(){
        return etat;
    }
    public SimpleFloatProperty getSommeProperty(){
        return somme;
    }
    
}
