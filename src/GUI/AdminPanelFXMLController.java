/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.Db_operations;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * Klasa ta zawiera wszystkiem metody, które zostały użyte w panelu administratora
 * Aby aplikacja mogła działać multiokienkowo, muszą być przesyłane między sobą kontrolery poszczególnych plików fxml
 * 
 * @author Dominik Stasik
 */
public class AdminPanelFXMLController {

    private MainStackPaneController mainStackPaneController;
    private ObservableList<ObservableList> data;
    public Db_operations DB = new Db_operations();
    @FXML
    private ComboBox tableCB;
    @FXML
    private TableView tabelaAdmin;
    @FXML
    public void initialize() {
        tableCB.getItems().addAll("users_work","laptopy","podzespoły_pc","utarg","users","zamowienia");
    }
    /**
     * otwiera nowe okno z możliwością dodania rekordów do odpowiedich tabel
     * @throws IOException 
     */
    @FXML
    public void dodajAction() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("dodajAdmin.fxml"));
        Stage stage = new Stage();
        AnchorPane anchorPane = null;
        anchorPane = loader.load();
        stage.setTitle("DodajSQL");
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }
    /**
     * metoda pozwala edytować rekordy w tabeli
     * @throws IOException 
     */
    @FXML
    public void edytujAction() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("users_work.fxml"));
        Stage stage = new Stage();
        AnchorPane anchorPane = null;
        anchorPane = loader.load();
        stage.setTitle("EdytujSQL");
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }
    /**
     * otwiera nowe okno, które pozwala usuwać odpowiednie rekordy
     * @throws IOException 
     */
    @FXML
    public void usunAction() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("usunFXML.fxml"));
        Stage stage = new Stage();
        AnchorPane anchorPane = null;
        anchorPane = loader.load();
        stage.setTitle("EdytujSQL");
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }
    /**
     * następuje powrót do panelu logowania
     */
    @FXML
    public void wylogujAction(){
        mainStackPaneController.loadLoginPanel();
    }
    /**
     * pozwala wyświetlać odpowiednie tabele
     * @throws SQLException 
     */
    @FXML
    public void wybierzAction() throws SQLException{
        if (tableCB.getSelectionModel().getSelectedItem().toString().equals("laptopy")) {
            setTable(DB.take_columns("SELECT * FROM laptopy;"), DB.take_rows("SELECT * FROM laptopy ORDER BY id_laptopa;"));
        }
        if (tableCB.getSelectionModel().getSelectedItem().toString().equals("podzespoły_pc")) {
            setTable(DB.take_columns("SELECT * FROM podzespoly_pc;"), DB.take_rows("SELECT * FROM podzespoly_pc ORDER BY id_podzespolu;"));
        }
        if (tableCB.getSelectionModel().getSelectedItem().toString().equals("zamowienia")) {
            setTable(DB.take_columns("SELECT * FROM zamowienia;"), DB.take_rows("SELECT * FROM zamowienia ORDER BY id_zamowienia;"));
        }
        if (tableCB.getSelectionModel().getSelectedItem().toString().equals("users_work")) {
            setTable(DB.take_columns("SELECT * FROM users_work;"), DB.take_rows("SELECT * FROM users_work;"));
        }
        if (tableCB.getSelectionModel().getSelectedItem().toString().equals("utarg")) {
            setTable(DB.take_columns("SELECT * FROM utarg;"), DB.take_rows("SELECT * FROM utarg;"));
        }
        if (tableCB.getSelectionModel().getSelectedItem().toString().equals("users")) {
            setTable(DB.take_columns("SELECT * FROM users;"), DB.take_rows("SELECT * FROM users;"));
        }
    }
    /**
     * w tej metodzie ustawiamy wyświetlanie rekordów w tabeli
     * 
     * @param kolumny przyjmuje wynik z metodą pobierającej kolumny
     * @param lista przyjmuje wynik z metody pobierającej wierze
     * @throws SQLException 
     * setCellValueFactory umożliwia utworzenie wierszy w danej kolumnie i są pobierane obiekty typu TableColumn i dla każdego obiektu są przypisywane wiersze
     * 
     */
    public void setTable(ArrayList<String> kolumny, ObservableList<ObservableList> lista) throws SQLException {
        tabelaAdmin.getColumns().clear();
        tabelaAdmin.getItems().clear();
        ArrayList<TableColumn> kolumny_final = new ArrayList<>();
        data = FXCollections.observableArrayList();
        for (int i = 0; i < kolumny.size(); i++) {
            System.err.println(kolumny.get(i));
            kolumny_final.add(new TableColumn(kolumny.get(i)));
        }
        for (int i = 0; i < kolumny_final.size(); i++) {
            final int j = i;
            kolumny_final.get(i).setCellValueFactory((new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty((String) param.getValue().get(j));
                }
            }));
        }
        tabelaAdmin.getColumns().addAll(kolumny_final);
        data = lista;
        tabelaAdmin.setItems(data);

    }
    /**
     * kontroler umożliwiający multiokienkową aplikację
     * @param mainStackPaneController 
     */
    void setController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }
    
}
