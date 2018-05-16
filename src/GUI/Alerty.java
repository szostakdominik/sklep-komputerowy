package GUI;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 * Klasa, która posiada metody z odpowiednimi alertami
 * @author Dominik Stasik
 */

public class Alerty {
    public void errorMessage(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        alert.setTitle("ERROR");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        }
    }

    public void goodMessage(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
        alert.setTitle("SUKCES");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        }
    }
    
}
