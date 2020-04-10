/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashbordback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author acer
 */
public class Main extends Application {
 
    @Override
    public void start(Stage primaryStage) {
         try {
             
            Parent root = FXMLLoader
        .load(getClass().getResource("Acceuil.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws  Exception {
       /* String details ="bonjour QR";
        ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
         File f = new File("C:\\Users\\acer\\Desktop\\ameni")  ; 
        FilePermission permission = new FilePermission("C:\\Users\\acer\\Desktop\\ameni", "read");
           
        
               FileOutputStream fos = new FileOutputStream(f);
               fos.write(out.toByteArray());
               fos.flush();
        */
        launch(args);
    }
    
}
