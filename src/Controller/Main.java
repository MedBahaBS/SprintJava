/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Entities.*;
import Service.*;
import java.sql.SQLException;
import java.util.Calendar;
import javafx.collections.ObservableList;


/**
 *
 * @author Baha
 */

public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        CategorieService Cs = new CategorieService();
        EvenementService Es = new EvenementService();
      //Categorie c = new Categorie("Excursion","Cette catégorie regroupe toutes les excursions organisées par l'école");
      //c.ajouterCategorie(c);
    
      
      //Categorie c = new Categorie(9,"Test","Test");
      //c.modifierCategorie(c);
      //System.out.println("Categorie modifiée avec succés");
      
      //c.supprimerCategorie(c);
      /*
      Categorie c = new Categorie("actu","");
      ObservableList<Categorie> categories = FXCollections.observableArrayList();
      categories = Cs.rechercheCategorie(c);
      categories.stream().forEach(System.out::println);
      */
      
      
      java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
      
      Evenement e = new Evenement("test java", "test java",date, 1, 100, "test java",
              0, date, 1);
      Es.ajouterEvenemenet(e);
      
      //Evenement e = new Evenement(40,"test java modifié", "test java",ourJavaDateObject, 0, 100, "test java",
        //      0, ourJavaDateObject, 1);
      //Es.supprimerEvenement(e);
      //Es.ajouterEvenemenet(e);
      /*
      List<Evenement> events = new ArrayList<>();
      events = Es.rechercheEvenement(e);
      events.stream().forEach(System.out::println);
       */
      /*
      ObservableList<Evenement> events = Es.listerEvenement();
      events.stream().forEach(System.out::println);
       */
      Categorie c = new Categorie(1,"","Cette catégorie regroupe");
      
    } 
    
}
