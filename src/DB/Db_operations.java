package DB;

import GUI.Alerty;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * 
 * @author Dominik Stasik
 */

/**
 * 
 * Klasa przechowywująca operacje na Bazie Danych
 */
public class Db_operations {

    private Alerty aL = new Alerty();
    /**
     * metoda login_test służy do sprawdzania podanego loginu i hasła
     * @param login
     * @param haslo
     * @return true, gdy podane login i hasło zgadzają się z zawartością w tabeli bazy danych 
     */
    public static boolean login_test(String login, String haslo) {
        String query = "SELECT * FROM users_work";
        try (Connection conn = db_connect.getConnection();
                Statement stat = conn.createStatement()) {
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                if (login.equals(rs.getString("login")) && haslo.equals(rs.getString("haslo"))) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * metoda insert_option
     * pobiera metodę db_connect oraz w razie komplikacji wyrzuca wyjątek
     * @param query uzyskuje komendę SQL, żeby wywołać wstawianie rekordów do tabeli 
     */
    public void insert_option(String query) {
        try (Connection connection = db_connect.getConnection();
                Statement stat = connection.createStatement()) {
            stat.executeUpdate(query);
            aL.goodMessage("OK", "Udało się wykonać operację!");
        } catch (Exception e) {
            aL.errorMessage("Bład podczas dodawania do Bazy Danych!", "Nie udało się wykonać danej operacji. Proszę sprawdzić połączenie z bazą lub poprawność danych");
        }
    }
    /**
     *  metoda ta odpowiada za edycję rekordu
     * @param query uzyskuje komendę SQL, żeby wywołać odpowiednią operację na tabeli
     */
    public void edit_option(String query) {
        try (Connection connect = db_connect.getConnection();
                Statement stat = connect.createStatement()) {
            stat.executeUpdate(query);
            aL.goodMessage("OK", "Udało się wykonać operację!");
        } catch (Exception e) {
            aL.errorMessage("Bład podczas edycji rekordów Bazy Danych!", "Nie udało się wykonać danej operacji. Proszę sprawdzić połączenie z bazą lub poprawność danych");
            e.printStackTrace();
        }
    }
    /**
     * metoda ta odpowiada za usunięcie rekordu
     * @param query uzyskuje komendę SQL, żeby wywołać odpowiednią operację na tabeli
     */
    public void delete_elements(String query) {
        System.out.println(query);
        try (Connection connect = db_connect.getConnection();
                Statement stat = connect.createStatement()) {
            
            stat.executeUpdate(query);
            aL.goodMessage("OK", "Udało się wykonać operację!");
        } catch (Exception e) {
            aL.errorMessage("Bład podczas usuwania rekordów Bazy Danych!", "Nie udało się wykonać danej operacji. Proszę sprawdzić połączenie z bazą lub poprawność danych");

        }
    }
    /**
     * Metoda dynamiczna, która pobiera kolumny z tabeli w bazie danych
     * @param query
     * @return - zwraca ArrayList z kolumnami w danej tabeli
     */
    public ArrayList<String> take_columns(String query){
        ArrayList<String> columns_names = new ArrayList<>();
        columns_names.clear();
        try(Connection connection = db_connect.getConnection();
                Statement stat = connection.createStatement()) {
            ResultSet rs = stat.executeQuery(query);
            int i = 1;
            ResultSetMetaData rsmd = rs.getMetaData();
            int c_count = rsmd.getColumnCount();
            for(int j = 1;j<=c_count;i++){
                columns_names.add(rsmd.getColumnName(i));
                System.err.println(rsmd.getColumnName(i) + "----------------------------------------------------------------");
            }
            
        } catch (Exception e) {
        }
        
        return columns_names;
    }
    /**
     * Metoda dynamicznie pobiera rekordy z tabeli bazy danych
     * @param query
     * @return zwraca Observable list, która przechowuje obiekty tego samego typu, które są rekordami w tabeli
     * @throws SQLException - wyjątek niekontrolowany, który wyrzuca informacje o wyjątku w razie potrzeby
     */
    public ObservableList<ObservableList> take_rows(String query) throws SQLException{
        ObservableList<ObservableList> wiersze = FXCollections.observableArrayList();
        try (Connection conn = db_connect.getConnection();
                Statement stat = conn.createStatement()) {
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                wiersze.add(row);
            }

            return wiersze;
        }
}
}
