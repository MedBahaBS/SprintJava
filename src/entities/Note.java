/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Note {
     int idnote;
    int eleve;
    int classe;
    int Matiere;
    float note1;
    float note2;
    float moyenne;
    String nomMatiere;

   

    public Note(int idnote, int eleve, int classe, int Matiere, float note1, float note2, float moyenne) {
        this.idnote = idnote;
        this.eleve = eleve;
        this.classe = classe;
        this.Matiere = Matiere;
        this.note1 = note1;
        this.note2 = note2;
        this.moyenne = moyenne;
    }

    public Note(int idnote, int Matiere, float note1, float note2, float moyenne) {
        this.idnote = idnote;
        this.Matiere = Matiere;
        this.note1 = note1;
        this.note2 = note2;
        this.moyenne = moyenne;
    }

    public Note(int Matiere, float note1, float note2, float moyenne) {
        this.Matiere = Matiere;
        this.note1 = note1;
        this.note2 = note2;
        this.moyenne = moyenne;
    }

    
    
    public Note(int eleve, int classe, int Matiere, float note1, float note2, float moyenne) {
        this.eleve = eleve;
        this.classe = classe;
        this.Matiere = Matiere;
        this.note1 = note1;
        this.note2 = note2;
        this.moyenne = moyenne;
    }

    public Note() {
    }

    public Note(String matiere, int parseInt, int parseInt0, int parseInt1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Note(int idNote, String Matiere, float note1, float note2, float moyenne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//haz cvbn 3aycheek amouna ya mezyenaa <3 menghir mweye <3 aya byeee ' 
    public Note(int idnote, float note1, float note2, float moyenne, String nomMatiere) {
        this.idnote = idnote;
        this.note1 = note1;
        this.note2 = note2;
        this.moyenne = moyenne;
        this.nomMatiere = nomMatiere;
    }
 public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
    
    public int getIdnote() {
        return idnote;
    }

    public void setIdnote(int idnote) {
        this.idnote = idnote;
    }

    public int getEleve() {
        return eleve;
    }

    public void setEleve(int eleve) {
        this.eleve = eleve;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public int getMatiere() {
        return Matiere;
    }

    public void setMatiere(int Matiere) {
        this.Matiere = Matiere;
    }

    public float getNote1() {
        return note1;
    }

    public void setNote1(float note1) {
        this.note1 = note1;
    }

    public float getNote2() {
        return note2;
    }

    public void setNote2(float note2) {
        this.note2 = note2;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }
    
    
    
}
