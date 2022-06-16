package projet.echecmartien.Vue

import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import projet.echecmartien.modele.Case
import projet.echecmartien.modele.GrandPion
import projet.echecmartien.modele.MoyenPion
import projet.echecmartien.modele.PetitPion


class GrilleJeu : BorderPane(){

    var grille : GridPane = GridPane()

    var couleur1 : String = "#f5000c"
    var nuance1 : String = "#F8916B"

    var couleur2 : String = "#243A73"
    var nuance2 : String = "#A5BECC"

    val bandeauHaut = HBox()
    val grilleGauche = GridPane()
    var grilleDroite = GridPane()

    val gauche = VBox()
    val droite = VBox()
    //val logoJ1 : ImageView
    //val logoJ2 : ImageView

    var joueur1 = Label("Fabeek")
    var nbPetitJ1 = 0
    var nbMoyenJ1 = 0
    var nbGrandJ1 = 0

    var joueur2 = Label("Masto")
    var nbPetitJ2 = 0
    var nbMoyenJ2 = 0
    var nbGrandJ2 = 0

    var MenuPerso2 = MenuPerso2()

    var imagedroite = ImageView()
    var imagegauche = ImageView()

    val quitter = Button("Quitter")
    val regles = Button("Règles")
    val ff = Button("Rejouer")

    init{
        creationDamier()
        //Positionnement
        this.center = grille
        grille.alignment = Pos.CENTER

        //Zone de gauche
        var rayon : Double = 30.0
        for (i in 0 until 3){
            val pionModele = Circle(rayon)
            pionModele.style = "-fx-fill: $couleur1;"
            grilleGauche.add(pionModele,0,i)
            rayon+=10.0
        }
        grilleGauche.add(Label("x$nbPetitJ1"),1,0)
        grilleGauche.add(Label("x$nbMoyenJ1"),1,1)
        grilleGauche.add(Label("x$nbGrandJ1"),1,2)

        gauche.alignment = Pos.BOTTOM_CENTER
        grilleGauche.vgap = 30.0
        this.left = gauche

        imagedroite = MenuPerso2.image_pp

        gauche.children.addAll(imagedroite, joueur1,grilleGauche)

        //Zone de droite
        grilleDroite.add(Label("x$nbPetitJ2"),1,0)
        grilleDroite.add(Label("x$nbMoyenJ2"),1,1)
        grilleDroite.add(Label("x$nbGrandJ2"),1,2)

        rayon = 30.0
        for (i in 0 until 3){
            val pionModele = Circle(rayon)
            pionModele.style = "-fx-fill: $couleur2;"
            grilleDroite.add(pionModele,0,i)
            rayon+=10.0
        }

        droite.alignment = Pos.BOTTOM_CENTER
        droite.padding = Insets(30.0)
        gauche.padding = Insets(30.0)
        droite.spacing = 50.0
        gauche.spacing = 50.0
        this.right = droite
        imagegauche = this.MenuPerso2.image_pp2


        droite.children.addAll(imagegauche,joueur2,grilleDroite)
        grilleDroite.vgap = 30.0

        //Centrage des grid
        for (i in 0 until 6){
            GridPane.setHalignment(grilleDroite.children[i],HPos.CENTER)
            GridPane.setHalignment(grilleGauche.children[i],HPos.CENTER)
        }

        var bandeauHautGauche = HBox()
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




}
