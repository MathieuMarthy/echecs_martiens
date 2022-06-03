package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene

import javafx.stage.Stage
import projet.echecmartien.Vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

       var vue = MainVue()


        val scene = Scene(vue, 715.0, 415.0)
        primaryStage.title="Echecs Martiens"
        primaryStage.scene=scene
        primaryStage.show()
      

    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



