package projet.echecmartien.modele_graphique

import javafx.scene.image.ImageView
import projet.echecmartien.AppliJeuEchecMartien

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class JoueurGraphique(pp: String = "0.png", couleur: String = "FFAAFF") {
    private var path = System.getProperty("user.dir") + "/Graphique"
    private var pp = ImageView(AppliJeuEchecMartien::class.java.getResource("Sauvegardes/$pp").toExternalForm())
    private var couleur: String = couleur

    fun setPP(string: String) {
        this.pp = ImageView(AppliJeuEchecMartien::class.java.getResource("Sauvegardes/$string").toExternalForm())
    }

    fun setCouleur(couleur: String) {
        this.couleur = couleur
    }
}