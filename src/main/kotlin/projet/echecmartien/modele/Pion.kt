package projet.echecmartien.modele


/**
 * Classe Pion
 */
abstract class Pion {

	/**
	 * récupère la valeur du score d'un pion
	 * @return la valeur du score
	 */
	open fun getScore(): Int {
		return 1
	}


	/**
	 * donne le chemin de coordonnées que constitue le déplacement
	 * du point de départ vers le point d'arrivée. Les déplacements autorisés sont horizontaux, verticaux, diagonaux.
	 *
	 * @param deplacement le déplacement
	 * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée.
	 * La liste ne contient pas les coordonnées du point de départ.
	 *
	 * @throws DeplacementException est levée si le déplacement n'est pas possible
	 */
	open fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
		return if (deplacement.estDiagonal()) {
			deplacement.getCheminDiagonal()
		} else if (deplacement.estHorizontal()) {
			deplacement.getCheminHorizontal()
		} else if (deplacement.estVertical()) {
			deplacement.getCheminVertical()
		} else {
			throw DeplacementException("Le déplacement n'est pas bon")
		}
	}
}