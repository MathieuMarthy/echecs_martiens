package projet.echecmartien

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.shape.Circle
import javafx.stage.Stage
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MenuPerso1
import projet.echecmartien.controleur.ControleurCoupsPossibles
import projet.echecmartien.modele.*


class ControleurInit1J(jeu : Jeu,menuPerso1: MenuPerso1, grilleJeu: GrilleJeu, scene: Scene, stage: Stage):EventHandler<ActionEvent>{

    var jeu = jeu
    var menuPerso1 = menuPerso1
    var grille = grilleJeu
    var stage = stage

    override fun handle(p0: ActionEvent?) {
        stage.scene.root = grille
        jeu.initialiserPartie(joueur1 = Joueur(menuPerso1.champ_de_saisi.text), IA("Cortana", jeu), 5)

        var plato = jeu.getPlateau().getCases()

        for ((i, ligne) in plato.withIndex()) {
            for ((j, case) in ligne.withIndex()) {

                val cercle = when (case.getPion()) {
                    is PetitPion -> Circle(30.0)
                    is MoyenPion -> Circle(40.0)
                    is GrandPion -> Circle(50.0)
                    else -> null
                }
                if (cercle != null){
                    cercle.onMouseClicked = EventHandler { ControleurCoupsPossibles(j,i,jeu,grille).afficherCoupsPossibles() }
                    grille.grille.add(cercle,j,i)
                    GridPane.setHalignment(cercle, HPos.CENTER)
                }
            }
        }
    }

}