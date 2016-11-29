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
    
    private final SimpleIntegerProperty Vid = new SimpleIntegerProperty();
    
    private final SimpleIntegerProperty Gid = new SimpleIntegerProperty();
    private final SimpleIntegerProperty GgevondenID = new SimpleIntegerProperty();
    private final SimpleIntegerProperty GvermistID = new SimpleIntegerProperty();
    private final SimpleStringProperty Gdatum = new SimpleStringProperty();
    private final SimpleIntegerProperty GlabelNummer = new SimpleIntegerProperty();
    private final SimpleIntegerProperty GvluchtNr = new SimpleIntegerProperty();
    private final SimpleStringProperty GbagageType = new SimpleStringProperty();
    private final SimpleStringProperty Gtijd = new SimpleStringProperty();
    private final SimpleStringProperty Gluchthaven = new SimpleStringProperty();
    private final SimpleStringProperty Gbestemming = new SimpleStringProperty();
    private final SimpleStringProperty Gmerk = new SimpleStringProperty();
    private final SimpleStringProperty Gkleur = new SimpleStringProperty();
    private final SimpleStringProperty GBijzondereKenmerken = new SimpleStringProperty();

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
    
    //Setters
    
    public void setVId(int id){
        this.Vid.set(id);
    }
    
    public void setGId(int id){
        this.Gid.set(id);
    }
    public void setGDatum(String datum){
        this.Gdatum.set(datum);
    }
    public void setGLabelNummer(int labelNummer){
        this.GlabelNummer.set(labelNummer);
    }
    public void setGVluchtNr(int vluchtNr){
        this.GvluchtNr.set(vluchtNr);
    }
    public void setGBagageType(String bagageType){
        this.GbagageType.set(bagageType);
    }
    public void setGTijd(String tijd){
        this.Gtijd.set(tijd);
    }
    public void setGLuchthaven(String luchthaven){
        this.Gluchthaven.set(luchthaven);
    }
    public void setGBestemming(String bestemming){
        this.Gbestemming.set(bestemming);
    }
    public void setGMerk(String merk){
        this.Gmerk.set(merk);
    }
    public void setGKleur(String kleur){
        this.Gkleur.set(kleur);
    }
    public void setGBijzondereKenmerken(String bijzondereKenmerken){
        this.GBijzondereKenmerken.set(bijzondereKenmerken);
    }
    
    //Getters
    public Integer getVId(){
        return Vid.get();
    }
    
    public Integer getGId(){
        return Gid.get();
    }
    public String getGDatum(){
        return Gdatum.get();
    }
    public Integer getGLabelNummer(){
        return GlabelNummer.get();
    }
    public Integer getGVluchtNr(){
        return GvluchtNr.get();
    }
    public String getGBagageType(){
        return GbagageType.get();
    }
    public String getGTijd(){
        return Gtijd.get();
    }
    public String getGLuchthaven(){
        return Gluchthaven.get();
    }
    public String getGBestemming(){
        return Gbestemming.get();
    }
    public String getGMerk(){
        return Gmerk.get();
    }
    public String getGKleur(){
        return Gkleur.get();
    }
    public String getGBijzondereKenmerken(){
        return GBijzondereKenmerken.get();
    }
}

