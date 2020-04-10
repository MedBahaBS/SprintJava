/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author acer
 */
public class Quran  implements java.io.Serializable {


     private Integer idQuran;
     private String image;
     private String url;

    public Integer getIdQuran() {
        return idQuran;
    }

    public void setIdQuran(Integer idQuran) {
        this.idQuran = idQuran;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Quran(Integer idQuran, String image, String url) {
        this.idQuran = idQuran;
        this.image = image;
        this.url = url;
    }
     
    
}
