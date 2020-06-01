/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.services;

import projet.utils.DbConnection;
import projet.entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author acer
 */
public class ServiceUser {
    
     public boolean verifpassword(String username, String password ) throws SQLException {
        if (!username.isEmpty() && !password.isEmpty() ) {
            String requete = "SELECT password FROM fos_user WHERE username = '" + username +"'";
            System.out.println("requete = " +requete);
            Statement s = DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
                String pw = rs.getString(1);
                System.out.println("pw1 = " +pw);
                pw = pw.replace("$2y$", "$2a$");
                System.out.println("pw2 = " +pw);
                return (BCrypt.checkpw(password, pw));
            }
            else return false ;
        }  
        else {
            return false;
        }

    }
     
    public boolean bloqué( int enable){
        if(enable==1){
            return true;
        }
        return false;
    }
    
    public void ajoutUser(User u, String password ){
        try {
            
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
            hashedpw = hashedpw.replace("$2a$", "$2y$");
            String UserRole = "a:0:{}";
            String requete = "INSERT INTO fos_user ( username, username_canonical, email , email_canonical ,enabled, password , roles) VALUES "
                + "('" + u.getUsername() + "','"+  u.getUsername() + "','" + u.getEmail() + "','" + u.getEmail() +"','" + 1 + "','" + hashedpw + "','" + UserRole +  "')";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
     
   public void ajoutAdmin(User u, String password ){
        try { String role="a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
        String requete = "INSERT INTO fos_user (username,email,password,roles) VALUES "
                + "('" + u.getUsername() + "','" + u.getEmail() + "','" + hashedpw + "','" +role+ "','" + "')";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
   
   public List<User> chercher(String s) throws SQLException {
		List<User> users = new ArrayList<>();
String rq = "select * from fos_user where username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%'" ;
			
			Statement st =  DbConnection.getInstance().getConnection().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				User u=new User(rst.getInt("id"),rst.getString("username"),rst.getString("email"), rst.getString("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    public List<User> chercherEnabled(String s) throws SQLException {
		List<User> users = new ArrayList<>();
String rq = "select * from fos_user where enabled ='"+ 1 +"' and (username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%')" ;
			
			Statement st =   DbConnection.getInstance().getConnection().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				User u=new User(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getString("enabled") ,rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    
    
   public List<User> chercherDisabled(String s) throws SQLException {
		List<User> users = new ArrayList<>();
String rq = "select * from fos_user where enabled ='"+ 0 +"' and (username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%')" ;
			
			Statement st =   DbConnection.getInstance().getConnection().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				User u=new User(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getString("enabled") ,rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    
    
     public List<User> getAllUsers() throws SQLException
    {   
        List <User> products= new ArrayList<>();
        String req="SELECT * FROM fos_user";
	Statement stm =   DbConnection.getInstance().getConnection().createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while (rst.next()){
              User u=new User(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getString("enabled"),rst.getString("roles"));
            products.add(u);
        }
        return products;
    }

    public boolean modifierUser(User u){
        try{
            String cpass=BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        String requete = "UPDATE fos_user SET "
                + "username = ?,"
                + "email = ?,"
                + "password = ?"
                + "WHERE id=?";
        PreparedStatement pst =  DbConnection.getInstance().getConnection().prepareStatement(requete);
        pst.setString(1, u.getUsername());
        pst.setString(2, u.getEmail());
        pst.setString(3, cpass);
        pst.setInt(4, u.getId());
        pst.executeUpdate();
        System.out.println(u.getEmail()+ u.getUsername());
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return true;
}
    
    
    public void modifierUsername(String email, String username) throws SQLException{
        String requete= "UPDATE fos_user SET username='"+ username +"'"+"WHERE email='"+ email+"'";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierEmail(String email, String mail) throws SQLException{
        String requete= "UPDATE fos_user SET email='"+ mail +"'"+"WHERE email='"+ email+"'";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierPassword(String email, String pass) throws SQLException{
       String cpass=BCrypt.hashpw(pass, BCrypt.gensalt());
        String requete= "UPDATE fos_user SET password='"+ cpass +"'"+"WHERE email='"+ email+"'";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(requete);
    }   
     public void modifierRole(String email, String role) throws SQLException{
        String requete= "UPDATE fos_user SET roles='"+ role +"'"+"WHERE email='"+ email+"'";
        Statement st =   DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(requete);
    }
    
     public void supprimerUser(int id ){
        try{
        String requete = "DELETE FROM fos_user WHERE id=?";
        PreparedStatement pst =  DbConnection.getInstance().getConnection().prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public List<User> afficherUser() {
        
        List<User> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    public List<User> afficherUserEnabled() {
        
        List<User> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user where enabled='"+ 1 +"'";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
     public List<User> afficherUserDisabled() {
        
        List<User> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user where enabled='"+ 0 +"'";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
     public User getUserByUsername(String username) throws SQLException{
        String requete="SELECT * FROM fos_user WHERE username='"+username+"'";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(requete);
        User u = new User();
       while(rs.next()){
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));  
            }
       return u ;
    }
    
    
    
    
     public boolean existUser (String email) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE email = '" + email +"'";
            Statement s =  DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
     
     public boolean existMail (String email) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE email = '" + email +"'";
            Statement s = DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
     
      public boolean existUsername (String username) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE username = '" + username +"'";
            Statement s =  DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
     public User getUser(String n) throws SQLException{
           User u=new User();

         try{
                   String requete = "SELECT * FROM fos_user WHERE username = '" + n +"'";
                       Statement s =  DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
             while (rs.next()) {

                u.setId(rs.getInt("id")); 
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
             } 
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
            return u; 


     }
     public int getId(String u)
     {
      int i=0;
         try{
                   String requete = "SELECT id FROM fos_user WHERE username = '" + u +"'";
                       Statement s =  DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
             while (rs.next()) {

             i=rs.getInt(1);
             } 
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
            return i; 

     }
    
      public String verifAdmin (String username) throws SQLException {
        String ch="";
          String requete = "SELECT roles FROM fos_user WHERE username = '" + username +"'";
            Statement s =  DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
           ch= rs.getString(1) ; }
        return ch;
    }
         public String getUsername (String username) throws SQLException {
        String ch="";
          String requete = "SELECT username FROM fos_user WHERE username = '" + username +"'";
            Statement s =  DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
           ch= rs.getString("username");}
        return ch;
    }
       
     public void creerToken(String token, String mail)
     {
       try {
           
           
        String requete = "INSERT INTO token (token,email) VALUES "
                + "('" + token +  "','"+mail+"')";
        Statement st =  DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
     
     }
       public boolean verifToken (String token,String mail) throws SQLException {
          String requete = "SELECT * FROM token WHERE token = '" + token +"' and email ='"+mail+"'";
            Statement s =  DbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.first()){
            return true ; }
            else 
                return false ;
        
    }
     
    public void supprimerToken(String t ){
        try{
        String requete = "DELETE FROM token WHERE email=?";
        PreparedStatement pst =  DbConnection.getInstance().getConnection().prepareStatement(requete);
        pst.setString(1, t);
        pst.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    
    }

    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
