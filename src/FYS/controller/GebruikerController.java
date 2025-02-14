package FYS.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import FYS.Database;
import FYS.Gebruiker;
import FYS.Main;
import FYS.MainNavigator;

/**
 * @author A/M
 */
public class GebruikerController implements Initializable {

    @FXML
    public TableView<Gebruiker> gebruikerTableView;
    @FXML
    public TableColumn<Gebruiker, Integer> gebruikerIDKolom;
    @FXML
    public TableColumn<Gebruiker, String> naamKolom;
    @FXML
    public TableColumn<Gebruiker, String> usernameKolom;
    @FXML
    public TableColumn<Gebruiker, Integer> telefoonnummerKolom;
    @FXML
    public TableColumn<Gebruiker, String> emailKolom;
    @FXML
    public TableColumn<Gebruiker, Integer> positieKolom;
    @FXML
    public Button gebruikerToevoegenButton, gebruikerVerwijderen, gebruikerEdit;

    private static int selectedIdGebruiker;

    public static int getSelectedIdGebruiker() {
        return selectedIdGebruiker;
    }

    private ObservableList<Gebruiker> data = FXCollections.observableArrayList();

    Database database = Main.getDatabase();

    @FXML
    private void writeTableData() {

        try {
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Gebruikers");

            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                Gebruiker gebruiker = new Gebruiker();
                gebruiker.setGebruikerID(result.getInt("ID"));
                gebruiker.setVoornaam(result.getString("Voornaam"));
                gebruiker.setTussenvoegsel(result.getString("Tussenvoegsel"));
                gebruiker.setAchternaam(result.getString("Achternaam"));
                gebruiker.samenvoegenNaam(gebruiker.getVoornaam(),
                        gebruiker.getTussenvoegsel(), gebruiker.getAchternaam());
                gebruiker.setUsername(result.getString("Username"));
                gebruiker.setTelefoonnummer(result.getInt("Telefoonnummer"));
                gebruiker.setEmail(result.getString("Email"));
                if (result.getInt("Positie") == 1) {
                    gebruiker.setPositie("Manager");
                } else if (result.getInt("Positie") == 0) {
                    gebruiker.setPositie("Medewerker");
                }

                data.add(gebruiker);
            }

        } catch (SQLException ex) {

        }

    }

    @FXML
    private void gebruikerToevoegen(ActionEvent event) throws IOException {
        MainNavigator.loadVista(MainNavigator.GEBRUIKER_TOEVOEGEN);
    }

    @FXML
    private void editGebruiker() {
        MainNavigator.loadVista(MainNavigator.EDIT_GEBRUIKER);
    }

    @FXML
    private void getSelected() {
        Gebruiker gebruiker = gebruikerTableView.getSelectionModel().getSelectedItem();
        selectedIdGebruiker = gebruiker.getGebruikerID();

        gebruikerVerwijderen.setDisable(false);
        gebruikerEdit.setDisable(false);
    }

    @FXML
    public TextField searchField;

    @FXML
    private void search(ActionEvent event) throws IOException {
        data.removeAll(data);
        if (searchField.getText().equals("")) {
            writeTableData();
        } else {
            try {
                ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Gebruikers "
                        + "WHERE ID LIKE '" + searchField.getText() + "' "
                        + "OR Voornaam LIKE '" + searchField.getText() + "' "
                        + "OR Tussenvoegsel LIKE '" + searchField.getText() + "' "
                        + "OR Achternaam LIKE '" + searchField.getText() + "' "
                        + "OR Username LIKE '" + searchField.getText() + "' "
                        + "OR Positie LIKE '" + searchField.getText() + "' "
                        + "OR Telefoonnummer LIKE '" + searchField.getText() + "' "
                        + "OR Email LIKE '" + searchField.getText() + "' ;");

                //Gaat net zo lang door, tot er geen records meer zijn
                while (result.next()) {
                    Gebruiker gebruiker = new Gebruiker();
                    gebruiker.setGebruikerID(result.getInt("ID"));
                    gebruiker.setVoornaam(result.getString("Voornaam"));
                    gebruiker.setTussenvoegsel(result.getString("Tussenvoegsel"));
                    gebruiker.setAchternaam(result.getString("Achternaam"));
                    gebruiker.samenvoegenNaam(gebruiker.getVoornaam(),
                            gebruiker.getTussenvoegsel(), gebruiker.getAchternaam());
                    gebruiker.setUsername(result.getString("Username"));
                    gebruiker.setTelefoonnummer(result.getInt("Telefoonnummer"));
                    gebruiker.setEmail(result.getString("Email"));
                    if (result.getInt("Positie") == 1) {
                        gebruiker.setPositie("Manager");
                    } else if (result.getInt("Positie") == 0) {
                        gebruiker.setPositie("Medewerker");
                    }

                    data.add(gebruiker);

                }

            } catch (SQLException ex) {

            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kollomen worden gelinkt aan Atributen van de Person class
        gebruikerIDKolom.setCellValueFactory(
                new PropertyValueFactory<Gebruiker, Integer>("gebruikerID"));
        naamKolom.setCellValueFactory(
                new PropertyValueFactory<Gebruiker, String>("naam"));
        usernameKolom.setCellValueFactory(
                new PropertyValueFactory<Gebruiker, String>("username"));
        telefoonnummerKolom.setCellValueFactory(
                new PropertyValueFactory<Gebruiker, Integer>("telefoonnummer"));
        emailKolom.setCellValueFactory(
                new PropertyValueFactory<Gebruiker, String>("email"));
        positieKolom.setCellValueFactory(
                new PropertyValueFactory<Gebruiker, Integer>("positie"));

        gebruikerTableView.setItems(data);
        writeTableData();
    }

    @FXML
    private void DeleteUser() {
        Gebruiker userClass = gebruikerTableView.getSelectionModel().getSelectedItem();
        String query = String.format("DELETE FROM testDatabase.Gebruikers WHERE ID = %d", userClass.getGebruikerID()),
                deletionInfo = String.format("Are you sure you want to permanently remove this item?\n\n"
                        + "Delete user: %s %s, %d", userClass.getVoornaam(), userClass.getAchternaam(), userClass.getGebruikerID());

        System.out.print("[QUERY]: " + query + " | " + userClass.getUsername() + "\n");

        Alert alertMessageBox = new Alert(Alert.AlertType.CONFIRMATION);

        alertMessageBox.setTitle("Confirm Deletion");
        alertMessageBox.setContentText(deletionInfo);

        ButtonType OKButton = new ButtonType("OK.", ButtonBar.ButtonData.OK_DONE);
        ButtonType CancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alertMessageBox.getButtonTypes().setAll(CancelButton, OKButton);

        Optional<ButtonType> result = alertMessageBox.showAndWait();

        try {
            if (result.get() == OKButton) {
                PreparedStatement statement = database.prepareStatement(query);
                gebruikerTableView.getItems().removeAll(gebruikerTableView.getSelectionModel().getSelectedItems());
                statement.executeUpdate();
            } else if (result.get() == CancelButton) {
                System.out.println("[USERINPUT]: Canceled");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}
