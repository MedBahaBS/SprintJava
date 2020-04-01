/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
//connexion matekhdemich
public class Connectiondb {
   String url = "jdbc:mysql://localhost:3306/testpi";
    Connection cnx; 
    String Login="root"; 
    String Password="" ; 
    static Connectiondb con ; 
    
   public Connectiondb() {
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
   public static Connectiondb getInstance()  {
       if (con==null) {
           con=new Connectiondb() ; 
       }
     return con ; 
       
   }

}
