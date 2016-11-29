package testswitch;

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

/**
 * @author Alexander 
 */
public class StartController implements Initializable {

    @FXML public CheckBox vermisteCheckBox;
    @FXML public CheckBox gevondenCheckBox;
    @FXML public Button buttonOvereenkomst;
    @FXML public Button FXGevondenDelete;
    
    
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
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Vermist;");
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
            
            result = database.executeQuery("SELECT * FROM testDatabase.Gevonden;");
            while (result.next()) 
            {
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
    
    @FXML public Label gevondenDatum, gevondenTijd, gevondenLuchthaven, gevondenID,
            gevondenType, gevondenMerk, gevondenKleur, gevondenBK, gevondenLabelNr,
            gevondenVluchtNr, gevondenBestemming;

    @FXML
    private void gevondenSelected() {
        
        Bagage bagage = gevondenTabel.getSelectionModel().getSelectedItem();
        
        boolean bSelected = gevondenCheckBox.isSelected() || vermisteCheckBox.isSelected();
        
        if (bSelected == true) gevondenCheckBox.setSelected(false);
    
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
    
    @FXML public Label  vermisteDatum, vermisteTijd, vermisteLuchthaven, vermisteID,
            vermisteNaam, vermisteAdres, vermisteWoonplaats, vermistePostcode,
            vermisteLand, vermisteTelefoon, vermisteEmail, vermisteType, vermisteMerk,
            vermisteKleur, vermisteBK, vermisteLabelNr, vermisteVluchtNr, vermisteBestemming;

    @FXML
    private void vermisteSelected() 
    {
        boolean bSelected = vermisteCheckBox.isSelected();
        
        Bagage bagage = vermisteTabel.getSelectionModel().getSelectedItem();
        
        if (bSelected == true) vermisteCheckBox.setSelected(false);
        
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
        writeTableData("Gevonden");
    }
    
     @FXML private void OvereenkomstDB() throws SQLException
     {
         boolean gVCheckBox = gevondenCheckBox.isSelected(), vVCheckBox = vermisteCheckBox.isSelected();
         
         Bagage vermisteBag = vermisteTabel.getSelectionModel().getSelectedItem();
         Bagage gevondenBag = gevondenTabel.getSelectionModel().getSelectedItem();
         
         String DB_NAME = "testDatabase", DB_SERVER = "ronpelt.synology.me:3306";
         String DB_ACCOUNT = "root", DB_PASSWORD = "kGjMtEO06BPiu2u4";

         Database database = new Database(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);
         
         
         String query = "INSERT INTO testDatabase.Overeenkomst (GevondenID, VermistID, Datum, Gesloten)"
                 + "VALUES (?, ?, ?, ?);";
         
         PreparedStatement statement = database.prepareStatement(query);
         
         try
         {
             if (!gVCheckBox || !vVCheckBox) 
             {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("BEVESTIG OR DIE");
                    alert.setHeaderText("BEVESTIG");
                    alert.setContentText("BEVESTIG");
                    alert.showAndWait();
             } else 
             {
                 statement.setString(1, gevondenBag.getId().toString());
                 
                 String selected_log = String.format("[TIMESTAMP]: %s\ngevondenBag(): %d\nvermisteBag(): %d", "xxxx-xx-xx",
                      gevondenBag.getId(), vermisteBag.getId());
                 
                 System.out.println(selected_log);
                 
                 statement.setString(2, vermisteBag.getId().toString());
                 statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                 statement.setString(4, "0");
                 statement.executeUpdate();
                 
                 vermisteCheckBox.setSelected(false);
                 gevondenCheckBox.setSelected(false);
             }
         
           
         } catch (Exception e) {
            e.printStackTrace(System.err);
        }
      
     }

     @FXML private void DeleteGevondenBagage()
     {
         String DB_NAME = "testDatabase", DB_SERVER = "ronpelt.synology.me:3306";
         String DB_ACCOUNT = "root", DB_PASSWORD = "kGjMtEO06BPiu2u4";

         Database database = new Database(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);
         
         gevondenTabel.getItems().removeAll(gevondenTabel.getSelectionModel().getSelectedItems());
         
           String query = "DELETE INTO testDatabase.Overeenkomst (GevondenID, VermistID, Datum, Gesloten)"
                 + "VALUES (?, ?, ?, ?);";
           
           //DELETE FROM MyGuests WHERE id=3
         
         Bagage gevondenBag = gevondenTabel.getSelectionModel().getSelectedItem();

          try
          {
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

}
