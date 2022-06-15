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
    jeu.sauvegarderPartie("1")

    var plateau = Plateau()
    plateau.initialiser()


    jeu = null

    jeu = Jeu()
    jeu.chargerPartie("1")
    println(jeu.getPlateau())
}