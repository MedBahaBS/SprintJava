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
 * @author Baha
 */
public class MyDB {
    private final String URL = "jdbc:mysql://localhost:3306/gestion_ecole?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String username = "root";
    private final String password = "";
    private static Connection instance;
    
    private MyDB() {
        try {
            instance = DriverManager.getConnection(URL,username,password);
            System.out.println("Connexion établie");
          
            }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Connection  getInstance () {
        if (instance == null)
           new MyDB();
        return instance;
                
    }
}
