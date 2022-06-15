package projet.echecmartien.modele


class IA(pseudo: String, private var jeu: Jeu): Joueur(pseudo) {

    fun deplace(): List<Int> {
        val plateau = jeu.getPlateau().getCases()
        // récupère tous les pions de l'IA
        val pions = mutableListOf<Coordonnee>()
        for ((i, ligne) in plateau.withIndex()) {
            for ((j, case) in ligne.withIndex()) {
                if (case.getJoueur() is IA && case.getPion() != null) {
                    pions.add(Coordonnee(i, j))
                }
            }
        }

        // récupère tous les coups possible
        val coupsPossibles = mutableListOf<MutableList<Coordonnee>>()
        for (pion in pions) {
            coupsPossibles.add(jeu.tousLesCoupsPossibles(pion.getX(), pion.getY()))
        }

        // si l'IA peut manger un pion elle le mange
        for ((i, coups) in coupsPossibles.withIndex()) {
            for (coor in coups) {
                val case = plateau[coor.getX()][coor.getY()]
                if (case.getPion() != null && case.getJoueur() != this) {
                    return listOf(pions[i].getY(), pions[i].getX(), coor.getY(), coor.getX())
                }
            }
        }

        // sinon elle joue aléatoirement
        while (true) {
            val pion = (0 until coupsPossibles.size).random()
            if (coupsPossibles[pion].size == 0) {
                continue
            }
            val coor = coupsPossibles[pion].random()
            return listOf(pions[pion].getY(), pions[pion].getX(), coor.getY(), coor.getX())
        }
    }
}