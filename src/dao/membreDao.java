/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import Entity.membre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import utils.ConnexionSingleton;
/**
 *
 * @author alaed
 */
public class membreDao implements Idao<membre>{
    private static membreDao instance;

    
    private Statement st;
    private ResultSet rs;
    
    
    private membreDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            
            Logger.getLogger(membreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static membreDao getInstance(){
        if(instance==null) 
            instance=new membreDao();
        return instance;
    }
    
    @Override
    public void insert(membre o) {
            
        String req="insert into membre (id,ideleve) values('"+o.getId()+"','"+o.getIdeleve()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            //Logger.getLogger(membreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(membre o) {
        String req="delete from membre where id="+o.getIdeleve();
        membre p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(membreDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<membre> displayAll() {
        String req="select * from membre";
        ObservableList<membre> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                membre c=new membre();
                c.setIdc(rs.getInt(1));
                c.setIdeleve(rs.getInt(2));
                
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(membreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public membre displayById(int id) {
        String req="select * from membre where id ="+id;
           membre c=new membre();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                c.setIdc(rs.getInt(1));
                c.setIdeleve(rs.getInt(2));
                
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(membreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;
    }

   
    
    private static ObservableList<membre> getClubObject(ResultSet rsSet)throws ClassNotFoundException, SQLException
    {
    try {
    ObservableList<membre> clublist =FXCollections.observableArrayList();
        while(rsSet.next())
        {
            membre c= new membre();
            c.setIdc(rsSet.getInt("id"));
            c.setIdeleve(rsSet.getInt("ideleve"));
            clublist.add(c);
        }
        return clublist;
    }catch(SQLException e)
    {
        System.out.println("error while fetching from DB");
        e.printStackTrace();
        throw e;
    }    
    
    }

    @Override
    public boolean update(membre os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    

    
}
