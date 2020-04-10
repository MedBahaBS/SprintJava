/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Database.Conct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class ServiceQuran {
     Connection con=Conct.getInstance().getCnx();
      ResultSet rs;
    public void afficherQuran()
 {
  
     try {
        Statement st = con.createStatement();
          String req="select * from quran";
  ResultSet rs=st.executeQuery(req);
  while(rs.next())
  {
      System.out.println("id_quran : "+rs.getInt(1)+" image : "+rs.getString(2)+" url : "+rs.getString(3));
  
  }
     } catch (SQLException ex) {
         Logger.getLogger(ServiceQuran.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
}
