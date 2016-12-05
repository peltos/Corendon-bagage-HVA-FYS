package testswitch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    
    @FXML private Button gevondenButton;
    
    Database database = Main.getDatabase();


    public void gevondenOpslaanDB(ActionEvent event) throws SQLException {
        String query = "INSERT INTO testDatabase.Gevonden"
                + " (Tijd, Datum, Luchthaven,"
                + " Bestemming, BagageType, Merk,"
                + " Kleur, BijzonderKenmerken, Labelnummer, Vluchtnummer, Visibility)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        
        PreparedStatement statement = database.prepareStatement(query);
        

        try {
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
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
/* Nog te fixen:
Cancel button

Not NULL bepalen

DATETIME ipv DATE & TIME

Algemeen:
Start en Open zijn het zelfde
*/
