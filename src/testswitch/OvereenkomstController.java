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
    @FXML public TableColumn<Bagage, Integer> idKolom;
    @FXML public TableColumn<Bagage, Integer> datumKolom;
    @FXML public TableColumn<Bagage, Integer> labelNummerKolom;
    @FXML public TableColumn<Bagage, String> bagageTypeKolom;
    
    private ObservableList<Bagage> data = FXCollections.observableArrayList();
    
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
                bagage.setDatum(result.getInt("Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setBagageType(result.getString("BagageType"));
                
                data.add(bagage);
            }

        } catch (SQLException ex) {

        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Kollomen worden gelinkt aan Atributen van de Person class
        idKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        datumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("datum"));
        labelNummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        bagageTypeKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("bagageType"));
                        
        overeenkomstTableView.setItems(data);
        writeTableData();
    }

}
