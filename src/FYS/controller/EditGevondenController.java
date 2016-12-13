/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYS.controller;

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
import FYS.Database;
import FYS.Main;
import FYS.MainNavigator;

/**
 *
 * @author Daan
 */
public class EditGevondenController implements Initializable {
    
    @FXML TextField FXGevondenLuchthaven, FXGevondenLabelNummer, FXGevondenVluchtNummer,
            FXGevondenBestemming, FXGevondenType, FXGevondenMerk, FXGevondenKleur,
            FXGevondenBijzondereKenmerken;
    @FXML Button gevondenButton;
    
    int id = StartController.getSelectedIdGevonden();
    Database database = Main.getDatabase();
    
    
    @FXML
    private void readData() {

        try {
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Gevonden WHERE idGevonden=" + id);
            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                FXGevondenLuchthaven.setText(result.getString("Luchthaven"));
                FXGevondenLabelNummer.setText(result.getString("Labelnummer"));
                FXGevondenVluchtNummer.setText(result.getString("Vluchtnummer"));
                FXGevondenBestemming.setText(result.getString("Bestemming"));
                FXGevondenType.setText(result.getString("BagageType"));
                FXGevondenMerk.setText(result.getString("Merk"));
                FXGevondenKleur.setText(result.getString("Kleur"));
                FXGevondenBijzondereKenmerken.setText(result.getString("BijzonderKenmerken"));
            }
            
        } catch(SQLException ex) {
            
        }
    }
    
    @FXML
    private void writeToDB() throws SQLException {
        
        String query = "UPDATE testDatabase.Gevonden SET "
                + "Luchthaven=?, "
                + "Labelnummer=?, "
                + "Vluchtnummer=?, "
                + "Bestemming=?, "
                + "BagageType=?, "
                + "Merk=?, "
                + "Kleur=?, "
                + "BijzonderKenmerken=? "
                + "WHERE idGevonden=?;";
        
        PreparedStatement statement = database.prepareStatement(query);

        try {
            statement.setString(1, FXGevondenLuchthaven.getText());
            
            int gevondenLabelNummer = Integer.parseInt(FXGevondenLabelNummer.getText());
            int gevondenVluchtNummer = Integer.parseInt(FXGevondenVluchtNummer.getText());
            statement.setInt(2, gevondenLabelNummer);
            statement.setInt(3, gevondenVluchtNummer);
            
            statement.setString(4, FXGevondenBestemming.getText());
            statement.setString(5, FXGevondenType.getText());
            statement.setString(6, FXGevondenMerk.getText());
            statement.setString(7, FXGevondenKleur.getText());
            statement.setString(8, FXGevondenBijzondereKenmerken.getText());
            statement.setInt(9, id);
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
