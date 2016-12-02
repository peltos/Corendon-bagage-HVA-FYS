/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testswitch;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Daan
 */
public class EditVermistController implements Initializable {
    
    @FXML private TextField  FXVermisteLabelNummer, FXVermisteVluchtNummer,
        FXVermisteBestemming, FXVermisteLuchthaven;
    @FXML private TextField FXVermisteType, FXVermisteMerk, FXVermisteKleur,
        FXVermisteBijzondereKenmerken;
    @FXML private TextField FXVermisteNaam, FXVermisteAdres, FXVermisteWoonplaats,
        FXVermistePostcode, FXVermisteLand, FXVermisteTelefoon, FXVermisteEmail;
    @FXML private Button vermisteButton;
    
    int id = StartController.getSelectedIdVermist();
    
    
    @FXML
    private void readData() {
        Database database = new Database(
                "testDatabase",
                "ronpelt.synology.me:3306",
                "root",
                "kGjMtEO06BPiu2u4"
        );

        try {
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Vermist WHERE idVermist=" + id);
            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                FXVermisteLuchthaven.setText(result.getString("Luchthaven"));
                FXVermisteLabelNummer.setText(result.getString("Labelnummer"));
                FXVermisteVluchtNummer.setText(result.getString("Vluchtnummer"));
                FXVermisteBestemming.setText(result.getString("Bestemming"));
                FXVermisteType.setText(result.getString("BagageType"));
                FXVermisteMerk.setText(result.getString("Merk"));
                FXVermisteKleur.setText(result.getString("Kleur"));
                FXVermisteBijzondereKenmerken.setText(result.getString("BijzonderKenmerken"));
                FXVermisteNaam.setText(result.getString("Naam"));
                FXVermisteAdres.setText(result.getString("Adres"));
                FXVermisteWoonplaats.setText(result.getString("Woonplaats"));
                FXVermistePostcode.setText(result.getString("Postcode"));
                FXVermisteLand.setText(result.getString("Land"));
                FXVermisteTelefoon.setText(result.getString("Telefoon"));
                FXVermisteEmail.setText(result.getString("Email"));
            }
            
        } catch(SQLException ex) {
            ex.getErrorCode();
        }
    }
    
    @FXML
    private void writeToDB() throws SQLException {
        Database database = new Database(
                "testDatabase",
                "ronpelt.synology.me:3306",
                "root",
                "kGjMtEO06BPiu2u4"
        );
        
        String query = "UPDATE testDatabase.Vermist SET "
                + "Luchthaven=?, "
                + "Labelnummer=?, "
                + "Vluchtnummer=?, "
                + "Bestemming=?, "
                + "BagageType=?, "
                + "Merk=?, "
                + "Kleur=?, "
                + "BijzonderKenmerken=?, "
                + "Naam=?, "
                + "Adres=?, "
                + "Woonplaats=?, "
                + "Postcode=?, "
                + "Telefoon=?, "
                + "Email=? "
                + "WHERE idVermist=?;";
        
        PreparedStatement statement = database.prepareStatement(query);

        try {
            statement.setString(1, FXVermisteLuchthaven.getText());
            statement.setInt(2, Integer.parseInt(FXVermisteLabelNummer.getText()));
            statement.setInt(3, Integer.parseInt(FXVermisteVluchtNummer.getText()));
            statement.setString(4, FXVermisteBestemming.getText());
            statement.setString(5, FXVermisteType.getText());
            statement.setString(6, FXVermisteMerk.getText());
            statement.setString(7, FXVermisteKleur.getText());
            statement.setString(8, FXVermisteBijzondereKenmerken.getText());
            statement.setString(9, FXVermisteNaam.getText());
            statement.setString(10, FXVermisteAdres.getText());
            statement.setString(11, FXVermisteWoonplaats.getText());
            statement.setString(12, FXVermistePostcode.getText());
            statement.setInt(13, Integer.parseInt(FXVermisteTelefoon.getText()));
            statement.setString(14, FXVermisteEmail.getText());
            statement.setInt(15, id);
            statement.executeUpdate();
           
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        MainNavigator.loadVista(MainNavigator.START);
    }
    
    @FXML
    private void cancel() {
        MainNavigator.loadVista(MainNavigator.START);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readData();
    }
    
}
