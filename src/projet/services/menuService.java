/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projet.entities.menu;
import projet.entities.plat;
import projet.utils.DbConnection;

/**
 *
 * @author hp
 */
public class menuService {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    public List<menu> getListMenu() throws SQLException {
        List<menu> listmenu = new ArrayList<>();
        // String req = "SELECT m.id, p.nomPlat, pp.nomPlat, ppp.nomPlat, m.jourMenu m.nbrLike,m.nbrFoisLike,m.moyenneLike FROM menu m, plat p,plat pp,plat ppp WHERE (m.entree = p.id AND m.platprincipal=pp.id AND m.dessert=ppp.id)";
        String req = "SELECT m.id, p.nomPlat, pp.nomPlat, ppp.nomPlat,m.entree,m.platprincipal,m.dessert,m.jourMenu,m.nbrFoisLike,m.nbrLike,m.moyenneLike  FROM menu m, plat p,plat pp,plat ppp WHERE (m.entree = p.id AND m.platprincipal=pp.id AND m.dessert=ppp.id)";

        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);

            while (res.next()) {
                menu mm=new menu();
                mm.setId(res.getInt(1));
                mm.setNomPlatEntree(res.getString(2));
                mm.setNomPlatPrincipal(res.getString(3));
                mm.setNomPlatDessert(res.getString(4));
                mm.setEntree(res.getInt(5));
                mm.setPlatprincipale(res.getInt(6));
                mm.setDessert(res.getInt(7));
                mm.setJourMenu(res.getString(8));
                mm.setNbrFoisLike(res.getInt(9));
                mm.setNbrLike(res.getInt(10));
                  mm.setMoyenneLike(res.getInt(11));
                //menu m = new menu(res.getInt(1), res.getString(5), res.getString(2), res.getString(3), res.getString(4));
                listmenu.add(mm);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listmenu;
    }

    public void ajouterMenu(menu m) throws SQLException {

        String req = "INSERT INTO menu  ( `jourMenu`,  `entree`, `platprincipal`, `dessert`,`nbrLike`, `nbrFoisLike`, `moyenneLike` )  VALUES ( '"
                + m.getJourMenu() + "', '" + m.getEntree() + "', '" + m.getPlatprincipale() + "', '" + m.getDessert() + "', '" + m.getNbrLike() + "', '" + m.getNbrFoisLike() + "', '" + m.getMoyenneLike() + "') ";
        Statement stm = connection.createStatement();

        stm.executeUpdate(req);

    }

    public void supprimerMenu(int x) {
        String req = "DELETE FROM menu WHERE id=?";

        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, x);

            pst.executeUpdate();

            System.out.println("menu Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

     public boolean modifierMenu(menu m) throws SQLException {
        {

            String req = "UPDATE menu SET jourMenu=? , entree=? , platprincipal=?, dessert=? WHERE `id` = ?";

            try {
                pst = connection.prepareStatement(req);
                pst.setString(1, m.getJourMenu());
                pst.setInt(2, m.getEntree());
                pst.setInt(3, m.getPlatprincipale());
                pst.setInt(4, m.getDessert());
                pst.setInt(5, m.getId());
                int res = pst.executeUpdate();
                if (res > 0) {
                    return true;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;

        }
    }
    
    public boolean modifierLike(menu c) {
        String req = "UPDATE menu SET moyenneLike= ?,nbrFoisLike= ?,nbrLike = ? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            // System.out.println(c.getId());
            pst.setFloat(1, c.getMoyenneLike());
            pst.setInt(2, c.getNbrFoisLike());
            pst.setInt(3, c.getNbrLike());
            pst.setInt(4, c.getId());

            int res = pst.executeUpdate();

            if (res > 0) {
                return true;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }
    
}
