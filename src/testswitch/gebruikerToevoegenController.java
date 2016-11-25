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
        String query = "INSERT INTO testDatabase.Gebruikers"
                + " (Voornaam, Tussenvoegsel, Achternaam,"
                + " Username, Password, Telefoonnummer, Email)"
                + " VALUES (?,?,?,?,?,?,?);";
        PreparedStatement statement = database.prepareStatement(query);
        try {
            if (FXVoornaam.getText().equals("") || FXTussenvoegsel.getText().equals("")
                    || FXAchternaam.getText().equals("") || FXGebruikersnaam.getText().equals("")
                    || FXWachtwoord.getText().equals("")) 
            {
                System.out.println("error");
                return;
            }
            statement.setString(1, FXVoornaam.getText());
            statement.setString(2, FXTussenvoegsel.getText());
            statement.setString(3, FXAchternaam.getText());
            statement.setString(4, FXGebruikersnaam.getText());
            statement.setString(5, FXWachtwoord.getText());
            statement.setString(6, FXTelefoonnummer.getText());
            statement.setString(7, FXEmail.getText());

            statement.executeUpdate();

        } catch (Exception e) {
            // log info somewhere at least until it's properly tested/
            // you implement a better way of handling the error
            e.printStackTrace(System.err);
        }
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
    }

    @FXML
    private void gebruikerToevoegenCancel(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
    }
}
