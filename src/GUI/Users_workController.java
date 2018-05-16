package GUI;

import DB.Db_operations;
import body.Options;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/**
 * nazwa Klasy jest celowo inna. Jest to pewne zabezpieczenie poprzez obfuskacje kodu
 * Klasa ta zawieta metody, które umożliwiają edycję rekordów, które znajdują się w tabeli
 * @author Dominik Szostak
 */
public class Users_workController {

    Alerty alerty = new Alerty();
    Db_operations DB = new Db_operations();
    Options oM = new Options();
    @FXML
    public Button backButton;
    //Pracownicy
    @FXML
    private ComboBox pracownicyCB;
    @FXML
    private TextField pracownicyTF;
    @FXML
    private TextField pracownicyID;
    /**
     * Wywoływanie metod odpowiedzialnych za edycje rekordów
     */
    @FXML
    public void pracownicyEdytuj() {
        if (pracownicyCB.getSelectionModel().getSelectedItem().toString().equals("login")) {
            System.err.println("UPDATE users_work set login = '" + pracownicyTF.getText() + "' WHERE id_pracownika=" + pracownicyID.getText() + ";");
            DB.edit_option("UPDATE users_work set login = '" + pracownicyTF.getText() + "' WHERE id_pracownika=" + pracownicyID.getText() + ";");
            
        }
        if (pracownicyCB.getSelectionModel().getSelectedItem().toString().equals("haslo")) {
            DB.edit_option("UPDATE users_work set haslo = '" + pracownicyTF.getText() + "' WHERE id_pracownika=" + pracownicyID.getText() + ";");
        }
    }
    //Laptopy
    @FXML
    private ComboBox laptopyCB;
    @FXML
    private TextField laptopyTF;
    @FXML
    private TextField laptopyID;

    @FXML
    public void laptopyEdytuj() {
        if (laptopyCB.getSelectionModel().getSelectedItem().toString().equals("nazwa_firmy")) {
            DB.edit_option("UPDATE laptopy set nazwa_firmy = '" + laptopyTF.getText() + "' WHERE id_laptopa=" + laptopyID.getText() + ";");
        }
        if (laptopyCB.getSelectionModel().getSelectedItem().toString().equals("model")) {
            DB.edit_option("UPDATE laptopy set model = '" + laptopyTF.getText() + "' WHERE id_laptopa=" + laptopyID.getText() + ";");
        }
        if (laptopyCB.getSelectionModel().getSelectedItem().toString().equals("cena")) {
            DB.edit_option("UPDATE laptopy set cena = " + laptopyTF.getText() + " WHERE id_laptopa=" + laptopyID.getText() + ";");
        }
    }
    //Podzespoly PC
    @FXML
    private ComboBox podzespolyCB;
    @FXML
    private TextField podzespolyTF;
    @FXML
    private TextField podzespolyID;

    @FXML
    public void podzespolyEdytuj() {
        if (podzespolyCB.getSelectionModel().getSelectedItem().toString().equals("nazwa_firmy")) {
            DB.edit_option("UPDATE podzespoly_pc set nazwa_firmy = '" + podzespolyTF.getText() + "' WHERE id_podzespolu=" + podzespolyID.getText() + ";");
        }
        if (podzespolyCB.getSelectionModel().getSelectedItem().toString().equals("model")) {
            DB.edit_option("UPDATE podzespoly_pc set model = '" + podzespolyTF.getText() + "' WHERE id_podzespolu=" + podzespolyID.getText() + ";");
        }
        if (podzespolyCB.getSelectionModel().getSelectedItem().toString().equals("cena")) {
            DB.edit_option("UPDATE podzespoly_pc set cena = " + podzespolyTF.getText() + " WHERE id_podzespolu=" + podzespolyID.getText() + ";");
        }
        if (podzespolyCB.getSelectionModel().getSelectedItem().toString().equals("typ")) {
            DB.edit_option("UPDATE podzespoly_pc set typ = '" + podzespolyTF.getText() + "' WHERE id_podzespolu=" + podzespolyID.getText() + ";");
        }
    }
    //Użytkownicy
    @FXML
    private ComboBox usersCB;
    @FXML
    private TextField uzytkownicyTF;
    @FXML
    private TextField uzytkownicyID;

    @FXML
    public void usersEdytuj() {
        if (usersCB.getSelectionModel().getSelectedItem().toString().equals("login")) {
            DB.edit_option("UPDATE users set login = '" + uzytkownicyTF.getText() + "' WHERE id_usera=" + uzytkownicyID.getText() + ";");
        }
        if (usersCB.getSelectionModel().getSelectedItem().toString().equals("haslo")) {
            DB.edit_option("UPDATE users set haslo = '" + uzytkownicyTF.getText() + "' WHERE id_usera=" + uzytkownicyID.getText() + ";");
        }
        if (usersCB.getSelectionModel().getSelectedItem().toString().equals("imie")) {
            DB.edit_option("UPDATE users set imie = '" + uzytkownicyTF.getText() + "' WHERE id_usera=" + uzytkownicyID.getText() + ";");
        }
        if (usersCB.getSelectionModel().getSelectedItem().toString().equals("nazwisko")) {
            DB.edit_option("UPDATE users set nazwisko = '" + uzytkownicyTF.getText() + "' WHERE id_usera=" + uzytkownicyID.getText() + ";");
        }
    }

//Zamowienia
    @FXML
    private ComboBox zamowieniaCB;
    @FXML
    private TextField zamowieniaTF;
    @FXML
    private TextField zamowieniaID;

    @FXML
    public void zamowieniaEdytuj() {
        if (zamowieniaCB.getSelectionModel().getSelectedItem().toString().equals("kwota")) {
            DB.edit_option("UPDATE zamowienia set kwota = " + zamowieniaTF.getText() + " WHERE id_podzespolu=" + zamowieniaID.getText() + ";");
        }
    }
    //Utarg
    @FXML
    private ComboBox utargCB;
    @FXML
    private TextField utargTF;
    @FXML
    private TextField utargID;

    @FXML
    public void utargEdytuj() {
        if (utargCB.getSelectionModel().getSelectedItem().toString().equals("kwota")) {
            DB.edit_option("UPDATE utarg set kwota = " + utargTF.getText() + " WHERE id_podzespolu=" + utargID.getText() + ";");
        }
    }

    /////////////////////////////////////////
    @FXML
    public void initialize() {
        comboboxmethod();
    }

    @FXML
    public void backAction() {
        oM.back_Option(backButton);
    }
    /**
     * metoda, która pozwala na umieszczenie napisów w ComboBoxie
     */
    public void comboboxmethod() {
        pracownicyCB.getItems().addAll("login", "haslo");
        laptopyCB.getItems().addAll("nazwa_firmy", "model", "cena");
        podzespolyCB.getItems().addAll("typ", "nazwa_firmy", "model", "cena");
        usersCB.getItems().addAll("login", "haslo", "imie", "nazwisko");
        zamowieniaCB.getItems().addAll("kwota");
        utargCB.getItems().addAll("kwota");
    }
}
