package projet.echecmartien.modele

class Joueur(private var pseudo: String) {

    private var pionsCaptures: MutableSet<Pion> = mutableSetOf()

    /**
     * récupére la liste des pions capturés
     * @return la liste des pions capturés qui est vide si aucun pion n'a été capturé
     */
    fun getPionsCaptures(): Set<Pion> = this.pionsCaptures

    /**
     * ajout à la liste d'un pion qui a été capturé
     * @param pion à ajouter
     */
    fun ajouterPionCaptures(pion: Pion) {
        this.pionsCaptures.add(pion)
    }


    /**
     * permet de connaître le nombre de pions capturés
     * @return le nombre de pions capturés
     */
    fun getNbPionsCaptures(): Int = this.pionsCaptures.size


    /**
     * récupère la valeur du pseudo
     *
     * @return la valeur du pseudo
     */
    fun getPseudo(): String = this.pseudo



    /**
     * calcule le score du joueur
     * @return le score du joueur
     */
    fun calculerScore(): Int {
        var sum = 0
        for (pion in this.pionsCaptures) {
            sum += pion.getScore()
        }

        return sum
    }

    fun setPionsCaptures(pions: MutableSet<Pion>) {
        this.pionsCaptures = pions
    }

}
