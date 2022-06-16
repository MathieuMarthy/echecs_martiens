package projet.echecmartien.controleur

import com.google.gson.Gson
import com.google.gson.JsonObject
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.stage.Stage
import projet.echecmartien.ControleurInit2JBis
import projet.echecmartien.Vue.Charger
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MainVue
import projet.echecmartien.modele.Jeu
import java.io.FileReader

class ControleurStartCharger(private var primaryStage: Stage , private var jeu: Jeu, private var home: MainVue, private var grille : GrilleJeu): EventHandler<ActionEvent> {

    override fun handle(p0: ActionEvent?) {
        val charger = Charger()
        charger.addStyle()
        this.primaryStage.scene.root = charger

        val listeButton = mutableListOf<Button>( charger.nouveau1, charger.nouveau2, charger.nouveau3, charger.nouveau4)

        for (i in 0 until this.jeu.sauvegardes.size) {
            val reader = FileReader(this.jeu.sauvegardes[i])
            val json = Gson().fromJson(reader, JsonObject::class.java)
            listeButton[i].text = json["title"].asString
            println(json["title"].asString)
        }

        charger.nouveau1.onAction = ControleurInit2JBis(jeu, grille, primaryStage, "1")
        charger.nouveau2.onAction = ControleurInit2JBis(jeu, grille, primaryStage, "2")
        charger.nouveau3.onAction = ControleurInit2JBis(jeu, grille, primaryStage,"3")
        charger.nouveau4.onAction = ControleurInit2JBis(jeu, grille, primaryStage, "4")
        charger.nouveau5.onAction = EventHandler { primaryStage.scene.root = home }
    }
}
