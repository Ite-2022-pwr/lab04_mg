package pl.pwr.ite.client.gluchowski.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@JsonIgnoreProperties(value = {"gegrLat", "gegrLon", "addressStreet"})
public class Stacja {
    private Integer id;
    private String stationName;

    @JsonIgnore
    private List<Sensor> sensors;
    private Miasto city;
    private IndexPowietrza index;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Miasto getCity() {
        return city;
    }

    public void setCity(Miasto city) {
        this.city = city;
    }

    public IndexPowietrza getIndex() {
        return index;
    }

    public void setIndex(IndexPowietrza index) {
        this.index = index;
    }
}
