package projet.echecmartien

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MainVue
import projet.echecmartien.Vue.MenuPerso2
import projet.echecmartien.controleur.ControleurCoupsPossibles
import projet.echecmartien.modele.*


class ControleurInit2J(jeu : Jeu, menuPerso2: MenuPerso2, grilleJeu: GrilleJeu, stage: Stage, main : MainVue):EventHandler<ActionEvent>{

    var jeu = jeu
    var menuPerso2 = menuPerso2
    var grille = grilleJeu
    var stage = stage
    var vue = main
    var controleur = ControleurCoupsPossibles(jeu, grille,stage,vue)

    override fun handle(p0: ActionEvent?) {

        jeu.initialiserPartie(Joueur(menuPerso2.champ_de_saisi.text), Joueur(menuPerso2.champ_de_saisi2.text), 5)

        grille.joueur1.text = menuPerso2.champ_de_saisi.text
        grille.joueur2.text = menuPerso2.champ_de_saisi2.text

        stage.scene.root = grille
        this.controleur.updatePlateau()
    }

}