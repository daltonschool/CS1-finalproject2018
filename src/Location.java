//Isabel Schoeman

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Location {
    String longitude;
    String latitude;
    String country;


    public static Location fetch() throws FileNotFoundException {
        Location secretPlace = new Location();

        File f = new File ("CountriesList");
        Scanner scan = new Scanner(f);
        Random rand = new Random ();
        String[][] countries = new String[244][4];
        int i = 0;
        while(scan.hasNext()){
            String a = scan.nextLine();
            String[] place = a.split(",\\s+");
            System.out.println(Arrays.toString(place));
            countries[i][0]=place[0];
            countries[i][1]=place[1];
            countries[i][2]=place[2];
            countries[i][3]=place[3];
            i++;
        }

        int randomPlace = rand.nextInt(244);


        secretPlace.country = countries[randomPlace][3];
        secretPlace.longitude = countries[randomPlace][2] ;
        secretPlace.latitude = countries[randomPlace][1];


        return secretPlace;
    }

}


