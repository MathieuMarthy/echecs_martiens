package projet.echecmartien.Vue


import javafx.scene.layout.VBox
import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color

import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import projet.echecmartien.AppliJeuEchecMartien


class NombreJoueurs: VBox() {

    //BOUTONS
    var joueur1 = Button("1 joueur")
    var joueur2 = Button("2 joueurs")
    var retour = Button("Retour")

    init {
        //POSITIONNEMENT BOUTONS
        this.spacing = 20.0
        this.alignment = Pos.CENTER

        //DIMENSIONNAGE
        joueur1.maxWidth = 1250.0
        joueur2.maxWidth = 1250.0

        joueur2.maxHeight = 1000.0
        joueur1.maxHeight = 1000.0

        retour.maxWidth = 750.0

        this.children.addAll(joueur1,joueur2,retour)
    }

    fun addStyle(){
        this.styleClass.add("fond")
        this.styleClass.add("boutons2")
    }
}
