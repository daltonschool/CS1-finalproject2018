//Grace Abrahams


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lattelib.WebLatte;

import java.util.Random;
public class LoveCalculator {
    String percentage;
    String result;


        public static LoveCalculator fetch(String name1, String name2) throws UnirestException {
            Random gen = new Random();
            HttpResponse<JsonNode> response =
                    Unirest.get("https://love-calculator.p.mashape.com/getPercentage?fname=" +name1 + "&sname=" + name2)
                    .header("X-Mashape-Key", "Ay5CTotNplmsh6aqby7CwqfcKGGrp1jcXQWjsnYngv7FLBh14j")
                    .header("Accept", "application/json")
                    .asJson();
           LoveCalculator pregunta = new LoveCalculator();
            System.out.println(response.getBody().getObject()); //.getString("result")
            pregunta.result = response.getBody().getObject().getString("result");
            pregunta.percentage = response.getBody().getObject().getString("percentage");
//            pregunta.percentage = response.getBody().getArray().getJSONObject(0).getJSONObject("percentage").getString("name");
//            pregunta.result = response.getBody().getArray().getJSONObject(0).get("result").toString();


            return pregunta;


    }
}

