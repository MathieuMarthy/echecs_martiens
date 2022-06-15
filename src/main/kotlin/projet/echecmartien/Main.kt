package projet.echecmartien

import projet.echecmartien.modele.*
import projet.echecmartien.modele_graphique.Graphique

fun main() {
    var j = Jeu()
    val ia = IA("salut", j)
    j.initialiserPartie(Joueur("bb"), ia, 39)
    ia.deplace()
}