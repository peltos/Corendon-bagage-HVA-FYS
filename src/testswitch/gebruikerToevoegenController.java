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
        String query = "INSERT INTO testDatabase.Gebruikers (Voornaam) VALUES (?);";
        PreparedStatement statement = database.prepareStatement(query);
        try {
            statement.setString(1, FXVoornaam.getText());
            statement.executeUpdate();
        } catch (Exception e) {
             // log info somewhere at least until it's properly tested/
             // you implement a better way of handling the error
             e.printStackTrace(System.err);
        }
    }
    
    
    
    
    @FXML
    private void gebruikerToevoegenCancel(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
    }
}
