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
public class EditGevondenController implements Initializable {
    
    @FXML TextField FXGevondenLuchthaven, FXGevondenLabelNummer, FXGevondenVluchtNummer,
            FXGevondenBestemming, FXGevondenType, FXGevondenMerk, FXGevondenKleur,
            FXGevondenBijzondereKenmerken;
    @FXML Button gevondenButton;
    
    int id = StartController.getSelectedId();
    
    
    @FXML
    private void readData() {
        Database database = new Database(
                "testDatabase",
                "ronpelt.synology.me:3306",
                "root",
                "kGjMtEO06BPiu2u4"
        );

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
        Database database = new Database(
                "testDatabase",
                "ronpelt.synology.me:3306",
                "root",
                "kGjMtEO06BPiu2u4"
        );
        
        String query = "UPDATE testDatabase.Gevonden SET"
                + "Luchthaven=? "
                + "WHERE idGevonden=" + id;
        
        PreparedStatement statement = database.prepareStatement(query);

        try {
            statement.setString(1, "'Hi there'");
            statement.executeUpdate();
           
        } catch(SQLException ex) {
            
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
