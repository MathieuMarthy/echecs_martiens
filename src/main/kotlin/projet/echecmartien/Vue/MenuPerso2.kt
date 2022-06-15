
package projet.echecmartien.Vue;

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.control.ColorPicker
import javafx.scene.control.Label;
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MenuPerso2: GridPane() {

    var titre: Label = Label("Menu de Personnalisation")
    var pseudo: Label = Label("Pseudo")
    var labelJoueur1: Label = Label("Joueur 1")
    var labelJoueur2: Label = Label("Joueur 2")
    var champ_de_saisi1: TextField = TextField("Entrez votre pseudo")
    var champ_de_saisi2: TextField = TextField("Entrez votre pseudo")
    var photo_de_profil: Label = Label("Photo de profil")
    var couleurs: Label = Label("Couleurs")
    var choix_de_couleur:  ColorPicker = ColorPicker()

    init {
        titre.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 30.0)
        pseudo.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        labelJoueur1.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15.0)
        labelJoueur2.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15.0)
        champ_de_saisi1.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15.0)
        champ_de_saisi2.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15.0)
        photo_de_profil.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0)
        couleurs.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0)

        val col0 = ColumnConstraints()
        col0.halignment = HPos.CENTER
        col0.percentWidth = 15.0

        val ligne0 = RowConstraints()
        ligne0.valignment =VPos.CENTER
        ligne0.percentHeight = 30.0

        val col1 = ColumnConstraints()
        col1.halignment = HPos.CENTER
        col1.percentWidth = 15.0

        val ligne1 = RowConstraints()
        ligne1.valignment =VPos.CENTER
        ligne1.percentHeight =10.0

        val ligne3 = RowConstraints()
        ligne1.valignment =VPos.CENTER
        ligne1.percentHeight =10.0

        val col2 = ColumnConstraints()
        col2.halignment = HPos.CENTER
        col2.percentWidth = 40.0

        val ligne2 = RowConstraints()
        ligne2.valignment =VPos.BASELINE
        ligne2.percentHeight = 4.0

        val col3 = ColumnConstraints()
        col3.halignment = HPos.CENTER
        col3.percentWidth = 30.0

        val col4 = ColumnConstraints()
        col4.halignment = HPos.CENTER
        col4.percentWidth = 30.0

        this.columnConstraints.addAll(col0,col1,col2,col3, col4)
        this.rowConstraints.addAll(ligne0, ligne1, ligne2, ligne3)

        choix_de_couleur.prefWidth = 300.0

        this.add(titre, 2, 0)
        this.add(pseudo, 1, 1)
        this.add(labelJoueur1,0,2)
        this.add(labelJoueur2,0,3)
        this.add(champ_de_saisi1,1,2)
        this.add(champ_de_saisi2,1,3)
        this.add(photo_de_profil, 2, 1)
        this.add(couleurs, 3, 1)
        this.add(choix_de_couleur, 3, 2)

        this.titre.padding = Insets(0.0, 0.0, 50.0, 0.0)
    }
}