package projet.echecmartien

import projet.echecmartien.modele.*
import projet.echecmartien.modele_graphique.Graphique

fun main() {
    val j = Jeu()
    val ia = IA("salut", j)
    j.initialiserPartie(Joueur("bb"), ia, 39)
    println(j.getPlateau())
    j.changeJoueurCourant()
    j.IAjoue()
    println(j.getPlateau())
}