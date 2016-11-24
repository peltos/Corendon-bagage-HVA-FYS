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

public class Gevonden {

    private final SimpleIntegerProperty gevondenID = new SimpleIntegerProperty();
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleIntegerProperty labelNr = new SimpleIntegerProperty();
    private final SimpleIntegerProperty vluchtNr = new SimpleIntegerProperty();
    
    //Setters
    public void setGevondenID(int gevondenID){
        this.gevondenID.set(gevondenID);
    }
    public void setDatum(String date){
        this.date.set(date);
    }
    public void setLabelNr(int labelNr){
        this.labelNr.set(labelNr);
    }
    public void setVluchtNr(int vluchtNr){
        this.vluchtNr.set(vluchtNr);
    }
    
    //Getters
    public Integer getGevondenID(){
        return gevondenID.get();
    }
    public String getDatum(){
        return date.get();
    }
    public Integer setLabelNr(){
        return labelNr.get();
    }
    public Integer setVluchtNr(){
        return vluchtNr.get();
    }
         
}

