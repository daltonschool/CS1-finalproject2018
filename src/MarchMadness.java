//Marshall Miller

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Random;

public class MarchMadness {
    int seed;
    String team;
    String college;
    String conference;
    int score;
    String location;
    String label;


    public static MarchMadness fetch() throws UnirestException {

        Random gen = new Random();

        // These code snippets use an open-source library. http://unirest.io/java
        HttpResponse<JsonNode> response = Unirest.get("https://community-march-madness-v1.p.mashape.com/games")
                .header("X-Mashape-Key", "Kq92bLcyJxmshNxBxjzVuLG62VsLp1ns3Efjsnrq0w11CI4RSN")
                .asJson();

        MarchMadness bracket = new MarchMadness();
        bracket.seed = response.getBody().getArray().getJSONObject(0).getJSONObject("seed").getInt("");
        bracket.team = response.getBody().getArray().getJSONObject(0).get("team").toString();
        bracket.college = response.getBody().getArray().getJSONObject(0).get("college").toString();
        bracket.conference = response.getBody().getArray().getJSONObject(0).get("conference").toString();
        bracket.score = response.getBody().getArray().getJSONObject(0).getJSONObject("score").getInt("");
        bracket.location = response.getBody().getArray().getJSONObject(0).get("location").toString();
        bracket.label = response.getBody().getArray().getJSONObject(0).get("label").toString();

        return bracket;
    }
}
