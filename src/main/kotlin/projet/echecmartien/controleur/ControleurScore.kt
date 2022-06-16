package projet.echecmartien.controleur

import projet.echecmartien.Vue.GrilleJeu
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.modele.MoyenPion
import projet.echecmartien.modele.PetitPion

class ControleurScore(grilleJeu: GrilleJeu,jeu: Jeu) {

    var grille = grilleJeu
    var jeu = jeu
    var joueur1 = jeu.joueurs[0]
    var joueur2 = jeu.joueurs[1]

    fun updateScore(){
        println(joueur1)
        grille.nbPetitJ1 = 0
        grille.nbMoyenJ1 = 0
        grille.nbGrandJ1 = 0

        // Score joueur 1
        for (pion in joueur1.getPionsCaptures()){
            if (pion is PetitPion){
                grille.nbPetitJ1+=1
            }
            if (pion is MoyenPion){
                grille.nbMoyenJ1+=1
            }
            else{
                grille.nbGrandJ1+=1
            }
        }

        println(grille.nbPetitJ1)
        println(grille.nbMoyenJ1)
        println(grille.nbGrandJ1)
    }
}