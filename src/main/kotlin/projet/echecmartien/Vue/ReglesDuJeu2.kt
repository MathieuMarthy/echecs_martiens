package projet.echecmartien.Vue

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.shape.Circle
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.scene.text.Text

class ReglesDuJeu2: VBox() {

    var titre: Label = Label("Règles du jeu")
    var prepa: Label = Label("Préparation")
    var reglesPrepa : Text = Text("Disposez les 18 pions comme sur la figure ci-contre.\n" +
            "Un joueur identifie ses pièces par leur position à un instant donné.\n" +
            "Le damier est divisé en 2 zones, une pour chaque joueur. Toute pièce dans la zone d'un joueur est la sienne.")
    var deroulement: Label = Label("Déroulement du jeu")
    var reglesDeroulement: Text = Text("Chaque joueur, à son tour de jeu, déplace une de ses pièces.\n" +
            "Les grands pions se déplacent verticalement, horizontalement et diagonalement de n cases (comme la dame aux Eches traditionnels).\n" +
            "Les pions moyens se déplacent verticalement, horizontalement et diagonalement de 1 ou 2 case(s).\n" +
            "Les petits pions se déplacent diagonalement de 1 case.\n" +
            "À son tour de jeu un joueur peut déplacer n'importe quel pion de son camp, soit à l'intérieur de sa zone soit vers la zone adverse.")
    var excep: Label = Label("Exception")
    var reglesException: Text = Text("Il est interdit de renvoyer dans la zone adverse un pion qui vient d'arriver dans sa zone. Mais on peut déplacer ce même pion à l'intérieur de sa zone" +
            "\n" +
            "On capture un pion adverse en prenant sa place (donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse).\n" + "Le pion capturé est retiré du damier.\n" +
            "Le saut par dessus un ou n pion(s) adverse(s) ou non n'est pas autorisé.")
    var finPartie: Label = Label("Fin de la partie")
    var reglesFin: Text = Text("Une fois la partie finie (plus de pions à capturer car ils sont tous capturés ou plus aucunes prises n'est possibles), on compte: \n" + "- 3 points par grands pions capturés,\n" + "- 2 par moyens et \n" + "- 1 par petits.\n" +
            "Le gagnant est évidemment le joueur qui à le plus de points.")

    val bandeauBas : GridPane = GridPane()

    var rayonGrand: Double = 50.0
    var pionModele1 = Circle(rayonGrand)

    var rayonMoyen: Double = 40.0
    val pionModele2 = Circle(rayonMoyen)

    var rayonPetit: Double = 30.0
    val pionModele3 = Circle(rayonPetit)

    val boutonRetour : Button = Button("Retour")

    var pointPion1 : Label = Label(": rapporte 3 points")

    var pointPion2 : Label = Label(": rapporte 2 points")

    var pointPion3 : Label = Label(": rapporte 1 point")


    init {
        titre.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30.0)
        prepa.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30.0)
        reglesPrepa.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20.0)
        deroulement.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30.0)
        reglesDeroulement.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20.0)
        excep.font = Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 25.0)
        reglesException.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20.0)
        finPartie.font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30.0)
        reglesFin.font = Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20.0)

        this.spacing = 15.0
        this.padding = Insets(30.0, 30.0, 10.0, 30.0)

        bandeauBas.add(pionModele1, 0, 0)
        bandeauBas.add(pionModele2, 2, 0)
        bandeauBas.add(pionModele3, 4, 0)

        bandeauBas.add(pointPion1, 1, 0)
        bandeauBas.add(pointPion2, 3, 0)
        bandeauBas.add(pointPion3, 5, 0)

        bandeauBas.add(boutonRetour, 6, 1)

        val col0 = ColumnConstraints()
        col0.halignment = HPos.CENTER
        col0.percentWidth = 5.0

        val ligne0 = RowConstraints()
        ligne0.valignment =VPos.CENTER
        ligne0.percentHeight = 5.0

        val col1 = ColumnConstraints()
        col1.halignment = HPos.CENTER
        col1.percentWidth = 15.0

        val ligne1 = RowConstraints()
        ligne1.valignment =VPos.BOTTOM
        ligne1.percentHeight =5.0

        val col2 = ColumnConstraints()
        col2.halignment = HPos.CENTER
        col2.percentWidth = 10.0

        val col3 = ColumnConstraints()
        col3.halignment = HPos.CENTER
        col3.percentWidth = 15.0

        val col4 = ColumnConstraints()
        col4.halignment = HPos.CENTER
        col4.percentWidth = 10.0

        val col5 = ColumnConstraints()
        col5.halignment = HPos.CENTER
        col5.percentWidth = 15.0

        val col6 = ColumnConstraints()
        col6.halignment = HPos.RIGHT
        col6.percentWidth = 20.0

        bandeauBas.columnConstraints.addAll(col0, col1, col2, col3, col4, col5, col6)
        bandeauBas.rowConstraints.addAll(ligne0, ligne1)

        this.children.addAll(titre, prepa, reglesPrepa, deroulement, reglesDeroulement, excep, reglesException, finPartie, reglesFin, bandeauBas)
    }

    fun addStyle(){
        this.styleClass.add("fond3")
        this.styleClass.add("boutonsRegles")
        titre.styleClass.add("label")
        prepa.styleClass.add("cc")
        deroulement.styleClass.add("cc")
        excep.styleClass.add("cc")
        finPartie.styleClass.add("cc")
        reglesPrepa.styleClass.add("infos")
        reglesDeroulement.styleClass.add("infos")
        reglesException.styleClass.add("infos")
        reglesFin.styleClass.add("infos")
        pointPion1.styleClass.add("points")
        pointPion2.styleClass.add("points")
        pointPion3.styleClass.add("points")
    }

}
