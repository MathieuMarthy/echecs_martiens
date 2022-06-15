package projet.echecmartien.modele_graphique

import javafx.scene.image.ImageView
import projet.echecmartien.AppliJeuEchecMartien
import java.io.File

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class JoueurGraphique(pp: String = "bob_razowski.png", couleur: String = "#FFAAFF") {
    private var pp = AppliJeuEchecMartien::class.java.getResource("pp/$pp").toExternalForm()
    private var couleur: String = couleur

    fun setPP(string: String) {
        this.pp = AppliJeuEchecMartien::class.java.getResource("pp/$string").toExternalForm()
    }

    fun setCouleur(couleur: String) {
        this.couleur = couleur
    }

    override fun toString(): String {
        return "$pp, $couleur"
    }

}