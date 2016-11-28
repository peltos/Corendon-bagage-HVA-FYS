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
                bagage.setGevondenID(result.getInt("GevondenID"));
                bagage.setVermistID(result.getInt("VermistID"));
                bagage.setDatum(result.getString("Datum"));
                
                //Checkt of Labelnummer/BagageType waarde heeft in Gevonden tabel --> voert waarde in uit de database
                ResultSet result2 = database.executeQuery("SELECT * FROM testDatabase.Gevonden WHERE idGevonden=" + bagage.getGevondenID());
                if (result2.getInt("Labelnummer") != 0) {
                    bagage.setLabelNummer(result.getInt("Labelnummer"));
                } if (result2.getString("BagageType") != null){
                    bagage.setBagageType(result.getString("BagageType"));
                }
                
                //Checkt of Labelnummer/BagageType waarde heeft in Vermist tabel, en of de atribuut geen waarde heeft --> voert waarde in uit de database
                result2 = database.executeQuery("SELECT * FROM testDatabase.Vermist WHERE idVermist=" + bagage.getVermistID());
                if ((result2.getInt("Labelnummer") != 0) && (bagage.getLabelNummer() == 0)) {
                    bagage.setLabelNummer(result.getInt("Labelnummer"));
                } if ((result2.getString("BagageType") != null) && (bagage.getBagageType() == null)){
                    bagage.setBagageType(result.getString("BagageType"));
                }
                
                System.out.println("1" + bagage.getLabelNummer() + "\t" + bagage.getBagageType());
                System.out.println("2" + bagage.getDatum());
                System.out.println("3" + bagage.getGevondenID() + "\t" + bagage.getVermistID());
                
                overeenkomstData.add(bagage);
                System.out.println(overeenkomstData);
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
