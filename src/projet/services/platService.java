/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.services;

import java.io.File;
import java.io.IOException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
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
                plat p = new plat ();
                p.setId(res.getInt(1));
                p.setNomPlat(res.getString(2));
                p.setImage(res.getString(3));
                p.setType(res.getString(4));
                p.setStatus(res.getString(5));
                
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
     public boolean exportXLS() throws WriteException, SQLException {
        try {

            ObservableList<plat> list = FXCollections.observableArrayList(getListPlats());

            File file = new File("C://Users//hp//Desktop//projetpii//Plats.xls");
            WritableWorkbook myexcel = Workbook.createWorkbook(file);
            WritableSheet mysheet = myexcel.createSheet("Plats", 0);
            Label id = new Label(0, 0, "id");
            Label nomPlat = new Label(1, 0, "nomPlat");
            Label type = new Label(2, 0, "type");
           
            mysheet.addCell(id);
            mysheet.addCell(nomPlat);
            mysheet.addCell(type);
           

            int i = 1;
            for (plat p : list) {

                id = new Label(0, i, String.valueOf(p.getId()));
                nomPlat = new Label(1, i, p.getNomPlat());
                type = new Label(2, i, p.getType());
                mysheet.addCell(id);
                mysheet.addCell(nomPlat);
                mysheet.addCell(type);
               
                i++;
            }

            myexcel.write();
            myexcel.close();

            return true;

        } catch (IOException ex) {
            //        Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public List<plat> rechercheCategories(String str) {
        List<plat> categories = new ArrayList<plat>();
        String sql = "SELECT * FROM plat WHERE nomPlat LIKE ? ";
        PreparedStatement statement;

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + str + "%");
            //statement.setString(2, "%" + str + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                plat c = new plat();
                c.setId(rs.getInt(1));
                c.setNomPlat(rs.getString(2));
                  c.setStatus(rs.getString(3));
              
                c.setImage(rs.getString(4));
              
                 c.setType(rs.getString(5));
               
                
                
                
         
             
                categories.add(c);
            }
        } catch (SQLException ex) {

        }
        return categories;
    }
     //controle de saisie
    private static Matcher matcher;
    private static final String chaineSimple_sanEspace_pattern = "^[A-Za-z]+$";
    private static Pattern chaineSimple_pattern__sanEspace_complie = Pattern.compile(chaineSimple_sanEspace_pattern);

    public static boolean validationChaineSimpleSansEspace(final String chaineSaisie) {
        matcher = chaineSimple_pattern__sanEspace_complie.matcher(chaineSaisie);
        return matcher.matches();
    }
    private static final String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern email_pattern_compile = Pattern.compile(email_pattern);

    public static boolean validationEmail(final String emailSaisie) {
        matcher = email_pattern_compile.matcher(emailSaisie);
        return matcher.matches();
    }
}
