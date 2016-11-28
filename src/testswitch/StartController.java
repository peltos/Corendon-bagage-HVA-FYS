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
public class StartController implements Initializable {

    //Gevonden bagage
    @FXML public TableView<Bagage> gevondenTabel;
    @FXML public TableColumn<Bagage, Integer> gevondenIdKolom;
    @FXML public TableColumn<Bagage, String> gevondenDatumKolom;
    @FXML public TableColumn<Bagage, Integer> gevondenLabelNummerKolom;
    @FXML public TableColumn<Bagage, Integer> gevondenVluchtNrKolom;
    @FXML public TableColumn<Bagage, String> gevondenBagageTypeKolom;
    
    //Vermiste bagage
    @FXML public TableView<Bagage> vermisteTabel;
    @FXML public TableColumn<Bagage, Integer> vermisteIdKolom;
    @FXML public TableColumn<Bagage, String> vermisteDatumKolom;
    @FXML public TableColumn<Bagage, Integer> vermisteLabelNummerKolom;
    @FXML public TableColumn<Bagage, Integer> vermisteVluchtNrKolom;
    @FXML public TableColumn<Bagage, String> vermisteBagageTypeKolom;
    
    private ObservableList<Bagage> gevondenData = FXCollections.observableArrayList();
    private ObservableList<Bagage> vermisteData = FXCollections.observableArrayList();

    @FXML
    private void writeTableData(String tabelNaam) {
        Database database = new Database(
            "testDatabase",
            "ronpelt.synology.me:3306",
            "root",
            "kGjMtEO06BPiu2u4"
        );

        try {
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase." 
                    + tabelNaam + ";");

            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("id" + tabelNaam));
                bagage.setDatum(result.getString("Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                bagage.setBagageType(result.getString("BagageType")); 
                
                if (tabelNaam == "Gevonden") {
                    gevondenData.add(bagage);
                } else if (tabelNaam == "Vermist") {
                    vermisteData.add(bagage);
                }
                
            }

        } catch (SQLException ex) {

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
        writeTableData("Gevonden");
        writeTableData("Vermist");
    }

    @FXML
    private void gevondenToevoegen(ActionEvent event) {
        MainNavigator.loadVista(MainNavigator.GEVONDEN);

    }
    
    @FXML
    private void vermistToevoegen(ActionEvent event) {
        MainNavigator.loadVista(MainNavigator.VERMIST);

    }
    
    
    
    
    
}
