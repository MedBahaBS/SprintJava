/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Evenement;
import Utils.MyDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Baha
 */
public class EvenementService {

    public ObservableList<Evenement> listerEvenement() throws SQLException{
        ObservableList<Evenement> events = FXCollections.observableArrayList();
        String req = "SELECT * FROM `Evenement`";
        Evenement event;
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(req);
        while (result.next()){
                int id = result.getInt("idevenement");
                String titre = result.getString("titre");
                String description = result.getString("description");
                Date date = result.getDate("date");         
                int etat = result.getInt("etat");
                int nb = result.getInt("nb_Participant_Max");
                String lieu = result.getString("lieu");
                int frais = result.getInt("frais");
                Date datefin = result.getDate("datefin");
                int cat = result.getInt("categorie");
                int club = result.getInt("club");
                event = new Evenement(id,titre,description,date,etat,nb,lieu,frais,datefin,cat,club);
                events.add(event);
            }
        return events;
    }
    
    
    public void ajouterEvenemenet(Evenement e) throws SQLException{    
       String req;
        if (e.getClub() > 0 )
            req = "INSERT INTO `Evenement` (`titre`, `description`, `date`, `etat`"
                + ", `nb_Participant_Max`, `lieu`, `frais`, `datefin`, `categorie`, `club`)"
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        else
            req = "INSERT INTO `Evenement` (`titre`, `description`, `date`, `etat`"
                + ", `nb_Participant_Max`, `lieu`, `frais`, `datefin`, `categorie`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement pstate = MyDB.getInstance().prepareStatement(req);
        pstate.setString(1, e.getTitre());
        pstate.setString(2, e.getDescription());
        pstate.setDate(3, e.getDate());
        pstate.setInt(4, 1);
        pstate.setInt(5, e.getNbParticipantMax());
        pstate.setString(6, e.getLieu());
        pstate.setFloat(7, e.getFrais());
        pstate.setDate(8, e.getDatefin());
        pstate.setInt(9, e.getCategorie());
        if (e.getClub() > 0)
            pstate.setInt(10, e.getClub());
        pstate.executeUpdate();
        System.out.println("Evenement ajouté avec succés");
    }
    
    public void modifierEvenement(Evenement e) throws SQLException{
        String preq = "SELECT * FROM `Evenement` WHERE `idevenement` = " + e.getIdevenement();
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(preq);
        String req;
        if (result.next()){    
             req = "UPDATE `Evenement` "
                    + "SET `titre` = '"+ e.getTitre()+"',"
                    + "`description` = '"+ e.getDescription()+"',"
                    + "`date` = '"+ e.getDate()+"',"
                    + "`etat` = '"+ e.getEtat()+"',"
                    + "`nb_Participant_Max` = "+ e.getNbParticipantMax()+","
                    + "`lieu` = '"+ e.getLieu()+"',"
                    + "`frais` = "+ e.getFrais()+","
                    + "`datefin` = '"+ e.getDatefin()+"',"
                    + "`categorie` = "+ e.getCategorie()+""
                    + " WHERE `idevenement` = " + e.getIdevenement();
               if (e.getClub() != 0)
                   req = "UPDATE `Evenement` "
                    + "SET `titre` = '"+ e.getTitre()+"',"
                    + "`description` = '"+ e.getDescription()+"',"
                    + "`date` = '"+ e.getDate()+"',"
                    + "`etat` = '"+ e.getEtat()+"',"
                    + "`nb_Participant_Max` = "+ e.getNbParticipantMax()+","
                    + "`lieu` = '"+ e.getLieu()+"',"
                    + "`frais` = "+ e.getFrais()+","
                    + "`datefin` = '"+ e.getDatefin()+"',"
                    + "`categorie` = "+ e.getCategorie()+","
                    + "`club` ="+e.getClub()+""
                    + " WHERE `idevenement` = " + e.getIdevenement();
            state.executeUpdate(req);
            System.out.println("Evenement modifié avec succès");
        }
        else System.out.println("Evenement non trouvé");      
    }
 
    public void supprimerEvenement(Evenement e) throws SQLException{
        String preq = "SELECT * FROM `Evenement` WHERE `idevenement` = " + e.getIdevenement();
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(preq);
        if (result.next()){
            String req = "DELETE FROM `Evenement` "                 
                    + " WHERE idevenement = " + e.getIdevenement();

            state.executeUpdate(req);
            System.out.println("Evenement supprimé avec succès");
        }
        else System.out.println("Evenement non trouvé");      
    }
    
