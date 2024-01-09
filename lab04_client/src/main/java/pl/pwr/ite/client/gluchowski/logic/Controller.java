package pl.pwr.ite.client.gluchowski.logic;

import java.util.ArrayList;
import java.util.Map;

public class Controller {
    String urlFindAll = "https://api.gios.gov.pl/pjp-api/rest/station/findAll";
    String utlGetAllStanowiska = "https://api.gios.gov.pl/pjp-api/rest/station/sensors/"; // + id
    Communication communication = null;
    DataParser dataParser = null;
    public Controller() {
        this.communication = new Communication();
        String tmp = this.communication.getResponse("https://api.gios.gov.pl/pjp-api/rest/station/findAll");
//        System.out.println(tmp);
        this.dataParser = new DataParser(tmp);

    }

    public void run() {
        /*
            TODO: FORMATOWANIE DANYCH NA BIERZĄCO I ZROBIENIE UI. Reszta działą na razie xd.
        */

        this.dataParser.downloadStationIdAndName();
    }

    public void downloadStanowiskaPomiarowe(Integer id) {
        String tmpUrl = "https://api.gios.gov.pl/pjp-api/rest/station/sensors/" + Integer.toString(id);
        String tmp = this.communication.getResponse(tmpUrl);
        DataParser tmpDataParser = new DataParser(tmp);
    }

    public ArrayList<String> getStanowiskaPomiarowe() {
        ArrayList<String> tmp = new ArrayList<>();

        return tmp;
    }

    public ArrayList<String> getStacjePomiarowe() {
        ArrayList<String> tmp = new ArrayList<>();
        Map<Integer, String> tmpMap = this.dataParser.getStacjePomiaroweOrazId();

        for (Map.Entry<Integer, String> entry : tmpMap.entrySet()) {
            tmp.add(entry.getKey() + "\t" + entry.getValue());
        }

        return tmp;
    }
}
