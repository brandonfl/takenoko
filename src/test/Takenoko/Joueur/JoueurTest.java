package Takenoko.Joueur;

import Takenoko.Deque.Deck;
import Takenoko.Joueur.Strategie.StrategieRandom;
import Takenoko.Plot.Plot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoueurTest {

    private final int DECK_SIZE = 100;
    private Deck dtest;
    private Joueur joueur1;
    private Joueur joueur2;

    @Before
    public void setUp() throws Exception {
        dtest = new Deck();
        joueur1 = new Joueur(1, new StrategieRandom());
        joueur2 = new Joueur(2, new StrategieRandom());

        for (int i = 0; i <= DECK_SIZE; i++){
            dtest.addFirst(new Plot(i,0));
        }
    }

    @Test
    public void getNumber(){
        assertEquals(1, joueur1.getNumber());
        assertEquals(2, joueur2.getNumber());
    }


    @Test
    public void draw(){
        //todo
    }


    @Test
    public void replaceInDeck(){
        //todo
    }

    @Test
    public void putPlot(){
        //todo
    }
}