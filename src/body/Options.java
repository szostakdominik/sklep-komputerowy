package body;

import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * Klasa zawiera metodę powrotu do poprzedniego okna
 * @author Witold Wątroba
 */
public class Options {
    public void back_Option(Button closeButton){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
}
