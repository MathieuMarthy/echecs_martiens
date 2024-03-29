package projet.echecmartien.Vue

import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Popup
import projet.echecmartien.ControleurInit2J
import projet.echecmartien.modele.Case
import projet.echecmartien.modele.GrandPion
import projet.echecmartien.modele.MoyenPion
import projet.echecmartien.modele.PetitPion


class GrilleJeu : BorderPane(){

    //Définition des éléments de la vue
    var grille : GridPane = GridPane()

    //couleurs
    var couleur1 : String = "#f5000c"
    var nuance1 : String = "#F8916B"
    var couleur2 : String = "#243A73"
    var nuance2 : String = "#A5BECC"

    //Elements sur les cotes et en haut
    val bandeauHaut = HBox()
    val grilleGauche = GridPane()
    var grilleDroite = GridPane()
    val gauche = VBox()
    val droite = VBox()
    val quitter = Button("Quitter")
    val regles = Button("Règles")
    val ff = Button("Rejouer")

    //Infos J1 & 2
    var joueur1 = Label("Fabeek")
    var nbPetitJ1 = 0
    var nbMoyenJ1 = 0
    var nbGrandJ1 = 0
    var joueur2 = Label("Masto")
    var nbPetitJ2 = 0
    var nbMoyenJ2 = 0
    var nbGrandJ2 = 0

    //Images
    var MenuPerso2 = MenuPerso2()
    var imagedroite = ImageView()
    var imagegauche = ImageView()


    init{
        //Update de la vue
        creationDamier()
        updateScoreboard(nbPetitJ1,nbMoyenJ1,nbGrandJ1,nbPetitJ2,nbMoyenJ2,nbGrandJ2)


        //Positionnement de la grille plateau de jeu
        this.center = grille
        grille.alignment = Pos.CENTER

        //Elements de gauche
        gauche.alignment = Pos.BOTTOM_CENTER
        grilleGauche.vgap = 30.0
        imagegauche = this.MenuPerso2.image_pp2
        this.left = gauche

        imagedroite = MenuPerso2.image_pp


        droite.children.addAll(imagegauche,joueur2,grilleDroite)
        gauche.children.addAll(imagedroite, joueur1,grilleGauche)


        droite.alignment = Pos.BOTTOM_CENTER
        droite.padding = Insets(30.0)
        gauche.padding = Insets(30.0)
        droite.spacing = 50.0
        gauche.spacing = 50.0
        this.right = droite


        grilleDroite.vgap = 30.0


        val bandeauHautGauche = HBox()
        bandeauHautGauche.children.addAll(quitter,regles)
        bandeauHaut.children.addAll(bandeauHautGauche,ff)
        this.top = bandeauHaut
        bandeauHautGauche.spacing = 10.0
        bandeauHaut.alignment = Pos.CENTER
        bandeauHaut.spacing = 1300.0

    }

    fun addStyle(){
        this.styleClass.add("grille")
        this.top.styleClass.add("boutonsGrille")
        this.left.styleClass.add("texteGrille")
        this.right.styleClass.add("texteGrille")
    }

    fun creationDamier(){
        for (nbLignes in 0 until 8){
            for (nbColonnes in 0 until 4){
                val case = Rectangle(110.0,110.0)
                if (nbLignes <=3){
                    if ((nbColonnes+nbLignes)%2==1){
                        case.style = "-fx-fill: $couleur1; -fx-stroke: white; -fx-stroke-width: 2;";
                    }else{
                        case.style = "-fx-fill: $nuance1; -fx-stroke: white; -fx-stroke-width: 2;";
                    }
                }
                else{
                    if ((nbColonnes+nbLignes)%2==1){
                        case.style = "-fx-fill: $couleur2; -fx-stroke: white; -fx-stroke-width: 2;";
                    }else{
                        case.style = "-fx-fill: $nuance2; -fx-stroke: white; -fx-stroke-width: 2;";
                    }
                }
                case.toBack()
                grille.add(case,nbColonnes,nbLignes)
            }
        }
    }

    fun updateScoreboard(nbPetitJ1 : Int ,nbMoyenJ1 : Int, nbGrandJ1 : Int, nbPetitJ2 : Int, nbMoyenJ2 : Int, nbGrandJ2 : Int) {
        //Zone de droite
        grilleDroite.children.clear()
        grilleGauche.children.clear()

        //Zone de gauche
        var rayon: Double = 30.0
        for (i in 0 until 3) {
            val pionModele = Circle(rayon)
            pionModele.style = "-fx-fill: $couleur1;"
            grilleGauche.add(pionModele, 0, i)
            rayon += 10.0
        }

        //Zone de gauche
        rayon = 30.0
        for (i in 0 until 3) {
            val pionModele = Circle(rayon)
            pionModele.style = "-fx-fill: $nuance2;"
            grilleDroite.add(pionModele, 0, i)
            rayon += 10.0
        }

        grilleGauche.add(Label("x$nbPetitJ2"), 1, 0)
        grilleGauche.add(Label("x$nbMoyenJ2"), 1, 1)
        grilleGauche.add(Label("x$nbGrandJ2"), 1, 2)

        //Zone de gauche
        grilleDroite.add(Label("x$nbPetitJ1"), 1, 0)
        grilleDroite.add(Label("x$nbMoyenJ1"), 1, 1)
        grilleDroite.add(Label("x$nbGrandJ1"), 1, 2)

        for (i in 0 until 3) {
            GridPane.setHalignment(grilleDroite.children[i], HPos.CENTER)
            GridPane.setHalignment(grilleGauche.children[i], HPos.CENTER)
        }
    }





}
