package projet.echecmartien.modele



class MoyenPion(): GrandPion() {

    override fun getScore(): Int {
        return 2
    }

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.longueur() > 2) {
            throw DeplacementException("Ce déplacement n'est pas possible")
        }

        return super.getDeplacement(deplacement)
    }
}