package testswitch;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Alexander
 */
public class StartController implements Initializable {

    @FXML
    Button editGevonden;
    @FXML
    Button FXGevondenDelete;
    @FXML
    Button gevondenToevoegenButton;

    @FXML
    Button editVermist;
    @FXML
    Button FXVermistDelete;
    @FXML
    Button VermistToevoegenButton;

    @FXML
    public CheckBox vermisteCheckBox;
    @FXML
    public CheckBox gevondenCheckBox;
    @FXML
    public Button buttonOvereenkomst;

    //Gevonden bagage
    @FXML
    public TableView<Bagage> gevondenTabel;
    @FXML
    public TableColumn<Bagage, Integer> gevondenIdKolom;
    @FXML
    public TableColumn<Bagage, String> gevondenDatumKolom;
    @FXML
    public TableColumn<Bagage, Integer> gevondenLabelNummerKolom;
    @FXML
    public TableColumn<Bagage, Integer> gevondenVluchtNrKolom;
    @FXML
    public TableColumn<Bagage, String> gevondenBagageTypeKolom;

    //Vermiste bagage
    @FXML
    public TableView<Bagage> vermisteTabel;
    @FXML
    public TableColumn<Bagage, Integer> vermisteIdKolom;
    @FXML
    public TableColumn<Bagage, String> vermisteDatumKolom;
    @FXML
    public TableColumn<Bagage, Integer> vermisteLabelNummerKolom;
    @FXML
    public TableColumn<Bagage, Integer> vermisteVluchtNrKolom;
    @FXML
    public TableColumn<Bagage, String> vermisteBagageTypeKolom;

    private ObservableList<Bagage> gevondenData = FXCollections.observableArrayList();
    private ObservableList<Bagage> vermisteData = FXCollections.observableArrayList();

    private static int selectedId;

    public static int getSelectedId() {
        return selectedId;
    }

