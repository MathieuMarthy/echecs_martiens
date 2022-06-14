package projet.echecmartien.modele


public class Jeu(): InterfaceJeu {

    // UML
    private var plateau: Plateau = Plateau()
    private var joueurs: MutableList<Joueur> = mutableListOf(Joueur("1"), Joueur("2"))
    private var joueurCourant: Joueur = this.joueurs[0]

    private var coordOrigine: Coordonnee? = null
    private var coordDest: Coordonnee? = null
    private var pionArriveDeZone: Pion? = null

    // Classe
    private var statut: Boolean = false
    private var nombreCoupsSansPriseMax = 5
    private var nombreCoupsSansPrise = 0

    /**
     * getter
     * @return la coordonnée origine du déplacement
     */
    fun getCoordOrigineDeplacement(): Coordonnee? = this.coordOrigine

    /**
     * getter
     * @return la coordonnée destination du déplacement
     */
    fun getCoordDestinationDeplacement(): Coordonnee? = this.coordDest


    /**
     * setter
     * @param origine la coordonnée origine du déplacement
     */
    fun setCoordOrigineDeplacement(origine: Coordonnee) {
        this.coordOrigine = origine
    }


    /**
     * setter
     * @param destination la coordonnée destination du déplacement
     */
    fun setCoordDestinationDeplacement(destination: Coordonnee) {
        this.coordDest = destination
    }


    /** retourne le joueur courant
     * @return le joueur courant
     */
    fun getJoueurCourant(): Joueur = this.joueurCourant


    /**
     * affectation des joueurs aux cases
     * @param joueur1 premier joueur
     * @paral joueur2 second joueur
     */
    private fun initialiserJoueur(joueur1: Joueur, joueur2: Joueur) {
        this.joueurs[0] = joueur1
        this.joueurs[1] = joueur2
        this.joueurCourant = joueur1

    }

    /**
     * permet de savoir si la partie est finie ou non
     * @return true si la partie est finie, false sinon
     */
    fun arretPartie(): Boolean = this.statut

    /**
     * modifie le joueur courant
     *
     */
    fun changeJoueurCourant() {
        if (this.joueurCourant == this.joueurs[0])
            this.joueurCourant = this.joueurs[1]
        else
            this.joueurCourant = this.joueurs[0]
    }

    override fun initialiserPartie(joueur1: Joueur, joueur2: Joueur, nombreCoupsSansPriseMax: Int) {
        this.initialiserJoueur(joueur1, joueur2)

        this.plateau.initialiser()
        this.statut = true

        for (b in 0 until this.plateau.getTailleVerticale()) {
            for (a in 0 until this.plateau.getTailleHorizontale()) {
                this.plateau.getCases()[b][a].setJoueur(if (b <= 3) joueur2 else joueur1)
                }
            }

        this.nombreCoupsSansPriseMax = nombreCoupsSansPriseMax
    }

    override fun deplacementPossible(coordOrigineY: Int, coordOrigineX: Int): Boolean {
        val case = this.plateau.getCases()[coordOrigineX][coordOrigineY]
        if (case.estLibre())
            return false

        for (i in coordOrigineX - 1..coordOrigineX + 1) {
            for (j in coordOrigineY - 1..coordOrigineY + 1) {
                if (this.deplacementPossible(coordOrigineX, coordOrigineY, i, j, this.joueurCourant))
                    return true
            }
        }

        return false
    }

    override fun deplacementPossible(coordOrigineX: Int, coordOrigineY: Int, coordDestinationX: Int, coordDestinationY: Int, joueur: Joueur?): Boolean {
        if (coordDestinationX >= this.plateau.getTailleVerticale() || coordOrigineX >= this.plateau.getTailleVerticale() || coordDestinationY >= this.plateau.getTailleHorizontale() || coordOrigineY >= this.plateau.getTailleHorizontale())
            throw DeplacementException("Le déplacement est en dehors des limites")

        if (coordDestinationX == coordOrigineX && coordDestinationY == coordOrigineY)
            return false

        val depart = this.plateau.getCases()[coordOrigineX][coordOrigineY]

        if (depart.estLibre())
            return false

        val arrivee = this.plateau.getCases()[coordDestinationX][coordDestinationY]

        if (depart.getJoueur() != arrivee.getJoueur() && depart.getPion() == this.pionArriveDeZone)
            return false

        if (!arrivee.estLibre() && arrivee.getJoueur() == joueur)
            return false

        var coords = depart.getPion()?.getDeplacement(Deplacement(Coordonnee(coordOrigineX, coordOrigineY), Coordonnee(coordDestinationX, coordDestinationY)))
        coords = coords!!.subList(0, coords.size - 1)

        for (coor in coords) {
            val case = this.plateau.getCases()[coor.getX()][coor.getY()]
            if (!case.estLibre()) {
                return false
            }
        }

        return depart.getJoueur() == joueur
    }

    fun tousLesCoupsPossibles(coordOrigineX: Int, coordOrigineY: Int){
        for (b in 0 until this.plateau.getTailleVerticale()) {
            for (a in 0 until this.plateau.getTailleHorizontale()) {
                print("")
            }
        }
    }

    override fun deplacer(coordOrigineY: Int, coordOrigineX: Int, coordDestinationY: Int, coordDestinationX: Int) {
        if (!this.deplacementPossible(coordOrigineX, coordOrigineY, coordDestinationX, coordDestinationY, this.joueurCourant))
            throw DeplacementException("Le déplacement n'est pas possible")


        val depart = this.plateau.getCases()[coordOrigineX][coordOrigineY]
        val arrivee = this.plateau.getCases()[coordDestinationX][coordDestinationY]

        if (arrivee.getPion() != null) {
            this.joueurCourant.ajouterPionCaptures(arrivee.getPion()!!)
        } else {
            this.nombreCoupsSansPrise++

            if (this.nombreCoupsSansPrise == this.nombreCoupsSansPriseMax ) {
                this.statut = false
            }
        }
        arrivee.setPion(depart.getPion())
        depart.setPion(null)

        if (depart.getJoueur() != arrivee.getJoueur()) {
            this.pionArriveDeZone = arrivee.getPion()
        } else {
            this.pionArriveDeZone = null
        }

        this.changeJoueurCourant()
    }

    override fun joueurVainqueur(): Joueur? {
        this.statut = false

        if (this.joueurs[0].calculerScore() == this.joueurs[1].calculerScore()) {
            return null
        } else if (this.joueurs[0].calculerScore() < this.joueurs[1].calculerScore()) {
            return this.joueurs[1]
        }
        return this.joueurs[0]
    }

    /*
     * fonction utile pour les tests
     */
    fun getPlateau(): Plateau = this.plateau
}