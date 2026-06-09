package day01.associationExamples.aggregation;

import java.util.HashSet;
import java.util.Set;

public class Team {
    private String name;
    private String league;
    private Set<Player> players;
    public Team(String name, String league) {
        this.name = name;
        this.league = league;
        this.players = new HashSet<Player>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLeague() {
        return league;
    }
    public void setLeague(String league) {
        this.league = league;
    }
    public Set<Player> getPlayers() {
        return players;
    }
    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
    public void addPlayer(Player player) {
        this.players.add(player);
    }
    public void removePlayer(Player player) {
        for(Player p : this.players) {
            if(p.getName().equals(player.getName())) {
                System.out.println("Removing " + player.getName());
                this.players.remove(p);
                return;
            }
        }
        System.out.println("Player " + player.getName() + " not found");
    }
}
