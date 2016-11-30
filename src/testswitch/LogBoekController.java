package testswitch;

import java.awt.Color;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Ron Pelt
 */
public class LogBoekController {
    
    /********************************************
    *                                           *
    *   Inladen Database data naar Tabellen     *
    *                                           *
    *********************************************/

    //Gevonden bagage
    @FXML public TableView<Bagage> gevondenTabel;
    @FXML public TableColumn<Bagage, Integer> gevondenIdKolom;
    @FXML public TableColumn<Bagage, String> gevondenDatumKolom;
    @FXML public TableColumn<Bagage, Integer> gevondenLabelNummerKolom;
    @FXML public TableColumn<Bagage, Integer> gevondenVluchtNrKolom;
    
    //Vermiste bagage
    @FXML public TableView<Bagage> vermisteTabel;
    @FXML public TableColumn<Bagage, Integer> vermisteIdKolom;
    @FXML public TableColumn<Bagage, String> vermisteDatumKolom;
    @FXML public TableColumn<Bagage, Integer> vermisteLabelNummerKolom;
    @FXML public TableColumn<Bagage, Integer> vermisteVluchtNrKolom;
    @FXML public TableColumn<Bagage, String> vermisteBagageTypeKolom;
    
    //Gesloten Bagage
    @FXML public TableView<Bagage> geslotenTableView;
    @FXML public TableColumn<Bagage, Integer> idKolom;
    @FXML public TableColumn<Bagage, String> datumKolom;
    @FXML public TableColumn<Bagage, Integer> labelnummerKolom;
    @FXML public TableColumn<Bagage, Integer> vluchtnummerKolom;
    
    //Overeenkomst Bagage
    @FXML public TableView<Bagage> overeenkomstTableView;
    @FXML public TableColumn<Bagage, Integer> overeenkomstIdKolom;
    @FXML public TableColumn<Bagage, String> overeenkomstDatumKolom;
    @FXML public TableColumn<Bagage, Integer> overeenkomstLabelNummerKolom;

    private final ObservableList<Bagage> gevondenData = FXCollections.observableArrayList();
    private final ObservableList<Bagage> vermisteData = FXCollections.observableArrayList();
    private ObservableList<Bagage> geslotenData = FXCollections.observableArrayList();
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
            ResultSet result = database.executeQuery("SELECT idGevonden, Datum, "
                    + "Labelnummer, Vluchtnummer FROM testDatabase.Gevonden;");
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("idGevonden"));
                bagage.setDatum(result.getString("Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                gevondenData.add(bagage);
            }
            
            result = database.executeQuery("SELECT idVermist, Datum, Labelnummer, "
                    + "Vluchtnummer FROM testDatabase.Vermist;");
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("idVermist"));
                bagage.setDatum(result.getString("Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                vermisteData.add(bagage);
            }
            
            result = database.executeQuery("SELECT OvereenkomstID, Datum "
                    + "FROM testDatabase.Overeenkomst;");
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("idGesloten"));
                bagage.setDatum(result.getString("Datum"));
                geslotenData.add(bagage);
            }
            
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("OvereenkomstID"));
                bagage.setDatum(result.getString("Datum"));
                overeenkomstData.add(bagage);
            }


        } catch (SQLException ex) {

        }

    }
    
    
    /************************
    *                       *
    *   PieChart berekenen  *
    *                       *
    *************************/
    
    
    @FXML private GridPane startPanel;
    static int aantalVermist, aantalGevonden, aantalOvereenkomst, aantalGesloten = 0;

    private void createPieChart() {

        Database database = new Database(
                "testDatabase",
                "ronpelt.synology.me:3306",
                "root",
                "kGjMtEO06BPiu2u4"
        );

        ResultSet graphVermist = database.executeQuery(
                "SELECT idVermist FROM testDatabase.Vermist;");
        ResultSet graphGevonden = database.executeQuery(
                "SELECT idGevonden FROM testDatabase.Gevonden;");
        ResultSet graphOvereenkomst = database.executeQuery(
                "SELECT OvereenkomstID, Gesloten FROM testDatabase.Overeenkomst;");

        try {
            while (graphVermist.next()) {
                aantalVermist += 1;
            }
        } catch (SQLException ex) {
        }
        try {
            while (graphGevonden.next()) {
                aantalGevonden += 1;
            }
        } catch (SQLException ex) {
        }
        try {
            while (graphOvereenkomst.next()) {
                if (graphOvereenkomst.getInt("Gesloten") == 0) {
                    aantalOvereenkomst += 1;
                } else {
                    aantalGesloten += 1;
                }
            }
        } catch (SQLException ex) {
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Gevonden Bagages = " + aantalGevonden, new Double(aantalGevonden));
        dataset.setValue("Vermiste Bagages = " + aantalVermist, new Double(aantalVermist));
        dataset.setValue("Overeenkomst = " + aantalOvereenkomst, new Double(aantalOvereenkomst));
        dataset.setValue("Gesloten = " + aantalGesloten, new Double(aantalGesloten));

        JFreeChart chart = ChartFactory.createPieChart(
                "Aantal bagages", // chart title
                dataset, // data
                true, // include legend
                true,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Gevonden Bagages = " + aantalGevonden, new Color(0, 187, 225));
        plot.setSectionPaint("Vermiste Bagages = " + aantalVermist, new Color(255, 196, 30));
        plot.setSectionPaint("Overeenkomst = " + aantalOvereenkomst, new Color(216, 30, 5));
        plot.setSectionPaint("Gesloten = " + aantalGesloten, new Color(255, 255, 255));
        
        aantalVermist = 0;
        aantalGevonden = 0;
        aantalOvereenkomst = 0;
        aantalGesloten = 0;

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(new ChartPanel(chart));
        startPanel.add(swingNode, 0, 1, 2, 1);
    }

    /********************************************************
    *                                                       *
    *   Direct ingeladen als Controller wordt ingeladen     *
    *                                                       *
    *********************************************************/
    
    //URL url, ResourceBundle rb
    public void initialize() {
        
        //Kollomen worden gelinkt aan Atributen van de Person class
        gevondenIdKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        gevondenDatumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        gevondenLabelNummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        gevondenVluchtNrKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("vluchtNr"));
        
        //Vermist
        vermisteIdKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        vermisteDatumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        vermisteLabelNummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        vermisteVluchtNrKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("vluchtNr"));
        
        //Overeenkomst
        overeenkomstIdKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        overeenkomstDatumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        
        createPieChart();
        gevondenTabel.setItems(gevondenData);
        vermisteTabel.setItems(vermisteData);
        geslotenTableView.setItems(geslotenData);
        overeenkomstTableView.setItems(overeenkomstData);
        writeTableData();

    }
}
