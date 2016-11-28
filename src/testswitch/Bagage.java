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
public class Bagage {
    
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty datum = new SimpleStringProperty();
    private final SimpleIntegerProperty labelNummer = new SimpleIntegerProperty();
    private final SimpleIntegerProperty vluchtNr = new SimpleIntegerProperty();
    private final SimpleStringProperty bagageType = new SimpleStringProperty();

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
    
}
