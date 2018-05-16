package GUI;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * klasa główna, gdzie w Javie FX musi mieć rozszerzenie Application
 * Klasa ta ma załadowanego StackPane, na którym będą się zmieniać Pane'y i dzięki temu zabiegowi umożliwia to multiokienkową aplikację
 * @author Dominik Stasik
 */

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("mainStackPane.fxml"));
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sklep_Komputerowy");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        DB.db_connect.getConnection();
        launch(args);
    }
    
}
