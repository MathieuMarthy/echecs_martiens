package projet.echecmartien

import projet.echecmartien.modele.*

fun main() {
    var jeu: Jeu? = Jeu()
    jeu!!.initialiserPartie(Joueur("attends"), Joueur("pypi"), 10)
    jeu.changeJoueurCourant()
    jeu.deplacer(2, 2, 3, 3)
    jeu.getJoueurCourant().setPionsCaptures(mutableSetOf(PetitPion(), PetitPion(), GrandPion(), GrandPion()))
    jeu.changeJoueurCourant()
    jeu.getJoueurCourant().setPionsCaptures(mutableSetOf(MoyenPion(), MoyenPion(), MoyenPion(), MoyenPion()))
    jeu.sauvegarderPartie("salut")

    println(jeu.getPlateau())

    jeu = null

    jeu = Jeu()
    jeu.chargerPartie("salut")

}