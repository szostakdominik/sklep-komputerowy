package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import GUI.Alerty;
/**
 * Klasa służąca do nawiązywania połączenia z bazą danych
 * @author Dominik Stasik
 */

public class db_connect {
    private final static Alerty alerty = new Alerty();
    private static final String url = "jdbc:postgresql://localhost:5432/Sklep_Komputerowy";
    private static final String user = "postgres";
    private static final String password = "123456";
/**
 * zmienne typu static final służą danym potrzebnym do połączenia się z bazą danych
 * metoda sprawdza steronik, połączenie i jeśli nastąpiło połączenie to zwraca obiektu connection, a jeśli nie to zostaje wyświetlony komunikat
 * @return połączenie z bazą danych 
 */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
            alerty.errorMessage("Nie udało się połączyć z bazą danych", "Sprawdź ustawienia i spróbuj ponownie");
        }
        return connection;
    }
    
}
