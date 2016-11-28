package testswitch;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Daan Dirker
 * 
 */

public class OvereenkomstController implements Initializable{
    
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
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Overeenkomst;");

            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("OvereenkomstID"));
                bagage.setDatum(result.getString("Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setBagageType(result.getString("BagageType"));
                
                overeenkomstData.add(bagage);
            }

        } catch (SQLException ex) {

        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kollomen worden gelinkt aan Atributen van de Person class
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
