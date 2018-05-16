/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.Db_operations;
import body.Options;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *Klasa, która jest kontrolerem dla admina i posiada metody umożliwiające dodawnie rekordów do tabel
 * @author Dominik Stasik
 */
public class DodajAdminController{

    private DB.Db_operations DB = new Db_operations();
    public body.Options options = new Options();
    @FXML
    public Button backButton ;
    //users_wrok
    @FXML
    private TextField id_pracownika;
    @FXML
    private TextField login;
    @FXML
    private TextField haslo;
    @FXML
    public void dodajUsersWork(){
        DB.insert_option("INSERT INTO users_work VALUES(" + id_pracownika.getText() + ",'" + login.getText() + "','" + haslo.getText() + "');");
    }
    //laptopy
    @FXML
    private TextField id_laptopa;
    @FXML
    private TextField nazwa_firmy;
    @FXML
    private TextField model;
    @FXML
    private TextField cena;
    @FXML
    public void dodajLaptopy(){
        DB.insert_option("INSERT INTO laptopy VALUES(" + id_laptopa.getText() + ",'" + nazwa_firmy.getText() + "','" + model.getText() +"','" + cena.getText() + "');");
    }
    //podzespoly_pc
    
    @FXML
    private TextField id_podzespolu;
    @FXML
    private TextField typ_podzespolu;
    @FXML
    private TextField nazwa_firmy_podzespolu;
    @FXML
    private TextField model_podzespolu;
    @FXML
    private TextField cena_podzespolu;
    @FXML
    public void dodajPodzespoly(){
        DB.insert_option("INSERT INTO podzespoly_pc VALUES(" + id_podzespolu.getText() + ",'"+ typ_podzespolu.getText() + "','" + nazwa_firmy_podzespolu.getText() + "','" + model_podzespolu.getText() +"','" + cena_podzespolu.getText() + "');");
    }
    //utarg
    @FXML
    private TextField id_utargu;
    @FXML
    private TextField kwota_utargu;
    @FXML
    public void dodajUtarg(){
        DB.insert_option("INSERT INTO utarg VALUES(" + id_utargu.getText() + "," + id_laptopa.getText() + "," + id_podzespolu.getText() + ",'"+ kwota_utargu.getText() + "');");
    }
    //users
    @FXML
    private TextField id_users;
    @FXML
    private TextField login_users;
    @FXML
    private TextField haslo_users;
    @FXML
    private TextField imie_users;
    @FXML
    private TextField nazwisko_users;
    @FXML
    public void dodajUsers(){
        DB.insert_option("INSERT INTO users VALUES(" + id_users.getText() + ",'"+ login_users.getText() + "','" + haslo_users.getText() + "','" + imie_users.getText() +"','" + nazwisko_users.getText() + "');");
    }
    //zamowienia
    @FXML
    private TextField id_zamowienia;
    @FXML
    private TextField kwota_zamowienia;
    @FXML
    public void dodajZamowienie(){
        DB.insert_option("INSERT INTO zamowienia VALUES(" + id_zamowienia.getText() + "," + id_users.getText() + "," + id_laptopa.getText() + "," + id_podzespolu.getText() + kwota_zamowienia.getText() + ");");
    }
    @FXML
    public void initialize() {
        // TODO
    }    
    @FXML
    public void backAction(){
        options.back_Option(backButton);
    }
}
