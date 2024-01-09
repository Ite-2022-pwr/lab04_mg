package pl.pwr.ite.client.gluchowski.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Sensor {
    public Integer stationId;
    public Integer id;

    @JsonIgnore
    public SensorDane sensorData;
    public Parametr param;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SensorDane getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorDane sensorData) {
        this.sensorData = sensorData;
    }

    public Parametr getParam() {
        return param;
    }

    public void setParam(Parametr param) {
        this.param = param;
    }

}
