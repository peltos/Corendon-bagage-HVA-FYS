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

public class Gebruiker {

    private final SimpleIntegerProperty gebruikerID = new SimpleIntegerProperty();
    private final SimpleStringProperty voornaam = new SimpleStringProperty();
    private final SimpleStringProperty tussenvoegsel = new SimpleStringProperty();
    private final SimpleStringProperty achternaam = new SimpleStringProperty();
    private final SimpleStringProperty naam = new SimpleStringProperty();
    private final SimpleStringProperty username = new SimpleStringProperty();
    private final SimpleIntegerProperty telefoonnummer = new SimpleIntegerProperty();
    private final SimpleStringProperty email = new SimpleStringProperty();
    private final SimpleIntegerProperty positie = new SimpleIntegerProperty();
    
    public void samenvoegenNaam(String voornaam, String tussenvoegsel, String achternaam){
        if (tussenvoegsel == null) {
            this.naam.set(voornaam + " " + achternaam);
        }else {
            this.naam.set(voornaam + " " + tussenvoegsel + " " + achternaam);
        } 
    }
    
    //Setters
    public void setGebruikerID(int gebruikerID){
        this.gebruikerID.set(gebruikerID);
    }
    public void setVoornaam(String voornaam){
        this.voornaam.set(voornaam);
    }
    public void setTussenvoegsel(String tussenvoegsel){
        this.tussenvoegsel.set(tussenvoegsel);
    }
    public void setAchternaam(String achternaam){
        this.achternaam.set(achternaam);
    }
    public void setUsername(String username){
        this.username.set(username);
    }
    public void setTelefoonnummer(int telefoonnummer){
        this.telefoonnummer.set(telefoonnummer);
    }
    public void setEmail(String email){
        this.email.set(email);
    }
    public void setPositie(int positie){
        this.positie.set(positie);
    }
    
    //Getters
    public Integer getGebruikerID(){
        return gebruikerID.get();
    }
    public String getVoornaam(){
        return voornaam.get();
    }
    public String getTussenvoegsel(){
        return tussenvoegsel.get();
    }
    public String getAchternaam(){
        return achternaam.get();
    }
    public String getUsername(){
        return username.get();
    }
    public Integer getTelefoonnummer(){
        return telefoonnummer.get();
    }
    public String getEmail(){
        return email.get();
    }
    public Integer getPositie(){
        return positie.get();
    }
    public String getNaam(){
        return naam.get();
    }
         
}

