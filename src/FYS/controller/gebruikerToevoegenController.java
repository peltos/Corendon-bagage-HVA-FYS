package FYS.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import FYS.Database;
import FYS.Database;
import FYS.MD5;
import FYS.Main;
import FYS.MainNavigator;

/**
 *
 * @author Alexander/Maarten
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
    private int positionCheck;
    @FXML
    private Button gebruikerButton;
    @FXML
    Label VoornaamError, AchternaamError, GebruikersnaamError, WachtwoordError, EmailError, TelefoonError;

    Database database = Main.getDatabase();

    public void handle(ActionEvent event) throws SQLException {
        String query = "INSERT INTO testDatabase.Gebruikers"
                + " (Voornaam, Tussenvoegsel, Achternaam,"
                + " Username, Password, Telefoonnummer,  Email, Positie)"
                + " VALUES (?,?,?,?,?,?,?,?);";
        PreparedStatement statement = database.prepareStatement(query);

        try {
//            if (FXVoornaam.getText().equals("") || FXAchternaam.getText().equals("")
//                    || FXGebruikersnaam.getText().equals("")
//                    || FXWachtwoord.getText().equals("")) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Statements missing");
//                alert.setHeaderText("There seems to be a problem");
//                alert.setContentText("Fields are missing");
//                alert.showAndWait();
//                return;
//            }

            VoornaamError.setText("");
            AchternaamError.setText("");
            GebruikersnaamError.setText("");
            WachtwoordError.setText("");
            EmailError.setText("");
            TelefoonError.setText("");

            if (FXVoornaam.getText().equals("")) {
                VoornaamError.setText(" Please enter your first name.");
                return;
            } else {
                statement.setString(1, FXVoornaam.getText());
            }

            statement.setString(2, FXTussenvoegsel.getText());

            if (FXAchternaam.getText().equals("")) {
                AchternaamError.setText(" Please enter your surname.");
                return;
            } else {
                statement.setString(3, FXAchternaam.getText());
            }

            if (FXGebruikersnaam.getText().equals("")) {
                GebruikersnaamError.setText(" Please enter your username.");
                return;
            } else {
                statement.setString(4, FXGebruikersnaam.getText());
            }
            if (FXWachtwoord.getText().equals("")) {
                WachtwoordError.setText(" Please enter a password");
                return;
            } else {
                String[] MD5 = {FXWachtwoord.getText()};
                MD5 encryptie = new MD5();
                statement.setString(5, encryptie.MD5(MD5));
            }

            try {
                int Telefoonnummer = Integer.parseInt(FXTelefoonnummer.getText());
                statement.setInt(6, Telefoonnummer);
                System.out.println("Phone number is an integer");

            } catch (NumberFormatException e) {
                System.out.println("Phone number is not an integer");
                TelefoonError.setText(" Please enter a valid phone number.");
                return;
                //Not an integer
            }

            
             if (FXEmail.getText().equals("")) {
                EmailError.setText(" Please enter your email.");
                return;
            } else {
                statement.setString(7, FXEmail.getText());
            }

            if (ManagerPosition.isSelected()) {
                statement.setInt(8, 1);
            } else {
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
