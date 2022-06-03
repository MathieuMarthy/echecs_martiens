package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach

internal class CoordonneeTest {

    private var coor: Coordonnee? = null

    @BeforeEach
    fun start() {
        coor = Coordonnee(35, 69)
    }

    @Test
    fun Test_getX() {
        assertEquals(35, coor!!.getX())
    }

    @Test
    fun Test_getY() {
        assertEquals(69, coor!!.getY())
    }

    @Test
    fun TestEgalite(){
        assertTrue { Coordonnee(3,4) == Coordonnee(3,4) }
        assertFalse { Coordonnee(2,5) == Coordonnee(1,1) }
    }

    @Test
    fun TestStringCoordonnees(){
        assertEquals("(35, 69)", coor.toString())
    }
}