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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Daan
 */
public class GeslotenController implements Initializable {
    
    @FXML public TableView<Bagage> geslotenTableView;
    @FXML public TableColumn<Bagage, Integer> idKolom;
    @FXML public TableColumn<Bagage, String> datumKolom;
    @FXML public TableColumn<Bagage, Integer> labelnummerKolom;
    @FXML public TableColumn<Bagage, Integer> vluchtnummerKolom;
    @FXML public TableColumn<Bagage, String> bagagetypeKolom;
    
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
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Gesloten;");

            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("idGesloten"));
                bagage.setDatum(result.getString("Datum"));               
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("VluchtNr"));
                bagage.setBagageType(result.getString("BagageType"));
                geslotenData.add(bagage);
            }

        } catch (SQLException ex) {

        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        datumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        labelnummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        vluchtnummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("vluchtNr"));
        bagagetypeKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("bagageType"));
        
        geslotenTableView.setItems(geslotenData);
        writeTableData();
    }
}


