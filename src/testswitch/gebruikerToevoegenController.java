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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import testswitch.Database;

/**
 *
 * @author Alexander/Maarten
 */
public class gebruikerToevoegenController {

    //TextFields
    @FXML private TextField FXVoornaam, FXTussenvoegsel, FXAchternaam, FXGebruikersnaam;
    @FXML private TextField FXWachtwoord, FXEmail, FXTelefoonnummer;

    //Boolean checkbox positie
    @FXML private CheckBox ManagerPosition;
    @FXML private int positionCheck;
    @FXML private Button gebruikerButton;

    Database database = Main.getDatabase();

    public void handle(ActionEvent event) throws SQLException {
        String query = "INSERT INTO testDatabase.Gebruikers"
                + " (Voornaam, Tussenvoegsel, Achternaam,"
                + " Username, Password, Telefoonnummer,  Email, Positie)"
                + " VALUES (?,?,?,?,?,?,?,?);";
        PreparedStatement statement = database.prepareStatement(query);
        try {
            if (FXVoornaam.getText().equals("") || FXTussenvoegsel.getText().equals("")
                    || FXAchternaam.getText().equals("") || FXGebruikersnaam.getText().equals("")
                    || FXWachtwoord.getText().equals("")) 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Niet alles ingevult");
                    alert.setHeaderText("Error");
                    alert.setContentText("Voer alles in");
                    alert.showAndWait();
                return;
            }
            
            statement.setString(1, FXVoornaam.getText());
            statement.setString(2, FXTussenvoegsel.getText());
            statement.setString(3, FXAchternaam.getText());
            statement.setString(4, FXGebruikersnaam.getText());
            statement.setString(5, FXWachtwoord.getText());
            statement.setString(6, FXTelefoonnummer.getText());
            statement.setString(7, FXEmail.getText());
            
            if (ManagerPosition.isSelected()) {
                statement.setInt(8, 1);
            }else{
                statement.setInt(8, 0);
            }

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
    }

    @FXML
    private void gebruikerToevoegenCancel(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
    }
}
