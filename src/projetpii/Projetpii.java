/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpii;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projet.services.menuService;
import projet.services.platService;

/**
 *
 * @author hp
 */
public class Projetpii extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {
        /*  Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
        platService c = new platService();
        c.getListPlats();

        try {
           // URL url = new File("src/projet/interfaces/afficherPlat.fxml").toURI().toURL();
            //  URL url = new File("src/projet/interfaces/inscriptionBack.fxml").toURI().toURL();
                      //  URL url = new File("src/GestionUser/SignIn.fxml").toURI().toURL();
               URL url = new File("src/projet/interfaces/front.fxml").toURI().toURL();

            Parent root = FXMLLoader.load(url);
            //  Parent root = FXMLLoader.load(getClass().getResource("afficherCategoriesClub.fxml"));
            primaryStage.setScene(new Scene(root));
            //set stage borderless
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
