package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgHomeBtn;

    @FXML
    void initialize() {
        assert imgHomeBtn != null : "fx:id=\"imgHomeBtn\" was not injected: check your FXML file 'welcome.fxml'.";

    }
}
