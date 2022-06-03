package projet.echecmartien.modele

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows


internal class JeuTest {

    private var jeu: Jeu? = null
    private var p1: Joueur? = null
    private var p2: Joueur? = null


    @BeforeEach
    fun setUp() {
        jeu = Jeu()
        p1 = Joueur("Tony chad")
        p2 = Joueur("aaaah ouaiiis Romain la ruine")
    }

    @Test
    fun Test_set_getCoordOrigineDestinationDeplacement() {
        val coor1 = Coordonnee(3, 8)
        val coor2 = Coordonnee(3, 2)

        assertEquals(null, jeu!!.getCoordOrigineDeplacement())
        assertEquals(null, jeu!!.getCoordDestinationDeplacement())

        jeu!!.setCoordOrigineDeplacement(coor1)
        jeu!!.setCoordDestinationDeplacement(coor2)

        assertEquals(coor1, jeu!!.getCoordOrigineDeplacement())
        assertEquals(coor2, jeu!!.getCoordDestinationDeplacement())
    }


    @Test
    fun Test_getJoueurCourant_changeCourant() {

        jeu!!.initialiserPartie(p1!!, p2!!, 5)

        assertEquals(p1, jeu!!.getJoueurCourant())
        jeu!!.changeJoueurCourant()
        assertEquals(p2, jeu!!.getJoueurCourant())
    }

    @Test
    fun Test_initialiserPartie() {
        jeu!!.initialiserPartie(p1!!, p2!!, 5)
        assertEquals("GGM.\n" +
                             "GMP.\n" +
                             "MPP.\n" +
                             "....\n" +
                             "....\n" +
                             ".PPM\n" +
                             ".PMG\n" +
                             ".MGG\n",
                        jeu!!.getPlateau().toString())
        assertEquals("2222\n" +
                             "2222\n" +
                             "2222\n" +
                             "2222\n" +
                             "1111\n" +
                             "1111\n" +
                             "1111\n" +
                             "1111\n",
                            jeu!!.getPlateau().getJoueurs(p1!!, p2!!))
    }


    @Test
    fun Test_partie() {
        // initialisation
        jeu!!.initialiserPartie(p1!!, p2!!, 5)

        // test d'un déplacment qui n'est pas possible
        assertThrows<DeplacementException> { jeu!!.deplacer(0,0,0,1) }

        // test de du statut de la partie
        assertTrue(jeu!!.arretPartie())

        // déplacement d'un petit sur une case vide
        val pitit_pion = jeu!!.getPlateau().getCases()[2][2].getPion()
        jeu!!.changeJoueurCourant()
        assertDoesNotThrow { jeu!!.deplacer(2, 2, 3, 3) }
        assertEquals(pitit_pion, jeu!!.getPlateau().getCases()[3][3].getPion())
        assertEquals(null, jeu!!.getPlateau().getCases()[2][2].getPion())

        // sauvegarde du pion pour voir si il est bien capturé
        val pion = jeu!!.getPlateau().getCases()[3][3].getPion()

        // déplacement d'un grand pion sur le petit pion précédemment déplacé
        val moyen_pion = jeu!!.getPlateau().getCases()[5][3].getPion()
        assertDoesNotThrow {jeu!!.deplacer(3,5,3,3)}
        assertEquals(moyen_pion,jeu!!.getPlateau().getCases()[3][3].getPion())

        // vérification de la capture du pion
        assertEquals(1, p1!!.getNbPionsCaptures())
        assertEquals(setOf(pion), p1!!.getPionsCaptures())

        // erreur de renvoyer le pion dans son camp d'origine
        assertThrows<DeplacementException> { jeu!!.deplacer(3, 3, 3, 5) }

        // erreur en dehors des limites
        assertThrows<DeplacementException> { jeu!!.deplacer(3, 3, 12, 31) }

        // erreur au meme endroit
        assertThrows<DeplacementException> { jeu!!.deplacer(3, 3, 3, 3) }

        // erreur case vide
        assertThrows<DeplacementException> { jeu!!.deplacer(3, 3, 12, 31) }

        // si le joueur 1 gagne ou pas la partie
        assertEquals(p1!!, jeu!!.joueurVainqueur())

    }

    @Test
    fun Test_deplacementPossible() {
        assertFalse(jeu!!.deplacementPossible(3, 7))
    }


}