package projet.echecmartien

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MainVue


class ControleurNew(stage : Stage, scene: Scene):EventHandler<ActionEvent>{

    var stage : Stage
    var scene : Scene

    init{
        this.stage = stage
        this.scene = scene
    }

    override fun handle(p0: ActionEvent?) {
        stage.scene = scene
        stage.isFullScreen = true
    }
}