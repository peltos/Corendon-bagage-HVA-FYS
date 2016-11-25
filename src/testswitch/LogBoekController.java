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

    @FXML
    public TableView<Gevonden> gevondenTableView;
    @FXML
    public TableColumn<Gevonden, Integer> gevondenIDKolom;
    @FXML
    public TableColumn<Gevonden, Date> dateKolom;
    @FXML
    public TableColumn<Gevonden, Integer> labelNrKolom;
    @FXML
    public TableColumn<Gevonden, Integer> vluchtNrKolom;

    private final ObservableList<Gevonden> data = FXCollections.observableArrayList();

    private void DatabaseLogboek() {
       
    }

    @FXML
    private void writeTableData() {

        Database database = new Database(
                "testDatabase",
                "ronpelt.synology.me:3306",
                "root",
                "kGjMtEO06BPiu2u4"
        );

        try {
            ResultSet result = database.executeQuery("SELECT * FROM testDatabase.Gevonden;");

            //Gaat net zo lang door, tot er geen records meer zijn
            while (result.next()) {
                Gevonden gevonden = new Gevonden();
                gevonden.setGevondenID(result.getInt("idGevonden"));
                gevonden.setDatum(result.getString("Datum"));
                gevonden.setLabelNr(result.getInt("Labelnummer"));
                gevonden.setVluchtNr(result.getInt("Vluchtnummer"));

                data.add(gevonden);
            }

        } catch (SQLException ex) {

        }

    }
    
    
    /************************
    *                       *
    *   PieChart berekenen  *
    *                       *
    *************************/
    
    
    @FXML
    private GridPane startPanel;

    static int aantalVermist = 0;
    static int aantalGevonden = 0;
    static int aantalOvereenkomst = 0;
    static int aantalGesloten = 0;

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
        createPieChart();
        gevondenIDKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("idGevonden"));
        dateKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Date>("Datum"));
        labelNrKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("Labelnummer"));
        vluchtNrKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("Vluchtnummer"));

        gevondenTableView.setItems(data);
        writeTableData();

    }
}
