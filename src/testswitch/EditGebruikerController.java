package testswitch;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Daan Dirker
 */

public class EditGebruikerController implements Initializable {
    
    //TextFields
    @FXML private TextField FXVoornaam, FXTussenvoegsel, FXAchternaam, FXGebruikersnaam;
    @FXML private TextField FXWachtwoord, FXEmail, FXTelefoonnummer;
    @FXML private CheckBox ManagerPosition;
    
    int id = GebruikerController.getSelectedIdGebruiker();
    Database database = Main.getDatabase();
    
    @FXML
    private void readData() {
        try {
            System.out.println(id);
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Gebruikers WHERE ID=" + id);
            
            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                FXVoornaam.setText(result.getString("Voornaam"));
                FXTussenvoegsel.setText(result.getString("Tussenvoegsel"));
                FXAchternaam.setText(result.getString("Achternaam"));
                FXGebruikersnaam.setText(result.getString("Username"));
                FXWachtwoord.setText(result.getString("Password"));
                ManagerPosition.setSelected(result.getBoolean("Positie"));
                FXEmail.setText(result.getString("Email"));
                FXTelefoonnummer.setText(result.getString("Telefoonnummer"));
            }
            
        } catch(SQLException ex) {
            
        }
    }
    
    @FXML
    private void writeToDB() throws SQLException {
        
        String query = "UPDATE testDatabase.Gebruikers SET "
                + "Voornaam=?, "
                + "Tussenvoegsel=?, "
                + "Achternaam=?, "
                + "Username=?, "
                + "Password=?, "
                + "Email=?, "
                + "Telefoonnummer=?, "
                + "Positie=? "
                + "WHERE ID=?;";
        
        PreparedStatement statement = database.prepareStatement(query);

        try {
            statement.setString(1, FXVoornaam.getText());
            statement.setString(2, FXTussenvoegsel.getText());
            statement.setString(3, FXAchternaam.getText());
            statement.setString(4, FXGebruikersnaam.getText());
            statement.setString(5, FXWachtwoord.getText());
            statement.setString(6, FXEmail.getText());
            statement.setInt(7, Integer.parseInt(FXTelefoonnummer.getText()));
            
            if (ManagerPosition.isSelected()) {
                statement.setInt(8, 1);
            }else{
                statement.setInt(8, 0);
            }
            
            statement.setInt(9, id);
            statement.executeUpdate();
           
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
    }
    
    @FXML
    private void cancel(){
        MainNavigator.loadVista(MainNavigator.GEBRUIKER);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readData();
    }
}
