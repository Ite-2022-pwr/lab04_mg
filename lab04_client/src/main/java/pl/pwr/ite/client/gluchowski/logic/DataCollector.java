package pl.pwr.ite.client.gluchowski.logic;

import pl.pwr.ite.client.gluchowski.data.*;
import pl.pwr.ite.client.gluchowski.data.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCollector {
    private List<Stacja> stations;
    private Parser parser = new Parser();

    public DataCollector() {
        this.stations = new ArrayList<>();

    }

    public List<Stacja> getAll() {
        return this.stations;
    }

    public Stacja getById(Integer stationId) {
        return stations.stream()
                .filter(s -> s.getId().equals(stationId))
                .findFirst()
                .orElse(null);
    }

    public void loadAllStations() {
        System.out.println("ok");
        this.stations = List.of(parser.get("/station/findAll", Stacja[].class));
        System.out.println("lk");
    }

    public List<Sensor> fetchSensors(Integer stationId) {
        return List.of(parser.get("/station/sensors/" + stationId, Sensor[].class));
    }

    public SensorDane fetchSensorData(Integer sensorId) {
        return parser.get("/data/getData/" + sensorId, SensorDane.class);
    }

    public IndexPowietrza fetchStationIndex(Integer stationId) {
        return parser.get("/aqindex/getIndex/" + stationId, IndexPowietrza.class);
    }

    public Map<String, Double> collectSensorDataForChart(Integer sensorId) {
        Map<String, Double> tmpSensorData = new HashMap();
        for (SensorDaneVal data : this.fetchSensorData(sensorId).getValues()) {
            tmpSensorData.put(data.getDate(), data.getValue());
        }
        return tmpSensorData;
    }

    public ArrayList<String> collectDataForDisplayStacjeIdName() {
        ArrayList<String> stationNames = new ArrayList<>();
        for (Stacja station : this.stations) {
            stationNames.add(station.getId() + "\t" + station.getStationName());
        }
        return stationNames;
    }

    public ArrayList<Integer> collectStationIdForValidation() {
        ArrayList<Integer> stationIds = new ArrayList<>();
        for (Stacja station : this.stations) {
            stationIds.add(station.getId());
        }
        return stationIds;
    }

    public ArrayList<String> collectDataForDisplaySensoryIdName(Integer stationId) {
        ArrayList<String> sensorNames = new ArrayList<>();
        for (Sensor sensor : this.fetchSensors(stationId)) {
            sensorNames.add(sensor.getId() + "\t" + sensor.getParam().getParamName());
        }
        System.out.println("[+] " + sensorNames);
        return sensorNames;
    }

    public ArrayList<Integer> collectSensorIdForValidation(Integer stationId) {
        ArrayList<Integer> sensorIds = new ArrayList<>();
        for (Sensor sensor : this.fetchSensors(stationId)) {
            sensorIds.add(sensor.getId());
        }
        System.out.println(sensorIds);
        return sensorIds;
    }

}
