package projet.echecmartien

import projet.echecmartien.modele.*
import projet.echecmartien.modele_graphique.Graphique

fun main() {
    var g = Graphique()
    println(g.getPPCourante())
    println(g.ppPrecedente())
    println(g.getPPCourante())
}