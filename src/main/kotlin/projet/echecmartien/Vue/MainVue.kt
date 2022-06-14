package projet.echecmartien.Vue

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.stage.Screen
import projet.echecmartien.AppliJeuEchecMartien
import java.net.URL


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainVue: AnchorPane() {

    var label1 = Label("ECHECS MARTIENS")

    var astro = ImageView(AppliJeuEchecMartien::class.java.getResource("style/astro2.gif").toExternalForm())
    var dog = ImageView(AppliJeuEchecMartien::class.java.getResource("style/dog.gif").toExternalForm())
    var planet = ImageView(AppliJeuEchecMartien::class.java.getResource("style/planet.gif").toExternalForm())
    //var musique = Media(AppliJeuEchecMartien::class.java.getResource("").toExternalForm())



    init {
        label1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0);
        label1.alignment = Pos.BASELINE_CENTER
        label1.textFill = Color.WHITE

        AnchorPane.setLeftAnchor(label1,325.0)
        AnchorPane.setTopAnchor(label1,150.0)

        AnchorPane.setRightAnchor(planet,500.0)
        AnchorPane.setTopAnchor(planet,80.0)

        AnchorPane.setBottomAnchor(astro,0.0)
        AnchorPane.setRightAnchor(astro,0.0)
        this.children.addAll(astro,label1)


    }

    fun addStyle(){
        this.styleClass.add("fond")
        label1.styleClass.add("titre")
    }

}