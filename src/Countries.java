import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Random;

public class Countries {
    String name;
    String capital;
    String region;

    static Countries fetch() throws UnirestException {
        Random gen = new Random();
        // These code snippets use an open-source library. http://unirest.io/java
        HttpResponse<JsonNode> response = Unirest.get("https://ajayakv-rest-countries-v1.p.mashape.com/rest/v1/all")
                .header("X-Mashape-Key", "7VvWMlHBbJmsheFwoSuamkgveI5Up1faL1ajsn48nOMbYXvhRS")
                .asJson();

        //pick a random number
        int x = gen.nextInt(200);
        //use that here:
        JSONObject myobject = response.getBody().getArray().getJSONObject(x);
        Countries country = new Countries();

        country.name = myobject.getString("nativeName");
        country.capital = myobject.getString("capital");
        country.region = myobject.getString("subregion");

        return country;

    }
}
