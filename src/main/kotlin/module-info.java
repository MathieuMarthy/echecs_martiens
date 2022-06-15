module projet.echecmartien {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.google.gson;
    requires jdk.jfr;

    opens projet.echecmartien to javafx.fxml;
    exports projet.echecmartien;


    opens projet.echecmartien.modele to com.google.gson;
    exports projet.echecmartien.modele;

}