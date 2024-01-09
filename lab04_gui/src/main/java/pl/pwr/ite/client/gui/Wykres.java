package pl.pwr.ite.client.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Wykres extends JFrame {
    private Map<String, Double> data = new HashMap<String, Double>();
    public Wykres(Map<String, Double> data) {
        super("Wykres słupkowy");

        this.data = data;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.setValue(entry.getValue(), "Values", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Wykres dla wybranyego sensoru",
                "Daty",
                "Wartości",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    public void displayBarChart() {
        SwingUtilities.invokeLater(() -> {
            Wykres example = new Wykres(this.data);
            example.pack();
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            example.setVisible(true);
        });
    }

    public void run() {
        displayBarChart();
    }
}
