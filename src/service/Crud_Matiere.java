/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entities.Matiere;
import entities.Note;
import utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alaed
 */

public class Crud_Matiere {
//lahtha nchouf codi okay
    private Connection con;
    private Statement st;
    private PreparedStatement pre;
    public Crud_Matiere() {
            con=MyDB .getInstance().getCnx();

    }
    
    
    
    
      public void ajouter(Matiere m) throws SQLException {
        try{
       st = con.createStatement();
        String requeteInsert = "INSERT INTO Matiere(nom, nbrseance, description, coefficient)VALUES ( '" + m.getNom() + "', '" + m.getNbrseance() + "', '" + m.getDescription() + "', '" + m.getCoefficient() + "')";
                   
              st.executeUpdate(requeteInsert);}
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
           
    }
    
    
    
    
    
    
     
      public void delete(int idmatiere) throws SQLException {
            try { 
            String query ="DELETE FROM Matiere WHERE idmatiere="+idmatiere; 
            Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("la Matiere de l id = "+idmatiere+"a été supprimée ") ; 
           
            
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
        
    }
    
    public void modifier(  int idmatiere,String nom,int nbrseance ,String description,int coefficient  )
      { 
          try 
       {
        String query ="UPDATE Matiere SET nom='"+nom+"',nbrseance='"+nbrseance+"',description='"+description+"',coefficient='"+coefficient+"' WHERE idmatiere='"+idmatiere+"'";
        Statement ste=con.createStatement();
        ste.executeUpdate(query) ;
       System.out.println("La Matiere a été modifié avec succés");
} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
}
    
    
        public List<Matiere> afficherMatiere() throws SQLException {
    List<Matiere> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Matiere");
     while (rs.next()) {                
               int idmatiere=rs.getInt(1);
               String nom=rs.getString(2);
               int nbrseance=rs.getInt(3);
               String description=rs.getString(4);
               int coefficient=rs.getInt(5);
               
                 
        Matiere m = new Matiere(idmatiere,nom, nbrseance, description,coefficient);
     arr.add(m);
     }
    return arr;
    
    
}
        
       
public List<Matiere> RechercherMatiere(String nom) throws SQLException{

     List<Matiere> listrecherche = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM Matiere WHERE Nom='"+nom+"'";
Statement st=con.createStatement() ; 
ResultSet rs=st.executeQuery(req);
            while(rs.next())
            {    
            Matiere m = new Matiere();
          m.setNom(rs.getString(2));
     m.setNbrseance(rs.getInt(3));
     m.setDescription(rs.getString(4));
    m.setCoefficient(rs.getInt(5));
         
                listrecherche.add(m);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crud_Matiere.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }

   

  
}