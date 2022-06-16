package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu

class ControleurCoupsPossibles(jeu:Jeu, grilleJeu : GrilleJeu): EventHandler<MouseEvent> {

    var jeu = jeu
    var grille = grilleJeu
    var oldPionSelected: Coordonnee? = null
    var listeRectangleVerts: MutableList<Rectangle> = mutableListOf()
    var listeStyle: MutableList<String> = mutableListOf()

    override fun handle(event: MouseEvent?) {
        //Récupère le node sur lequel on clique
        val node: Node = event!!.source as Node
        val x = GridPane.getColumnIndex(node)
        val y = GridPane.getRowIndex(node)

        //Applique le style de base à tous les
        val caseSelected = grille.grille.children[x * 4 + y]

        //Remet les styles comme à l'origine
        for (i in 0 until this.listeRectangleVerts.size) {
            this.listeRectangleVerts[i].style = this.listeStyle[i]
        }

        this.colore(y, x)

        if (this.oldPionSelected != null) {

            if (this.jeu.deplacementPossible(this.oldPionSelected!!.getX(), this.oldPionSelected!!.getY(), x, y, this.jeu.getJoueurCourant())) {
                // deplace le pion
                this.jeu.deplacer(this.oldPionSelected!!.getY(), this.oldPionSelected!!.getX(), y, x)
                println(this.jeu.getPlateau())
                this.oldPionSelected = null
            }

        }
        this.oldPionSelected = Coordonnee(x, y)
    }

        fun colore(y : Int, x : Int) {
            this.listeRectangleVerts = mutableListOf()
            this.listeStyle = mutableListOf()
            // Applique le style vert sur les cases possibles
            val coups = jeu.tousLesCoupsPossibles(y, x)
            for (valeur in coups) {
                val casePossible = grille.grille.children[valeur.getX() * 4 + valeur.getY()]
                this.listeRectangleVerts.add(casePossible as Rectangle)
                this.listeStyle.add(casePossible.style)
                casePossible.style += "-fx-fill: #339933;"
            }
        }

}