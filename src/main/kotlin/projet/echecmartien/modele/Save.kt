package projet.echecmartien.modele

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.IllegalArgumentException

class Save {
    private var path = System.getProperty("user.dir") + "/Sauvegardes"

    fun importer(nomSauvegarde: String): Jeu {
        if (!File("$path/$nomSauvegarde.json").exists()) {
            throw IllegalArgumentException("La sauvegarde $nomSauvegarde n'existe pas")
        }

        val file = FileReader("$path/$nomSauvegarde.json")
        val jeu = Gson().fromJson(file, Jeu::class.java)

        file.close()
        return jeu
    }

    fun exporter(jeu: Jeu, nomSauvegarde: String) {
        val fichier = File("$path/$nomSauvegarde.json")
        println("$path/$nomSauvegarde.json")
        if (!fichier.exists()) {
            fichier.createNewFile()
        }

        val fichier_writer = FileWriter("$path/$nomSauvegarde.json")
        Gson().toJson(jeu, fichier_writer)
        //GsonBuilder().serializeNulls().create().toJson(jeu,fichier_writer)
        fichier_writer.flush()
        fichier_writer.close()

    }
}