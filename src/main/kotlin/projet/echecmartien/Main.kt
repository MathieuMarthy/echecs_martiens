package projet.echecmartien

import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.modele.Save

fun main() {
    var jeu: Jeu? = Jeu()
    jeu!!.initialiserPartie(Joueur("attends"), Joueur("pypi"), 10)
    jeu.changeJoueurCourant()
    jeu.deplacer(2, 2, 3, 3)
    println(jeu.getPlateau())
    val a = Save()
    a.exporter(jeu, "salut")

    jeu = null

    jeu = a.importer("salut")
    println(jeu.getPlateau())

}