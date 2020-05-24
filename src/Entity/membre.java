/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author alaed
 */
public class membre {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty ideleve;
    

    public membre() {
    }

    public membre(int id, int ideleve) {
        this.id = new SimpleIntegerProperty(id);
        this.ideleve = new SimpleIntegerProperty(ideleve);
        
    }

    

    public int getId() {
        return id.get();
    }

    public void setIdc(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    
    
    public int getIdeleve() {
        return ideleve.get();
    }

    public void setIdeleve(int ideleve) {
        this.ideleve = new SimpleIntegerProperty(ideleve);
    }
    public SimpleIntegerProperty getidProperty(){
        return id;
    }
    public SimpleIntegerProperty getideleveProperty(){
        return ideleve;
    }

    

}
