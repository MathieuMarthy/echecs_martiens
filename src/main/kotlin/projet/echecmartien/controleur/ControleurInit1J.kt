package projet.echecmartien

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MenuPerso1
import projet.echecmartien.controleur.ControleurCoupsPossibles
import projet.echecmartien.modele.*


class ControleurInit1J(jeu : Jeu,menuPerso1: MenuPerso1, grilleJeu: GrilleJeu, stage: Stage):EventHandler<ActionEvent>{

    var jeu = jeu
    var menuPerso1 = menuPerso1
    var grille = grilleJeu
    var stage = stage
    var controleur = ControleurCoupsPossibles(jeu,grille)

    override fun handle(p0: ActionEvent?) {
        stage.scene.root = grille
        jeu.initialiserPartie(joueur1 = Joueur(menuPerso1.champ_de_saisi.text), IA("Cortana", jeu), 5)
        ControleurCoupsPossibles(this.jeu, this.grille).updatePlateau()
    }




}