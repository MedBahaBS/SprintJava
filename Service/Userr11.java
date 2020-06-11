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
import java.util.ArrayList;
import CL.Entities.*;
import java.sql.PreparedStatement;

/**
 *
 * @author amena
 */
public class Userr11 {
    
      Connection con=Connectiondb.getInstance().getCnx();
    
    
     public ArrayList<Userr> listeUsers() {
        ArrayList<Userr> myList = new ArrayList<Userr>();
        try {
            /**********************************************************************************************************/
            // matensech ba3ed matintegri 5edmet ahmed " gestion user " twali taffichi ken talents : SELECT * FROM users where role=talent
            /**********************************************************************************************************/
            String req3 = "SELECT * FROM users";
            Statement st3 = Connectiondb.getInstance().getInstance().createStatement();
            ResultSet rs = st3.executeQuery(req3);
            while (rs.next()) {

                Userr userrr = new Userr();
                
                userrr.setId(rs.getInt("id_user"));
                userrr.setUsername(rs.getString("username"));
         

                userrr.setEmail(rs.getString("email"));

                //userrr.setTitre(rs.getString("titre"));

                
             
                myList.add(userrr);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
     
       
        
        
        
        
        
        

        
    }   
       
      
   
        
        

        
    
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    

