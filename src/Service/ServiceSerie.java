/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Database.Conct;
import Entity.Cours;
import Entity.Serie;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class ServiceSerie {
    Connection con=Conct.getInstance().getCnx();
   
    public void ajouter( Serie s ) throws SQLException {
        try{
        Statement ste = con.createStatement();
        String requeteInsert = "INSERT INTO `ecole`.`serie` ( `lien`, `nomserie`, `description`, `cour`) VALUES ( '" + s.getLien() + "', '" + s.getNomserie() + "', '" + s.getDescription() + "',  '" + s.getCour() +  "');";
        ste.executeUpdate(requeteInsert);}
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
     
      public void delete(int idserie) throws SQLException {
            try { 
            String query ="DELETE FROM serie WHERE idserie="+idserie; 
            Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("le serie de l id = "+idserie+"a ete bien supprimer ") ; 
           
            
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
        
    }
    
    public void modifier(int idserie, String lien, String nomserie, String description)
      { 
          try 
       {
        String query ="UPDATE serie SET lien='"+lien+"',nomserie='"+nomserie+"',description='"+description+"'WHERE idserie='"+idserie+"'";
        Statement ste=con.createStatement();
        ste.executeUpdate(query) ;
       System.out.println("Serie bien modifié");
} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
}
    
    
        public List<Serie> readAll() throws SQLException {
    List<Serie> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from serie ");
   
     while (rs.next()) {                
               int idserie=rs.getInt(1);
               String lien=rs.getString(2);
               String nomserie=rs.getString(3);
               String description=rs.getString(4);
               int cour=rs.getInt(5);
                 
        Serie a = new Serie (idserie, lien, nomserie, description, cour);
     arr.add(a);
     }
    return arr;
    
    
}
        
        
        
        
            public void chercherParNom( String nomserie )
{  try { 
            String query ="SELECT * FROM serie WHERE nomserie='"+nomserie+"'" ; 
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
    

}
            
            
            
            
            
}
