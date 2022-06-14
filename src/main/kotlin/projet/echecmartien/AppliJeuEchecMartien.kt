package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.KeyCombination
import javafx.stage.Screen

import javafx.stage.Stage
import projet.echecmartien.Vue.Charger
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        //Initialisation des variables
        val vue = MainVue()
        val grille = GrilleJeu()
        val charger =  Charger()

        //Récupération des dimensions de l'écran & mise en plein écran de la scène
        val largeur = Screen.getPrimary().bounds.width
        val longueur = Screen.getPrimary().bounds.height
        val scene = Scene(charger, largeur, longueur)
        primaryStage.isFullScreen = true

        //Ajout du style
        scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("style/style.css").toExternalForm())
        charger.addStyle()

        //Mise en place de la scène
        primaryStage.title="Echecs Martiens"
        primaryStage.scene=scene
        primaryStage.show()
    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



