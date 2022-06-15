package projet.echecmartien.modele

import projet.echecmartien.librairie.EnumPion
import projet.echecmartien.librairie.GeneralData


class Plateau {
    private var tailleHorizontal: Int = 4
    private var tailleVertical: Int = 8

    private var cases: Array<Array<Case>> = Array(this.tailleVertical) { Array(this.tailleHorizontal) { Case() } }


    /**
     * initialise le plateau de jeu avec les pions
     */
    fun initialiser() {
        val generaldata = GeneralData()
        this.cases = Array(this.tailleVertical) { Array(this.tailleHorizontal) { Case() } }

        for (b in 0 until generaldata.tableau.size) {
            for (a in 0 until generaldata.tableau[0].size) {

                when (generaldata.tableau[b][a].valeur) {
                    "G" -> this.cases[a][b].setPion(GrandPion())
                    "M" -> this.cases[a][b].setPion(MoyenPion())
                    "P" -> this.cases[a][b].setPion(PetitPion())
                }
            }
        }
    }



    /**
     * donne la taille horizontale du plateau
     * @return la taille horizontale du plateau
     */
    fun getTailleHorizontale(): Int = this.tailleHorizontal


    /**
     * donne la taille verticale du plateau
     * @return la taille verticale du plateau
     */
    fun getTailleVerticale(): Int = this.tailleVertical


    /**
     * donne le tableau des cases du plateau
     * @return les cases du plateau
     */
    fun getCases(): Array<Array<Case>> = this.cases

    override fun toString(): String {
        var string = ""
        for (ligne in this.cases) {
            for (case in ligne) {
                string += when (case.getPion()) {
                    is PetitPion -> "P"
                    is MoyenPion -> "M"
                    is GrandPion -> "G"
                    else -> "."
                }
            }
            string += "\n"
        }
        return string
    }

    fun getJoueurs(joueur1: Joueur, joueur2: Joueur): String {
        var string = ""
        for (ligne in this.cases) {
            for (case in ligne) {
                string += when (case.getJoueur()) {
                    joueur1 -> "1"
                    joueur2 -> "2"
                    else -> "."
                }
            }
            string += "\n"
        }
        return string
    }

    fun setMatrice(matrice: Array<Array<Case>>) {
        this.cases = matrice
    }
}