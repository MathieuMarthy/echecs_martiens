package projet.echecmartien

import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.FileChooser
import javafx.stage.Screen
import javafx.stage.Stage
import projet.echecmartien.Vue.*
import projet.echecmartien.modele.*
import projet.echecmartien.modele.IA
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur


class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        //Initialisation des variables
        val vue = MainVue()
        val grille = GrilleJeu()
        val charger =  Charger()
        var nombreJoueurs = NombreJoueurs()
        val MenuPerso1 = MenuPerso1()
        val MenuPerso2 = MenuPerso2()
        val regles = ReglesDuJeu()


        //Récupération des dimensions de l'écran & mise en plein écran de la scène
        val largeur = Screen.getPrimary().bounds.width
        val longueur = Screen.getPrimary().bounds.height
        primaryStage.isFullScreen = true

        //Création des scènes
        val sceneMenu = Scene(vue, largeur, longueur)

        //Ajout du style
        sceneMenu.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("style/style.css").toExternalForm())
        nombreJoueurs.addStyle()
        vue.addStyle()
        charger.addStyle()
        grille.addStyle()
        MenuPerso1.addStyle()
        MenuPerso2.addStyle()
        regles.addStyle()

        //Controleurs

        //NouvellePartie
        vue.boutonNew.onAction = EventHandler{ primaryStage.scene.root = nombreJoueurs}
        //Retour (Nouvelle partie)
        nombreJoueurs.retour.onAction = EventHandler{ primaryStage.scene.root = vue }

        //ChargerPartie
        vue.boutonLoad.onAction = EventHandler{ primaryStage.scene.root = charger }
        //Retour (Charger partie)
        charger.nouveau5.onAction = EventHandler{ primaryStage.scene.root = vue }
        //Nombre de joueur boutons
        nombreJoueurs.joueur1.onAction = EventHandler{primaryStage.scene.root = MenuPerso1}
        nombreJoueurs.joueur2.onAction = EventHandler{primaryStage.scene.root = MenuPerso2}
        //MenuPerso2 boutons
        MenuPerso2.boutton2.onAction = EventHandler{primaryStage.scene.root = nombreJoueurs}
        MenuPerso2.boutton1.onAction = EventHandler{primaryStage.scene.root = grille}
        //Grille Boutons
        grille.regles.onAction = EventHandler{primaryStage.scene.root = regles }
        vue.boutonRules.onAction = EventHandler{primaryStage.scene.root = regles }
        // Règle Bouton



        // switch image PP
        //MenuPerso1.right1.onAction = EventHandler{MenuPerso1.fileChooser.showOpenDialog(primaryStage)}
        MenuPerso1.right1.onAction = EventHandler {MenuPerso1.graphique.ppSuivante(); MenuPerso1.image_pp.image = MenuPerso1.graphique.getPPCourante()}
        MenuPerso1.left1.onAction = EventHandler {MenuPerso1.graphique.ppPrecedente(); MenuPerso1.image_pp.image = MenuPerso1.graphique.getPPCourante()}
        MenuPerso1.fileChooser.title = "Open File"
        MenuPerso1.testlabel.text = MenuPerso1.fileChooser.initialFileName



        //Mise en place du plateau
        var jeu: Jeu = Jeu()

        //Lancer partie
        MenuPerso1.boutton2.onAction = EventHandler{primaryStage.scene.root = nombreJoueurs}
        MenuPerso1.boutton1.onAction = ControleurInit1J(jeu,MenuPerso1,grille,sceneMenu,primaryStage)








        //Mise en place de la scène
        primaryStage.title="Echecs Martiens"
        primaryStage.scene=sceneMenu
        primaryStage.show()
    }

    /*fun updateGrille(matrice : Array<Array<Case>>){

        for ((i, ligne) in matrice.withIndex()) {
            for ((j, case) in ligne.withIndex()) {

                val cercle = when (case.getPion()) {
                    is PetitPion -> Circle(30.0)
                    is MoyenPion -> Circle(40.0)
                    is GrandPion -> Circle(50.0)
                    else -> null
                }
                if (cercle != null){
                    cercle.onMouseClicked = EventHandler { println("cc") }
                    grille.grille.add(cercle,j,i)
                    GridPane.setHalignment(cercle, HPos.CENTER)
                }
            }
        }
    }*/
}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



