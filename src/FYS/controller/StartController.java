package FYS.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import FYS.Bagage;
import FYS.Database;
import FYS.Main;
import FYS.MainNavigator;
import FYS.pdf.Form;
import com.itextpdf.text.DocumentException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.image.ImageView;
import nl.hva.dmci.ict.fys.CSVWriter;

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

    private static int selectedIdGevonden;
    private static int selectedIdVermist;

    public static int getSelectedIdGevonden() {
        return selectedIdGevonden;
    }

    public static int getSelectedIdVermist() {
        return selectedIdVermist;
    }

    Database database = Main.getDatabase();

    @FXML
    private void writeTableData() {

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

        if (gevondenTabel.getSelectionModel().getSelectedItem() != null) {
            Bagage bagage = gevondenTabel.getSelectionModel().getSelectedItem();
            selectedIdGevonden = bagage.getId();

            editVermist.setDisable(true);
            FXVermistDelete.setDisable(true);
            createFoundPdf.setDisable(false);

            if (selectedIdGevonden != 0 && selectedIdVermist != 0) {
                buttonOvereenkomst.setDisable(false);
            }

            if (bagage.getId() != null) {
                editGevonden.setDisable(false);
                FXGevondenDelete.setDisable(false);
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
    }

    @FXML
    public Label vermisteDatum, vermisteTijd, vermisteLuchthaven, vermisteID,
            vermisteNaam, vermisteAdres, vermisteWoonplaats, vermistePostcode,
            vermisteLand, vermisteTelefoon, vermisteEmail, vermisteType, vermisteMerk,
            vermisteKleur, vermisteBK, vermisteLabelNr, vermisteVluchtNr, vermisteBestemming;

    @FXML
    private void vermisteSelected() {

        if (vermisteTabel.getSelectionModel().getSelectedItem() != null) {
            Bagage bagage = vermisteTabel.getSelectionModel().getSelectedItem();
            selectedIdVermist = bagage.getId();

            editGevonden.setDisable(true);
            FXGevondenDelete.setDisable(true);
            createLostPdf.setDisable(false);

            if (selectedIdGevonden != 0 && selectedIdVermist != 0) {
                buttonOvereenkomst.setDisable(false);
            }

            if (bagage.getId() != null) {
                editVermist.setDisable(false);
                FXVermistDelete.setDisable(false);
            }

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

        bagage = gevondenTabel.getSelectionModel().getSelectedItem();
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

        gevondenData.removeAll(gevondenData);
        vermisteData.removeAll(vermisteData);

        if (searchField.getText().equals("")) {
            writeTableData();
        } else {
            try {
                ResultSet resultGevonden = database.executeQuery("SELECT * FROM testDatabase.Overeenkomst LEFT JOIN testDatabase.Gevonden ON Gevonden.idGevonden = Overeenkomst.GevondenID  WHERE Gesloten = 0;");
                ResultSet resultVermist = database.executeQuery("SELECT * FROM testDatabase.Overeenkomst LEFT JOIN testDatabase.Vermist ON Vermist.idvermist = Overeenkomst.VermistID  WHERE Gesloten = 0;");

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
        boolean gVCheckBox = gevondenCheckBox.isSelected(), vVCheckBox = vermisteCheckBox.isSelected(); // get selected table

        Bagage vermisteBag = vermisteTabel.getSelectionModel().getSelectedItem(); // 
        Bagage gevondenBag = gevondenTabel.getSelectionModel().getSelectedItem();

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
                alert.setTitle("Not confirmed");
                alert.setHeaderText("Not confirmed");
                alert.setContentText("Click on the checkbox \"Confirm\" to confirm the match");
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
        Bagage gevondenBag = gevondenTabel.getSelectionModel().getSelectedItem();
        String query = String.format("DELETE FROM testDatabase.Gevonden WHERE idGevonden = %d", gevondenBag.getId()),
                deletionInfo = String.format("Are you sure you want to permanently remove this item?\n\n"
                        + "Destination - %s\nBrand - %s\nType - %s\n", gevondenBag.getBestemming(), gevondenBag.getMerk(), gevondenBag.getBagageType());

        System.out.print("[QUERY]: " + query + "\n");

        Alert alertMessageBox = new Alert(AlertType.CONFIRMATION);

        alertMessageBox.setTitle("Confirm Deletion");
        alertMessageBox.setContentText(deletionInfo);

        ButtonType OKButton = new ButtonType("OK.", ButtonData.OK_DONE);
        ButtonType CancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alertMessageBox.getButtonTypes().setAll(CancelButton, OKButton);

        Optional<ButtonType> result = alertMessageBox.showAndWait();

        try {
            if (result.get() == OKButton) {
                PreparedStatement statement = database.prepareStatement(query);
                gevondenTabel.getItems().removeAll(gevondenTabel.getSelectionModel().getSelectedItems());
                statement.executeUpdate();
            } else if (result.get() == CancelButton) {
                System.out.println("[USERINPUT]: Canceled");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    @FXML
    private void DeleteVermisteBagage() {
        Bagage vermisteBagage = vermisteTabel.getSelectionModel().getSelectedItem();
        String query = String.format("DELETE FROM testDatabase.Vermist WHERE idVermist = %d", vermisteBagage.getId()),
                deletionInfo = String.format("Are you sure you want to permanently remove this item?\n\n"
                        + "Destination - %s\nBrand - %s\nType - %s\n", vermisteBagage.getBestemming(), vermisteBagage.getMerk(), vermisteBagage.getBagageType());

        System.out.print("[QUERY]: " + query + "\n");

        Alert alertMessageBox = new Alert(AlertType.CONFIRMATION);

        alertMessageBox.setTitle("Confirm Deletion");
        alertMessageBox.setContentText(deletionInfo);

        ButtonType OKButton = new ButtonType("OK.", ButtonData.OK_DONE);
        ButtonType CancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alertMessageBox.getButtonTypes().setAll(CancelButton, OKButton);

        Optional<ButtonType> result = alertMessageBox.showAndWait();

        try {
            if (result.get() == OKButton) {
                PreparedStatement statement = database.prepareStatement(query);
                vermisteTabel.getItems().removeAll(vermisteTabel.getSelectionModel().getSelectedItems());
                statement.executeUpdate();
            } else if (result.get() == CancelButton) {
                System.out.println("[USERINPUT]: Canceled");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    @FXML Button createLostPdf, createFoundPdf;
    
    @FXML
    private void createLostPdf() throws IOException, DocumentException{
        Bagage bagage = vermisteTabel.getSelectionModel().getSelectedItem();
        selectedIdVermist = bagage.getId();
        
        Form form = new Form();
        form.createMissing("pdf/vermist.pdf", selectedIdVermist);
    }
    
    @FXML
    private void createFoundPdf() throws IOException, DocumentException{
        Bagage bagage = gevondenTabel.getSelectionModel().getSelectedItem();
        selectedIdGevonden = bagage.getId();
        
        Form form = new Form();
        form.createFound("pdf/gevonden.pdf", selectedIdGevonden);
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

    @FXML
    private void editVermist(ActionEvent event) {
        MainNavigator.loadVista(MainNavigator.EDIT_VERMIST);
    }

    @FXML
    public void CSVGevonden(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation CSV file");
        alert.setHeaderText("Confirmation CSV file");
        alert.setContentText("Are you sure you want to make a CSV file?");

        Optional<ButtonType> resultAlert = alert.showAndWait();
        if (resultAlert.get() == ButtonType.OK) {
            String[] idGevonden = new String[1];
            String[] gTijd = new String[1];
            String[] gDatum = new String[1];
            String[] gLuchthaven = new String[1];
            String[] gLabelnummer = new String[1];
            String[] gVluchtnummer = new String[1];
            String[] gBestemming = new String[1];
            String[] gBagageType = new String[1];
            String[] gMerk = new String[1];
            String[] gKleur = new String[1];
            String[] gBijzonderKenmerken = new String[1];

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();

            CSVWriter excel = new CSVWriter("ReportFound-" + dateFormat.format(date) + ".csv");
            excel.writeHeaders("MatchID", "DateFound", "IdFound", "DateFound", "TimeFound", "AirportFound",
                    "LabelnumberFound", "FlightnumberFound", "DestinationFound", "BagageTypeFound", "BrandFound",
                    "ColorFound", "SpecialCharFound");

            try {
                ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Gevonden WHERE Visibility = 0;");
                //Gaat net zo lang door, tot er geen records meer zijn
                for (int i = 0; i < idGevonden.length; i++) {
                    while (result.next()) {
                        idGevonden[i] = result.getString("idGevonden");
                        gTijd[i] = result.getString("Tijd");
                        gDatum[i] = result.getString("Datum");
                        gLuchthaven[i] = result.getString("Luchthaven");
                        gLabelnummer[i] = result.getString("Labelnummer");
                        gVluchtnummer[i] = result.getString("Vluchtnummer");
                        gBestemming[i] = result.getString("Bestemming");
                        gBagageType[i] = result.getString("BagageType");
                        gMerk[i] = result.getString("Merk");
                        gKleur[i] = result.getString("Kleur");
                        gBijzonderKenmerken[i] = result.getString("BijzonderKenmerken");

                        excel.writeData(idGevonden[i], gTijd[i],
                                gDatum[i], gLuchthaven[i], gLabelnummer[i], gVluchtnummer[i], gBestemming[i],
                                gBagageType[i], gMerk[i], gKleur[i], gBijzonderKenmerken[i]);

                    }
                }
                excel.close();

            } catch (SQLException ex) {

            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }
    
    @FXML
    public void CSVVermist(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation CSV file");
        alert.setHeaderText("Confirmation CSV file");
        alert.setContentText("Are you sure you want to make a CSV file?");

        Optional<ButtonType> resultAlert = alert.showAndWait();
        if (resultAlert.get() == ButtonType.OK) {

            String[] idVermist = new String[1];
            String[] vTijd = new String[1];
            String[] vDatum = new String[1];
            String[] vLuchthaven = new String[1];
            String[] vLabelnummer = new String[1];
            String[] vVluchtnummer = new String[1];
            String[] vBestemming = new String[1];
            String[] vBagageType = new String[1];
            String[] vMerk = new String[1];
            String[] vKleur = new String[1];
            String[] vBijzonderKenmerken = new String[1];
            String[] vNaam = new String[1];
            String[] vAdres = new String[1];
            String[] vWoonplaats = new String[1];
            String[] vPostcode = new String[1];
            String[] vLand = new String[1];
            String[] vTelefoon = new String[1];
            String[] vEmail = new String[1];

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();

            CSVWriter excel = new CSVWriter("ReportLost-" + dateFormat.format(date) + ".csv");
            excel.writeHeaders("IdLost", "DateLost", "TimeLost", "AirportLost",
                    "LabelnumberLost", "FlightnumberLost", "DestinationLost", "BagageTypeLost",
                    "BrandLost", "ColorLost", "Name", "Adress", "Residence", "ZipCode", "Country", "PhoneNumber",
                    "Email", "SpecialCharlost");

            try {
                ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Vermist WHERE Visibility = 0;");
                //Gaat net zo lang door, tot er geen records meer zijn
                for (int i = 0; i < idVermist.length; i++) {
                    while (result.next()) {

                        idVermist[i] = result.getString("idVermist");
                        vDatum[i] = result.getString("Datum");
                        vTijd[i] = result.getString("Tijd");
                        vLuchthaven[i] = result.getString("Luchthaven");
                        vLabelnummer[i] = result.getString("Labelnummer");
                        vVluchtnummer[i] = result.getString("Vluchtnummer");
                        vBestemming[i] = result.getString("Bestemming");
                        vBagageType[i] = result.getString("BagageType");
                        vMerk[i] = result.getString("Merk");
                        vKleur[i] = result.getString("Kleur");
                        vBijzonderKenmerken[i] = result.getString("BijzonderKenmerken");
                        vNaam[i] = result.getString("Naam");
                        vAdres[i] = result.getString("Adres");
                        vWoonplaats[i] = result.getString("Woonplaats");
                        vPostcode[i] = result.getString("Postcode");
                        vLand[i] = result.getString("Land");
                        vTelefoon[i] = result.getString("Telefoon");
                        vEmail[i] = result.getString("Email");

                        excel.writeData(idVermist[i], vDatum[i], vTijd[i], vLuchthaven[i], vLabelnummer[i],
                                vVluchtnummer[i], vBestemming[i], vBagageType[i], vMerk[i], vKleur[i],
                                vNaam[i], vAdres[i], vWoonplaats[i], vPostcode[i], vLand[i], vTelefoon[i],
                                vEmail[i], vBijzonderKenmerken[i]);

                    }
                }
                excel.close();

            } catch (SQLException ex) {

            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }
    
}
