package projet.echecmartien

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MenuPerso2
import projet.echecmartien.controleur.ControleurCoupsPossibles
import projet.echecmartien.modele.*


class ControleurInit2JBis(jeu : Jeu, grilleJeu: GrilleJeu, stage: Stage, numeroSauvegarde: String): EventHandler<ActionEvent> {

    var jeu = jeu
    var grille = grilleJeu
    var stage = stage
    var controleur = ControleurCoupsPossibles(jeu, grille)
    var numeroSauvegarde = numeroSauvegarde

    override fun handle(p0: ActionEvent?) {
        this.jeu.chargerPartie(numeroSauvegarde)

        stage.scene.root = grille
        this.controleur.updatePlateau()
    }

}