package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.shape.Rectangle
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu

class ControleurCoupsPossibles(jeu:Jeu, grilleJeu : GrilleJeu): EventHandler<MouseEvent> {

    var jeu = jeu
    var grille = grilleJeu
    var oldPionSelected: Coordonnee? = null

    override fun handle(event: MouseEvent?) {
        //Récupère le node sur lequel on clique
        val node: Node = event!!.source as Node
        val x = GridPane.getColumnIndex(node)
        val y = GridPane.getRowIndex(node)

        //Applique le style de base à tous les
        val caseSelected = grille.grille.children[x * 4 + y]

        if (this.oldPionSelected != null) {
            if (this.jeu.deplacementPossible(this.oldPionSelected!!.getX(), this.oldPionSelected!!.getY(), x, y, this.jeu.getJoueurCourant())) {
                // deplace le pion
                this.jeu.deplacer(this.oldPionSelected!!.getX(), this.oldPionSelected!!.getY(), x, y)
                println(this.jeu.getPlateau())
                this.oldPionSelected = null
            }
        } else {
            // Applique le style vert sur les cases possibles
            val coups = jeu.tousLesCoupsPossibles(y, x)
            for (valeur in coups) {
                val casePossible = grille.grille.children[valeur.getX() * 4 + valeur.getY()]
                casePossible.style += "-fx-fill: #339933;"
            }
            this.oldPionSelected = Coordonnee(x, y)
        }

    }

}