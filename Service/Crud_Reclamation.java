/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CL.Service;
import CL.Entities.Reclamation;
import CL.Utils.Connectiondb;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author alaed
 */
 
public class Crud_Reclamation {
    Connection con=Connectiondb.getInstance().getCnx();
   
    public void ajouter( Reclamation r ) throws SQLException {
        try{
//         LocalDateTime now = LocalDateTime.now();
//         java.util.Date DateReclamation = java.util.Date.from( now.atZone( ZoneId.systemDefault()).toInstant());
            
         
        Statement ste = con.createStatement();
        String requeteInsert = "INSERT INTO reclamation (   TypeReclamation, ContenuReclamation,user,target_id) VALUES  (  '" + r.getTypeReclamation() + "',  '" + r.getContenuReclamation()  + "','" + r.getUser()+ "','" + r.getTarget_id()+ "');";
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
    
      public void modifier3(int id, String NewValue)
      { 
          try 
       {
        String query ="UPDATE reclamation SET etat='"+NewValue+"' WHERE id='"+id+"'";
        Statement ste=con.createStatement();
        ste.executeUpdate(query) ;
       System.out.println("La réclamation bien modifiée");
              try {
                  ServiceMail.SendMail("nadhirgouia@gmail.com", "votre réclamation a été traité avec succées");
              } catch (Exception ex) {
                  Logger.getLogger(Crud_Reclamation.class.getName()).log(Level.SEVERE, null, ex);
              }

} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
}
    
    
   
    
    
    
    
        public List<Reclamation> afficherReclamation(String ch1,String ch2,String ch3) throws SQLException {
    List<Reclamation> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    
   
    ResultSet rs=ste.executeQuery("select * from  reclamation WHERE etat='"+ch1+"' or etat='"+ch2+"' or etat='"+ch3+"' ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int user=rs.getInt(2);
               String DateReclamation=rs.getString(3);
               String TypeReclamation=rs.getString(4);
               String ContenuReclamation=rs.getString(5);
                String etat=rs.getString(7);
              
                 
        Reclamation a = new Reclamation (id,DateReclamation, TypeReclamation, ContenuReclamation, etat,user);
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

    public void cacher(int id, String archiver2) {
          try 
       {
        String query ="UPDATE reclamation SET etat='"+archiver2+"' WHERE id='"+id+"'";
        Statement ste=con.createStatement();
        ste.executeUpdate(query) ;
       System.out.println("La réclamation bien archivé");
} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    }

    

 

  

    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        