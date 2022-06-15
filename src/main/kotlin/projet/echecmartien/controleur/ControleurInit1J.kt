package projet.echecmartien

import javafx.event.ActionEvent
import javafx.event.EventHandler
import projet.echecmartien.Vue.MenuPerso1
import projet.echecmartien.modele.IA
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur


class ControleurInit1J(jeu : Jeu,menuPerso1: MenuPerso1):EventHandler<ActionEvent>{

    var jeu = jeu
    var menuPerso1 = menuPerso1

    override fun handle(p0: ActionEvent?) {
        jeu.initialiserPartie(joueur1 = Joueur(menuPerso1.champ_de_saisi.text),IA())

    }
}