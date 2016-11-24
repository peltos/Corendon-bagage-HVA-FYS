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
    
    @FXML public TableView<Gevonden> gebruikerTableView;
    @FXML public TableColumn<Gevonden, Integer> gebruikerIDKolom;
    @FXML public TableColumn<Gevonden, Date> dateKolom;
    @FXML public TableColumn<Gevonden, Integer> labelNrKolom;
    @FXML public TableColumn<Gevonden, Integer> vluchtNrKolom;
    
    private ObservableList<Gevonden> data = FXCollections.observableArrayList();
    
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
                gevonden.setGebruikerID(result.getInt("idGevonden"));
                gevonden.setVoornaam(result.getString("Datum"));
                gevonden.setTussenvoegsel(result.getString("Labelnummer"));
                gevonden.setAchternaam(result.getString("Vluchtnummer"));

                data.add(gevonden);
            }

        } catch (SQLException ex) {

        }

    }
    
    @FXML
    private GridPane startPanel;

    private void createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Gevonden", new Double(60));
        dataset.setValue("Vermiste", new Double(40));
        dataset.setValue("Overeenkomst", new Double(75));
        dataset.setValue("Gesloten", new Double(89));
        JFreeChart chart = ChartFactory.createPieChart(
                "Maandelijkse Gegevens", // chart title
                dataset, // data
                true, // include legend
                true,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Gevonden", new Color(0, 187, 225));
        plot.setSectionPaint("Vermiste", new Color(255, 196, 30));
        plot.setSectionPaint("Overeenkomst", new Color(216, 30, 5));
        plot.setSectionPaint("Gesloten", new Color(255, 255, 255));

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(new ChartPanel(chart));
        startPanel.add(swingNode, 0, 1, 2, 1);
    }

    //URL url, ResourceBundle rb
    public void initialize() {
        createPieChart();
        gebruikerIDKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("idGevonden"));
        dateKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Date>("Datum"));
        labelNrKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("Labelnummer"));
        vluchtNrKolom.setCellValueFactory(
                new PropertyValueFactory<Gevonden, Integer>("Vluchtnummer"));
                        
        gebruikerTableView.setItems(data);
        writeTableData();
        
    }
}
