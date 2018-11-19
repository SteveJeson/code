package wind;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.awt.*;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private TextField text;
    @FXML
    private ComboBox comboBox_API;
    @FXML
    private Label lab;

    @FXML protected void parse(){
        System.out.println(text);
        System.out.println(lab);
        System.out.println(button);
    }
}
