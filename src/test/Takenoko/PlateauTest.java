package Takenoko;

import Takenoko.Irrigation.CoordIrrig;
import Takenoko.Irrigation.Orient;
import Takenoko.Joueur.Joueur;
import Takenoko.Joueur.Strategie.StrategieConcrete;
import Takenoko.Joueur.Strategie.StrategieCoord.StrategieCoordRandom;
import Takenoko.Joueur.Strategie.StrategieIrrig.StrategieIrrigBase;
import Takenoko.Plot.CoordAxial;
import Takenoko.Plot.Plot;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        Joueur bot = new Joueur(1, new StrategieConcrete(new StrategieCoordRandom(),new StrategieIrrigBase(plat)));
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
}
