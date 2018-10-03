package Takenoko.Joueur;

import Takenoko.Joueur.StrategieCoord.StrategieCoordRandom;
import Takenoko.Joueur.StrategieIrrig.StrategieIrigBase;
import Takenoko.Plateau;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoueurComparable {
    private Joueur joueur1;
    private Joueur joueur2;
    private Plateau plateau;

    @Before public void JoueurComparable(){
        plateau = new Plateau();
        joueur1 = new Joueur(1,new StrategieCoordRandom(),new StrategieIrigBase(plateau));
        joueur2 = new Joueur(2,new StrategieCoordRandom(),new StrategieIrigBase(plateau));
        joueur1.addScore1();
    }

    @Test public void comparable(){
        assertTrue(joueur1.compareTo(joueur2) > 0);
    }


    @Test public void isUpper(){
        assertTrue(joueur1.isUpper(joueur2));
    }


}
