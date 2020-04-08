/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Categorie;
import Utils.MyDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Baha
 */
public class CategorieService {
    
    
    public ObservableList<Categorie> listerCategorie() throws SQLException{
        ObservableList<Categorie> categories = FXCollections.observableArrayList();
        String req = "SELECT * FROM `Categorie`";
        Categorie nouvelle_cat;
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(req);
        while (result.next()){
                int id = result.getInt("idcategorie");
                String libelle = result.getString("libelle");
                String description = result.getString("description");
                nouvelle_cat = new Categorie(id,libelle,description);
                categories.add(nouvelle_cat);
            }
        return categories;
    }
    
    public void ajouterCategorie(Categorie c) throws SQLException{      
        String req = "INSERT INTO `Categorie` (`libelle`, `description`) VALUES ( ?, ?) ";
        PreparedStatement pstate = MyDB.getInstance().prepareStatement(req);
        pstate.setString(1, c.getLibelle());
        pstate.setString(2, c.getDescription());
        pstate.executeUpdate();
        System.out.println("Categorie ajoutée avec succés");
    }
    
    public void modifierCategorie(Categorie c) throws SQLException{
        String preq = "SELECT * FROM `Categorie` WHERE `idcategorie` = " + c.getIdcategorie();
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(preq);
        if (result.next()){
            String req = "UPDATE `Categorie` "
                    + "SET `libelle` = '" + c.getLibelle()+"',"
                    + "`description` = '" + c.getDescription()+"'"
                    + " WHERE idcategorie = " + c.getIdcategorie();

            state.executeUpdate(req);
            System.out.println("Catégorie modifiée avec succès");
        }
        else System.out.println("Catégorie non trouvée");      
    }
 
    public void supprimerCategorie(Categorie c) throws SQLException{
        String preq1 = "SELECT * FROM `Categorie` WHERE `idcategorie` = " + c.getIdcategorie();
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(preq1);
        if (result.next()){
            //String preq2 = "DELETE FROM `Evenement` WHERE `categorie` = " + c.getIdcategorie();
           // state.executeUpdate(preq2);
            String req = "DELETE FROM `Categorie` "                 
                    + " WHERE idcategorie = " + c.getIdcategorie();

            state.executeUpdate(req);
            System.out.println("Catégorie supprimée avec succès");
        }
        else System.out.println("Catégorie non trouvée");      
    }
    
    public ObservableList<Categorie> rechercheCategorie(Categorie c) throws SQLException{
        String req;
        Statement state = MyDB.getInstance().createStatement();
        ObservableList<Categorie> categories =  FXCollections.observableArrayList();
        Categorie nouvelle_cat;
                
        if (!"".equals(c.getDescription())){
            req = "SELECT * FROM `Categorie` WHERE `description` LIKE '%" + c.getDescription() + "%'";
            ResultSet result = state.executeQuery(req);
            while (result.next()){
                int id = result.getInt("idcategorie");
                String libelle = result.getString("libelle");
                String description = result.getString("description");
                nouvelle_cat = new Categorie(id,libelle,description);
                if (categories.contains(nouvelle_cat))
                    categories.add(nouvelle_cat);
            }
        }
        
        if (!"".equals(c.getLibelle())){
            req = "SELECT * FROM `Categorie` WHERE `libelle` LIKE '%" +c.getLibelle() + "%'";
            ResultSet result = state.executeQuery(req);
            while (result.next()){
                int id = result.getInt("idcategorie");
                String libelle = result.getString("libelle");
                String description = result.getString("description");
                nouvelle_cat = new Categorie(id,libelle,description);
                if (!categories.contains(nouvelle_cat))
                    categories.add(nouvelle_cat);
            }
                
        }
        
        return categories;
    }
    
   public boolean isValidLibelle(String libelle){
       return libelle.matches("^[A-Z][a-zéèù]+$");
   }
   
   public boolean isValidDescription(String description){
       return description.matches("^[A-Z][a-zéèàù\'\\s]+$");
   }
}
