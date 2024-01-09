package pl.pwr.ite.client.gluchowski.logic;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataParser {
    String data;
    ArrayList<String> stationName;
    ArrayList<Integer> stationId;
    Map<Integer, String> stationNamesAndIds;
    public DataParser(String data) {
        this.data = data;
        this.stationNamesAndIds = new HashMap<>();
        this.stationName = new ArrayList<>();
        this.stationId = new ArrayList<>();
    }

    // METHODS WHICH ARE COORDINATING THE PARSING PROCESS

    public void downloadStationIdAndName() {
        this.downloadIdStacjiPomiarowych();
        this.downloadStacjiPomiarowych();
        //TODO check if stationId and stationName have the same size, cause something is wrong with parsing

        System.out.println(this.stationId.size() + " " + this.stationName.size());
        for (int i = 0; i < Math.min(this.stationId.size(), this.stationName.size()); i++) {
            this.stationNamesAndIds.put(this.stationId.get(i), this.stationName.get(i));
        }
    }

//    public void downloadStanowiskaIdAndName(int id) {
//            this.downloadIdStanowiskaPomiarowe(id);
//            this.downloadStanowiskaPomiarowe();
//        //TODO check if stationId and stationName have the same size, cause something is wrong with parsing
//
//        System.out.println(this.stationId.size() + " " + this.stationName.size());
//        for (int i = 0; i < Math.min(this.stationId.size(), this.stationName.size()); i++) {
//            this.stationNamesAndIds.put(this.stationId.get(i), this.stationName.get(i));
//        } }
//    private void downloadIdStanowiskaPomiarowe(){
//        try {
//
//            JsonFactory factory = new JsonFactory();
//            JsonParser parser = factory.createParser(this.data);
//            JsonToken token = parser.nextToken();
//
//            while(token != JsonToken.END_ARRAY) {
//                if (token == JsonToken.START_OBJECT) {
//                    while (token != JsonToken.END_OBJECT) {
//                        if (token == JsonToken.FIELD_NAME) {
//                            String fieldName = parser.getCurrentName();
//                            if (fieldName.equals("id")) {
//                                token = parser.nextToken();
//                                if (parser.getValueAsInt() == stacjaId) {
//                                    token = parser.nextToken();
//
//                                }
//                            }
//                            else {
//                                token = parser.nextToken();
//                            }
//                        } else {
//                            token = parser.nextToken();
//                        }
//                    }
//                }
//                token = parser.nextToken();
//            }
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//        }
//    }

    private void downloadStanowiskPomiarowych() {
        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(data);
            JsonToken token = parser.nextToken();
            while (token != JsonToken.END_ARRAY) {
                if (token == JsonToken.START_OBJECT) {
                    while (token != JsonToken.END_OBJECT) {
                        if (token == JsonToken.FIELD_NAME) {
                            String fieldName = parser.getCurrentName();
                            if (fieldName.equals("paramFormula")) {
                                token = parser.nextToken();
                                this.stationName.add(parser.getValueAsString());
                            }
                            else {
                                token = parser.nextToken();
                            }
                        } else {
                            token = parser.nextToken();
                        }
                    }
                }
                token = parser.nextToken();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }


    // METHODS WHICH ARE PARSING THE DATA AND ARE PRIVATE

    private void downloadIdStacjiPomiarowych() {
        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(this.data);

            JsonToken token = parser.nextToken();
            while (token != JsonToken.END_ARRAY) {
                if (token == JsonToken.START_OBJECT) {
                    while (token != JsonToken.END_OBJECT) {
                        if (token == JsonToken.FIELD_NAME) {
                            Integer stationIdTmp;
                            String fieldName = parser.getCurrentName();
                            if (fieldName.equals("id")) {
                                token = parser.nextToken();
                                this.stationId.add(parser.getValueAsInt());
                            }
                            else {
                                token = parser.nextToken();
                            }
                        } else {
                            token = parser.nextToken();
                        }
                    }
                }
                token = parser.nextToken();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private void downloadStacjiPomiarowych() {
        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(data);
            JsonToken token = parser.nextToken();
            while (token != JsonToken.END_ARRAY) {
                if (token == JsonToken.START_OBJECT) {
                    while (token != JsonToken.END_OBJECT) {
                        if (token == JsonToken.FIELD_NAME) {
                            String fieldName = parser.getCurrentName();
                            if (fieldName.equals("stationName")) {
                                token = parser.nextToken();
                                this.stationName.add(parser.getValueAsString());
                            }
                            else {
                                token = parser.nextToken();
                            }
                        } else {
                            token = parser.nextToken();
                        }
                    }
                }
                token = parser.nextToken();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void printStacjePomiaroweOrazId() {
        for (Map.Entry<Integer, String> entry : this.stationNamesAndIds.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // GETTERS & SETTERS
    public Map<Integer, String> getStacjePomiaroweOrazId() {
        return stationNamesAndIds;
    }

}
