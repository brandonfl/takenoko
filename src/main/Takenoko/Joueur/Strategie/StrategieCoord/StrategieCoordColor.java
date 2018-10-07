package Takenoko.Joueur.Strategie.StrategieCoord;

import Takenoko.Plateau;
import Takenoko.Plot.CoordAxial;
import Takenoko.Plot.Plot;
import Takenoko.Util.Comparators.ComparateurPosColorAdj;

import java.util.Collections;
import java.util.List;

/**
 * La stratégie Couleur consiste à placer les parcelles en maximisant le nombre de voisins de la même couleur qui la parcelle
 */
public class StrategieCoordColor implements StrategieCoord {

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

    public CoordAxial getCoord(Plateau plateau) {
        return null;

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

    public List<CoordAxial> getCoords(Plateau p) {
        return null;
    }



    @Override
    public String getStrategieLabel() {
        return "Max Color Adj";
    }

}