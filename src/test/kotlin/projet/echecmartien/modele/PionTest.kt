package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PionTest {
    private var pitit_pion = PetitPion()
    private var moyon_pion = MoyenPion()
    private var gras_pion = GrandPion()

    companion object {
        @JvmStatic
        fun data_getScore(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(1, PetitPion()),
                Arguments.of(2, MoyenPion()),
                Arguments.of(3, GrandPion())
            )
        }
    }

    @ParameterizedTest
    @MethodSource("data_getScore")
    fun getScore(oracle: Int, pion: Pion) {
        assertEquals(oracle, pion.getScore())
    }

    @Test
    fun Test_getDeplacement() {
        /*Grand pion*/
        assertThrows<DeplacementException> { gras_pion.getDeplacement(Deplacement(Coordonnee(1, 1), Coordonnee(2, 3))) }
        assertDoesNotThrow { gras_pion.getDeplacement(Deplacement(Coordonnee(1, 1), Coordonnee(2, 2))) }

        /*Moyen pion*/
        assertThrows<DeplacementException> { moyon_pion.getDeplacement(Deplacement(Coordonnee(3,5),Coordonnee(3,2))) }
        assertThrows<DeplacementException> { moyon_pion.getDeplacement(Deplacement(Coordonnee(3,5),Coordonnee(0,2))) }
        assertDoesNotThrow {moyon_pion.getDeplacement(Deplacement(Coordonnee(3,5),Coordonnee(3,3)))}

        /*Petit pion*/
        assertThrows<DeplacementException> { pitit_pion.getDeplacement(Deplacement(Coordonnee(0,0),Coordonnee(0,1))) }
        assertThrows<DeplacementException> { pitit_pion.getDeplacement(Deplacement(Coordonnee(0,0),Coordonnee(2,2))) }
        assertDoesNotThrow {pitit_pion.getDeplacement(Deplacement(Coordonnee(0,0),Coordonnee(1,1)))}

    }
}