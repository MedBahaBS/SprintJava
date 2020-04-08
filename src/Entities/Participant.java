/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Baha
 */
public class Participant {
  private int ideleve;
  private int idevent;

    public Participant(int ideleve, int idevent) {
        this.ideleve = ideleve;
        this.idevent = idevent;
    }
    
    public int getIdeleve() {
        return ideleve;
    }

    public void setIdeleve(int ideleve) {
        this.ideleve = ideleve;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Override
    public String toString() {
        return "Participant{" + "ideleve=" + ideleve + ", idevent=" + idevent + '}';
    }
}
