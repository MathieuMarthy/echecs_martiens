package projet.echecmartien

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MainVue
import projet.echecmartien.Vue.MenuPerso1
import projet.echecmartien.controleur.ControleurCoupsPossibles
import projet.echecmartien.modele.*


class ControleurInit1J(jeu : Jeu, menuPerso1: MenuPerso1, grilleJeu: GrilleJeu, stage: Stage, main : MainVue):EventHandler<ActionEvent>{

    var jeu = jeu
    var menuPerso1 = menuPerso1
    var grille = grilleJeu
    var stage = stage
    var vue = main
    var controleur = ControleurCoupsPossibles(jeu, grille,stage,vue)

    override fun handle(event: ActionEvent?) {
        stage.scene.root = grille
        val ia = IA("Cortana", jeu)
        jeu.initialiserPartie(Joueur(menuPerso1.champ_de_saisi.text), ia, 5)
        ControleurCoupsPossibles(this.jeu, this.grille,stage,vue).updatePlateau()

        grille.joueur2.text = this.jeu.joueurs[0].getPseudo()

        grille.joueur1.text = ia.getPseudo()
        this.controleur.updatePlateau()

    }




}