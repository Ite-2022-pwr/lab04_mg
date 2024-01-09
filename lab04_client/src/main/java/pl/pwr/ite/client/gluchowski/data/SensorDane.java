package pl.pwr.ite.client.gluchowski.data;


public class SensorDane {

    private SensorDaneVal[] values;
    private String key;

    public SensorDaneVal[] getValues() {
        return values;
    }

    public void setValues(SensorDaneVal[] values) {
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
