package GUI;

import DB.Db_operations;
import body.Options;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/**
 * Klasa ta pozwala usuwać rekordy z bazy danych
 * @author Dominik Szostak
 */
public class UsunFXMLController {

    /**
     * Tutaj mamy zaimplementowane obiekty, z różnych klas, aby używać ponownie raz napisany kod kilka razy
     */
    Options oP = new Options();
    public Button closeButton;
    Alerty alerty = new Alerty();
    Db_operations DB = new Db_operations();
    Options oM = new Options();
    @FXML
    public Button backButton;
    /**
     * Usuwanie rekordów jest to też w pewnym sensie edycja tabeli, dlatego metody są opatrzone w takie nazwy
     */
    //Pracownicy
    @FXML
    private TextField pracownicyID;

    @FXML
    public void pracownicyEdytuj() {

        DB.delete_elements("DELETE users_work WHERE id_pracownika=" + pracownicyID.getText() + ";");

    }
    //Laptopy
    @FXML
    private TextField laptopyID;

    @FXML
    public void laptopyEdytuj() {
        DB.delete_elements("DELETE laptopy WHERE id_laptopa=" + laptopyID.getText() + ";");
    }
    //Podzespoly PC
    @FXML
    private TextField podzespolyID;
    
    @FXML
    public void podzespolyEdytuj() {
        DB.delete_elements("DELETE podzespoly_pc WHERE id_podzespolu=" + podzespolyID.getText() + ";");
    }
    //Użytkownicy
    @FXML
    private TextField uzytkownicyID;

    @FXML
    public void usersEdytuj() {
        DB.delete_elements("DELETE users WHERE id_usera=" + uzytkownicyID.getText() + ";");
    }

//Zamowienia
    @FXML
    private TextField zamowieniaID;

    @FXML
    public void zamowieniaEdytuj() {
        DB.delete_elements("DELETE zamowienia WHERE id_zamowienia=" + zamowieniaID.getText() + ";");
    }
    //Utarg
    @FXML
    private TextField utargID;

    @FXML
    public void utargEdytuj() {
        DB.delete_elements("DELETE utarg WHERE id_utargu=" + utargID.getText() + ";");
    }

    @FXML
    public void initialize() {
    }
/**
 * metoda, w której jest wywołana metoda z klasy Options pozwalająca na zamknięcie okna
 */
    @FXML
    public void backAction() {
        oP.back_Option(closeButton);
    }
    

}
