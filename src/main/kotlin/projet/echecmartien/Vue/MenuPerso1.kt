package projet.echecmartien.Vue;

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.control.Button
import javafx.scene.control.ColorPicker
import javafx.scene.control.Label;
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MenuPerso1: GridPane() {

    var Border1 = BorderPane()
    var Border2 = BorderPane()



    var titre: Label = Label("Menu de Personnalisation")
    var pseudo1: Label = Label("Pseudo")
    var pseudo2: Label = Label("Pseudo")
    var labelJoueur1: Label = Label("Joueur 1")
    var labelJoueur2: Label = Label("Joueur 2")
    var champ_de_saisi: TextField = TextField("Entrez votre pseudo")
    var photo_de_profil1: Label = Label("Photo de profil")

    var couleurs1: Label = Label("Couleurs")
    var couleurs2: Label = Label("Couleurs")
    var choix_de_couleur1:  ColorPicker = ColorPicker()
    var choix_de_couleur2:  ColorPicker = ColorPicker()

    var boutton2 = Button("Retour")
    var boutton1 = Button("Lancer")


    var right1 = Button(">")
    var right2 = Button(">")

    var left1 = Button(">")
    var left2 = Button(">")





    init {

        titre.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50.0)
        pseudo1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)
        pseudo2.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)
        labelJoueur1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelJoueur2.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        champ_de_saisi.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        photo_de_profil1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)
        couleurs1.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)
        couleurs2.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0)

        Border1.left = left1
        Border1.right = right1
        Border1.center

        Border2.left = left2
        Border2.right = right2





        /*choix_de_couleur1.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))
        choix_de_couleur2.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))
        champ_de_saisi1.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))
        champ_de_saisi2.background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))*/


        val col0 = ColumnConstraints()
        col0.halignment = HPos.CENTER
        col0.percentWidth = 15.0

        val ligne0 = RowConstraints()
        ligne0.valignment = VPos.CENTER
        ligne0.percentHeight = 30.0

        val col1 = ColumnConstraints()
        col1.halignment = HPos.CENTER
        col1.percentWidth = 15.0

        val ligne1 = RowConstraints()
        ligne1.valignment = VPos.CENTER
        ligne1.percentHeight = 30.0

        val ligne3 = RowConstraints()
        ligne1.valignment = VPos.CENTER
        ligne1.percentHeight = 30.0

        val col2 = ColumnConstraints()
        col2.halignment = HPos.CENTER
        col2.percentWidth = 40.0

        val ligne2 = RowConstraints()
        ligne2.valignment = VPos.BASELINE
        ligne2.percentHeight = 20.0



        val col3 = ColumnConstraints()
        col3.halignment = HPos.CENTER
        col3.percentWidth = 30.0



        this.columnConstraints.addAll(col0, col1, col2, col3)
        this.rowConstraints.addAll(ligne0, ligne1, ligne2, ligne3)

        choix_de_couleur1.prefWidth = 300.0



        this.add(titre, 2, 0)
        this.add(pseudo1, 1, 1)


        this.add(labelJoueur1, 0, 2)

        this.add(champ_de_saisi, 1, 2)

        this.add(photo_de_profil1, 2, 1)

        this.add(couleurs1, 3, 1)
        this.add(couleurs2, 3, 1)
        this.add(choix_de_couleur1, 3, 2)

        this.add(boutton1, 2,3)
        this.add(boutton2, 3,3)


        this.titre.padding = Insets(0.0, 0.0, 50.0, 0.0)

    }
    fun addStyle(){
        choix_de_couleur1.styleClass.add("")
        choix_de_couleur2.styleClass.add("")
        champ_de_saisi.styleClass.add("")
        this.styleClass.add("textfield")
        this.styleClass.add("boutons3")
        this.styleClass.add("fond2")
        this.styleClass.add("text1")

    }


}
