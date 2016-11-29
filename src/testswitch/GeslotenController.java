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
public class GeslotenController implements Initializable {
    
    @FXML public TableView<Bagage> geslotenTableView;
    @FXML public TableColumn<Bagage, Integer> geslotenIdKolom;
    @FXML public TableColumn<Bagage, Integer> geslotenLabelNummerKolom;
    @FXML public TableColumn<Bagage, String> geslotenBagageTypeKolom;
    @FXML public TableColumn<Bagage, String> geslotenDatumKolom;
    
    private ObservableList<Bagage> geslotenData = FXCollections.observableArrayList();
    
    @FXML
    private void writeTableData() {
        Database database = new Database(
            "testDatabase",
            "ronpelt.synology.me:3306",
            "root",
            "kGjMtEO06BPiu2u4"
        );

        try {
            ResultSet resultOvereenkomst = database.executeQuery("SELECT * FROM testDatabase.Overeenkomst WHERE Gesloten = 1;");
            ResultSet resultGevonden = database.executeQuery("SELECT *  FROM testDatabase.Gevonden");
            
            

            //Gaat net zo lang door, tot er geen records meer zijn
            while (resultOvereenkomst.next() && resultGevonden.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(resultOvereenkomst.getInt("OvereenkomstID"));
                bagage.setDatum(resultOvereenkomst.getString("Datum"));

                bagage.setLabelNummer(resultGevonden.getInt("Labelnummer"));
                bagage.setBagageType(resultGevonden.getString("BagageType")); 
                geslotenData.add(bagage);

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
    private void geslotenSelected() {
        
        Bagage bagage = geslotenTableView.getSelectionModel().getSelectedItem();
        
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
        geslotenIdKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        geslotenDatumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        geslotenLabelNummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("Labelnummer"));
        geslotenBagageTypeKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("BagageType"));
        
        geslotenTableView.setItems(geslotenData);
        writeTableData();
    }
}