    @FXML
    private void writeTableData() {
        Database database = new Database(
                "testDatabase",
                "ronpelt.synology.me:3306",
                "root",
                "kGjMtEO06BPiu2u4"
        );

        try {
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Vermist WHERE Visibility = 0;");
            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("idVermist"));
                bagage.setTijd(result.getString("Tijd"));
                bagage.setDatum(result.getString("Datum"));
                bagage.setLuchthaven(result.getString("Luchthaven"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                bagage.setBestemming(result.getString("Bestemming"));
                bagage.setBagageType(result.getString("BagageType"));
                bagage.setMerk(result.getString("Merk"));
                bagage.setKleur(result.getString("Kleur"));
                bagage.setBijzondereKenmerken(result.getString("BijzonderKenmerken"));
                bagage.setNaam(result.getString("Naam"));
                bagage.setAdres(result.getString("Adres"));
                bagage.setWoonplaats(result.getString("Woonplaats"));
                bagage.setPostcode(result.getString("Postcode"));
                bagage.setLand(result.getString("Land"));
                bagage.setTelefoonnummer(result.getInt("Telefoon"));
                bagage.setEmail(result.getString("Email"));

                vermisteData.add(bagage);
            }

            result = database.executeQuery("SELECT * FROM testDatabase.Gevonden WHERE Visibility = 0;");
            while (result.next()) {
                Bagage bagage = new Bagage();

                bagage.setId(result.getInt("idGevonden"));
                bagage.setTijd(result.getString("Tijd"));
                bagage.setDatum(result.getString("Datum"));
                bagage.setLuchthaven(result.getString("Luchthaven"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                bagage.setBestemming(result.getString("Bestemming"));
                bagage.setBagageType(result.getString("BagageType"));
                bagage.setMerk(result.getString("Merk"));
                bagage.setKleur(result.getString("Kleur"));
                bagage.setBijzondereKenmerken(result.getString("BijzonderKenmerken"));

                gevondenData.add(bagage);
            }

        } catch (SQLException ex) {

        }

    }

    @FXML
    public Label gevondenDatum, gevondenTijd, gevondenLuchthaven, gevondenID,
            gevondenType, gevondenMerk, gevondenKleur, gevondenBK, gevondenLabelNr,
            gevondenVluchtNr, gevondenBestemming;

    @FXML
    private void gevondenSelected() {

        Bagage bagage = gevondenTabel.getSelectionModel().getSelectedItem();
        selectedId = bagage.getId();

        if (bagage.getId() != null) {
            editGevonden.setDisable(false);
            FXGevondenDelete.setDisable(false);
            gevondenToevoegenButton.setDisable(false);

        }

        boolean bSelected = gevondenCheckBox.isSelected() || vermisteCheckBox.isSelected();

        if (bSelected == true) {
            gevondenCheckBox.setSelected(false);
        }

        gevondenDatum.setText(bagage.getDatum());
        gevondenTijd.setText(bagage.getTijd());
        gevondenLuchthaven.setText(bagage.getLuchthaven());
        gevondenID.setText(String.valueOf(bagage.getId()));
        gevondenType.setText(bagage.getBagageType());
        gevondenMerk.setText(bagage.getMerk());
        gevondenKleur.setText(bagage.getKleur());
        gevondenBK.setText(bagage.getBijzondereKenmerken());
        gevondenLabelNr.setText(String.valueOf(bagage.getLabelNummer()));
        gevondenVluchtNr.setText(String.valueOf(bagage.getVluchtNr()));
        gevondenBestemming.setText(bagage.getBestemming());
    }

    @FXML
    public Label vermisteDatum, vermisteTijd, vermisteLuchthaven, vermisteID,
            vermisteNaam, vermisteAdres, vermisteWoonplaats, vermistePostcode,
            vermisteLand, vermisteTelefoon, vermisteEmail, vermisteType, vermisteMerk,
            vermisteKleur, vermisteBK, vermisteLabelNr, vermisteVluchtNr, vermisteBestemming;

    @FXML
    private void vermisteSelected() {

        Bagage bagage = vermisteTabel.getSelectionModel().getSelectedItem();
//        
//        if (bagage.getId() != null){
//            editVermist.setDisable(false);
//            FXVermistDelete.setDisable(false);
//            VermistToevoegenButton.setDisable(false);
//        }

        boolean bSelected = gevondenCheckBox.isSelected() || vermisteCheckBox.isSelected();

        if (bSelected == true) {
            vermisteCheckBox.setSelected(false);

        }
        vermisteDatum.setText(bagage.getDatum());
        vermisteTijd.setText(bagage.getTijd());
        vermisteLuchthaven.setText(bagage.getLuchthaven());
        vermisteID.setText(String.valueOf(bagage.getId()));
        vermisteNaam.setText(bagage.getNaam());
        vermisteAdres.setText(bagage.getAdres());
        vermisteWoonplaats.setText(bagage.getWoonplaats());
        vermistePostcode.setText(bagage.getPostcode());
        vermisteLand.setText(bagage.getLand());
        vermisteTelefoon.setText(String.valueOf(bagage.getTelefoonnummer()));
        vermisteEmail.setText(bagage.getEmail());
        vermisteType.setText(bagage.getBagageType());
        vermisteMerk.setText(bagage.getMerk());
        vermisteKleur.setText(bagage.getKleur());
        vermisteBK.setText(bagage.getBijzondereKenmerken());
        vermisteLabelNr.setText(String.valueOf(bagage.getLabelNummer()));
        vermisteVluchtNr.setText(String.valueOf(bagage.getVluchtNr()));
        vermisteBestemming.setText(bagage.getBestemming());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kollomen worden gelinkt aan Atributen van de Person class
        gevondenIdKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        gevondenDatumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        gevondenLabelNummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        gevondenVluchtNrKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("vluchtNr"));
        gevondenBagageTypeKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("bagageType"));

        //Vermist
        vermisteIdKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        vermisteDatumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        vermisteLabelNummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        vermisteVluchtNrKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("vluchtNr"));
        vermisteBagageTypeKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("bagageType"));

        gevondenTabel.setItems(gevondenData);
        vermisteTabel.setItems(vermisteData);
        writeTableData();
    }
    @FXML
    public TextField searchField;

    @FXML
    private void search(ActionEvent event) throws IOException {

        Bagage bagage = vermisteTabel.getSelectionModel().getSelectedItem();
        gevondenDatum.setText(" ");
        gevondenTijd.setText(" ");
        gevondenLuchthaven.setText(" ");
        gevondenID.setText(" ");
        gevondenType.setText(" ");
        gevondenMerk.setText(" ");
        gevondenKleur.setText(" ");
        gevondenBK.setText(" ");
        gevondenLabelNr.setText(" ");
        gevondenVluchtNr.setText(" ");
        gevondenBestemming.setText(" ");

        bagage = vermisteTabel.getSelectionModel().getSelectedItem();
        vermisteDatum.setText(" ");
        vermisteTijd.setText(" ");
        vermisteLuchthaven.setText(" ");
        vermisteID.setText(" ");
        vermisteNaam.setText(" ");
        vermisteAdres.setText(" ");
        vermisteWoonplaats.setText(" ");
        vermistePostcode.setText(" ");
        vermisteLand.setText(" ");
        vermisteTelefoon.setText(" ");
        vermisteEmail.setText(" ");
        vermisteType.setText(" ");
        vermisteMerk.setText(" ");
        vermisteKleur.setText(" ");
        vermisteBK.setText(" ");
        vermisteLabelNr.setText(" ");
        vermisteVluchtNr.setText(" ");
        vermisteBestemming.setText(" ");

        Database database = new Database(
                "testDatabase",
                "ronpelt.synology.me:3306",
                "root",
                "kGjMtEO06BPiu2u4"
        );
        gevondenData.removeAll(gevondenData);
        vermisteData.removeAll(vermisteData);

        if (searchField.getText().equals("")) {
            writeTableData();
        } else {
            try {
                ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Vermist "
                        + "WHERE idVermist LIKE '" + searchField.getText() + "' "
                        + "OR Tijd LIKE '" + searchField.getText() + "' "
                        + "OR Datum LIKE '" + searchField.getText() + "' "
                        + "OR Luchthaven LIKE '" + searchField.getText() + "' "
                        + "OR Labelnummer LIKE '" + searchField.getText() + "' "
                        + "OR Vluchtnummer LIKE '" + searchField.getText() + "' "
                        + "OR Bestemming LIKE '" + searchField.getText() + "' "
                        + "OR BagageType LIKE '" + searchField.getText() + "' "
                        + "OR Merk LIKE '" + searchField.getText() + "' "
                        + "OR Kleur LIKE '" + searchField.getText() + "' "
                        + "OR BijzonderKenmerken LIKE '" + searchField.getText() + "' "
                        + "OR Naam LIKE '" + searchField.getText() + "' "
                        + "OR Adres LIKE '" + searchField.getText() + "' "
                        + "OR Woonplaats LIKE '" + searchField.getText() + "' "
                        + "OR Postcode LIKE '" + searchField.getText() + "' "
                        + "OR Land LIKE '" + searchField.getText() + "' "
                        + "OR Telefoon LIKE '" + searchField.getText() + "' "
                        + "OR Email LIKE '" + searchField.getText() + "' "
                        + "AND Visibility = 0;");

                //Gaat net zo lang door, tot er geen records meer zijn
                while (result.next()) {
                    bagage = new Bagage();
                    bagage.setId(result.getInt("idVermist"));
                    bagage.setTijd(result.getString("Tijd"));
                    bagage.setDatum(result.getString("Datum"));
                    bagage.setLuchthaven(result.getString("Luchthaven"));
                    bagage.setLabelNummer(result.getInt("Labelnummer"));
                    bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                    bagage.setBestemming(result.getString("Bestemming"));
                    bagage.setBagageType(result.getString("BagageType"));
                    bagage.setMerk(result.getString("Merk"));
                    bagage.setKleur(result.getString("Kleur"));
                    bagage.setBijzondereKenmerken(result.getString("BijzonderKenmerken"));
                    bagage.setNaam(result.getString("Naam"));
                    bagage.setAdres(result.getString("Adres"));
                    bagage.setWoonplaats(result.getString("Woonplaats"));
                    bagage.setPostcode(result.getString("Postcode"));
                    bagage.setLand(result.getString("Land"));
                    bagage.setTelefoonnummer(result.getInt("Telefoon"));
                    bagage.setEmail(result.getString("Email"));

                    vermisteData.add(bagage);
                }

                result = database.executeQuery("SELECT * FROM testDatabase.Gevonden "
                        + "WHERE idGevonden LIKE '" + searchField.getText() + "' "
                        + "OR Tijd LIKE '" + searchField.getText() + "' "
                        + "OR Datum LIKE '" + searchField.getText() + "' "
                        + "OR Luchthaven LIKE '" + searchField.getText() + "' "
                        + "OR Labelnummer LIKE '" + searchField.getText() + "' "
                        + "OR Vluchtnummer LIKE '" + searchField.getText() + "' "
                        + "OR Bestemming LIKE '" + searchField.getText() + "' "
                        + "OR BagageType LIKE '" + searchField.getText() + "' "
                        + "OR Merk LIKE '" + searchField.getText() + "' "
                        + "OR Kleur LIKE '" + searchField.getText() + "' "
                        + "OR BijzonderKenmerken LIKE '" + searchField.getText() + "' "
                        + "AND Visibility = 0;");

                while (result.next()) {
                    bagage = new Bagage();

                    bagage.setId(result.getInt("idGevonden"));
                    bagage.setTijd(result.getString("Tijd"));
                    bagage.setDatum(result.getString("Datum"));
                    bagage.setLuchthaven(result.getString("Luchthaven"));
                    bagage.setLabelNummer(result.getInt("Labelnummer"));
                    bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                    bagage.setBestemming(result.getString("Bestemming"));
                    bagage.setBagageType(result.getString("BagageType"));
                    bagage.setMerk(result.getString("Merk"));
                    bagage.setKleur(result.getString("Kleur"));
                    bagage.setBijzondereKenmerken(result.getString("BijzonderKenmerken"));

                    gevondenData.add(bagage);
                }

            } catch (SQLException ex) {

            }
        }

    }

    @FXML
    private void OvereenkomstDB() throws SQLException {
        boolean gVCheckBox = gevondenCheckBox.isSelected(), vVCheckBox = vermisteCheckBox.isSelected();

        Bagage vermisteBag = vermisteTabel.getSelectionModel().getSelectedItem();
        Bagage gevondenBag = gevondenTabel.getSelectionModel().getSelectedItem();

        String DB_NAME = "testDatabase", DB_SERVER = "ronpelt.synology.me:3306";
        String DB_ACCOUNT = "root", DB_PASSWORD = "kGjMtEO06BPiu2u4";

        Database database = new Database(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);

        String query = "INSERT INTO testDatabase.Overeenkomst (GevondenID, VermistID, Datum, Gesloten)"
                + "VALUES (?, ?, ?, ?);";

        PreparedStatement statement = database.prepareStatement(query);

        String queryGevonden = "UPDATE `testDatabase`.`Gevonden` SET `Visibility`='1' WHERE `idGevonden`=" + gevondenBag.getId() + ";";
        String queryVermist = "UPDATE `testDatabase`.`Vermist` SET `Visibility`='1' WHERE `idVermist`=" + vermisteBag.getId() + ";";

        PreparedStatement statementGevonden = database.prepareStatement(queryGevonden);
        PreparedStatement statementVermist = database.prepareStatement(queryVermist);

        try {
            if (!gVCheckBox || !vVCheckBox) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Niet bevestigd");
                alert.setHeaderText("Niet bevestigd");
                alert.setContentText("Klik op de checkbox \"bevestigen\" om te bevestigen dat je het echt wil doen");
                alert.showAndWait();
            } else {
                statement.setString(1, gevondenBag.getId().toString());

                String selected_log = String.format("[TIMESTAMP]: %s\ngevondenBag(): %d\nvermisteBag(): %d", "xxxx-xx-xx",
                        gevondenBag.getId(), vermisteBag.getId());

                System.out.println(selected_log);

                statement.setString(2, vermisteBag.getId().toString());
                statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                statement.setString(4, "0");
                statement.executeUpdate();

                statementGevonden.executeUpdate();
                statementVermist.executeUpdate();

                vermisteCheckBox.setSelected(false);
                gevondenCheckBox.setSelected(false);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        MainNavigator.loadVista(MainNavigator.START);

    }

    @FXML
    private void DeleteGevondenBagage() {
        String DB_NAME = "testDatabase", DB_SERVER = "ronpelt.synology.me:3306";
        String DB_ACCOUNT = "root", DB_PASSWORD = "kGjMtEO06BPiu2u4";

        Database database = new Database(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);

        gevondenTabel.getItems().removeAll(gevondenTabel.getSelectionModel().getSelectedItems());

        String query = "DELETE INTO testDatabase.Overeenkomst (GevondenID, VermistID, Datum, Gesloten)"
                + "VALUES (?, ?, ?, ?);";

        //DELETE FROM MyGuests WHERE id=3
        Bagage gevondenBag = gevondenTabel.getSelectionModel().getSelectedItem();

        try {
            PreparedStatement statement = database.prepareStatement(query);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    @FXML
    private void gevondenToevoegen(ActionEvent event) {
        MainNavigator.loadVista(MainNavigator.GEVONDEN);

    }

    @FXML
    private void vermistToevoegen(ActionEvent event) {
        MainNavigator.loadVista(MainNavigator.VERMIST);
    }

    @FXML
    private void editGevonden(ActionEvent event) {
        MainNavigator.loadVista(MainNavigator.EDIT_GEVONDEN);
    }

}
