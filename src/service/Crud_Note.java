/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Matiere;
import entities.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author asus
 */
public class Crud_Note {
     private Connection con;
    private Statement st;
    private PreparedStatement pre;
    public Crud_Note() {
            con=MyDB .getInstance().getCnx();

    }
    
    public void ajouter(Note n) throws SQLException {
        try{
       st = con.createStatement();
        String requeteInsert = "INSERT INTO Note(note1, note2,moyenne,Matiere)VALUES ( '" + n.getNote1() + "', '" + n.getNote2() + "','" + n.getMoyenne() +"','"+n.getMatiere()+"')";
                   
              st.executeUpdate(requeteInsert);
        //awka mrigla 3ayech khouyaa l ghalii  bahaa ;)
        }
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
           
    }
    public void delete(int idnote) throws SQLException {
            try { 
            String query ="DELETE FROM Note WHERE idnote="+idnote; 
            Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("la Note de l id = "+idnote+"a été supprimée ") ; 
           
            
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    
        
    }
        public List<Note> afficherNote() throws SQLException {
    List<Note> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Note ");
     while (rs.next()) {                
               int idNote=rs.getInt(1);
               int Matiere=rs.getInt(7);
               float note1=rs.getFloat(4);
               float note2=rs.getFloat(5);
               float moyenne=rs.getFloat(6);
               //eyy emmeemm ki nheb nraja juste nom f l'affichage ok nom matiere? eyyy 
               
                 
        Note n = new Note(idNote,Matiere, note1, note2,moyenne);
     arr.add(n);
     }
    return arr;
    
    
}
         public List<Note> afficherNote2() throws SQLException {
    List<Note> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select n.*,m.nom from Note n INNER JOIN matiere m on m.idmatiere=n.Matiere");
     while (rs.next()) {                
               int idNote=rs.getInt(1);
               int Matiere=rs.getInt(7);
               float note1=rs.getFloat(4);
               float note2=rs.getFloat(5);
               float moyenne=rs.getFloat(6);
               String nomMatiere=rs.getString(8);
               //eyy emmeemm ki nheb nraja juste nom f l'affichage ok nom matiere? eyyy 
               

        Note n = new Note(idNote, note1, note2,moyenne,nomMatiere);
     arr.add(n);
     }
    return arr;
    
    
}
        
        
       //aaamalet liste ena récuperiit fehaa les nom lkol ml matiere eyy ey rotha lahtha tawnekhdemha

    
}
