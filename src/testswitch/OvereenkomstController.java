/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testswitch;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Daan
 */
public class OvereenkomstController implements Initializable {
    
    @FXML public TableView<Bagage> overeenkomstTableView;
    @FXML public TableColumn<Bagage, Integer> overeenkomstIdKolom;
    @FXML public TableColumn<Bagage, String> overeenkomstDatumKolom;
    @FXML public TableColumn<Bagage, Integer> overeenkomstLabelNummerKolom;
    @FXML public TableColumn<Bagage, String> overeenkomstBagageTypeKolom;
    
    private ObservableList<Bagage> overeenkomstData = FXCollections.observableArrayList();
    
    @FXML
    private void writeTableData() {
        Database database = new Database(
            "testDatabase",
            "ronpelt.synology.me:3306",
            "root",
            "kGjMtEO06BPiu2u4"
        );

        try {
            ResultSet resultGevonden = database.executeQuery("SELECT * FROM testDatabase.Overeenkomst LEFT JOIN testDatabase.Gevonden ON Gevonden.idGevonden = Overeenkomst.GevondenID  WHERE Gesloten = 0;");
            ResultSet resultVermist = database.executeQuery("SELECT * FROM testDatabase.Overeenkomst LEFT JOIN testDatabase.Vermist ON Vermist.idvermist = Overeenkomst.VermistID  WHERE Gesloten = 0;");
            

            //Gaat net zo lang door, tot er geen records meer zijn
            while (resultGevonden.next() && resultVermist.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(resultGevonden.getInt("OvereenkomstID"));
                bagage.setDatum(resultGevonden.getString("Datum"));

                bagage.setGId(resultGevonden.getInt("idGevonden"));
                bagage.setGTijd(resultGevonden.getString("Tijd"));
                bagage.setGDatum(resultGevonden.getString("Datum"));
                bagage.setGLuchthaven(resultGevonden.getString("Luchthaven"));
                bagage.setGLabelNummer(resultGevonden.getInt("Labelnummer"));
                bagage.setGVluchtNr(resultGevonden.getInt("Vluchtnummer"));
                bagage.setGBestemming(resultGevonden.getString("Bestemming"));
                bagage.setGBagageType(resultGevonden.getString("BagageType")); 
                bagage.setGId(resultGevonden.getInt("idGevonden"));
                bagage.setGMerk(resultGevonden.getString("Merk"));
                bagage.setGKleur(resultGevonden.getString("Kleur"));
                bagage.setGBijzondereKenmerken(resultGevonden.getString("BijzonderKenmerken"));
                System.out.println(bagage.getGLabelNummer());

                bagage.setId(resultVermist.getInt("idVermist"));
                bagage.setTijd(resultVermist.getString("Tijd")); 
                bagage.setDatum(resultVermist.getString("Datum"));
                bagage.setLuchthaven(resultVermist.getString("Luchthaven"));
                bagage.setLabelNummer(resultVermist.getInt("Labelnummer"));
                bagage.setVluchtNr(resultVermist.getInt("Vluchtnummer"));
                bagage.setBestemming(resultVermist.getString("Bestemming"));
                bagage.setBagageType(resultVermist.getString("BagageType")); 
                bagage.setMerk(resultVermist.getString("Merk"));
                bagage.setKleur(resultVermist.getString("Kleur"));
                bagage.setBijzondereKenmerken(resultVermist.getString("BijzonderKenmerken"));
                bagage.setNaam(resultVermist.getString("Naam"));
                bagage.setAdres(resultVermist.getString("Adres"));
                bagage.setWoonplaats(resultVermist.getString("Woonplaats"));
                bagage.setPostcode(resultVermist.getString("Postcode"));
                bagage.setLand(resultVermist.getString("Land"));
                bagage.setTelefoonnummer(resultVermist.getInt("Telefoon"));
                bagage.setEmail(resultVermist.getString("Email"));
                overeenkomstData.add(bagage);

            }
            
        } catch (SQLException ex) {

        }

    }
    @FXML public Label gevondenDatum, gevondenTijd, gevondenLuchthaven, gevondenID,
            gevondenType, gevondenMerk, gevondenKleur, gevondenBK, gevondenLabelNr,
            gevondenVluchtNr, gevondenBestemming;
    
    @FXML public Label  vermisteDatum, vermisteTijd, vermisteLuchthaven, vermisteID,
            vermisteNaam, vermisteAdres, vermisteWoonplaats, vermistePostcode,
            vermisteLand, vermisteTelefoon, vermisteEmail, vermisteType, vermisteMerk,
            vermisteKleur, vermisteBK, vermisteLabelNr, vermisteVluchtNr, vermisteBestemming;

    @FXML
    private void overeenkomstSelected() {
        
        Bagage bagage = overeenkomstTableView.getSelectionModel().getSelectedItem();
        
        gevondenDatum.setText(bagage.getGDatum());
        gevondenTijd.setText(bagage.getGTijd());
        gevondenLuchthaven.setText(bagage.getGLuchthaven());
        gevondenID.setText(String.valueOf(bagage.getGId()));
        gevondenType.setText(bagage.getGBagageType());
        gevondenMerk.setText(bagage.getGMerk());
        gevondenKleur.setText(bagage.getGKleur());
        gevondenBK.setText(bagage.getGBijzondereKenmerken());
        gevondenLabelNr.setText(String.valueOf(bagage.getGLabelNummer()));
        gevondenVluchtNr.setText(String.valueOf(bagage.getGVluchtNr()));
        gevondenBestemming.setText(bagage.getGBestemming());
        
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
        overeenkomstIdKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        overeenkomstDatumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        overeenkomstLabelNummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        overeenkomstBagageTypeKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("bagageType"));
        
        overeenkomstTableView.setItems(overeenkomstData);
        writeTableData();
    }
}


