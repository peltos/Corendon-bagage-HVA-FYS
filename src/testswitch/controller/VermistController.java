package testswitch.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import testswitch.Database;
import testswitch.Main;
import testswitch.MainNavigator;

/**
 *
 * @author Maarten Mes
 */
public class VermistController {
    
    
    
@FXML private TextField  FXVermisteLabelNummer, FXVermisteVluchtNummer,
        FXVermisteBestemming, FXVermisteLuchthaven;
@FXML private TextField FXVermisteType, FXVermisteMerk, FXVermisteKleur,
        FXVermisteBijzondereKenmerken;
@FXML private TextField FXVermisteNaam, FXVermisteAdres, FXVermisteWoonplaats,
        FXVermistePostcode, FXVermisteLand, FXVermisteTelefoon, FXVermisteEmail;
@FXML private Button vermisteButton;


Database database = Main.getDatabase();

    public void vermisteOpslaanDB(ActionEvent event) throws SQLException {
        
        String query = "INSERT INTO testDatabase.Vermist"
                + " (Datum, Tijd, LabelNummer, VluchtNummer, Bestemming,"
                + " Luchthaven, Naam, Adres, Woonplaats, Postcode, Land,"
                + " Telefoon, Email, BagageType, Merk, Kleur, BijzonderKenmerken, Visibility)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        
        
        PreparedStatement statement = database.prepareStatement(query);
        
        
        

        try{ 
        statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        int Labelnummer = Integer.parseInt(FXVermisteLabelNummer.getText());
        statement.setInt(3, Labelnummer);
        int Vluchtnummer = Integer.parseInt(FXVermisteVluchtNummer.getText());
        statement.setInt(4, Vluchtnummer);
        statement.setString(5, FXVermisteBestemming.getText());
        statement.setString(6, FXVermisteLuchthaven.getText());
        statement.setString(7, FXVermisteNaam.getText());
        statement.setString(8, FXVermisteAdres.getText());
        statement.setString(9, FXVermisteWoonplaats.getText());
        statement.setString(10, FXVermistePostcode.getText());
        statement.setString(11, FXVermisteLand.getText());
        int VermisteTelefoon = Integer.parseInt(FXVermisteTelefoon.getText());
        statement.setInt(12, VermisteTelefoon);
        statement.setString(13, FXVermisteEmail.getText());
        statement.setString(14, FXVermisteType.getText());
        statement.setString(15, FXVermisteMerk.getText());
        statement.setString(16, FXVermisteKleur.getText());
        statement.setString(17, FXVermisteBijzondereKenmerken.getText());
        statement.setInt(18, 0);
        
        statement.executeUpdate();

        
        } catch (Exception e) {
            e.printStackTrace(System.err);

        }
        MainNavigator.loadVista(MainNavigator.START);
    }

  
    

}
