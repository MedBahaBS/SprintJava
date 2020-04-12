/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import Entity.Club;
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
public class ClubDao implements Idao<Club>{
    private static ClubDao instance;
    private Statement st;
    private ResultSet rs;
    
    
    private ClubDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ClubDao getInstance(){
        if(instance==null) 
            instance=new ClubDao();
        return instance;
    }
    
    @Override
    public void insert(Club o) {
        String req="insert into Club (nom,description,responsable) values('"+o.getNom()+"','"+o.getDescription()+"','"+o.getResponsable()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Club o) {
        String req="delete from Club where id="+o.getIdc();
        Club p=displayById(o.getIdc());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Club> displayAll() {
        String req="select * from Club";
        ObservableList<Club> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Club c=new Club();
                c.setIdc(rs.getInt(1));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString(3));
                c.setResponsable(rs.getString(4));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Club> displayAllList() {
        String req="select * from Club";
        List<Club> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Club c=new Club();
                c.setIdc(rs.getInt(1));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString(3));
                c.setResponsable(rs.getString(4));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Club displayById(int id) {
        String req="select * from Club where id ="+id;
           Club c=new Club();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                c.setIdc(rs.getInt(1));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString(3));
                c.setResponsable(rs.getString(4));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;
    }

    @Override
    public boolean update(Club c) {
        String qry = "UPDATE Club SET nom = '"+c.getNom()+"', description = '"+c.getDescription()+"', responsable = '"+c.getResponsable()+"' WHERE id = "+c.getIdc();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
