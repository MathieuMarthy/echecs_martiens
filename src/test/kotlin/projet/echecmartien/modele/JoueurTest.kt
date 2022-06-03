package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class JoueurTest {

    @Test
    fun getPseudo() {
        val pseudo = "Tonikaku kawaii c'est trop bien"
        val p1 = Joueur(pseudo)
        assertEquals(pseudo, p1.getPseudo())
    }
}