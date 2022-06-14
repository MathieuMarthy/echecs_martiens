package projet.echecmartien.modele



class Case {
    private var pion: Pion? = null
    private var joueur: Joueur? = null

    /**
     * teste si une case contient un pion ou non
     * @return true si la case ne contient pas un pion, false sinon.
     */
    fun estLibre(): Boolean = this.pion == null

    /** getter
     * @return le joueur associé à la case
     */
    fun getJoueur(): Joueur? = this.joueur

    /** setter
     * @param joueur qui est associé à la case
     */
    fun setJoueur(joueur: Joueur?) {
        this.joueur = joueur
    }

    /** getter
     * @return le pion associé à la case
     */
    fun getPion(): Pion? = this.pion

    /** setter
     * @param pion qui est associé à la case
     */
    fun setPion(pion: Pion?) {
        this.pion = pion
    }

    override fun toString(): String {
        return when(this.pion) {
            is MoyenPion -> "M"
            is GrandPion -> "G"
            is PetitPion -> "P"
            else -> "."
        }
    }
}
