package testswitch;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Heaven
 */
public class gebruikerToevoegenController {

    @FXML
    private TextField FXVoornaam, FXAchternaam, FXGebruikersnaam;
    @FXML
    private TextField FXWachtwoord, FXEmail, FXTelefoonnummer;
    @FXML
    private CheckBox ManagerPosition;
    @FXML
    private Button gebruikerButton;

    Main Main = new Main();
    Database database = new Database(
            "database",
            "ronpelt.synology.me:3306",
            "root",
            "kGjMtEO06BPiu2u4"
    );

    @FXML
    private void SaveToDB(ActionEvent event) throws IOException, SQLException {
        // write all info to DB.

        //FXAchternaam.setText(FXVoornaam.getText());
        //FXAchternaam.setText("asd123123123");
        System.out.println(FXVoornaam.getText());

        Statement stmt = null;
        String query = "INSERT INTO Gebruikers VALUES (" + FXVoornaam.getText() + "," + FXAchternaam.getText() + ")";
        try {
//            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            //print exception
        }

    }

//    ResultSet result = database.executeQuery("INSERT INTO testDatabase.Gebruikers(voornaam) VALUES" + FXVoornaam.getText());
}


