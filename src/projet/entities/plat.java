/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

/**
 *
 * @author hp
 */
public class plat {
    private int id ;
    private String nomPlat;
    private String image;
    private String type;
    private String status; 

    public plat() {
    }

    public plat(int id, String nomPlat,String image, String type, String status) {
        this.id = id;
        this.nomPlat = nomPlat;
        this.image= image;
        this.type = type;
        this.status = status;
    }
    public plat(String nomPlat,String type) {
      
        this.nomPlat = nomPlat;
       
        this.type = type;
     
    }
     public plat(int id, String nomPlat) {
        this.id = id;
        this.nomPlat = nomPlat;
        
    }
public plat( String nomPlat,String image, String type, String status) {
        this.nomPlat = nomPlat;
        this.image= image;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "plat{" + "id=" + id + ", nomPlat=" + nomPlat + ", image=" + image + ", type=" + type + ", status=" + status + '}';
    }
    
}
