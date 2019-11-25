package com.xode.spaceTrader.model;

import com.xode.spaceTrader.util.FixedRandom;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private Player player;
    private NPC npc;
    private String eventLog;
    private Difficulty difficulty;
    private Universe universe;
    private Integer karma;
    private static final String[] REGION_NAMES = {
        "Milky Way", "Andromeda", "Sombrero", "Whirlpool", "Black Eye", "LMC", "Cigar", "Pinwheel",
        "Tadpole", "Antennae", "Circinus", "Comet", "Hoag's", "Mice", "Subflower", "Mayall's"
    };

    public Game() {
    }

    public void startGame() {
        universe = Universe.getSingleton();
        player = new Player();
        difficulty = null;
        player.setRegion(universe.getRegions()[FixedRandom.nextInt(universe.getRegions().length)]);
        npc = null;
        eventLog = null;
        karma = 0;
    }

    public Player getPlayer() {
        return player;
    }
    public NPC getNpc() {
        return npc;
    }
    public void setNpc(NPC npc) {
        this.npc = npc;
    }
    public String getEventLog() {
        return eventLog;
    }
    public void setEventLog(String eventLog) {
        this.eventLog = eventLog;
    }
    public Integer getKarma() {
        return karma;
    }
    public void incKarma() {
        karma++;
    }
    public void decKarma() {
        karma--;
    }

    public static ArrayList<String> getRegionNames() {
        ArrayList<String> ret = new ArrayList<>();
        Collections.addAll(ret, REGION_NAMES);
        return ret;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    public Universe getUniverse() {
        return universe;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}