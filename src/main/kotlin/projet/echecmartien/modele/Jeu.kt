package projet.echecmartien.modele

import com.google.gson.Gson
import com.google.gson.JsonObject
import projet.echecmartien.AppliJeuEchecMartien
import java.io.*
import java.lang.Exception


public class Jeu(): InterfaceJeu {

    // UML
    private var plateau: Plateau = Plateau()
    var joueurs: MutableList<Joueur> = mutableListOf(Joueur("1"), Joueur("2"))
    private var joueurCourant: Joueur = this.joueurs[0]

    private var coordOrigine: Coordonnee? = null
    private var coordDest: Coordonnee? = null
    private var pionArriveDeZone: Pion? = null

    // Classe
    private var statut: Boolean = false
    private var nombreCoupsSansPriseMax = 5
    private var nombreCoupsSansPrise = 0

    // utile
    private var path = System.getProperty("user.dir") + "/Sauvegardes"
    private var sauvegardes: MutableList<String> = mutableListOf()

    init {
        File("$path/").walk().forEach {
            if (it.isFile) {
                this.sauvegardes.add(it.toString())
            }
        }
    }

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

    fun tousLesCoupsPossibles(coordOrigineX: Int, coordOrigineY: Int): MutableList<Coordonnee> {
        val coups = mutableListOf<Coordonnee>()
        for (b in 0 until this.plateau.getTailleVerticale()) {
            for (a in 0 until this.plateau.getTailleHorizontale()) {
                try {
                    if (this.deplacementPossible(coordOrigineX, coordOrigineY, b, a, this.joueurs[1])) {
                        coups.add(Coordonnee(b, a))
                    }
                } catch (_: Exception) {

                }
            }
        }
        return coups
    }

    override fun deplacer(coordOrigineY: Int, coordOrigineX: Int, coordDestinationY: Int, coordDestinationX: Int) {
        if (!this.deplacementPossible(coordOrigineX, coordOrigineY, coordDestinationX, coordDestinationY, this.joueurCourant))
            throw DeplacementException("Le déplacement n'est pas possible")


        val depart = this.plateau.getCases()[coordOrigineY][coordOrigineX]
        val arrivee = this.plateau.getCases()[coordDestinationY][coordDestinationX]

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

    fun sauvegarderPartie(numeroSauvegarde: String) {

        // création du json
        var string_json = "{"

        // sauvegarde des joueurs
        for (i in 0 until this.joueurs.size) {
            string_json += """"joueur${i + 1}_pseudo":"${this.joueurs[i].getPseudo()}""""

            var string_pions_joueur = ""
            for (pion in this.joueurs[i].getPionsCaptures()) {
                string_pions_joueur += when (pion) {
                    is MoyenPion -> "M"
                    is GrandPion -> "G"
                    else -> "P"
                }
            }
            string_json += ""","joueur${i + 1}_pions": "$string_pions_joueur","""
        }

        // sauvegarde joueur courant
        string_json += """"joueur_courant":"${this.joueurCourant.getPseudo()}""""

        // plateau
        string_json += ""","plateau":""""
        var arrivedezone = ""
        for ((i, ligne) in this.plateau.getCases().withIndex()) {
            for ((j, case) in ligne.withIndex()) {
                if (case.getPion() == this.pionArriveDeZone) {
                    arrivedezone = "$i,$j"
                }
                string_json += case.toString()
            }
        }

        // arrive de zone
        string_json += """","arrivedezone":"$arrivedezone"}"""

        // vérification du fichier
        val check_file = File(AppliJeuEchecMartien::class.java.getResource("Sauvegardes/$numeroSauvegarde.json").file)
        if (!check_file.exists()) {
            check_file.createNewFile()
        }

        val file = FileWriter(AppliJeuEchecMartien::class.java.getResource("Sauvegardes/$numeroSauvegarde.json").file)
        file.write(string_json)
        file.flush()
        file.close()
    }

    fun chargerPartie(numeroSauvegarde: String) {
        // vérification si le fichier existe
        val file = File(AppliJeuEchecMartien::class.java.getResource("Sauvegardes/$numeroSauvegarde.json").file)
        if (!file.exists()) {
            throw FileNotFoundException("Le fichier de sauvegarde n'existe pas")
        }

        // récupération des données
        val reader = FileReader(AppliJeuEchecMartien::class.java.getResource("Sauvegardes/$numeroSauvegarde.json").file)
        val json = Gson().fromJson(reader, JsonObject::class.java)

        // -- Plateau
        // création d'une matrice vide
        val tempPlateau = Array(this.plateau.getTailleVerticale()) { Array(this.plateau.getTailleHorizontale()) { Case() } }

        // remplissage de la matrice
        for ((i, ligne) in tempPlateau.withIndex()) {
            for ((j, case) in ligne.withIndex()) {
                val pion = when(json["plateau"].asString[i * 4 + j].toString()) {
                    "M" -> MoyenPion()
                    "G" -> GrandPion()
                    "P" -> PetitPion()
                    else -> null
                }
                tempPlateau[i][j].setPion(pion)
            }
        }
        this.plateau.setMatrice(tempPlateau)

        // -- arrive de zone
        val x_y = json["arrivedezone"].asString.split(",")
        this.pionArriveDeZone = this.plateau.getCases()[x_y[0].toInt()][x_y[1].toInt()].getPion()

        // -- joueurs
        val p1 = Joueur(json["joueur1_pseudo"].asString)
        val p2 = Joueur(json["joueur2_pseudo"].asString)

        for ((i, p) in listOf(p1, p2).withIndex()) {
            val pions = json["joueur${i + 1}_pions"].asString
            val captures = mutableSetOf<Pion>()
            for (pion in pions) {
                captures.add(when(pion.toString()) {
                    "M" -> MoyenPion()
                    "G" -> GrandPion()
                    else -> PetitPion()
                })
            }
            p.setPionsCaptures(captures)
        }
        this.joueurs = mutableListOf(p1, p2)

        // -- joueur courant
        val courant = json["joueur_courant"].asString
        this.joueurCourant = this.joueurs[if (p1.getPseudo() == courant) 0 else 1]
    }

    fun IAjoue() {
        if (this.joueurCourant is IA) {
            val deplacement = (this.joueurCourant as IA).deplace()
            this.deplacer(deplacement[0], deplacement[1], deplacement[2], deplacement[3])
        }
    }



}