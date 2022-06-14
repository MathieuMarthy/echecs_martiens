package projet.echecmartien

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.KeyCombination
import javafx.stage.Screen

import javafx.stage.Stage
import projet.echecmartien.Vue.Charger
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MainVue
import projet.echecmartien.Vue.NombreJoueurs

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        //Initialisation des variables
        val vue = MainVue()
        val grille = GrilleJeu()
        val charger =  Charger()
        var nombreJoueurs = NombreJoueurs()

        //Récupération des dimensions de l'écran & mise en plein écran de la scène
        val largeur = Screen.getPrimary().bounds.width
        val longueur = Screen.getPrimary().bounds.height
        primaryStage.isFullScreen = true

        //Création des scènes
        val sceneMenu = Scene(grille, largeur, longueur)

        //Ajout du style
        sceneMenu.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("style/style.css").toExternalForm())
        nombreJoueurs.addStyle()
        vue.addStyle()
        charger.addStyle()

        //Controleurs

        //NouvellePartie
        vue.boutonNew.onAction = EventHandler{ primaryStage.scene.root = nombreJoueurs}
        //Retour (Nouvelle partie)
        nombreJoueurs.retour.onAction = EventHandler{ primaryStage.scene.root = vue }

        //ChargerPartie
        vue.boutonLoad.onAction = EventHandler{ primaryStage.scene.root = charger }
        //Retour (Charger partie)
        charger.nouveau5.onAction = EventHandler{ primaryStage.scene.root = vue }



        //Mise en place de la scène
        primaryStage.title="Echecs Martiens"
        primaryStage.scene=sceneMenu
        primaryStage.show()
    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



