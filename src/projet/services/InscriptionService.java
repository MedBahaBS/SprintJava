/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import projet.entities.cantine;
import projet.utils.DbConnection;
import projet.entities.Inscription;
import static projet.services.platService.statement;
/**
 *
 * @author hp
 */
public class InscriptionService {
     static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();
    
    public List<Inscription> selectAllInscris() {
        ArrayList<Inscription> inscriptions = new ArrayList<>();
        String req = "SELECT * FROM inscription ";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req);
            
            while (rs.next()) {
                Inscription c = new Inscription();
                c.setId(rs.getInt(1));
                c.setIdUser(rs.getInt(2));
                c.setStatus(rs.getString(3));
                c.setBtn_delete(new Button("Supprimer"));
                c.setBtn_confirmer(new Button("valider"));
                
                inscriptions.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(menuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscriptions;
    }
    
    public boolean supprimerInscription(int id) {
        
         String req="DELETE FROM inscription WHERE id="+id;
              
        try {
            pst = connection.prepareStatement(req);
            int res = pst.executeUpdate();

            if(res > 0) {
                    return true;
            }	
        } catch (SQLException e1) {
                e1.printStackTrace();
        }   
        return false;
    }
    public boolean confirmerInscription(int id) {
        
         String req="UPDATE inscription SET status= 'Approuvée' WHERE id="+id;
              
        try {
            pst = connection.prepareStatement(req);
            int res = pst.executeUpdate();

            if(res > 0) {
                    return true;
            }	
        } catch (SQLException e1) {
                e1.printStackTrace();
        }   
        return false;
    }
    public List<Integer> getState() {
        String req11 = "Select id from cantine";
        List<Integer> liste = new ArrayList<Integer>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req11);

            while (rs.next()) {

                liste.add(rs.getInt(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    public String getState1(int x) {
        String g = "";
        String req11 = "Select nom  From cantine where id=? ";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req11);

            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                g = rs.getString(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }
    
      
    public void ajouterInscription(Inscription c) {
        String requete = "INSERT INTO inscription (id,user,status)"
                + " VALUES ('" + c.getId()+ "','"+c.getIdUser()+ "','"+c.getStatus()+ "');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
  /*  public int retournerIdClub(int id){
        int idClub=0;
        String req = "SELECT c.club_id FROM inscription c where c.id = "+id;
         try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                idClub=rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idClub;
    }*/
     public int retournerIdUtilisateur(int id){
        int idClub=0;
        String req = "SELECT c.user FROM inscription c where c.id = "+id;
         try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                idClub=rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(menuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idClub;
    }
     public String retournerEmailUtilisateur(int id){
        String idClub="";
        String req = "SELECT c.email FROM fos_user c where c.id = "+id;
         try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                idClub=rs.getString(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(menuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idClub;
    }
    public cantine retournerCapacite(int id){
        cantine c=new cantine();
        String req = "SELECT c.id,c.nom,c.capacite FROM cantine c where c.id = "+id;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                c.setId(rs.getInt(1));
                 c.setNom(rs.getString(2));
                c.setCapacite(rs.getInt(3));
               
            }
        } catch (Exception ex) {
            Logger.getLogger(menuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    public boolean modifierCapacite(int capacite) {
        String req = "UPDATE cantine SET capacite= ? ";
        try {
            pst = connection.prepareStatement(req);
            // System.out.println(c.getId());
            pst.setInt(1,capacite);

            int res = pst.executeUpdate();

            if (res > 0) {
                return true;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }
}
