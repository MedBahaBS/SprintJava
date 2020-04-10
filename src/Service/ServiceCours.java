/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Database.Conct;
import Entity.Cours;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;


/**
 *
 * @author acer
 */
public class ServiceCours {
    Connection con=Conct.getInstance().getCnx();
      ResultSet rs;
   
      public void ajouter(Cours t) throws SQLException {
        try{
            LocalDateTime now = LocalDateTime.now();
            java.util.Date date = java.util.Date.from( now.atZone( ZoneId.systemDefault()).toInstant());
     
        Statement ste = con.createStatement();
        String requeteInsert = "INSERT INTO `ecole`.`cours` ( `niveau`, `matiere`, `nomchapitre`, `email`, `lien`,date) VALUES ( '" + t.getNiveau() + "', '" + t.getMatiere() + "', '" + t.getNomchapitre() + "',  '" + t.getEmail() + "', '" + t.getLien() + "', '" + date+ "');";
        ste.executeUpdate(requeteInsert);}
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
     
      public void delete(int idcour) throws SQLException {
            try { 
            String query ="DELETE FROM cours WHERE idcour="+idcour; 
            Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("le cour de l id = "+idcour+"a ete bien supprimer ") ; 
           
            
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
        
    }
    
    public void modifier(  int idcour ,String niveau,String matiere,String nomchapitre,String email,String lien )
      { 
          try 
       {
        String query ="UPDATE cours SET niveau='"+niveau+"',matiere='"+matiere+"',nomchapitre='"+nomchapitre+"',email='"+email+"',lien='"+lien+"' WHERE idcour='"+idcour+"'";
        Statement ste=con.createStatement();
        ste.executeUpdate(query) ;
       System.out.println("Client bien modifié");
} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
}
    
    
        public List<Cours> readAll() throws SQLException {
    List<Cours> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from cours");
     while (rs.next()) {                
               int idcours=rs.getInt(1);
               String niveau=rs.getString(2);
               String matiere=rs.getString(3);
               String nomchapitre=rs.getString(4);
               String date=rs.getString(5);
               String email=rs.getString(6);
               String lien=rs.getString(7);
                 
        Cours a = new Cours(idcours, niveau, matiere, nomchapitre, email, lien, date);
     arr.add(a);
     }
    return arr;
    
    
}
        public int getCourbNom(String nomchap) throws SQLException
        { 
               try 
       {
            String query ="Select * FROM cours WHERE nomchapitre='"+nomchap+"'" ; 
            Statement ste=con.createStatement();
            ResultSet rst = ste.executeQuery(query);
         rst.last();
          int nbr =rst.getRow() ;  
          if (nbr!=0)
          {
                return    rst.getInt(1);
          } else {
                return 0;
          }
             
       }
             catch (SQLException ex){
            System.out.println(ex.getMessage()) ;    
        }
               return 0;
        }
        public String getCourbyid(int idcours) throws SQLException
        { 
               try 
       {
            String query ="Select * FROM cours WHERE idcour='"+idcours+"'" ; 
            Statement ste=con.createStatement();
            ResultSet rst = ste.executeQuery(query);
            rst.last();
          int nbr =rst.getRow() ;  
          if (nbr!=0)
          {
                return    rst.getString("nomchapitre");
              
         
          } else {
                return "";
          }
             
       }
             catch (SQLException ex){
            System.out.println(ex.getMessage()) ;    
        }
               return"" ;
        }
                
        
           /* public int chercherParNiveau( String niveau )
            {  try { 
            String query ="SELECT * FROM cours WHERE niveau='"+niveau+"'" ; 
            Statement ste=con.createStatement();
           ResultSet rst = ste.executeQuery(query);
         rst.last();
          int nbr =rst.getRow() ;  
          if (nbr!=0)
          {
                  System.out.println("la serie  est trouvé ") ; 
          } else {
                  System.out.println("la serie n'est pas trouvé ") ; 
          }
              
        
           
            
        }catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
        return 0;*/
        
        public ObservableList<Cours> displayFiltreCour(int idcour) throws SQLException{
        String requete="SELECT * FROM Cours Where idcour = " +idcour+" ORDER BY niveau DESC" ;
           Statement ste=con.createStatement();
           ResultSet rst = ste.executeQuery(requete);
        ObservableList<Cours > list =FXCollections.observableArrayList(); 
        while(rst.next()){    
            
            
        Cours C = new Cours(rst.getInt(1),rst.getString(2), rst.getString(3), 
                rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7));
        list.add(C) ;
        }
        System.out.println(list);
        return list; 
                
    }
       
public void supprimer2parID (int idcour) {
        try { 
            String query ="DElETE FROM Cours WHERE  idcour="+idcour ; 
             Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("l' cour de l id = "+idcour+"a ete bien supprimer ") ; 
        }catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
        }
    }
} 

   

   


