package projet.echecmartien.modele

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class DeplacementTest {

    private var deplacement_vertical_plus = Deplacement(Coordonnee(1, 1), Coordonnee(1, 5))
    private var deplacement_vertical_moins = Deplacement(Coordonnee(1, 5), Coordonnee(1, 1))

    private var deplacement_horizontal_plus = Deplacement(Coordonnee(1, 1), Coordonnee(5, 1))
    private var deplacement_horizontal_moins = Deplacement(Coordonnee(5, 1), Coordonnee(1, 1))

    private var deplacement_diagonal_plus = Deplacement(Coordonnee(1, 1), Coordonnee(5, 5))
    private var deplacement_diagonal_moins = Deplacement(Coordonnee(5, 5), Coordonnee(1, 1))



    @Test
    fun Test_getCheminVertical() {
        var coordonnees = listOf<Coordonnee>(Coordonnee(1, 2), Coordonnee(1, 3), Coordonnee(1, 4), Coordonnee(1, 5))
        assertEquals(coordonnees, deplacement_vertical_plus.getCheminVertical())

        coordonnees = listOf<Coordonnee>(Coordonnee(1, 4), Coordonnee(1, 3), Coordonnee(1, 2), Coordonnee(1, 1))
        assertEquals(coordonnees, deplacement_vertical_moins.getCheminVertical())

        assertThrows<DeplacementException> {deplacement_diagonal_moins.getCheminHorizontal()}
    }

    @Test
    fun Test_getCheminHorizontal() {
        var coordonnees = listOf<Coordonnee>(Coordonnee(2, 1), Coordonnee(3, 1), Coordonnee(4, 1), Coordonnee(5, 1))
        assertEquals(coordonnees, deplacement_horizontal_plus.getCheminHorizontal())

        coordonnees = listOf<Coordonnee>(Coordonnee(4, 1), Coordonnee(3, 1), Coordonnee(2, 1), Coordonnee(1, 1))
        assertEquals(coordonnees, deplacement_horizontal_moins.getCheminHorizontal())

        assertThrows<DeplacementException> {deplacement_diagonal_moins.getCheminHorizontal()}

    }

    @Test
    fun Test_getCheminDiagonal() {
        var coordonnees = listOf<Coordonnee>(Coordonnee(2, 2), Coordonnee(3, 3), Coordonnee(4, 4), Coordonnee(5, 5))
        assertEquals(coordonnees, deplacement_diagonal_plus.getCheminDiagonal())

        coordonnees = listOf<Coordonnee>(Coordonnee(4, 4), Coordonnee(3, 3), Coordonnee(2, 2), Coordonnee(1, 1))
        assertEquals(coordonnees, deplacement_diagonal_moins.getCheminDiagonal())

    }

    @Test
    fun Test_estHorizontal() {
        assertEquals(true, deplacement_horizontal_plus.estHorizontal())
        assertEquals(false, deplacement_vertical_moins.estHorizontal())

        assertThrows<DeplacementException> {deplacement_vertical_moins.getCheminHorizontal()}

    }


    companion object {

        @JvmStatic
        fun data_Test_estVertical(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(false, Deplacement(Coordonnee(1, 1), Coordonnee(5, 1))),
                Arguments.of(true, Deplacement(Coordonnee(1, 5), Coordonnee(1, 1)))
            )
        }

        @JvmStatic
        fun data_Test_estDiagonal(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(true, Deplacement(Coordonnee(0, 4), Coordonnee(3, 1))),
                Arguments.of(false, Deplacement(Coordonnee(1, 5), Coordonnee(1, 1)))
            )
        }

        @JvmStatic
        fun data_Test_estVerticalPositif(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(true, Deplacement(Coordonnee(1, 5), Coordonnee(1, 1))),
                Arguments.of(false, Deplacement(Coordonnee(1, 5), Coordonnee(1, 7)))
            )
        }

        @JvmStatic
        fun data_Test_estHorizontalPositif(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(true, Deplacement(Coordonnee(1, 1), Coordonnee(5, 1))),
                Arguments.of(false, Deplacement(Coordonnee(5, 1), Coordonnee(1, 1)))
            )
        }

        @JvmStatic
        fun data_Test_longueur(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(4, Deplacement(Coordonnee(1, 1), Coordonnee(5, 1))),
                Arguments.of(4, Deplacement(Coordonnee(5, 5), Coordonnee(1, 1))),
                Arguments.of(4, Deplacement(Coordonnee(1, 5), Coordonnee(1, 1))),
                Arguments.of(4, Deplacement(Coordonnee(1, 5), Coordonnee(1, 1)))
            )
        }
    }

    @ParameterizedTest
    @MethodSource("data_Test_estVertical")
    fun Test_estVertical(oracle: Boolean, deplacement: Deplacement) {
        assertEquals(oracle, deplacement.estVertical())
    }

    @ParameterizedTest
    @MethodSource("data_Test_estDiagonal")
    fun Test_estDiagonal(oracle: Boolean, deplacement: Deplacement) {
        assertEquals(oracle, deplacement.estDiagonal())
    }

    @ParameterizedTest
    @MethodSource("data_Test_estVerticalPositif")
    fun Test_estVerticalPositif(oracle: Boolean, deplacement: Deplacement) {
        assertEquals(oracle, deplacement.estVerticalPositif())
    }

    @ParameterizedTest
    @MethodSource("data_Test_estHorizontalPositif")
    fun Test_estHorizontalPositif(oracle: Boolean, deplacement: Deplacement) {
        assertEquals(oracle, deplacement.estHorizontalPositif())
    }

    @ParameterizedTest
    @MethodSource("data_Test_longueur")
    fun Test_longueur(oracle: Int, deplacement: Deplacement) {
        assertEquals(oracle, deplacement.longueur())
    }

    @ParameterizedTest
    @MethodSource("data_Test_estDiagonal")
    fun Test_estDiagonalPositifXPositifY(oracle: Boolean, deplacement: Deplacement) {
        assertEquals(oracle, deplacement.estDiagonalPositifXPositifY())
    }

    @Test
    fun Test_estDiagonalNegatifXPositifY() {
        assertEquals(true, Deplacement(Coordonnee(2, 3), Coordonnee(1, 0)).estDiagonalNegatifXPositifY())
    }

    @Test
    fun Test_estDiagonalPositifXNegatifY() {
        assertEquals(true, Deplacement(Coordonnee(0, 1), Coordonnee(2, 1)).estDiagonalPositifXNegatifY())
    }

    @Test
    fun Test_estDiagonalNegatifXNegatifY() {
        assertEquals(true, Deplacement(Coordonnee(2, 0), Coordonnee(0, 2)).estDiagonalNegatifXNegatifY())
    }

    @Test
    fun Test_getDestination_getOrigine() {
        val ori = Coordonnee(1, 1)
        val des = Coordonnee(2, 2)
        val depla = Deplacement(ori, des)
        assertEquals(ori, depla.getOrigine())
        assertEquals(des, depla.getDestination())
    }
}