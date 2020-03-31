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
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;
import projet.utils.DbConnection;
import projet.entities.plat;

/**
 *
 * @author hp
 */
public class platService {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    public List<plat> getListPlats() throws SQLException {
        List<plat> listplats = new ArrayList<>();
        String req = "SELECT * FROM `plat`";

        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);

            while (res.next()) {
                plat p = new plat(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                listplats.add(p);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listplats;

    }

    public void ajouterPlat(plat p) throws SQLException {

        String req = "INSERT INTO `plat` (`id`, `nomPlat`, `image`, `type`,`status`) VALUES ( '"
                + p.getId() + "', '" + p.getNomPlat() + "', '" + p.getImage() + "', '" + p.getType() + "', '" + p.getStatus() + "') ";
        Statement stm = connection.createStatement();
        stm.executeUpdate(req);

    }

    public void supprimerPlat(int x) {
        String req = "DELETE FROM plat WHERE id=?";

        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, x);

            pst.executeUpdate();

            System.out.println("plat Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public HashMap<String, Integer> getPlatTypeEntree() {

        HashMap<String, Integer> mapTypeEntree = new HashMap<String, Integer>();

        String req = "SELECT id,nomPlat,type FROM `plat` WHERE type=\"Entree\" and status=\"non reserve\" ";

        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);
            plat p;
            while (res.next()) {
                p = new plat(res.getInt(1), res.getString(2));
                mapTypeEntree.put(p.getNomPlat(), p.getId());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mapTypeEntree;
    }

    public HashMap<String, Integer> getPlatTypePlatPrincipal() {

        HashMap<String, Integer> mapTypePlatprincipal = new HashMap<String, Integer>();

        String req = "SELECT id,nomPlat,type FROM `plat` WHERE type=\"plat principal\" and status=\"non reserve\" ";

        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);
            plat p;
            while (res.next()) {
                p = new plat(res.getInt(1), res.getString(2));
                mapTypePlatprincipal.put(p.getNomPlat(), p.getId());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mapTypePlatprincipal;
    }

    public HashMap<String, Integer> getPlatTypeDessert() {

        HashMap<String, Integer> mapTypeDessert = new HashMap<String, Integer>();

        String req = "SELECT id,nomPlat,type FROM `plat` WHERE type=\"Dessert\" and status=\"non reserve\" ";

        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);
            plat p;
            while (res.next()) {
                p = new plat(res.getInt(1), res.getString(2));
                mapTypeDessert.put(p.getNomPlat(), p.getId());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mapTypeDessert;
    }

    public static PopOver popNotification(String notification) {
        Text notifcationText = new Text(notification);
        notifcationText.setWrappingWidth(230);
        notifcationText.setStyle("-fx-font-weight: bold");

        HBox hBox = new HBox(notifcationText);
        hBox.setPadding(new Insets(0, 5, 0, 5));

        PopOver popOver = new PopOver(hBox);
        popOver.setTitle("New Notification");

        return popOver;
    }

    public boolean modifierPlat(plat p) throws SQLException {
        {

            String req = "UPDATE plat SET nomPlat=? , type=? , image=? WHERE `id` = ?";

            try {
                pst = connection.prepareStatement(req);
                pst.setString(1, p.getNomPlat());
                pst.setString(2, p.getType());
                pst.setString(3, p.getImage());
                pst.setInt(4, p.getId());
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
// apres l'ajout d'un menu on change le status du plat utilisé 
    public boolean modifierStatus(int id) throws SQLException {
        {

            String req = "UPDATE plat SET status=? WHERE `id` = ?";

            try {
                pst = connection.prepareStatement(req);
                pst.setString(1, "reserve");
                pst.setInt(2, id);
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

     //controle de saisie
    private static Matcher matcher;
    private static final String chaineSimple_sanEspace_pattern = "^[A-Za-z]+$";
    private static Pattern chaineSimple_pattern__sanEspace_complie = Pattern.compile(chaineSimple_sanEspace_pattern);

    public static boolean validationChaineSimpleSansEspace(final String chaineSaisie) {
        matcher = chaineSimple_pattern__sanEspace_complie.matcher(chaineSaisie);
        return matcher.matches();
    }
}
