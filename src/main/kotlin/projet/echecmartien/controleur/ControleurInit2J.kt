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
import projet.echecmartien.Vue.MenuPerso2
import projet.echecmartien.controleur.ControleurCoupsPossibles
import projet.echecmartien.modele.*


class ControleurInit2J(jeu : Jeu, menuPerso2: MenuPerso2, grilleJeu: GrilleJeu, scene: Scene, stage: Stage):EventHandler<ActionEvent>{

    var jeu = jeu
    var menuPerso2 = menuPerso2
    var grille = grilleJeu
    var stage = stage
    var controleur = ControleurCoupsPossibles(jeu,grille)

    override fun handle(p0: ActionEvent?) {

        jeu.initialiserPartie(joueur1 = Joueur(menuPerso2.champ_de_saisi.text), IA("Cortana", jeu), 5)

        this.grille.imagedroite = this.menuPerso2.image_pp2

        stage.scene.root = grille
    }

}