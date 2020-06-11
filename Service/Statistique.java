

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CL.Service;

import CL.Utils.Connectiondb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author amena
 */
public class Statistique {
    
    
    
              
    public static int totaleNT = 0;
    public static int totaleT = 0;
    
    Connection con=Connectiondb.getInstance().getCnx();

    public void calculatereportspermonth() throws SQLException {
 
        
        
        
        
        
        

        

             Statement statement =con.createStatement();
   
       
                String request = "SELECT * FROM reclamation ";
            
            
            
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                if ("En attente".equals(rs.getString(7))) {
                    totaleNT += 1;
                   

                } else if ("Traitée".equals(rs.getString(7))) {
                    totaleT += 1;  
                    


            }

        } 

    } 
    
}
    
    
    
    
    
    
    
    
    
    
    
    
