package Takenoko.Joueur.Strategie;
import Takenoko.Irrigation.CoordIrrig;
import Takenoko.Irrigation.Orient;
import Takenoko.Plateau;
import Takenoko.Plot.CoordAxial;
import Takenoko.Plot.Plot;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StrategieColor implements Strategie{

    /**
     * Renvoie la première coordonnée de la position légale la plus rentable
     * pour la stratégie couleur
     * @param plateau
     * @param plot
     * @return
     */
    public CoordAxial getCoord(Plateau plateau, Plot plot) {

        List<CoordAxial> listOfPos = getCoords(plateau, plot);
        return listOfPos.get(0);

    }

    /**
     * Renvoie la liste trier par nombre d'adjacence de même couleur que
     * le plot que le robot veut poser, des positions légales de poses.
     * @param p
     * @param plot
     * @return
     */
    public List<CoordAxial> getCoords(Plateau p, Plot plot) {
        List<CoordAxial> legPos = p.legalPositions();

        ComparateurPosColorAdj test = new ComparateurPosColorAdj(p, plot.getCouleur());

        legPos.sort(test);
        Collections.reverse(legPos);

        return legPos;
    }

    public Optional<CoordIrrig> getIrrig(Plateau plateau) {
        var res = plateau.legalIrrigPositions();
        if (res.size() >= 1) {
            return Optional.of(res.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String getStrategieLabel() {
        return "Max Color Adj";
    }


    public StrategieColor() {
    }
}