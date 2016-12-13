package FYS.controller;

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
import FYS.Bagage;
import FYS.Database;
import FYS.Main;

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
    
    //tabellen logboek
    private final ObservableList<Bagage> gevondenData = FXCollections.observableArrayList();
    private final ObservableList<Bagage> vermisteData = FXCollections.observableArrayList();
    private final ObservableList<Bagage> geslotenData = FXCollections.observableArrayList();
    private final ObservableList<Bagage> overeenkomstData = FXCollections.observableArrayList();
    
    Database database = Main.getDatabase();

    @FXML
    private void writeTableData() {

        try {
            ResultSet result = database.executeQuery("SELECT idGevonden, Datum, "
                    + "Labelnummer, Vluchtnummer FROM testDatabase.Gevonden WHERE Datum BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND CURDATE();");
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("idGevonden"));
                bagage.setDatum(result.getString("Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                gevondenData.add(bagage);
            }
            
            result = database.executeQuery("SELECT idVermist, Datum, Labelnummer, "
                    + "Vluchtnummer FROM testDatabase.Vermist WHERE Datum BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND CURDATE();");
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("idVermist"));
                bagage.setDatum(result.getString("Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                vermisteData.add(bagage);
            }
            
            result = database.executeQuery("SELECT OvereenkomstID, Overeenkomst.Datum, "
                    + "Labelnummer FROM testDatabase.Overeenkomst JOIN testDatabase.Vermist "
                    + "ON VermistID=idVermist WHERE Gesloten = 0 AND Overeenkomst.Datum BETWEEN "
                    + "DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND CURDATE();");
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("OvereenkomstID"));
                bagage.setDatum(result.getString("Overeenkomst.Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                overeenkomstData.add(bagage);
            }
            
            result = database.executeQuery("SELECT OvereenkomstID, Overeenkomst.Datum, "
                    + "Labelnummer, Vluchtnummer FROM testDatabase.Overeenkomst JOIN testDatabase.Vermist "
                    + "ON VermistID=idVermist WHERE Gesloten = 1 AND Overeenkomst.Datum BETWEEN "
                    + "DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND CURDATE();");
            while (result.next()) {
                Bagage bagage = new Bagage();
                bagage.setId(result.getInt("OvereenkomstID"));
                bagage.setDatum(result.getString("Overeenkomst.Datum"));
                bagage.setLabelNummer(result.getInt("Labelnummer"));
                bagage.setVluchtNr(result.getInt("Vluchtnummer"));
                geslotenData.add(bagage);
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
        dataset.setValue("Found Baggages = " + aantalGevonden, new Double(aantalGevonden));
        dataset.setValue("Lost Baggages = " + aantalVermist, new Double(aantalVermist));
        dataset.setValue("Matches = " + aantalOvereenkomst, new Double(aantalOvereenkomst));
        dataset.setValue("Closed = " + aantalGesloten, new Double(aantalGesloten));

        JFreeChart chart = ChartFactory.createPieChart(
                "Total baggages", // chart title
                dataset, // data
                true, // include legend
                true,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Found Baggages = " + aantalGevonden, new Color(0, 187, 225));
        plot.setSectionPaint("Lost Baggages = " + aantalVermist, new Color(255, 196, 30));
        plot.setSectionPaint("Matches = " + aantalOvereenkomst, new Color(216, 30, 5));
        plot.setSectionPaint("Closed = " + aantalGesloten, new Color(255, 255, 255));
        
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
        overeenkomstLabelNummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        
        //gesloten
        idKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("id"));
        datumKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, String>("datum"));
        labelnummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("labelNummer"));
        vluchtnummerKolom.setCellValueFactory(
                new PropertyValueFactory<Bagage, Integer>("VluchtNr"));
        
        createPieChart();
        gevondenTabel.setItems(gevondenData);
        vermisteTabel.setItems(vermisteData);
        geslotenTableView.setItems(geslotenData);
        overeenkomstTableView.setItems(overeenkomstData);
        writeTableData();

    }
}
