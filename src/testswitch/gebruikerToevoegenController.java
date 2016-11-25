package testswitch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import testswitch.Database;

/**
 *
 * @author Maarten
 */
public class gebruikerToevoegenController {

    //TextFields
    @FXML
    private TextField FXVoornaam, FXTussenvoegsel, FXAchternaam, FXGebruikersnaam;
    @FXML
    private TextField FXWachtwoord, FXEmail, FXTelefoonnummer;
    
    //Boolean checkbox positie
    @FXML
    private CheckBox ManagerPosition;
    @FXML
    private Button gebruikerButton;

    public final String DB_NAME = "testDatabase";
    public final String DB_SERVER = "ronpelt.synology.me:3306";
    public final String DB_ACCOUNT = "root";
    public final String DB_PASSWORD = "kGjMtEO06BPiu2u4";

    Database database = new Database(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);

    public void handle(ActionEvent event) throws SQLException {
        String sql = "INSERT INTO testDatabase.Gebruikers (Voornaam) VALUES " + FXVoornaam.getText();
        try {

//            database.executeQuery(sql);
       
        } catch (Exception e) {

        }
    }
}
