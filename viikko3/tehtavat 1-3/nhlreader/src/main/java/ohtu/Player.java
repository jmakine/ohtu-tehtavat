
package ohtu;

public class Player {
    private String name;
    private String team;
    private String goals;    
    private String assists;    
    private String penalties;    
    private String nationality;    
    private String birthdate;
    private Integer points;
    
    public Player(String name, String team, String goals, String assists, String penalties, String nationality, String birthdate){
        this.name=name;
        this.team=team;
        this.goals=goals;
        this.assists=assists;
        this.penalties=penalties;
        this.nationality=nationality;
        this.birthdate=birthdate;
        this.points = Integer.parseInt(goals)+Integer.parseInt(assists);
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public String getTeam() {
        return team;
    }
    
    public void setGoals(String goals) {
        this.goals = goals;
    }
    
    public String getGoals() {
        return goals;
    }
    
    public void setAssists(String assists) {
        this.assists = assists;
    }
    
    public String getAssists() {
        return assists;
    }
    
    public void setPenalties(String penalties) {
        this.penalties = penalties;
    }
    
    public String getPenalties() {
        return penalties;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    
    public String getBirthdate() {
        return birthdate;
    }
    
    public Integer getPoints(){
        return Integer.parseInt(goals) + Integer.parseInt(assists);
    }
    
    public void setPoints(Integer points){
        this.points = points;
    }
    
    @Override
    public String toString() {
        return name;
    }
      
}
