//Phoebe Cahill

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UrbanDictionary {

    String word;
    String definition;
    String example;


    public static UrbanDictionary fetch() throws UnirestException {

        //Declaratives
        Random rand = new Random();
        String[] wordlist = new String[2019];
        Scanner scan = null;
        UrbanDictionary word = new UrbanDictionary();

       //Name file
        try {
            scan = new Scanner(new File("src/Words.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Fill wordlist array with those from the word text file
        int i = 0;
        while(scan.hasNext()) {
            wordlist[i] = scan.nextLine();
            i++;
        }

        //random number bound to the number of words in the list
        int x = rand.nextInt(2018);

        HttpResponse<JsonNode> response =
                null;

        //API
        try {
            response = Unirest.get("https://mashape-community-urban-dictionary.p.mashape.com/define?term=" + wordlist[x])
            .header("X-Mashape-Key", "5fFnqUcYGamshso7FNdZmksEmuXHp1onXv5jsnrYRAw9zCg41m")
            .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        //See if the word from the word list exists in the urban dictionary word bank
        if (!(response.getBody().getObject().getString("result_type").equalsIgnoreCase("no_results"))) {
            //Fill UrbanDictionary object from information on the web

            word.word = response.getBody().getObject().getJSONArray("list").getJSONObject(0).getString("word");
            word.definition = response.getBody().getObject().getJSONArray("list").getJSONObject(0).getString("definition");
            word.example = response.getBody().getObject().getJSONArray("list").getJSONObject(0).getString("example");
//
//            //Print the word
//            System.out.println(word.word);


            //blank out word in the example and definition

            word.example = word.example.replaceAll(word.word.toLowerCase(), "________________");
            word.definition = word.definition.replace(word.word.toLowerCase(), "________________");


            word.word = Character.toTitleCase(word.word.charAt(0)) + word.word.substring(1);
            word.example = word.example.replaceAll(word.word, "________________");
            word.definition = word.definition.replace(word.word, "________________");

            word.example = word.example.replaceAll(word.word.toUpperCase(), "________________");
            word.definition = word.definition.replace(word.word.toUpperCase(), "________________");


//            //Print the definition and the example
//            System.out.println(word.definition);
//            System.out.println(word.example);

        }

        //If there is a word that is from the list that is not on the API, give a word, def, and example
        else if (response.getBody().getObject().getString("result_type").equalsIgnoreCase("no_results")) {
            word.word = "dog";
            word.definition = "an animal commonly domesticated by humans which walks on four legs and is an emeny of cats.";
            word.example = "A ___________ is man's best friend";

            //Print word, definition, example
            System.out.println(word.word);
            System.out.println(word.definition);
            System.out.println(word.example);
        }

        //return the UrbanDictionary object for use
        return word;
    }
}
