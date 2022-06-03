package projet.echecmartien.modele

import kotlin.math.abs


/**
 * cette classe permet de tester les déplacements sur le plateau de jeu
 *
 */

class Deplacement(private var origine: Coordonnee, private var destination: Coordonnee) {

    /**
     * dans le constructeur la validité du déplacement dans la grille est testée
     *@throws DeplacementException si le déplacement n'est ni horizontal, ni vertical est ni diagonal
     * les autres cas lèvent une IllegalArgumentException (peut être mis en place avec "require")
     */


    /**
     * getter
     * @return la destination de ce déplacement
     */
    fun getDestination(): Coordonnee = this.destination


    /**
     * getter
     * @return l'origine de ce déplacement
     */
    fun getOrigine(): Coordonnee = this.origine

    /**
     *méthode qui permet de tester si le déplacement est horizontal
     * @return true si le déplacement est horizontal, false sinon
     */
    fun estHorizontal(): Boolean = this.origine.getY() == this.destination.getY()

    /**
     *méthode qui permet de tester si le déplacement est vertical
     * @return true si le déplacement est vertical, false sinon
     */
    fun estVertical(): Boolean = this.origine.getX() == this.destination.getX()

    /**
     * méthode qui permet de tester si le déplacement est diagonal
     * @return true si le déplacement est diagonal, false sinon
     */
    fun estDiagonal(): Boolean = abs(this.destination.getX() - this.origine.getX()) == abs(this.destination.getY() - this.origine.getY())

    /**
     *méthode qui permet de calculer le nombre de case d'un déplacement
     * @return le nombre de case que le pion sera déplacée
     */
    fun longueur(): Int {
        return if (this.estDiagonal()) {
            abs(this.destination.getX() - this.origine.getX())
        } else {
            abs((this.destination.getX() - this.origine.getX() - (this.destination.getY() - this.origine.getY())))
        }
    }


    /**
     * méthode qui permet de déterminer le sens d'un déplacement vertical
     *
     *@return true si le déplacement est positif, false sinon
     */
    fun estVerticalPositif(): Boolean = this.origine.getY() > this.destination.getY()

    /**
     * méthode qui permet de déterminer le sens d'un déplacement horizontal
     *
     * @return true si le déplacement est positif, false sinon
     */
    fun estHorizontalPositif():Boolean = this.origine.getX() < this.destination.getX()

    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est positif en X et en Y
     *
     * @return true si le déplacement est positif en X et Y, false sinon
     */
    fun estDiagonalPositifXPositifY(): Boolean = this.estHorizontalPositif() && this.estVerticalPositif()
    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est négatif en X et positif en Y
     *
     * @return true si le déplacement est négatif en X et positif en Y, false sinon
     */
    fun estDiagonalNegatifXPositifY(): Boolean = !this.estHorizontalPositif() && this.estVerticalPositif()

    /**
     *
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est positif en X et négatif en Y
     *
     * @return true si le déplacement est positif en X et négatif en Y, false sinon
     */
    fun estDiagonalPositifXNegatifY(): Boolean = this.estHorizontalPositif() && !this.estVerticalPositif()

    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est négatif en X et négatif en Y
     *
     * @return true si le déplacement est négatif en X et négatif en Y, false sinon
     */
    fun estDiagonalNegatifXNegatifY(): Boolean = !this.estHorizontalPositif() && !this.estVerticalPositif()

    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplacement demandé est vertical.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée
     * si le déplacement est vertical. Le point de départ n'est pas stocké dans la liste.
     * @throws DeplacementException est levée si le déplacement n'est pas vertical
     */
    fun getCheminVertical(): List<Coordonnee> {
        if (!this.estVertical()) {
            throw DeplacementException("Le déplacement n'est pas vertical")
        }
        val liste_coordonnees = mutableListOf<Coordonnee>()

        val debut = this.origine.getY()
        val fin = this.destination.getY()

        if (this.origine.getY() < this.destination.getY()) {
            for (y in debut + 1..fin) {
                liste_coordonnees.add(Coordonnee(this.origine.getX(), y))
            }
        } else {
            for (y in debut - 1 downTo fin) {
                liste_coordonnees.add(Coordonnee(this.origine.getX(), y))
            }
        }
        return liste_coordonnees
    }


    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplaceme{"origine Y dépasse"}nt demandé est horizontal.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée.
     * Le point de départ n'est pas stocké dans la liste.
     * si le déplacement est horizontal
     * @throws DeplacementException est levée si le déplacement n'est pas horizontal
     */
    fun getCheminHorizontal(): List<Coordonnee> {
        if (!this.estHorizontal()) {
            throw DeplacementException("Le déplacement n'est pas horizontal")
        }
        val liste_coordonnees = mutableListOf<Coordonnee>()

        val debut = this.origine.getX()
        val fin = this.destination.getX()

        if (this.origine.getX() < this.destination.getX()) {
            for (x in debut + 1..fin) {
                liste_coordonnees.add(Coordonnee(x, this.origine.getY()))
            }
        } else {
            for (x in debut - 1 downTo fin) {
                liste_coordonnees.add(Coordonnee(x, this.origine.getY()))
            }
        }
        return liste_coordonnees
    }


    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplacement demandé est diagonal.
     * Le point de départ n'est pas stocké dans la liste.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée
     * si le déplacement est diagonal
     * @throws DeplacementException est levée si le déplacement n'est pas diagonal
     */
    fun getCheminDiagonal(): List<Coordonnee> {
        if (!this.estDiagonal()) {
            throw DeplacementException("Le déplacement n'est pas diagonal")
        }

        val liste_coordonnees = mutableListOf<Coordonnee>()

        val debut = this.origine.getX()
        val fin = this.destination.getX()

        if (this.origine.getX() < this.destination.getX()) {
            for (x in debut + 1..fin) {
                liste_coordonnees.add(Coordonnee(x, x))
            }
        } else {
            for (x in debut - 1 downTo fin) {
                liste_coordonnees.add(Coordonnee(x, x))
            }
        }
        return liste_coordonnees
    }


}
