package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
        /*  
        Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );
                
        Matcher m = new And( 
                            new Not( new HasAtLeast(1, "goals") ), 
                            new PlaysIn("NYR")
        );
        
        Matcher m = new And( 
                            new HasFewerThan(1, "goals"), 
                            new PlaysIn("NYR")
        );
        
        System.out.println(stats.matches(new All()).size());
        
        Matcher m = new Or( new HasAtLeast(40, "goals"),
                            new HasAtLeast(60, "assists")
        ); 
        
        Matcher m = new And(
                            new HasAtLeast(50, "points"),
                            new Or( 
                                new PlaysIn("NYR"),
                                new PlaysIn("NYI"),
                                new PlaysIn("BOS")
                            )
        );
        */
        
        QueryBuilder query = new QueryBuilder();
 
        //Matcher m = query.playsIn("NYR").build();
        //Matcher m = query.build();
        
        Matcher m = query.playsIn("NYR")
                         .hasAtLeast(5, "goals")
                         .hasFewerThan(10, "goals").build();
        
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        
        System.out.println(stats.matches(m).size());
    }
}
