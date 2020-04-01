/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author bhk
 */
public class MyDB {
    
    String URL = "jdbc:mysql://localhost:3306/gestion_ecole";
    Connection cnx;
     
    String USERNAME = "root";
    String PASSWORD = "";
    static MyDB con ; 


    public MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connexion étalbie");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
  
   
    public static MyDB getInstance(){
        if (con == null)
            con = new MyDB();
        
        
        return con;
    }
    
    
   
   

}
