/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.Db_operations;
import body.Java_Mail;
import body.Options;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * Klasa, która umożliwia zakup danego przedmiotu i wysyła szczegóły zakupu na e_mail klienta
 * @author Dominik Stasik
 */
public class KupnoFXMLController {

    Options oP = new Options();
    Db_operations DB = new Db_operations();
    Java_Mail JM = new Java_Mail();
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField idField;
    @FXML
    public ComboBox kupnoCB;
    @FXML
    public Label labelShow;
    @FXML
    public Button closeButton;
    @FXML
    public TextField emailField;

    @FXML
    public void wyjscieButton() {
        oP.back_Option(closeButton);
    }

    @FXML
    public void zrobZamowienie() throws SQLException {
        if (kupnoCB.getSelectionModel().getSelectedItem().toString().equals("laptopy")) {
            labelShow.setText(DB.take_rows("SELECT * FROM laptopy WHERE id_laptopa =" + idField.getText() + ";").toString());
            JM.java_Mail_send(labelShow.getText(), emailField.getText());
        } else {
            labelShow.setText(DB.take_rows("SELECT * FROM podzespoly_pc WHERE id_podzespolu =" + idField.getText() + ";").toString());
            JM.java_Mail_send(labelShow.getText(), emailField.getText());
        }
    }

    @FXML
    public void initialize() {
        kupnoCB.getItems().addAll("laptopy", "podzespoly_pc");
    }

}
