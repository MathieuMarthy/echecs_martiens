package projet.echecmartien.modele


class IA(pseudo: String, private var jeu: Jeu): Joueur(pseudo) {

    fun deplace() {
        // récupère tous les pions de l'IA
        val pions = mutableListOf<Coordonnee>()
        for ((i, ligne) in jeu.getPlateau().getCases().withIndex()) {
            for ((j, case) in ligne.withIndex()) {
                if (case.getJoueur() is IA && case.getPion() != null) {
                    pions.add(Coordonnee(i, j))
                }
            }
        }

        val coupsPossibles = mutableListOf<MutableList<MutableList<Coordonnee>>>()
        for (i in 0 until pions.size) {
            coupsPossibles.add(mutableListOf())
        }
        println(coupsPossibles)


        for ((i, pion) in pions.withIndex()) {
            coupsPossibles[i].add(jeu.tousLesCoupsPossibles(pion.getX(), pion.getY()))
        }



    }
}