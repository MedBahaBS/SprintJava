/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.services;

import projet.entities.CommentaireEvenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import projet.entities.plat;
import static projet.services.menuService.statement;
import static projet.services.platService.statement;
import projet.utils.DbConnection;

/**
 *
 * @author solta
 */
public class ServiceCommentaireEvenement {
 static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    public void  ajouterCommentaireEvenement(String commentaireEvenement,int id) throws SQLException {
        
        String sql = "INSERT INTO commentaire(user,message) VALUES('" + id + "','" + commentaireEvenement + "')";
       try {
            pst = connection.prepareStatement(sql);
            pst.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }


    }

   
    public  List<CommentaireEvenement> afficherCommentaire() {
        List<CommentaireEvenement> tsCommentaire = new ArrayList<CommentaireEvenement>();
        ResultSet resultSet = null;
        // ResultSet resultSet2 = null;
         String req = "SELECT c.message,f.username FROM commentaire c,fos_user f where f.id=c.user";
       try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);

            while (res.next()) {
                CommentaireEvenement p = new CommentaireEvenement ();
                
               // p.setId_commentaire(res.getInt(3));
                p.setUsername(res.getString(2));
                p.setMeessage(res.getString(1));
             
                tsCommentaire.add(p);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return tsCommentaire;

    }

}
