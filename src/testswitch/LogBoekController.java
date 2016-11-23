package testswitch;

import java.awt.Color;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
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
    }
}
