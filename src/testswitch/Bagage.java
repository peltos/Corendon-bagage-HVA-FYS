/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testswitch;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Daan Dirker
 */
public class Bagage extends Gebruiker{
    
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleIntegerProperty gevondenID = new SimpleIntegerProperty();
    private final SimpleIntegerProperty vermistID = new SimpleIntegerProperty();
    private final SimpleStringProperty datum = new SimpleStringProperty();
    private final SimpleIntegerProperty labelNummer = new SimpleIntegerProperty();
    private final SimpleIntegerProperty vluchtNr = new SimpleIntegerProperty();
    private final SimpleStringProperty bagageType = new SimpleStringProperty();
    private final SimpleStringProperty tijd = new SimpleStringProperty();
    private final SimpleStringProperty luchthaven = new SimpleStringProperty();
    private final SimpleStringProperty bestemming = new SimpleStringProperty();
    private final SimpleStringProperty merk = new SimpleStringProperty();
    private final SimpleStringProperty kleur = new SimpleStringProperty();
    private final SimpleStringProperty BijzondereKenmerken = new SimpleStringProperty();

    //Setters
    public void setId(int id){
        this.id.set(id);
    }
    public void setDatum(String datum){
        this.datum.set(datum);
    }
    public void setLabelNummer(int labelNummer){
        this.labelNummer.set(labelNummer);
    }
    public void setVluchtNr(int vluchtNr){
        this.vluchtNr.set(vluchtNr);
    }
    public void setBagageType(String bagageType){
        this.bagageType.set(bagageType);
    }
    public void setTijd(String tijd){
        this.tijd.set(tijd);
    }
    public void setLuchthaven(String luchthaven){
        this.luchthaven.set(luchthaven);
    }
    public void setBestemming(String bestemming){
        this.bestemming.set(bestemming);
    }
    public void setMerk(String merk){
        this.merk.set(merk);
    }
    public void setKleur(String kleur){
        this.kleur.set(kleur);
    }
    public void setBijzondereKenmerken(String bijzondereKenmerken){
        this.BijzondereKenmerken.set(bijzondereKenmerken);
    }
    public void setGevondenID(int gevondenID){
        this.gevondenID.set(gevondenID);
    }
    public void setVermistID(int vermistID){
        this.vermistID.set(vermistID);
    }
    
    //Getters
    public Integer getId(){
        return id.get();
    }
    public String getDatum(){
        return datum.get();
    }
    public Integer getLabelNummer(){
        return labelNummer.get();
    }
    public Integer getVluchtNr(){
        return vluchtNr.get();
    }
    public String getBagageType(){
        return bagageType.get();
    }
    public String getTijd(){
        return tijd.get();
    }
    public String getLuchthaven(){
        return luchthaven.get();
    }
    public String getBestemming(){
        return bestemming.get();
    }
    public String getMerk(){
        return merk.get();
    }
    public String getKleur(){
        return kleur.get();
    }
    public String getBijzondereKenmerken(){
        return BijzondereKenmerken.get();
    }
    public Integer getVermistID(){
        return vermistID.get();
    }
    public Integer getGevondenID(){
        return gevondenID.get();
    }
    
}
