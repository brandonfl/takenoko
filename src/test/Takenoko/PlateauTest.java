package Takenoko;

import Takenoko.Irrigation.CoordIrrig;
import Takenoko.Irrigation.Orient;
import Takenoko.Joueur.Joueur;
import Takenoko.Joueur.Strategie.StrategieCoord.StrategieCoordRandom;
import Takenoko.Joueur.Strategie.StrategieIrrig.StrategieIrrigBase;
import Takenoko.Joueur.Strategie.StrategieSansPions;
import Takenoko.Plot.CoordAxial;
import Takenoko.Plot.Plot;
import Takenoko.Properties.Couleur;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlateauTest {

    @Test
    public void getputPlot() {
        Plateau plat = new Plateau();
        Plot parc = new Plot(3, 5);
        CoordAxial coo = new CoordAxial(3, 5);
        plat.putPlot(parc, coo);
        assertEquals(parc, plat.getPlot(3, 5));
    }

    @Test
    public void newputPlot() {
        Plateau plat = new Plateau();
        Plot parc = new Plot();
        Joueur bot = new Joueur(1, new StrategieSansPions(new StrategieCoordRandom(),new StrategieIrrigBase(plat)));
        bot.setPlot(parc);
        CoordAxial coo = bot.putPlot(parc, plat);
        assertEquals(coo.getQ(), parc.getq());
        assertEquals(coo.getR(), parc.getr());
    }

    @Test public void irrigationInit(){
        Plateau plateau = new Plateau();
        assertEquals(6,plateau.getIrrigations().size());
    }

    @Test
    public void getPlotsFromIrig() {
        Plateau p = new Plateau();

        List<Plot> listPlots = p.getPlotsFromIrig(new CoordIrrig(0,0,Orient.N));

        assertEquals(1, listPlots.size());

        assertTrue(listPlots.contains(p.getPlot(0,0)));

        p.putPlot(new Plot(new CoordAxial(0,-1)));

        listPlots = p.getPlotsFromIrig(new CoordIrrig(0,0,Orient.N));
        assertEquals(2, listPlots.size());
        assertTrue(listPlots.contains(p.getPlot(0,0)));
        assertTrue(listPlots.contains(p.getPlot(0,-1)));


        listPlots = p.getPlotsFromIrig(new CoordIrrig(50,50,Orient.W));

        assertTrue(listPlots.isEmpty());
    }

    @Test
    public void movePanda() {
        Plateau p = new Plateau();

        CoordAxial coordTest = new CoordAxial(1,-1);

        // Pas de parcelle à cet emplacement
        assertEquals(Couleur.BLEU, p.movePanda(coordTest));


        p.putPlot(new Plot(Couleur.VERT), coordTest);
        assertFalse(p.getPlot(coordTest).haveBambou());
        p.getPlot(coordTest).setWater(true);
        p.getPlot(coordTest).pousserBambou();
        assertTrue(p.getPlot(coordTest).haveBambou());
        assertEquals(Couleur.VERT, p.movePanda(coordTest));
        assertFalse(p.getPlot(coordTest).haveBambou());

        // Tuile qui n'est pas en "ligne" avec la position du panda
        p.putPlot(new Plot(new CoordAxial(10,-12)));

        assertEquals(Couleur.BLEU, p.movePanda(new CoordAxial(11,-12)));

        // Tuile qui est en ligne avec le panda, mais avec du vide
        p.putPlot(new Plot(new CoordAxial(3,-3)));

        assertEquals(Couleur.BLEU, p.movePanda(new CoordAxial(11,-12)));

    }

    @Test
    public void moveJardinier() {
        Plateau p = new Plateau();

        assertFalse(p.moveJardinier(new CoordAxial(1,1)));

        p.putPlot(new Plot(), 1,1);

        assertFalse(p.moveJardinier(new CoordAxial(1,1)));
        p.putPlot(new Plot(), 1,0);
        assertTrue(p.moveJardinier(new CoordAxial(1,0)));

        p.putPlot(new Plot(), 1,1);

    }

    @Test
    public void isMotifInAll(){
        Plateau p = new Plateau();
        p.putPlot(new Plot(Couleur.VERT), 0, 1);
        p.getPlot(0,1).pousserBambou();
        p.getPlot(0,1).pousserBambou();
        p.getPlot(0,1).pousserBambou();
        assertFalse(p.isMotifInAll(Couleur.VERT, 1));
        p.getPlot(0,1).pousserBambou();
        assertTrue(p.isMotifInAll(Couleur.VERT, 1));

        p.putPlot(new Plot(Couleur.JAUNE), 1, 1);
        p.getPlot(1,1).setWater(true);
        p.getPlot(1,1).pousserBambou();
        p.getPlot(1,1).pousserBambou();
        p.getPlot(1,1).pousserBambou();

        p.putPlot(new Plot(Couleur.JAUNE), -1, 1);
        p.getPlot(-1,1).setWater(true);
        p.getPlot(-1,1).pousserBambou();

        assertFalse(p.isMotifInAll(Couleur.JAUNE, 2));
        p.getPlot(-1,1).pousserBambou();
        p.getPlot(-1,1).pousserBambou();

        assertTrue(p.isMotifInAll(Couleur.JAUNE, 2));

        p.putPlot(new Plot(Couleur.JAUNE), 1, 2);
        p.getPlot(1,2).setWater(true);
        p.getPlot(1,2).pousserBambou();
        p.getPlot(1,2).pousserBambou();
        p.getPlot(1,2).pousserBambou();

        assertTrue(p.isMotifInAll(Couleur.JAUNE, 3));


        p.putPlot(new Plot(Couleur.JAUNE), 1, 3);
        p.getPlot(1,3).setWater(true);
        p.getPlot(1,3).pousserBambou();
        p.getPlot(1,3).pousserBambou();
        assertFalse(p.isMotifInAll(Couleur.JAUNE, 4));
        p.getPlot(1,3).pousserBambou();

        assertTrue(p.isMotifInAll(Couleur.JAUNE, 4));

        assertFalse(p.isMotifInAll(Couleur.JAUNE, 6));

    }
}
