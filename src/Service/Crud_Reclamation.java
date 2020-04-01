/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Reclamation;
import Utils.Connectiondb;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alaed
 */
public class Crud_Reclamation {
    Connection con=Connectiondb.getInstance().getCnx();
   
    public void ajouter( Reclamation r ) throws SQLException {
        try{
        Statement ste = con.createStatement();
        String requeteInsert = "INSERT INTO reclamation (   TypeReclamation, ContenuReclamation) VALUES  (  '" + r.getTypeReclamation() + "', '" + r.getContenuReclamation() + "');";
        ste.executeUpdate(requeteInsert);}
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
     
      public void delete(int id) throws SQLException {
            try { 
            String query ="DELETE FROM reclamation WHERE id="+id; 
            Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("le serie de l id = "+id+"a été bien supprimée ") ; 
           
            
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
        
    }
    
    public void modifier(int id,String TypeReclamation, String ContenuReclamation)
      { 
          try 
       {
        String query ="UPDATE reclamation SET typeReclamation='"+TypeReclamation+"',contenuReclamation='"+ ContenuReclamation+"' WHERE id='"+id+"'";
        Statement ste=con.createStatement();
        ste.executeUpdate(query) ;
       System.out.println("La réclamation bien modifiée");
} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
}
    
    
        public List<Reclamation> afficherReclamation() throws SQLException {
    List<Reclamation> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String DateReclamation=rs.getString(8);
               String TypeReclamation=rs.getString(2);
               String ContenuReclamation=rs.getString(3);
                String etat=rs.getString(6);
                 
        Reclamation a = new Reclamation (id,DateReclamation, TypeReclamation, ContenuReclamation, etat);
     arr.add(a);
     }
    return arr;
    
    

        
        }
        
        
       /* public ArrayList<Reclamation> listerReclamation() {
        ArrayList<Reclamation> myList = new ArrayList<Reclamation>();
        try {

            String req3 = "SELECT * FROM reclamation";
            Statement st3 = Connectiondb.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(req3);
            while (rs.next()) {

                Reclamation reclamation = new Reclamation();
                
              
              
                reclamation.setTypeReclamation(rs.getString("typeReclamation"));

                reclamation.setDateReclamation(rs.getString("dateReclamation"));

                reclamation.setContenuReclamation(rs.getString("contenuReclamation"));

               
           
              
                myList.add(reclamation);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;*/

    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        