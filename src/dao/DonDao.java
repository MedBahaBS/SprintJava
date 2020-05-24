/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import Entity.Don;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionSingleton;
/**
 *
 * @author alaed
 */
public class DonDao implements Idao<Don>{
    private static DonDao instance;
    private Statement st;
    private ResultSet rs;
    
    
    private DonDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DonDao getInstance(){
        if(instance==null) 
            instance=new DonDao();
        return instance;
    }
    
    @Override
    public void insert(Don o) {
        
        String req="insert into Don (club,type,description,email,etat,somme) values('"+o.getClub()+"','"+o.getType()+"','"+o.getDescription()+"','"+o.getEmail()+"','"+o.getEtat()+"','"+o.getSomme()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(DonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Don o) {
        String req="delete from Don where id="+o.getId();
        Don p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(DonDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Don> displayAll() {
        String req="select * from Don";
        ObservableList<Don> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Don d=new Don();
                d.setId(rs.getInt(1));
                d.setClub(rs.getInt(2));
                d.setType(rs.getString("type"));
                d.setDescription(rs.getString(4));
                d.setEmail(rs.getString(5));
                d.setEtat(rs.getInt(6));
                d.setSomme(rs.getFloat(7));
                list.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Don> displayAllList() {
        String req="select * from Don";
        List<Don> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Don d=new Don();
                d.setId(rs.getInt(1));
                d.setClub(rs.getInt(2));
                d.setType(rs.getString("type"));
                d.setDescription(rs.getString(4));
                d.setEmail(rs.getString(5));
                d.setEtat(rs.getInt(6));
                d.setSomme(rs.getFloat(7));
                list.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Don displayById(int id) {
        String req="select * from Don where id ="+id;
           Don c=new Don();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                Don d=new Don();
                d.setId(rs.getInt(1));
                d.setClub(rs.getInt(2));
                d.setType(rs.getString("type"));
                d.setDescription(rs.getString(4));
                d.setEmail(rs.getString(5));
                d.setEtat(rs.getInt(6));
                d.setSomme(rs.getFloat(7));
                
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(DonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;
    }

    @Override
    public boolean update(Don d) {
        return false;
        
    }
    
}
