package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.KeyCombination
import javafx.stage.Screen

import javafx.stage.Stage
import projet.echecmartien.Vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        //Initialisation des variables
        var vue = MainVue()

        //Récupération des dimensions de l'écran & mise en plein écran de la scène
        var largeur = Screen.getPrimary().bounds.width
        var longueur = Screen.getPrimary().bounds.height
        val scene = Scene(vue, largeur, longueur)
        primaryStage.isFullScreen = true

        //Ajout du style
        scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("style/style.css").toExternalForm())
        vue.addStyle()

        //Mise en place de la scène
        primaryStage.title="Echecs Martiens"
        primaryStage.scene=scene
        primaryStage.show()
    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



