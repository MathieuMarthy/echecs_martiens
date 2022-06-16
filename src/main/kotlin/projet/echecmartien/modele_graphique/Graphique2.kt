package projet.echecmartien.modele_graphique

import javafx.scene.image.Image
import projet.echecmartien.AppliJeuEchecMartien
import java.io.File
import java.lang.IllegalArgumentException

class Graphique2 {
    public var joueur1 = JoueurGraphique()
    public var joueur2 = JoueurGraphique()
    private var liste_pp: MutableList<Image> = mutableListOf()
    private var actuel = 0

    init {
        this.refreshPP()
    }

    /*
     * recharge toutes les pp qui sont dans le dossier
     */
    fun refreshPP() {
        this.liste_pp = mutableListOf()
        File(AppliJeuEchecMartien::class.java.getResource("pp/").file).walk().forEach {
            if (it.isFile) {
                this.liste_pp.add(Image("file:" + it.toString()))
            }
        }
    }

    /*
     * replace l'image courante par l'image suivante
     */
    fun ppSuivante() {
        this.actuel += 1
        if (this.actuel > this.liste_pp.size - 1)
            this.actuel = 0
    }

    /*
     * replace l'image courante par l'image precedente
     */
    fun ppPrecedente() {
        this.actuel -= 1
        if (this.actuel < 0)
            this.actuel = this.liste_pp.size - 1
    }

    /*
     * donne le chemin de l'image actuel
     */
    fun getPPCourante(): Image = this.liste_pp[this.actuel]

    /*
     * donne le numero de la pp
     */
    fun getCourant(): Int = this.actuel

    /*
     * met la pp numero-ième en courant
     */
    fun setCourant(numero: Int) {
        if (numero > this.liste_pp.size || numero < 0) {
            throw IllegalArgumentException("Le numero doit etre entre 0 et le nombre de pp - 1")
        }
        this.actuel = numero
    }

    /*
     * retourne la liste des pp
     */
    fun getListePP(): MutableList<Image> = this.liste_pp

    override fun toString(): String {
        return "$joueur1,\n$joueur2,\n$liste_pp"
    }
}