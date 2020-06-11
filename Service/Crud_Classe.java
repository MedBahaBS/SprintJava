/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CL.Service;
import CL.Entities.Classe;
import CL.Utils.Connectiondb;
import Entity.Serie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alaed
 */

public class Crud_Classe {
    Connection con=Connectiondb.getInstance().getCnx();
    
    
    private PreparedStatement pre;
    
      public void ajouter(Classe c) throws SQLException {
        try{
        Statement ste = con.createStatement();
        String requeteInsert = "INSERT INTO Classe ( libelle, Nombreeleve, Numsalle) VALUES ( '" + c.getLibelle() + "', '" + c.getNombreeleve() + "', '" + c.getNumsalle() + "')";
        ste.executeUpdate(requeteInsert);}
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
       
    }
    
    
    
    
    
    
     
      public void delete(int idclasse) throws SQLException {
            try { 
            String query ="DELETE FROM Classe WHERE idclasse="+idclasse; 
            Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("la classe de l id = "+idclasse+"a été supprimée ") ; 
           
            
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
        
    }
    
   
     
    public void modifier(  int idclasse,String libelle,int Nombreeleve ,int Numsalle )
      { 
          try 
       {
        String query ="UPDATE Classe SET libelle='"+libelle+"',Nombreeleve='"+Nombreeleve+"',Numsalle='"+Numsalle+"' WHERE idclasse='"+idclasse+"'";
        Statement ste=con.createStatement();
        ste.executeUpdate(query) ;
       System.out.println("La classe "+libelle+" a été modifié avec succés");
} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
}
    
    
        public List<Classe> afficherClass() throws SQLException {
    List<Classe> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Classe");
     while (rs.next()) {                
               int idclasse=rs.getInt(1);
               String libelle=rs.getString(2);
               int Nombreeleve=rs.getInt(3);
               int Numsalle=rs.getInt(4);
               
                 
        Classe a = new Classe(idclasse,libelle, Nombreeleve, Numsalle);
     arr.add(a);
     }
    return arr;
    
    
}
    
  public ObservableList<Classe> rechercheClasse(String text) throws SQLException {
         Statement ste=con.createStatement();
          ObservableList<Classe> list =FXCollections.observableArrayList(); 
             String query="SELECT * FROM Classe Where  Libelle LIKE '%"+text +"%'" ;
              ResultSet rs = ste.executeQuery(query);
                     while(rs.next()){  
                       int id=rs.getInt(1);
                       
               String libelle=rs.getString(2);
                int Nombreeleve=rs.getInt(3);
               int Numsalle=rs.getInt(4);
              
               
                         
        Classe c = new Classe (id, libelle, Nombreeleve, Numsalle);
     list.add(c);
        }
        System.out.println(list);
        return list ;
    
       
    }
}