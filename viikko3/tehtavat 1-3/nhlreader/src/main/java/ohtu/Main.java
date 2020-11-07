package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
         
        Arrays.sort(players, (Player m1, Player m2) -> m2.getPoints().compareTo(m1.getPoints()));
        
        for (Player player : players) {
            if(player.getNationality().equals("FIN")) {
                System.out.println(player +" "+ player.getTeam() +" "+ player.getGoals() +" + "+ player.getAssists()+" = " + player.getPoints());
            }
        }   
    }  

}