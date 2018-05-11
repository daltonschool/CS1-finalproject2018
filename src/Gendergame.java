//Julia Radomisli

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Random;

public class Gendergame {
    String name;
    String gender;
    int accuracy;


    public static Gendergame fetch(String name) throws UnirestException {

        Random gen = new Random();

        // These code snippets use an open-source library. http://unirest.io/java
        HttpResponse<JsonNode> response =
                Unirest.get("https://gender-api.com/get?name=" + name + "&key=gutDkjNdXvHvQerPSZ")
                .asJson();

        System.out.println(response.getBody().getObject().getString("gender"));
        System.out.println();

        Gendergame question = new Gendergame();
        question.name = response.getBody().getObject().getString("name");
        question.gender = response.getBody().getObject().getString("gender");
        question.accuracy = response.getBody().getObject().getInt("accuracy");

        System.out.println();
        return question;
    }
}