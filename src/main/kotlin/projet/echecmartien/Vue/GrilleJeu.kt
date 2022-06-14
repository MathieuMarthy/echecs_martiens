package projet.echecmartien.Vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

internal class
GrilleJeu : BorderPane(){

    val grilles = VBox()
    val grille1 : GridPane = GridPane()
    val grille2 : GridPane = GridPane()


    init{

        for (nbLignes in 0 until 4){
            for (nbColonnes in 0 until 4){
                val case1 = Rectangle(100.0,100.0)
                if ((nbColonnes+nbLignes)%2==1){
                    case1.style = "-fx-fill: #F8916B; -fx-stroke: black; -fx-stroke-width: 3;";
                }else{
                    case1.style = "-fx-fill: #f5000c; -fx-stroke: black; -fx-stroke-width: 3;";
                }
                grille1.add(case1,nbColonnes,nbLignes)
                val case2 = Rectangle(100.0,100.0)
                if ((nbColonnes+nbLignes)%2==1){
                    case2.style = "-fx-fill: #A5BECC; -fx-stroke: black; -fx-stroke-width: 3;";
                }else{
                    case2.style = "-fx-fill: #243A73; -fx-stroke: black; -fx-stroke-width: 3;";
                }
                grille2.add(case2,nbColonnes,nbLignes)
            }
        }




        grilles.children.addAll(grille1,grille2)

        grilles.spacing = 15.0
        grilles.alignment = Pos.CENTER
        grille1.vgap = 5.0
        grille2.vgap = 5.0
        grille1.hgap = 5.0
        grille2.hgap = 5.0
        this.center = grilles
        var b = FlowPane()
        this.left =b
    }
}
