/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testswitch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Daan Dirker
 */
public class Gevonden {

    @FXML
    private TextField FXGevondenLuchthaven;
    @FXML
    private TextField FXGevondenLabelNummer, FXGevondenVluchtNummer, FXGevondenBestemming;
    @FXML
    private TextField FXGevondenType, FXGevondenMerk, FXGevondenKleur, FXGevondenBijzondereKenmerken;
    
    @FXML private Button gevondenButton;


    private final SimpleIntegerProperty gevondenID = new SimpleIntegerProperty();
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleIntegerProperty labelNr = new SimpleIntegerProperty();
    private final SimpleIntegerProperty vluchtNr = new SimpleIntegerProperty();

    //Setters
    public void setGevondenID(int gevondenID) {
        this.gevondenID.set(gevondenID);
    }

    public void setDatum(String date) {
        this.date.set(date);
    }

    public void setLabelNr(int labelNr) {
        this.labelNr.set(labelNr);
    }

    public void setVluchtNr(int vluchtNr) {
        this.vluchtNr.set(vluchtNr);
    }

    //Getters
    public Integer getGevondenID() {
        return gevondenID.get();
    }

    public String getDatum() {
        return date.get();
    }

    public Integer setLabelNr() {
        return labelNr.get();
    }

    public Integer setVluchtNr() {
        return vluchtNr.get();
    }

    public final String DB_NAME = "testDatabase";
    public final String DB_SERVER = "ronpelt.synology.me:3306";
    public final String DB_ACCOUNT = "root";
    public final String DB_PASSWORD = "kGjMtEO06BPiu2u4";

    Database database = new Database(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);
    
    private static java.sql.Timestamp getCurrentTimeStamp() {

	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());
    

}

    public void gevondenOpslaanDB(ActionEvent event) throws SQLException {
        String query = "INSERT INTO testDatabase.Gevonden"
                + " (Tijd, Datum, Luchthaven, Bestemming, BagageType, Merk, Kleur, BijzonderKenmerken, Labelnummer, Vluchtnummer)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement statement = database.prepareStatement(query);
        

        try {
            statement.setTimestamp(1,getCurrentTimeStamp());            
            statement.setTimestamp(2,getCurrentTimeStamp());            
            statement.setString(3, FXGevondenLuchthaven.getText());
            statement.setString(4, FXGevondenBestemming.getText());
            statement.setString(5, FXGevondenType.getText());
            statement.setString(6, FXGevondenMerk.getText());
            statement.setString(7, FXGevondenKleur.getText());
            statement.setString(8, FXGevondenBijzondereKenmerken.getText());
            
            int GevondenLabelNummer = Integer.parseInt(FXGevondenLabelNummer.getText());
            statement.setInt(9, GevondenLabelNummer);
            
            int VluchtNummer = Integer.parseInt(FXGevondenVluchtNummer.getText());
            statement.setInt(10, VluchtNummer);
            
            statement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace(System.err);

        }

    }
}
