package pl.pwr.ite.client.gui;

import pl.pwr.ite.client.gluchowski.logic.Controller;
import pl.pwr.ite.client.gluchowski.logic.DataCollector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private JButton submitButton;
    private JPanel mainPane;
    private JScrollPane jScrollPane1;
    private JComboBox<String> comboBox1;
    private JComboBox comboBox2;
    private ArrayList<String> stations;
    private Controller controller;
    private ArrayList<String> stationsAndIds = new ArrayList<>();
    private ArrayList<String> stanowiskaPomiarowe = new ArrayList<>();
    private DataCollector dataCollector = new DataCollector();
    private ArrayList<String> stacjePomiarowe = new ArrayList<>();
    private ArrayList<Integer> stacjeValidationIds = new ArrayList<>();
    private ArrayList<String> tmpStanowiskaPomiarowe = new ArrayList<>();
    private ArrayList<Integer> sensorValidationIds = new ArrayList<>();

    public MainFrame() {
        this.dataCollector.loadAllStations();
        this.stacjePomiarowe = dataCollector.collectDataForDisplayStacjeIdName();
        this.stacjeValidationIds = dataCollector.collectStationIdForValidation();
        this.controller = new Controller();
        this.controller.run();
        this.stationsAndIds = this.controller.getStacjePomiarowe();
        this.stanowiskaPomiarowe = this.controller.getStanowiskaPomiarowe();

        final DefaultComboBoxModel defaultComboBoxModel = displayStations();
        comboBox1.setModel(defaultComboBoxModel);

        mainPane.revalidate();
        mainPane.repaint();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stationId = comboBox1.getSelectedItem().toString().split("\t")[0].trim();
                try {
                    int stationIdInt = Integer.parseInt(stationId);
                    if (stacjeValidationIds.contains(stationIdInt)) {
                        DefaultComboBoxModel defaultComboBoxModel1 = displayStanowiskaPomiarowe(stationIdInt);
                        comboBox2.setModel(defaultComboBoxModel1);
                    } else {
                        JOptionPane.showMessageDialog(submitButton, "No data for a station.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(submitButton, "Please enter a valid ID.");
                }
            }

        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stationId = comboBox2.getSelectedItem().toString().split("\t")[0].trim();
                try {
                    int stationIdInt = Integer.parseInt(stationId);
                    if (sensorValidationIds.contains(stationIdInt)) {
                        displayChartForSensor(stationIdInt);
                    } else {
                        JOptionPane.showMessageDialog(submitButton, "No data for a sensor.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(submitButton, "Please enter a valid ID.");
                }
            }
        });
    }

    private void displayChartForSensor(int stationIdInt) {
        Map<String, Double> tmp = new HashMap();
        tmp = this.dataCollector.collectSensorDataForChart(stationIdInt);

        Wykres wykres = new Wykres(tmp);
        wykres.run();
    }



    private DefaultComboBoxModel displayStanowiskaPomiarowe(Integer id) {
        this.tmpStanowiskaPomiarowe = this.dataCollector.collectDataForDisplaySensoryIdName(id);
        this.sensorValidationIds = this.dataCollector.collectSensorIdForValidation(id);
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        StringBuilder stationsText = new StringBuilder();
        for (String station : tmpStanowiskaPomiarowe) {
            defaultComboBoxModel.addElement(station);
        }
        return defaultComboBoxModel;
    }


    private DefaultComboBoxModel displayStations() {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        String[] tmp = stationsAndIds.toArray(new String[]{});
        for (var station : tmp) {
            Integer stationId = Integer.parseInt(station.split("\t")[0].trim());
            if (stacjeValidationIds.contains(stationId)) {
                defaultComboBoxModel.addElement(station);
            }
        }
        return defaultComboBoxModel;
    }

    public void run() {
        this.setContentPane(mainPane);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        this.setVisible(true);

        this.controller.run();

    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPane = new JPanel();
        mainPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPane.setMaximumSize(new Dimension(280, 132));
        mainPane.setMinimumSize(new Dimension(280, 132));
        jScrollPane1 = new JScrollPane();
        mainPane.add(jScrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        comboBox1.setModel(defaultComboBoxModel1);
        jScrollPane1.setViewportView(comboBox1);
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPane.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        comboBox2 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        comboBox2.setModel(defaultComboBoxModel2);
        mainPane.add(comboBox2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPane;
    }

}
