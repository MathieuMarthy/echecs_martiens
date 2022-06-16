package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import projet.echecmartien.ControleurInit1J
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MenuPerso1
import projet.echecmartien.Vue.NombreJoueurs
import projet.echecmartien.modele.Jeu

class ControleurLancerMenu1(private var menu: MenuPerso1, private var primaryStage: Stage, private var grille : GrilleJeu, private var jeu: Jeu, private var nombreJoueurs: NombreJoueurs): EventHandler<ActionEvent> {

    override fun handle(p0: ActionEvent?) {
        menu = MenuPerso1()
        menu.addStyle()
        primaryStage.scene.root = menu

        menu.right1.onAction = EventHandler { menu.graphique.ppSuivante(); menu.image_pp.image = menu.graphique.getPPCourante() ;grille.imagegauche.image = menu.graphique.getPPCourante()}
        menu.left1.onAction = EventHandler { menu.graphique.ppPrecedente(); menu.image_pp.image = menu.graphique.getPPCourante() ;grille.imagegauche.image = menu.graphique.getPPCourante()}

        menu.boutton2.onAction = EventHandler { primaryStage.scene.root = nombreJoueurs }
        menu.boutton1.onAction = ControleurInit1J(jeu, menu, grille, primaryStage)
    }
}