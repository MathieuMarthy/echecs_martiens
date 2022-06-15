package projet.echecmartien

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Screen

import javafx.stage.Stage
import projet.echecmartien.Vue.*
import projet.echecmartien.Vue.GrilleJeu
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



        //Mise en place du plateau
        var jeu: Jeu = Jeu()

        //Lancer partie
        MenuPerso1.boutton2.onAction = EventHandler{primaryStage.scene.root = nombreJoueurs}
        MenuPerso1.boutton1.onAction = EventHandler{
            primaryStage.scene.root = grille
            jeu.initialiserPartie(joueur1 = Joueur(MenuPerso1.champ_de_saisi.text), IA("Cortana", jeu), 5)
            grille.updateGrille(jeu.getPlateau().getCases())
        }


        //Mise en place de la scène
        primaryStage.title="Echecs Martiens"
        primaryStage.scene=sceneMenu
        primaryStage.show()
    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



