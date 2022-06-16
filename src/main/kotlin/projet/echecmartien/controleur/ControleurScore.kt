package projet.echecmartien.controleur

import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.modele.*

class ControleurScore(grilleJeu: GrilleJeu,jeu: Jeu) {

    var grille = grilleJeu
    var jeu = jeu
    var joueur1 = jeu.joueurs[0]
    var joueur2 = jeu.joueurs[1]

    fun updateScore(){
        grille.nbPetitJ1 = 0
        grille.nbMoyenJ1 = 0
        grille.nbGrandJ1 = 0

        grille.nbPetitJ2 = 0
        grille.nbMoyenJ2 = 0
        grille.nbGrandJ2 = 0

        // Score joueur 1
        for (pion in joueur1.getPionsCaptures()){
            if (pion is PetitPion){
                grille.nbPetitJ1+=1
            }
            if (pion is MoyenPion){
                grille.nbMoyenJ1+=1
            }
            else if (pion is GrandPion){
                grille.nbGrandJ1+=1
            }
        }

        // Score joueur 2
        for (pion in joueur2.getPionsCaptures()){
            if (pion is PetitPion){
                grille.nbPetitJ2+=1
            }
            if (pion is MoyenPion){
                grille.nbMoyenJ2+=1
            }
            else if (pion is GrandPion){
                grille.nbGrandJ2+=1
            }
        }

        grille.updateScoreboard(grille.nbPetitJ1,grille.nbMoyenJ1,grille.nbGrandJ1,grille.nbPetitJ2,grille.nbMoyenJ2,grille.nbGrandJ2)
    }
}