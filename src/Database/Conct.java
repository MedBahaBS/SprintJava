/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class Conct {
   String url = "jdbc:mysql://localhost:3306/ecole";
    Connection cnx; 
    String Login="root"; 
    String Password="" ; 
    static Conct con ; 
    
   public Conct() {
       try {
       
     cnx =DriverManager.getConnection(url,Login,Password);
     System.out.println("connected") ; 
       } catch (SQLException ex) {
                      System.out.println(ex.getMessage()) ; 

       }
       
    }
   public Connection getCnx() {
       return cnx ; 
   }
   public static Conct getInstance()  {
       if (con==null) {
           con=new Conct() ; 
       }
     return con ; 
       
   }

}
