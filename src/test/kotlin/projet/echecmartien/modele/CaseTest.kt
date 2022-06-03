package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class CaseTest {

    private var case: Case? = null

    @BeforeEach
    fun start() {
        case = Case()
        case!!.setJoueur(null)
        case!!.setPion(null)
    }

    @Test
    fun Test_estLibre_set_getPion() {
        val πont = MoyenPion()
        assertTrue(case!!.estLibre())
        case!!.setPion(πont)
        assertFalse(case!!.estLibre())
        assertEquals(πont, case!!.getPion())
    }

    @Test
    fun Test_set_getJoueur() {
        assertEquals(null, case!!.getJoueur())
        val p1 = Joueur("Albert uwu stein")
        case!!.setJoueur(p1)
        assertEquals(p1, case!!.getJoueur())
    }
}