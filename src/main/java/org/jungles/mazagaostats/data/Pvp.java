package org.jungles.mazagaostats.data;

public class Pvp {
    String type = "pvp";
    String killer;
    String victim;

    public Pvp(String killer, String victim) {
        this.killer = killer;
        this.victim = victim;
    }
}
