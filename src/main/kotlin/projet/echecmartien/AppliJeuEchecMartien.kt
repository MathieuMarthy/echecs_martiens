package projet.echecmartien

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Screen
import javafx.stage.Stage
import projet.echecmartien.Vue.*
import projet.echecmartien.controleur.*
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur


class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        //Initialisation des variables
        val vue = MainVue()
        val grille = GrilleJeu()
        val nombreJoueurs = NombreJoueurs()
        val MenuPerso1 = MenuPerso1()
        val MenuPerso2 = MenuPerso2()
        val regles = ReglesDuJeu()
        val regles2 = ReglesDuJeu2()


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
        grille.addStyle()
        MenuPerso1.addStyle()
        MenuPerso2.addStyle()
        regles.addStyle()
        regles2.addStyle()

        //Mise en place du plateau
        val jeu: Jeu = Jeu()

        //Controleurs

        //NouvellePartie
        vue.boutonNew.onAction = EventHandler { primaryStage.scene.root = nombreJoueurs }

        //Retour (Nouvelle partie)
        nombreJoueurs.retour.onAction = EventHandler { primaryStage.scene.root = vue }

        //ChargerPartie
        vue.boutonLoad.onAction = ControleurStartCharger(primaryStage, jeu, vue, grille)

        //Nombre de joueur boutons
        nombreJoueurs.joueur1.onAction = ControleurLancerMenu1(MenuPerso1, primaryStage, grille, jeu, nombreJoueurs)
        nombreJoueurs.joueur2.onAction = ControleurLancerMenu2(MenuPerso2, primaryStage, grille, jeu, nombreJoueurs)

        //Grille Boutons
        grille.regles.onAction = EventHandler { primaryStage.scene.root = regles2 }
        vue.boutonRules.onAction = EventHandler { primaryStage.scene.root = regles }

        // Règles Bouton
        regles.boutonRetour.onAction = EventHandler { primaryStage.scene.root = vue }
        regles2.boutonRetour.onAction = EventHandler { primaryStage.scene.root = grille }


        // bouton "Quitter" sur la grille et fait apparaître un pop up
        val popup: Alert = Alert(Alert.AlertType.CONFIRMATION)

        grille.quitter.onAction = EventHandler {
            popup.title = "Quitter la partie ?";
            popup.headerText = "Voulez-vous vraiment quitter la partie ?";
            popup.contentText = "Quitter la partie vous mènera à un menu de sauvegarde. Continuer ?";

            // créé les boutons du pop up
            val bouton_oui : ButtonType = ButtonType("Oui");
            val bouton_non : ButtonType = ButtonType("Non");
            val bouton_annuler : ButtonType = ButtonType("Annuler")
            popup.getButtonTypes().setAll(bouton_oui, bouton_non, bouton_annuler);

            // ce que font les boutons
            val result = popup.showAndWait()
            if (result.get() == bouton_oui) {           // bouton menant au menu de sauvegarde
                ControleurStartSauvegarder(primaryStage, jeu, vue, grille).handle(null)
            } else if (result.get() == bouton_non){      // bouton menant au menu principal
                primaryStage.scene.root = vue
            } else if (result.get() == bouton_annuler) { // bouton faisant revenir sur la grille
                primaryStage.scene.root = grille
            }
        }

        // bouton "Rejouer" sur la grille
        grille.ff.onAction = EventHandler {
            // initialise le plateau avec les mêmes pseudos
            jeu.initialiserPartie(Joueur(jeu.joueurs[0].getPseudo()), Joueur(jeu.joueurs[1].getPseudo()), 5)
            // update le plateau
            ControleurCoupsPossibles(jeu, grille).updatePlateau()
        }

        // switch image PP + Image PP grille
        //MenuPerso1.right1.onAction = EventHandler{MenuPerso1.fileChooser.showOpenDialog(primaryStage)}
        MenuPerso1.right1.onAction = EventHandler { MenuPerso1.graphique.ppSuivante(); MenuPerso1.image_pp.image = MenuPerso1.graphique.getPPCourante() ;grille.imagegauche.image = MenuPerso1.graphique.getPPCourante()}
        MenuPerso1.left1.onAction = EventHandler { MenuPerso1.graphique.ppPrecedente(); MenuPerso1.image_pp.image = MenuPerso1.graphique.getPPCourante() ;grille.imagegauche.image = MenuPerso1.graphique.getPPCourante()}

        MenuPerso1.right1.onAction = EventHandler { MenuPerso1.graphique.ppSuivante(); MenuPerso1.image_pp.image = MenuPerso1.graphique.getPPCourante() ;grille.imagegauche.image = MenuPerso1.graphique.getPPCourante()}
        MenuPerso1.right1.onAction = EventHandler { MenuPerso1.graphique.ppPrecedente(); MenuPerso1.image_pp.image = MenuPerso1.graphique.getPPCourante() ;grille.imagegauche.image = MenuPerso1.graphique.getPPCourante()}

        MenuPerso2.right1.onAction = EventHandler { MenuPerso2.graphique.ppSuivante(); MenuPerso2.image_pp.image = MenuPerso2.graphique.getPPCourante(); grille.imagegauche.image = MenuPerso2.graphique.getPPCourante() }
        MenuPerso2.left1.onAction = EventHandler { MenuPerso2.graphique.ppPrecedente(); MenuPerso2.image_pp.image = MenuPerso2.graphique.getPPCourante() ;grille.imagegauche.image = MenuPerso2.graphique.getPPCourante()}

        MenuPerso2.right2.onAction = EventHandler { MenuPerso2.graphique2.ppSuivante(); MenuPerso2.image_pp2.image = MenuPerso2.graphique2.getPPCourante(); grille.imagedroite.image = MenuPerso2.graphique2.getPPCourante()}
        MenuPerso2.left2.onAction = EventHandler { MenuPerso2.graphique2.ppPrecedente(); MenuPerso2.image_pp2.image = MenuPerso2.graphique2.getPPCourante() ; grille.imagedroite.image = MenuPerso2.graphique2.getPPCourante() }

        //Lancer partie
        MenuPerso1.boutton2.onAction = EventHandler { primaryStage.scene.root = nombreJoueurs }
        MenuPerso1.boutton1.onAction = ControleurInit1J(jeu, MenuPerso1, grille, primaryStage)

        //Mise en place de la scène
        primaryStage.title = "Echecs Martiens"
        primaryStage.scene = sceneMenu
        primaryStage.show()
    }
}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