    public ObservableList<Evenement> rechercheEvenement(Evenement e) throws SQLException{
        String req;
        Statement state = MyDB.getInstance().createStatement();
        ObservableList<Evenement> events = FXCollections.observableArrayList();
        Evenement event;
        
        if (!"".equals(e.getTitre())){
            req = "SELECT * FROM `Evenement` WHERE `etat` = 1 AND `titre` LIKE '%" +e.getTitre()+ "%'";
            ResultSet result = state.executeQuery(req);
            while (result.next()){
                int id = result.getInt("idevenement");
                String titre = result.getString("titre");
                String description = result.getString("description");
                Date date = result.getDate("date");         
                int etat = result.getInt("etat");
                int nb = result.getInt("nb_Participant_Max");
                String lieu = result.getString("lieu");
                int frais = result.getInt("frais");
                Date datefin = result.getDate("datefin");
                int cat = result.getInt("categorie");
                event = new Evenement(id,titre,description,date,etat,nb,lieu,frais,datefin,cat);
                if (!events.contains(e))
                    events.add(event);
            }
        }
        
        if (e.getDate()!=null){
            req = "SELECT * FROM `Evenement` WHERE `etat` = 1 AND`date` >= '" +e.getDate()+ "'";
            
            ResultSet result = state.executeQuery(req);
            while (result.next()){
                int id = result.getInt("idevenement");
                String titre = result.getString("titre");
                String description = result.getString("description");
                Date date = result.getDate("date");         
                int etat = result.getInt("etat");
                int nb = result.getInt("nb_Participant_Max");
                String lieu = result.getString("lieu");
                int frais = result.getInt("frais");
                Date datefin = result.getDate("datefin");
                int cat = result.getInt("categorie");
                event = new Evenement(id,titre,description,date,etat,nb,lieu,frais,datefin,cat);
                if (!events.contains(e))
                    events.add(event);
            }
        }
        
        if (!"".equals(e.getLieu())){
            req = "SELECT * FROM `Evenement` WHERE `etat` = 1 AND `lieu` LIKE '%" +e.getLieu()+ "%'";
            ResultSet result = state.executeQuery(req);
            while (result.next()){
                int id = result.getInt("idevenement");
                String titre = result.getString("titre");
                String description = result.getString("description");
                Date date = result.getDate("date");         
                int etat = result.getInt("etat");
                int nb = result.getInt("nb_Participant_Max");
                String lieu = result.getString("lieu");
                int frais = result.getInt("frais");
                Date datefin = result.getDate("datefin");
                int cat = result.getInt("categorie");
                event = new Evenement(id,titre,description,date,etat,nb,lieu,frais,datefin,cat);
                if (!events.contains(e))
                    events.add(event);
            }
        }
        
        if (e.getCategorie() > 0){
            req = "SELECT * FROM `Evenement` WHERE `etat` = 1 AND `categorie` = " +e.getCategorie()+ "";
            ResultSet result = state.executeQuery(req);
            while (result.next()){
                int id = result.getInt("idevenement");
                String titre = result.getString("titre");
                String description = result.getString("description");
                Date date = result.getDate("date");         
                int etat = result.getInt("etat");
                int nb = result.getInt("nb_Participant_Max");
                String lieu = result.getString("lieu");
                int frais = result.getInt("frais");
                Date datefin = result.getDate("datefin");
                int cat = result.getInt("categorie");
                event = new Evenement(id,titre,description,date,etat,nb,lieu,frais,datefin,cat);
                if (!events.contains(e))
                    events.add(event);
            }
        }
        
        if (e.getClub() > 0){
            req = "SELECT * FROM `Evenement` WHERE `etat` = 1 AND `club` = " +e.getClub()+ "";
            ResultSet result = state.executeQuery(req);
            while (result.next()){
                int id = result.getInt("idevenement");
                String titre = result.getString("titre");
                String description = result.getString("description");
                Date date = result.getDate("date");         
                int etat = result.getInt("etat");
                int nb = result.getInt("nb_Participant_Max");
                String lieu = result.getString("lieu");
                int frais = result.getInt("frais");
                Date datefin = result.getDate("datefin");
                int cat = result.getInt("categorie");
                event = new Evenement(id,titre,description,date,etat,nb,lieu,frais,datefin,cat);
                if (!events.contains(e))
                    events.add(event);
            }
        }
        
        return events;
    }
    
    public ObservableList<String> getNomClubs() throws SQLException{
        String req = "SELECT `nom` FROM `club`";
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(req);
        ObservableList<String> clubs = FXCollections.observableArrayList();
        while (result.next()){
            clubs.add(result.getString("nom"));
        }
        return clubs;
    }
    
    public int getIdClub(String nomClub) throws SQLException{
        String req = "SELECT `id` FROM `club` WHERE `nom` LIKE '" + nomClub + "'";
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(req);
        int id = 0;
        while (result.next())
            id = result.getInt("id");
            
           return id;
    }
    
    public String getImageName(int idevenement) throws SQLException{
       String req = "SELECT image_name FROM `Evenement` WHERE `idevenement` = " + idevenement; 
       Statement state = MyDB.getInstance().createStatement();
       String image_name = null;
       ResultSet result = state.executeQuery(req);
       
       while(result.next())
          image_name = result.getString("image_name");
       return image_name;
    }
    
    public boolean isValidTitre(String titre){
        return titre.matches("^[A-Z][a-zéè\\s]+$");
    }
    
    public boolean isValidDescription(String description){
        return description.matches("^[A-Z][a-zéè0-9A-Z\\s]+$");
    }
    
    public boolean isValidDateDeb(Date dateDeb){
        return dateDeb.toLocalDate().compareTo(LocalDate.now()) > 0;
    }
    
    public boolean isValideDateFin(Date dateDeb, Date dateFin){
        return dateFin.toLocalDate().compareTo(dateDeb.toLocalDate()) > 0 
                ||
                dateFin.toLocalDate().compareTo(dateDeb.toLocalDate()) == 0;
    }
    
    public boolean isValidNbPart(int nbPart){
        return nbPart > 0;
    }
    
    public boolean isValidFrais(int frais){
        return frais >= 0 ;
    }
    
    public boolean isValidLieu(String lieu){
        return lieu.matches("^[A-Z][a-zéè\\s]+$");
    }
}
