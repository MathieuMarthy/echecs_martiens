package projet.echecmartien.controleur

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Stage
import projet.echecmartien.ControleurInit1J
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MainVue
import projet.echecmartien.modele.IA
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur

class ControleurFinPartie(jeu : Jeu, grilleJeu: GrilleJeu,stage: Stage, main : MainVue) {

    var jeu = jeu
    var grille = grilleJeu
    var stage = stage
    var vue = main


    fun finPartie(){
        if (jeu.statut == false){
            //pop up de victoire
            var popupVictoire: Alert = Alert(Alert.AlertType.CONFIRMATION)
            popupVictoire.title = "Résultats de la partie"
            popupVictoire.headerText = "Bravo ${jeu.joueurVainqueur()!!.getPseudo()}, vous avez gagné la partie."
            popupVictoire.contentText = "Refaire une partie ?"

            var bouton_menu : ButtonType = ButtonType("Retour au menu")
            var bouton_rejouer : ButtonType = ButtonType("Rejouer")
            popupVictoire.getButtonTypes().setAll(bouton_menu,bouton_rejouer)
            val resultat_victoire = popupVictoire.showAndWait()
            if (resultat_victoire.get() == bouton_menu){
                stage.scene.root = vue
            }
            else{
                if (jeu.joueurs[1] is IA){
                    jeu.initialiserPartie(Joueur(jeu.joueurs[0].getPseudo()), IA(jeu.joueurs[1].getPseudo(),jeu), 5)
                }else{
                    jeu.initialiserPartie(Joueur(jeu.joueurs[0].getPseudo()), Joueur(jeu.joueurs[1].getPseudo()), 5)
                }
                ControleurCoupsPossibles(jeu, grille,stage,vue).updatePlateau()
            }
        }
    }

}