package projet.echecmartien

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Screen
import javafx.stage.Stage
import projet.echecmartien.Vue.*
import projet.echecmartien.controleur.ControleurLancerMenu1
import projet.echecmartien.controleur.ControleurLancerMenu2
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur


class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        //Initialisation des variables
        val vue = MainVue()
        val grille = GrilleJeu()
        val charger = Charger()
        val sauvegarder = Charger()
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
        charger.addStyle()
        sauvegarder.addStyle()
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
        vue.boutonLoad.onAction = EventHandler { primaryStage.scene.root = charger }

        //Nombre de joueur boutons
        nombreJoueurs.joueur1.onAction = ControleurLancerMenu1(MenuPerso1, primaryStage, grille, jeu, nombreJoueurs)
        nombreJoueurs.joueur2.onAction = ControleurLancerMenu2(MenuPerso2, primaryStage, grille, jeu, nombreJoueurs)

        //Grille Boutons
        grille.regles.onAction = EventHandler { primaryStage.scene.root = regles2 }
        vue.boutonRules.onAction = EventHandler { primaryStage.scene.root = regles }

        // Règles Bouton
        regles.boutonRetour.onAction = EventHandler { primaryStage.scene.root = vue }
        regles2.boutonRetour.onAction = EventHandler { primaryStage.scene.root = grille }

        // controleur sauvegarde
        //charger.nouveau1.onAction = ControleurSauvgarde()
        charger.nouveau1.onAction = EventHandler { jeu.chargerPartie("1") ; sauvegarder.nouveau1.text = "Partie 1: 1"}
        charger.nouveau2.onAction = EventHandler { jeu.chargerPartie("2") ; sauvegarder.nouveau1.text = "Partie 1: 2"}
        charger.nouveau3.onAction = EventHandler { jeu.chargerPartie("3") ; sauvegarder.nouveau1.text = "Partie 1: 3"}
        charger.nouveau4.onAction = EventHandler { jeu.chargerPartie("4") ; sauvegarder.nouveau1.text = "Partie 1: 4"}
        charger.nouveau5.onAction = EventHandler { primaryStage.scene.root = vue }

        // ----------------------- A FAIRE SI ON PEUX | sauvegarder.nouveau1.text = grille.joueur1.text + " vs " + grille.joueur2.text

        // sauvegarder
        sauvegarder.nouveau1.onAction = EventHandler { jeu.sauvegarderPartie("1") ; sauvegarder.nouveau1.text = "Partie 1: 1"}
        sauvegarder.nouveau2.onAction = EventHandler { jeu.sauvegarderPartie("2") ; sauvegarder.nouveau2.text = "Partie 1: 2"}
        sauvegarder.nouveau3.onAction = EventHandler { jeu.sauvegarderPartie("3") ; sauvegarder.nouveau3.text = "Partie 1: 3"}
        sauvegarder.nouveau4.onAction = EventHandler { jeu.sauvegarderPartie("4") ; sauvegarder.nouveau4.text = "Partie 1: 4"}
        sauvegarder.nouveau5.onAction = EventHandler { primaryStage.scene.root = vue }


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
                primaryStage.scene.root = sauvegarder
            }else if (result.get() == bouton_non){      // bouton menant au menu principal
                primaryStage.scene.root = vue
            }else if (result.get() == bouton_annuler) { // bouton faisant revenir sur la grille
                primaryStage.scene.root = grille
            }
        }

        // bouton "Rejouer" sur la grille
        grille.ff.onAction = EventHandler {
            jeu.initialiserPartie(Joueur(jeu.joueurs[0].getPseudo()), Joueur(jeu.joueurs[1].getPseudo()), 5)
        }

        // pop up de victoire
//        var popupVictoire: Alert = Alert(Alert.AlertType.CONFIRMATION)
//        popupVictoire.title = "Résultats de la partie"
//        popupVictoire.headerText = "Bravo ${jeu.joueurVainqueur()} tu es trop fort, on devrait faire une statue à votre effigie."
//        popupVictoire.contentText = "Refaire une partie ?"

//        var bouton_menu : ButtonType = ButtonType("Retour au menu")
//        popup.getButtonTypes().setAll(bouton_menu)
//        val resultat_victoire = popupVictoire.showAndWait()
//        if (resultat_victoire.get() == bouton_menu){
//            primaryStage.scene.root = vue
//        }

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

        MenuPerso1.fileChooser.title = "Open File"
        MenuPerso1.testlabel.text = MenuPerso1.fileChooser.initialFileName

        //Lancer partie
        MenuPerso1.boutton2.onAction = EventHandler { primaryStage.scene.root = nombreJoueurs }
        MenuPerso1.boutton1.onAction = ControleurInit1J(jeu,MenuPerso1,grille,primaryStage)

        //Mise en place de la scène
        primaryStage.title = "Echecs Martiens"
        primaryStage.scene = sceneMenu
        primaryStage.show()
    }
}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



