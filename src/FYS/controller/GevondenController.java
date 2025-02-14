package FYS.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import FYS.Database;
import FYS.Main;
import FYS.MainNavigator;
import javafx.scene.control.Label;

/**
 * @author Daan Dirker/Maarten Mes
 */
public class GevondenController {

    @FXML
    private TextField FXGevondenLuchthaven;
    @FXML
    private TextField FXGevondenLabelNummer, FXGevondenVluchtNummer, FXGevondenBestemming;
    @FXML
    private TextField FXGevondenType, FXGevondenMerk, FXGevondenKleur, FXGevondenBijzondereKenmerken;

    @FXML
    private Button gevondenButton;

    @FXML
    private Label GevondenLuchthavenError;

    @FXML
    private Label BagageTypeError;

    Database database = Main.getDatabase();

    public void gevondenOpslaanDB(ActionEvent event) throws SQLException {
        String query = "INSERT INTO testDatabase.Gevonden"
                + " (Tijd, Datum, Luchthaven,"
                + " Bestemming, BagageType, Merk,"
                + " Kleur, BijzonderKenmerken, Labelnummer, Vluchtnummer, Visibility)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement statement = database.prepareStatement(query);

        try {
            GevondenLuchthavenError.setText("");
            BagageTypeError.setText("");
            
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            
            if (FXGevondenLuchthaven.getText().equals("")) {
                GevondenLuchthavenError.setText(" Please enter an airport.");
                return;
            } else {
                statement.setString(3, FXGevondenLuchthaven.getText());
                
            }
            statement.setString(4, FXGevondenBestemming.getText());

            if (FXGevondenType.getText().equals("")) {
                BagageTypeError.setText(" Please enter a type.");
                return;
            } else {
                statement.setString(5, FXGevondenType.getText());

            }
            statement.setString(6, FXGevondenMerk.getText());
            statement.setString(7, FXGevondenKleur.getText());
            statement.setString(8, FXGevondenBijzondereKenmerken.getText());

            int GevondenLabelNummer;
            
            if (FXGevondenLabelNummer.getText().equals("")) {
                GevondenLabelNummer = 0;
            }else{
                GevondenLabelNummer = Integer.parseInt(FXGevondenLabelNummer.getText());
            }
            statement.setInt(9, GevondenLabelNummer);

            int VluchtNummer;
            if (FXGevondenVluchtNummer.getText().equals("")) {
                VluchtNummer = 0;
            }else{
                VluchtNummer = Integer.parseInt(FXGevondenVluchtNummer.getText());
            }
            statement.setInt(10, VluchtNummer);
            statement.setInt(11, 0);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.err);

        }
        MainNavigator.loadVista(MainNavigator.START);
    }
    
    @FXML
    private void gevondenToevoegenCancel(ActionEvent event) {
        MainNavigator.loadVista(MainNavigator.START);
    }

}

