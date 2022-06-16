package projet.echecmartien.controleur

import com.google.gson.Gson
import com.google.gson.JsonObject
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.stage.Stage
import projet.echecmartien.Vue.Charger
import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.Vue.MainVue
import projet.echecmartien.modele.Jeu
import java.io.FileReader

class ControleurStartSauvegarder(private var primaryStage: Stage, private var jeu: Jeu, private var home: MainVue, private var grille : GrilleJeu): EventHandler<ActionEvent> {

    override fun handle(p0: ActionEvent?) {
        val sauvegarder = Charger()
        sauvegarder.addStyle()
        this.primaryStage.scene.root = sauvegarder

        val listeButton = mutableListOf<Button>( sauvegarder.nouveau1, sauvegarder.nouveau2, sauvegarder.nouveau3, sauvegarder.nouveau4)

        for (i in 0 until this.jeu.sauvegardes.size) {
            val reader = FileReader(this.jeu.sauvegardes[i])
            val json = Gson().fromJson(reader, JsonObject::class.java)
            listeButton[i].text = json["title"].asString
        }

        sauvegarder.nouveau1.onAction = EventHandler { jeu.sauvegarderPartie("${jeu.joueurs[0].getPseudo()} vs ${jeu.joueurs[1].getPseudo()}", "1"); primaryStage.scene.root = home }
        sauvegarder.nouveau2.onAction = EventHandler { jeu.sauvegarderPartie("${jeu.joueurs[0].getPseudo()} vs ${jeu.joueurs[1].getPseudo()}", "2"); primaryStage.scene.root = home }
        sauvegarder.nouveau3.onAction = EventHandler { jeu.sauvegarderPartie("${jeu.joueurs[0].getPseudo()} vs ${jeu.joueurs[1].getPseudo()}", "3"); primaryStage.scene.root = home }
        sauvegarder.nouveau4.onAction = EventHandler { jeu.sauvegarderPartie("${jeu.joueurs[0].getPseudo()} vs ${jeu.joueurs[1].getPseudo()}", "4"); primaryStage.scene.root = home }
        sauvegarder.nouveau5.onAction = EventHandler { primaryStage.scene.root = home }
    }

}