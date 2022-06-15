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


class Charger: VBox() {

    //BOUTONS
    var nouveau1 = Button("Partie 1 : Vide")
    var nouveau2 = Button("Partie 2 : Vide")
    var nouveau3 = Button("Partie 3 : Vide")
    var nouveau4 = Button("Partie 4 : Vide")
    var nouveau5 = Button("Retour")


    init {
        //POSITIONNEMENT BOUTONS
        this.spacing = 20.0
        this.alignment = Pos.CENTER

        //DIMENSIONNAGE
        nouveau1.maxWidth = 1250.0
        nouveau2.maxWidth = 1250.0
        nouveau3.maxWidth = 1250.0
        nouveau4.maxWidth = 1250.0

        nouveau1.maxHeight = 1000.0
        nouveau2.maxHeight = 1000.0
        nouveau3.maxHeight = 1000.0
        nouveau4.maxHeight = 1000.0

        //DIMENSION BOUTON RETOUR
        nouveau5.maxWidth = 750.0

        this.children.addAll(nouveau1,nouveau2,nouveau3,nouveau4,nouveau5)


    }


    fun addStyle(){
        this.styleClass.add("fond")
        this.styleClass.add("boutons2")
    }

    fun charger(){

    }





}
