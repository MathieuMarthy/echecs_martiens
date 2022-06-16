package projet.echecmartien

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Screen
import javafx.stage.Stage
import projet.echecmartien.Vue.*
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur


class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        //Initialisation des variables
        val vue = MainVue()
        val grille = GrilleJeu()
        val charger = Charger()
        var nombreJoueurs = NombreJoueurs()
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
        grille.addStyle()
        MenuPerso1.addStyle()
        MenuPerso2.addStyle()
        regles.addStyle()
        regles2.addStyle()

        //Mise en place du plateau
        var jeu: Jeu = Jeu()

        //Controleurs

        //NouvellePartie
        vue.boutonNew.onAction = EventHandler { primaryStage.scene.root = nombreJoueurs }
        //Retour (Nouvelle partie)
        nombreJoueurs.retour.onAction = EventHandler { primaryStage.scene.root = vue }

        //ChargerPartie
        vue.boutonLoad.onAction = EventHandler { primaryStage.scene.root = charger }
        //Retour (Charger partie)
        charger.nouveau5.onAction = EventHandler { primaryStage.scene.root = vue }
        //Nombre de joueur boutons
        nombreJoueurs.joueur1.onAction = EventHandler { primaryStage.scene.root = MenuPerso1 }
        nombreJoueurs.joueur2.onAction = EventHandler { primaryStage.scene.root = MenuPerso2 }
        //MenuPerso2 boutons
        MenuPerso2.boutton2.onAction = EventHandler { primaryStage.scene.root = nombreJoueurs }
        MenuPerso2.boutton1.onAction = EventHandler { primaryStage.scene.root = grille ; grille.joueur1.text = MenuPerso2.champ_de_saisi.text ;grille.joueur2.text = MenuPerso2.champ_de_saisi2.text }

        //Grille Boutons
        grille.regles.onAction = EventHandler { primaryStage.scene.root = regles2 }
        vue.boutonRules.onAction = EventHandler { primaryStage.scene.root = regles }

        // Règles Bouton
        regles.boutonRetour.onAction = EventHandler { primaryStage.scene.root = vue }
        regles2.boutonRetour.onAction = EventHandler { primaryStage.scene.root = grille }

        // controleur sauvegarde
        //charger.nouveau1.onAction = ControleurSauvgarde()
        charger.nouveau1.onAction = EventHandler { jeu.sauvegarderPartie("1") ; charger.nouveau1.text = grille.joueur1.text + " vs " + grille.joueur2.text}
        charger.nouveau2.onAction = EventHandler { jeu.sauvegarderPartie("2") ; charger.nouveau2.text = grille.joueur1.text + " vs " + grille.joueur2.text}
        charger.nouveau3.onAction = EventHandler { jeu.sauvegarderPartie("3") ; charger.nouveau3.text = grille.joueur1.text + " vs " + grille.joueur2.text}
        charger.nouveau4.onAction = EventHandler { jeu.sauvegarderPartie("4") ; charger.nouveau4.text = grille.joueur1.text + " vs " + grille.joueur2.text}

        // bouton "Quitter" sur la grille et fait apparaître un pop up
        var popup: Alert = Alert(Alert.AlertType.CONFIRMATION)

        grille.quitter.onAction = EventHandler {
            popup.title = "Quitter la partie ?";
            popup.headerText = "Voulez-vous vraiment quitter la partie ?";
            popup.contentText = "Quitter la partie vous mènera à un menu de sauvegarde. Continuer ?";

            // créé les boutons du pop up
            var bouton_oui : ButtonType = ButtonType("Oui");
            var bouton_non : ButtonType = ButtonType("Non");
            var bouton_annuler : ButtonType = ButtonType("Annuler")
            popup.getButtonTypes().setAll(bouton_oui, bouton_non, bouton_annuler);

            // ce que font les boutons
            val result = popup.showAndWait()
            if (result.get() == bouton_oui) {           // bouton menant au menu de sauvegarde
                primaryStage.scene.root = charger
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



