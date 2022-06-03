package projet.echecmartien.modele

class PetitPion(): Pion() {


    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.longueur() > 1 || !deplacement.estDiagonal()) {
            throw DeplacementException("Ce déplacement n'est pas possible")
        }

        return deplacement.getCheminDiagonal()
    }

}