/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//file:///H:/ohtu-tehtavat/viikko1/tehtavat%2014-16/NhlStatistics1/build/reports/jacoco/test/html/ohtuesimerkki/Statistics.java.html

package ohtuesimerkki;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 *
 * @author jenni.makinen
 */
public class StatisticsTest {
    
    /*
    Testit eiv‰t saa k‰ytt‰‰ verkkoyhteytt‰.
    verkkoyhteyden tarve saadaan eliminoitua luomalla testi‰ varten rajapinnan 
    Reader-toteuttavan ìstubinî, jonka sis‰lle kovakoodataan palautettava pelaajalista
    Luodaan stubi testin sis‰lle anonyymin‰ sis‰luokkana seuraavasti:
    */
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
    
    Statistics stats;
    //ArrayList<Player> playerList;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
        
    } 
    
    @Test
    public void testStatistics() {
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Semenko", "EDM", 4, 12));
        playerList.add(new Player("Lemieux", "PIT", 45, 54));
        playerList.add(new Player("Kurri",   "EDM", 37, 53));
        playerList.add(new Player("Yzerman", "DET", 42, 56));
        playerList.add(new Player("Gretzky", "EDM", 35, 89));
        assertEquals(playerList.toString(), readerStub.getPlayers().toString());
    }

    @Test
    public void testSearch() {
        Player player1 = new Player("Semenko", "EDM", 4, 12);
        Player player2 = new Player("Lemieux", "PIT", 45, 54);
        Player player3 = new Player("Kurri",   "EDM", 37, 53);
        Player player4 = new Player("Yzerman", "DET", 42, 56);
        Player player5 = new Player("Gretzky", "EDM", 35, 89);
        assertEquals(player1.toString(), stats.search("Semenko").toString());
        assertEquals(player2.toString(), stats.search("Lemieux").toString());
        assertEquals(player3.toString(), stats.search("Kurri").toString());
        assertEquals(player4.toString(), stats.search("Yzerman").toString());
        assertEquals(player5.toString(), stats.search("Gretzky").toString());        
        assertEquals(null, stats.search("Pertti"));        
    }

    @Test
    public void testTeam() {
        ArrayList<Player> teamEDM = new ArrayList<>();   
        teamEDM.add(new Player("Semenko", "EDM", 4, 12));
        teamEDM.add(new Player("Kurri", "EDM", 37, 53));
        teamEDM.add(new Player("Gretzky", "EDM", 35, 89));
        ArrayList<Player> teamPIT = new ArrayList<>();
        teamPIT.add(new Player("Lemieux", "PIT", 45, 54));
        ArrayList<Player> teamDET = new ArrayList<>();
        teamDET.add(new Player("Yzerman", "DET", 42, 56));
        assertEquals(teamEDM.toString(), stats.team("EDM").toString());
        assertEquals(teamPIT.toString(), stats.team("PIT").toString());
        assertEquals(teamDET.toString(), stats.team("DET").toString());   
    }

    @Test
    public void testTopScorers() {
        ArrayList<Player> players0 = new ArrayList<>();
        ArrayList<Player> players1 = new ArrayList<>();
        ArrayList<Player> players2 = new ArrayList<>();
        ArrayList<Player> players3 = new ArrayList<>();
        ArrayList<Player> players4 = new ArrayList<>();
        ArrayList<Player> players5 = new ArrayList<>();
        Player player5 = new Player("Semenko", "EDM", 4, 12); //16
        Player player2 = new Player("Lemieux", "PIT", 45, 54); //99
        Player player4 = new Player("Kurri",   "EDM", 37, 53); //90
        Player player3 = new Player("Yzerman", "DET", 42, 56); //98
        Player player1 = new Player("Gretzky", "EDM", 35, 89); //124
        players1.add(player1);
        players2.add(player1); players2.add(player2);
        players3.add(player1); players3.add(player2); players3.add(player3);
        players4.add(player1); players4.add(player2); players4.add(player3); players4.add(player4);
        players5.add(player1); players5.add(player2); players5.add(player3); players5.add(player4); players5.add(player5);
        assertEquals(players0, stats.topScorers(-1));
        assertEquals(players1.toString(), stats.topScorers(0).toString());
        assertEquals(players2.toString(), stats.topScorers(1).toString());
        assertEquals(players3.toString(), stats.topScorers(2).toString());
        assertEquals(players4.toString(), stats.topScorers(3).toString());
        assertEquals(players5.toString(), stats.topScorers(4).toString());
    }
    
}
