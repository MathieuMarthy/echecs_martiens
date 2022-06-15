package projet.echecmartien.Vue

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle


internal class
GrilleJeu : BorderPane(){

    val grille : GridPane = GridPane()

    var couleur1 : String = "#F8916B"
    var nuance1 : String = "#f5000c"

    var couleur2 : String = "#A5BECC"
    var nuance2 : String = "#243A73"

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

    val quitter = Button("Quitter")
    val regles = Button("Règles")
    val ff = Button("FF")

    init{

        //Création du damier
        for (nbLignes in 0 until 8){
            for (nbColonnes in 0 until 4){
                val case = Rectangle(120.0,120.0)
                if (nbLignes <=3){
                    if ((nbColonnes+nbLignes)%2==1){
                        case.style = "-fx-fill: $couleur1; -fx-stroke: white; -fx-stroke-width: 2;";
                    }else{
                        case.style = "-fx-fill: $nuance1; -fx-stroke: white; -fx-stroke-width: 2;";
                    }
                    grille.add(case,nbColonnes,nbLignes)
                }
                else{
                    if ((nbColonnes+nbLignes)%2==1){
                        case.style = "-fx-fill: $couleur2; -fx-stroke: white; -fx-stroke-width: 2;";
                    }else{
                        case.style = "-fx-fill: $nuance2; -fx-stroke: white; -fx-stroke-width: 2;";
                    }
                    grille.add(case,nbColonnes,nbLignes)
                }
            }
        }

        //Positionnement
        this.center = grille
        grille.alignment = Pos.CENTER

        //Zone de gauche
        var rayon : Double = 30.0
        for (i in 0 until 3){
            val pionModele = Circle(rayon)
            grilleGauche.add(pionModele,0,i)
            rayon+=10.0
        }
        grilleGauche.add(Label("x$nbPetitJ1"),1,0)
        grilleGauche.add(Label("x$nbMoyenJ1"),1,1)
        grilleGauche.add(Label("x$nbGrandJ1"),1,2)

        gauche.alignment = Pos.BOTTOM_CENTER
        this.left = gauche

        gauche.children.addAll(joueur1,grilleGauche)

        //Zone de droite
        grilleDroite.add(Label("x$nbPetitJ2"),0,0)
        grilleDroite.add(Label("x$nbMoyenJ2"),0,1)
        grilleDroite.add(Label("x$nbGrandJ2"),0,2)

        rayon = 30.0
        for (i in 0 until 3){
            val pionModele = Circle(rayon)
            grilleDroite.add(pionModele,1,i)
            rayon+=10.0
        }

        droite.alignment = Pos.BOTTOM_CENTER
        this.right = droite

        droite.children.addAll(joueur2,grilleDroite)

        //Centrage des grid
        for (i in 0 until 6){
            GridPane.setHalignment(grilleDroite.children[i],HPos.CENTER)
            GridPane.setHalignment(grilleGauche.children[i],HPos.CENTER)
        }


        bandeauHaut.children.addAll(quitter,regles,ff)
        this.top = bandeauHaut
    }

    fun addStyle(){
        this.styleClass.add("grille")
    }
}
