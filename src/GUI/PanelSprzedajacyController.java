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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * Klasa ta jest kontrolerem do AnchorPane Panel Sprzedającego. Posiada ona szereg metod, które umożliwiają korzystanie z tego panelu. Sprzedawca jest oczywiście okrojny
 * 
 * @author Dominik Stasik
 */
public class PanelSprzedajacyController {
/**
 * Podobnie jak w adminie są wykonywane te same operacje, tylko okrojone dla sprzedawcy, zgodne z uprawnieniami
 */
    private MainStackPaneController mainStackPaneController;
    private ObservableList<ObservableList> data;
    private Db_operations DB = new Db_operations();
    @FXML
    public TableView tabelaWyswietlanie;
    @FXML
    private ComboBox tabeleCB;

    @FXML
    public void initialize() {
        tabeleCB.getItems().addAll("laptopy", "podzespoly_pc", "zamowienia");
    }

    @FXML
    public void wybierzAction() throws SQLException {
        if (tabeleCB.getSelectionModel().getSelectedItem().toString().equals("laptopy")) {
            setTable(DB.take_columns("SELECT * FROM laptopy;"), DB.take_rows("SELECT * FROM laptopy ORDER BY id_laptopa;"));
        }
        if (tabeleCB.getSelectionModel().getSelectedItem().toString().equals("podzespoly_pc")) {
            setTable(DB.take_columns("SELECT * FROM podzespoly_pc;"), DB.take_rows("SELECT * FROM podzespoly_pc ORDER BY id_podzespolu;"));
        }
        if (tabeleCB.getSelectionModel().getSelectedItem().toString().equals("zamowienia")) {
            setTable(DB.take_columns("SELECT * FROM zamowienia;"), DB.take_rows("SELECT * FROM zamowienia ORDER BY id_zamowienia;"));
        }
    }

    @FXML
    public void actionWyloguj() {
        mainStackPaneController.loadLoginPanel();
    }

    public void setTable(ArrayList<String> kolumny, ObservableList<ObservableList> lista) throws SQLException {
        tabelaWyswietlanie.getColumns().clear();
        tabelaWyswietlanie.getItems().clear();
        ArrayList<TableColumn> kolumny_final = new ArrayList<>();
        data = FXCollections.observableArrayList();
        for (int i = 0; i < kolumny.size(); i++) {
            System.err.println(kolumny.get(i));
            kolumny_final.add(new TableColumn(kolumny.get(i)));
        }
        for (int i = 0; i < kolumny_final.size(); i++) {
            final int j = i;
            kolumny_final.get(i).setCellValueFactory((new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty((String) param.getValue().get(j));
                }
            }));
            //tabelaWyswietlanie.getColumns().addAll(kolumny_final.get(i));
        }
        tabelaWyswietlanie.getColumns().addAll(kolumny_final);
        data = lista;
        tabelaWyswietlanie.setItems(data);
        for (int i = 0; i < data.size(); i++) {
            System.err.println(data.get(i));
        }

    }

    @FXML
    public void sumaAction() throws IOException {
        AnchorPane anchorPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("kupnoFXML.fxml"));
            Stage stage = new Stage();
            anchorPane = loader.load();
            stage.setTitle("Dodaj_Zamówienie");
            stage.setScene(new Scene(anchorPane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    void setController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }

}
