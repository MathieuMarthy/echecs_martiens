package projet.echecmartien.Vue

import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import java.io.FileInputStream

class MainVue: Pane() {

    val path = System.getProperty("user.dir")

    var image = Image(FileInputStream("$path/src/main/kotlin/projet/echecmartien/Vue/357485-Mars-space-universe-artwork-planet-space_art.jpg"))

    var imageView = ImageView(image)
    var button_test = Button("Test")



    init {
        imageView.fitHeight = 715.0
        imageView.fitWidth = 400.0
        this.children.add(imageView)
        this.children.add(button_test)
    }


}