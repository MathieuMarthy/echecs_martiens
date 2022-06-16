
package projet.echecmartien.Vue;

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.control.Button
import javafx.scene.control.ColorPicker
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.stage.FileChooser
import projet.echecmartien.modele_graphique.Graphique
import projet.echecmartien.modele_graphique.Graphique2


public class MenuPerso2: GridPane() {

    var g1 = GridPane()
    var g2 = GridPane()



    var titre: Label = Label("                                   Menu Personnalisation")
    var pseudo1: Label = Label("Pseudo")

    var labelJoueur1: Label = Label("Joueur 1")
    var labelJoueur2: Label = Label("Joueur 2")
    var champ_de_saisi: TextField = TextField("")
    var champ_de_saisi2: TextField = TextField("")
    var photo_de_profil1: Label = Label("Photo de profil")

    var cercle : Circle = Circle(100.0)
    var cercle2 : Circle = Circle(100.0)

    //var couleurs1: Label = Label("Couleurs")
    //var couleurs2: Label = Label("Couleurs")
    //var choix_de_couleur1:  ColorPicker = ColorPicker()
    //var choix_de_couleur2:  ColorPicker = ColorPicker()

    var boutton2 = Button("Retour")
    var boutton1 = Button("Lancer")


    var right1 = Button(">")
    var right2 = Button(">")

    var left1 = Button("<")
    var left2 = Button("<")

    val fileChooser = FileChooser()
    var testlabel = Label("")

    var graphique = Graphique()
    var graphique2 = Graphique2()

    var image_pp: ImageView = ImageView(graphique.getPPCourante())
    var image_pp2: ImageView = ImageView(graphique2.getPPCourante())







    init {

        titre.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10.0)
        pseudo1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)

        labelJoueur1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 5.0)
        labelJoueur2.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        champ_de_saisi.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        photo_de_profil1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)
        //couleurs1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)
        //couleurs2.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)
        champ_de_saisi.setMaxWidth(300.0);
        champ_de_saisi.setStyle("-fx-text-fill: white ; -fx-font-size: 25;-fx-padding: 1,1,1,1;-fx-border-color: #A149FA;-fx-border-width: 2;-fx-border-radius: 1;-fx-border: gone;-fx-background-color: #1687ab;");
        champ_de_saisi.promptText = "Entrez votre Pseudo"

        champ_de_saisi2.setMaxWidth(300.0);
        champ_de_saisi2.setStyle("-fx-text-fill: white ; -fx-font-size: 25;-fx-padding: 1,1,1,1;-fx-border-color: #A149FA;-fx-border-width: 2;-fx-border-radius: 1;-fx-border: gone;-fx-background-color: #1687ab;");
        champ_de_saisi2.promptText = "Entrez votre Pseudo"




        //cercle.fill = Color.WHITE
        //choix_de_couleur1.onAction = EventHandler{cercle.fill = choix_de_couleur1.value}
        //choix_de_couleur1.onAction = EventHandler{cercle.stroke = choix_de_couleur1.value}

        // var test = ImageView(Image("C:\\Users\\tomas\\IdeaProjects\\eq_2_07_marthy-mathieu_martineau-tomas_nicou-fabien_vandemeulebroucke-bertin-nolan\\target\\classes\\projet\\echecmartien\\pp\\3_yeux_violets.png"))


        //PP joueur1
        cercle.centerX = image_pp.fitHeight+150
        cercle.centerY = image_pp.fitWidth+150
        image_pp.fitHeight = 300.0
        image_pp.fitWidth = 300.0


        image_pp.clip = cercle

        //PP joueur2

        cercle2.centerX = image_pp2.fitHeight+150
        cercle2.centerY = image_pp2.fitWidth+150
        image_pp2.fitHeight = 300.0
        image_pp2.fitWidth = 300.0


        image_pp2.clip = cercle2



        g1.padding = Insets(0.0,0.0,0.0,120.0)
        g1.add(left1,0,0)
        g1.add(image_pp,1,0)
        g1.add(right1,2,0)

        g2.padding = Insets(0.0,0.0,0.0,120.0)
        g2.add(left2,0,0)
        g2.add(image_pp2,1,0)
        g2.add(right2,2,0)












        /*choix_de_couleur1.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))
        choix_de_couleur2.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))
        champ_de_saisi1.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))
        champ_de_saisi2.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))*/


        val col0 = ColumnConstraints()
        col0.halignment = HPos.CENTER
        col0.percentWidth = 25.0

        val ligne0 = RowConstraints()
        ligne0.valignment = VPos.CENTER
        ligne0.percentHeight = 15.0

        val col1 = ColumnConstraints()
        col1.halignment = HPos.CENTER
        col1.percentWidth = 50.0

        val ligne1 = RowConstraints()
        ligne1.valignment = VPos.CENTER
        ligne1.percentHeight = 20.0

        val ligne3 = RowConstraints()
        ligne1.valignment = VPos.CENTER
        ligne1.percentHeight = 20.0

        val col2 = ColumnConstraints()
        col2.halignment = HPos.CENTER
        col2.percentWidth = 50.0

        val ligne2 = RowConstraints()
        ligne2.valignment = VPos.BASELINE
        ligne2.percentHeight = 40.0

        val ligne4 = RowConstraints()
        ligne2.valignment = VPos.BASELINE
        ligne2.percentHeight = 25.0







        this.columnConstraints.addAll(col0, col1, col2)
        this.rowConstraints.addAll(ligne0, ligne1, ligne2, ligne3,ligne4)

        //choix_de_couleur1.prefWidth = 300.0



        this.add(titre, 1, 0)
        this.add(pseudo1, 1, 1)


        this.add(labelJoueur1, 0, 2)
        this.add(labelJoueur2, 0,3)

        this.add(champ_de_saisi, 1, 2)
        this.add(champ_de_saisi2, 1, 3)

        this.add(photo_de_profil1, 2, 1)

        this.add(g1, 2, 2)
        this.add(g2, 2,3)


        //this.add(couleurs1, 3, 1)
        //this.add(couleurs2, 3, 1)


        this.add(boutton1, 1,4)
        this.add(boutton2, 2,4)


        this.titre.padding = Insets(0.0, 0.0, 50.0, 0.0)

    }
    fun addStyle(){
        //choix_de_couleur1.styleClass.add("")
        //choix_de_couleur2.styleClass.add("")

        this.styleClass.add("textfield")
        this.styleClass.add("boutons4")
        this.styleClass.add("fond2")
        this.styleClass.add("cc")


    }



}
