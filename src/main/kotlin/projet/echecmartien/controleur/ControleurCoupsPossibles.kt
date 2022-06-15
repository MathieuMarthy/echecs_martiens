package projet.echecmartien.controleur

import javafx.scene.layout.StackPane
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu

class ControleurCoupsPossibles(x : Int, y : Int, jeu:Jeu, grilleJeu : GrilleJeu) {

    var jeu = jeu
    var grille = grilleJeu
    var x = x
    var y = y

    fun afficherCoupsPossibles(){
        grille.creationDamier()

        var coups = jeu.tousLesCoupsPossibles(x,y)
        for (valeur in coups){
            var x = valeur.getX()
            var y = valeur.getY()
            val casePossible = grille.grille.children[y*4+x]
            if (casePossible is StackPane){
                casePossible.children[0].style += "-fx-fill: #339933;"
            }

        }
    }

}