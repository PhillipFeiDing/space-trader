package com.xode.spaceTrader.presenter;

import com.xode.spaceTrader.model.Game;
import com.xode.spaceTrader.util.Calculator;
import com.xode.spaceTrader.util.Stacking;
import com.xode.spaceTrader.model.NPC;

import javax.swing.JFrame;
import java.awt.*;

public class Main {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private static JFrame frame;
    private static Welcome welcome;
    private static ConfigureSettings configureSettings;
    private static ConfirmSettings confirmSettings;
    private static CurrentRegion currentRegion;
    private static UniverseMap universeMap;
    private static RegionMarket regionMarket;
    private static GameInfo gameInfo;
    private static EventStart eventStart;
    private static EncounterBandit encounterBandit;
    private static EncounterPolice encounterPolice;
    private static EncounterTrader encounterTrader;
    private static EventOutcome eventOutcome;
    private static Shipyard shipyard;
    private static GameWin gameWin;
    private static GameOver gameOver;

    private static Game game;

    public static void main(String[] args) {
        // create views and controllers
        frame = new JFrame("Space Trader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT + Stacking.BAR_HEIGHT));
        frame.setResizable(false);
        restart();
    }

    public static void restart() {

        // create models
        game = new Game();
        game.startGame();

        welcome = new Welcome();
        configureSettings = new ConfigureSettings();
        confirmSettings = new ConfirmSettings();
        currentRegion = new CurrentRegion();
        universeMap = new UniverseMap();
        regionMarket = new RegionMarket();
        gameInfo = new GameInfo();
        eventStart = new EventStart();
        encounterBandit = new EncounterBandit();
        encounterPolice = new EncounterPolice();
        encounterTrader = new EncounterTrader();
        eventOutcome = new EventOutcome();
        shipyard = new Shipyard();
        gameWin = new GameWin();
        gameOver = new GameOver();

        // connect controllers to the root frame
        welcome.setRootFrame(frame);
        configureSettings.setRootFrame(frame);
        confirmSettings.setRootFrame(frame);
        currentRegion.setRootFrame(frame);
        universeMap.setRootFrame(frame);
        regionMarket.setRootFrame(frame);
        gameInfo.setRootFrame(frame);
        eventStart.setRootFrame(frame);
        encounterBandit.setRootFrame(frame);
        encounterPolice.setRootFrame(frame);
        encounterTrader.setRootFrame(frame);
        eventOutcome.setRootFrame(frame);
        shipyard.setRootFrame(frame);
        gameWin.setRootFrame(frame);
        gameOver.setRootFrame(frame);

        // connect controller to models
        configureSettings.setGame(game);
        confirmSettings.setGame(game);
        currentRegion.setGame(game);
        universeMap.setGame(game);
        regionMarket.setGame(game);
        gameInfo.setGame(game);
        eventStart.setGame(game);
        encounterBandit.setGame(game);
        encounterPolice.setGame(game);
        encounterTrader.setGame(game);
        eventOutcome.setGame(game);
        shipyard.setGame(game);
        NPC.setGame(game);
        Calculator.setGame(game);


        // connect controllers to other controllers
        welcome.setConfigureSettings(configureSettings);

        configureSettings.setConfirmSettings(confirmSettings);
        configureSettings.setWelcome(welcome);

        confirmSettings.setConfigureSettings(configureSettings);
        confirmSettings.setCurrentRegion(currentRegion);

        currentRegion.setUniverseMap(universeMap);
        currentRegion.setRegionMarket(regionMarket);
        currentRegion.setGameInfo(gameInfo);
        currentRegion.setShipyard(shipyard);

        regionMarket.setCurrentRegion(currentRegion);
        regionMarket.setGameWin(gameWin);

        universeMap.setCurrentRegion(currentRegion);
        universeMap.setEventStart(eventStart);

        gameInfo.setReturnPage(null);

        eventStart.setEncounterBandit(encounterBandit);
        eventStart.setEncounterPolice(encounterPolice);
        eventStart.setEncounterTrader(encounterTrader);

        encounterBandit.setGameInfo(gameInfo);
        encounterBandit.setEventOutcome(eventOutcome);
        encounterBandit.setUniverseMap(universeMap);
        encounterPolice.setGameInfo(gameInfo);
        encounterPolice.setEventOutcome(eventOutcome);
        encounterPolice.setUniverseMap(universeMap);
        encounterTrader.setEventOutcome(eventOutcome);
        encounterTrader.setUniverseMap(universeMap);

        eventOutcome.setUniverseMap(universeMap);
        eventOutcome.setGameOver(gameOver);

        shipyard.setCurrentRegion(currentRegion);

        // start with the welcome page
        frame.setContentPane(welcome.getMainContainer());
        frame.setVisible(true);
    }
}
