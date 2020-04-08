/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Evenement;
import Entities.Participant;
import Utils.MyDB;
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
public class ParticipantService {
    
    public ObservableList<Participant> listerParticipants(int idevenement) throws SQLException{
        ObservableList<Participant> participants = FXCollections.observableArrayList();
        String req = "SELECT * FROM `Participant` WHERE `idevenement` =" + idevenement;
        Participant part;
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(req);
        while (result.next()){
            int ideleve = result.getInt("ideleve");
            int idevent = result.getInt("idevenement");
            part = new Participant(ideleve,idevent);
        }
        return participants;
    }
    
    public void ajouterParticipant(Participant p) throws SQLException{
        int nb_participants = 0;
        Evenement e = new Evenement(p.getIdevent());
        String req;
        String preq1 = "SELECT `nb_participant_max`,`date` FROM `Evenement` WHERE `idevenement` =" + p.getIdevent();
        String preq2 = "SELECT COUNT(`ideleve`) as nb FROM `Participant` WHERE `idevenement`=" + p.getIdevent();
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result1 = state.executeQuery(preq1);
        ResultSet result2 = state.executeQuery(preq2);
        if (result1.next()){
            e.setNbParticipantMax(result1.getInt("nb_participant_max"));
            e.setDate(result1.getDate("date"));
        }
        if (result2.next()){ 
            nb_participants = result2.getInt("nb");
        }
        if ((nb_participants < e.getNbParticipantMax()) && 
                (e.getDate().toLocalDate().compareTo(LocalDate.now()) > 0 )){
            req = "INSERT INTO `Participant` (`ideleve`, `idevenement`) VALUES ( ?, ?) ";
            PreparedStatement pstate = MyDB.getInstance().prepareStatement(req);
            pstate.setInt(1, p.getIdeleve());
            pstate.setInt(2, p.getIdevent());
            pstate.executeUpdate();
            System.out.println("Participant ajouté avec succés");  
        }
    }
    
    public void supprimerParticipant(Participant p) throws SQLException{
        String preq1 = "SELECT * FROM `Participant` WHERE `ideleve` = " + p.getIdeleve() +""
                + " AND `idevenement` = " +p.getIdevent();
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(preq1);
        if (result.next()){
            String req = "DELETE FROM `Participant` WHERE `ideleve` = " + p.getIdeleve() + ""
                    + "AND `idevenement` = " + p.getIdevent();
            
            state.executeUpdate(req);
            System.out.println("Participation annulée avec succès");
        }
        else System.out.println("Participation non existante");      
    }
    
    public String getNomParticipant(Participant p) throws SQLException {
        String nom = null;
        String req = "SELECT `login` FROM `fos_user` WHERE `id` = " + p.getIdeleve();
        Statement state = MyDB.getInstance().createStatement();
        ResultSet result = state.executeQuery(req);
        if (result.next()){
            nom = result.getString("login");
        }
        return nom;
    }
}
