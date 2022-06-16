package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import projet.echecmartien.ControleurInit1J
import projet.echecmartien.ControleurInit2J
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MenuPerso1
import projet.echecmartien.Vue.MenuPerso2
import projet.echecmartien.Vue.NombreJoueurs
import projet.echecmartien.modele.Jeu

class ControleurLancerMenu2(private var menu: MenuPerso2, private var primaryStage: Stage, private var grille : GrilleJeu, private var jeu: Jeu, private var nombreJoueurs: NombreJoueurs): EventHandler<ActionEvent> {

    override fun handle(p0: ActionEvent?) {
        menu = MenuPerso2()
        menu.addStyle()
        primaryStage.scene.root = menu

        menu.right1.onAction = EventHandler { menu.graphique.ppSuivante(); menu.image_pp.image = menu.graphique.getPPCourante() ;grille.imagedroite.image = menu.graphique.getPPCourante()}
        menu.left1.onAction = EventHandler { menu.graphique.ppPrecedente(); menu.image_pp.image = menu.graphique.getPPCourante() ;grille.imagedroite.image = menu.graphique.getPPCourante()}

        menu.right2.onAction = EventHandler { menu.graphique2.ppSuivante(); menu.image_pp2.image = menu.graphique2.getPPCourante() ;grille.imagegauche.image = menu.graphique2.getPPCourante()}
        menu.left2.onAction = EventHandler { menu.graphique2.ppPrecedente(); menu.image_pp2.image = menu.graphique2.getPPCourante() ;grille.imagegauche.image = menu.graphique2.getPPCourante()}

        menu.boutton2.onAction = EventHandler { primaryStage.scene.root = nombreJoueurs }
        menu.boutton1.onAction = ControleurInit2J(jeu, menu, grille, primaryStage)
    }
}