package testswitch;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Daan Dirker
 */
public class GebruikerController implements Initializable {

    //@FXML public Button refreshButton;
    @FXML public TableView<Gevonden> gebruikerTableView;
    @FXML public TableColumn<Gevonden, Integer> gebruikerIDKolom;
    @FXML public TableColumn<Gevonden, String> naamKolom;
    @FXML public TableColumn<Gevonden, String> usernameKolom;
    @FXML public TableColumn<Gevonden, Integer> telefoonnummerKolom;
    @FXML public TableColumn<Gevonden, String> emailKolom;
    @FXML public TableColumn<Gevonden, Integer> positieKolom;
    
    private ObservableList<Gevonden> data = FXCollections.observableArrayList();

    @FXML
    private void writeTableData() {
        Database database = new Database(
            "testDatabase",
            "ronpelt.synology.me:3306",
            "root",
            "kGjMtEO06BPiu2u4"
        );

        try {
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Gebruikers;");

            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                Gevonden gebruiker = new Gevonden();
                gebruiker.setGebruikerID(result.getInt("ID"));
                gebruiker.setVoornaam(result.getString("Voornaam"));
                gebruiker.setTussenvoegsel(result.getString("Tussenvoegsel"));
                gebruiker.setAchternaam(result.getString("Achternaam"));
                gebruiker.samenvoegenNaam(gebruiker.getVoornaam(), 
                        gebruiker.getTussenvoegsel(), gebruiker.getAchternaam());
                gebruiker.setUsername(result.getString("Username"));               
                gebruiker.setTelefoonnummer(result.getInt("Telefoonnummer"));
                gebruiker.setEmail(result.getString("Email"));
                gebruiker.setPositie(result.getInt("Positie"));
                
                data.add(gebruiker);
            }

        } catch (SQLException ex) {

        }

    }
    
    /*
    @FXML
    private void refreshButtonAction(ActionEvent event) {
        System.out.println("Refresh button pressed.");
        data.removeAll(data);
        writeTableData();
    }
    */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kollomen worden gelinkt aan Atributen van de Person class
        gebruikerIDKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("gebruikerID"));
        naamKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, String>("naam"));
        usernameKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, String>("username"));
        telefoonnummerKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("telefoonnummer"));
        emailKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, String>("email"));
        positieKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("positie"));
                        
        gebruikerTableView.setItems(data);
        writeTableData();
    }

}
