package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
/**
 * Klasa ta jest kontrolerem do StackPane, gdzie na starcie zostaje załadowane okno z logowaniem
 * @author Dominik Stasik
 */
public class MainStackPaneController{

    @FXML
    private StackPane mainStackPane;
    @FXML
    public void initialize() {
        loadLoginPanel();
    }
    /**
     * metoda ta dodaje okno do StackPane za pomocą metody getChildrer. Aby zapobiedz nakładania się na siebie fxml-ów, trzeba przy każdym wywołaniu ją wczyścić
     * @param anchorPane 
     */
    public void setScreen(AnchorPane anchorPane){
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(anchorPane);
    }
    /**
     * operacja ta wczytuje panel logowania. Została zamknięta w metodzie, ze względu na częste wykorzystywanie tego kodu
     */
    public void loadLoginPanel(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LoginPanelFXML.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginPanelFXMLController loginPanelFXMLController = loader.getController();
        loginPanelFXMLController.setController(this);
        setScreen(anchorPane);
    }
    
}
