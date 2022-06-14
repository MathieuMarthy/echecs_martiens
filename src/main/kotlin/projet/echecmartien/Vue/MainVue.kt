package projet.echecmartien.Vue

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.stage.Screen
import projet.echecmartien.AppliJeuEchecMartien
import java.net.URL

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainVue: AnchorPane() {

    //TITRE
    private var label1 = Label("ECHECS MARTIENS")

    //IMAGES
    private var astro = ImageView(AppliJeuEchecMartien::class.java.getResource("style/astro2.gif").toExternalForm())
    private var dog = ImageView(AppliJeuEchecMartien::class.java.getResource("style/dog.gif").toExternalForm())
    private var planet = ImageView(AppliJeuEchecMartien::class.java.getResource("style/planet.gif").toExternalForm())

    //BOUTONS
    private var boutonNew = Button("Nouvelle partie")
    private var boutonLoad = Button("Charger partie")
    private var boutonRules = Button("Règles du jeu")
    private var boutons = VBox()

    init {

        //TITRE
        label1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0);
        label1.alignment = Pos.BASELINE_CENTER
        label1.textFill = Color.WHITE

        //BOUTONS
        boutons.spacing = 20.0
        boutons.alignment = Pos.CENTER
        boutonNew.maxWidth = Double.MAX_VALUE
        boutonLoad.maxWidth = Double.MAX_VALUE
        boutonRules.maxWidth = Double.MAX_VALUE

        //PLACEMENT DES ELEMENTS
        setLeftAnchor(label1,325.0)
        setTopAnchor(label1,150.0)

        setRightAnchor(planet,500.0)
        setTopAnchor(planet,80.0)

        setBottomAnchor(astro,0.0)
        setRightAnchor(astro,0.0)

        setLeftAnchor(boutons,390.0)
        setTopAnchor(boutons,400.0)

        this.boutons.children.addAll(boutonNew,boutonLoad,boutonRules)
        this.children.addAll(astro,label1,boutons)
    }

    fun addStyle(){
        this.styleClass.add("fond")
        label1.styleClass.add("titre")
        this.boutons.styleClass.add("boutons")
    }

}