package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
/**
 * klasa, która jest kontrolerem do panelu logowania i umożliwia uruchomienie odpowiednich okien dla poszczególnych użytkowników
 * @author Dominik Stasik
 */
public class LoginPanelFXMLController {

    public Alerty aA = new Alerty();
    public MainStackPaneController mainStackPaneController;
    @FXML
    public TextField loginField;
    public PasswordField passField;

    @FXML
    public void initialize() {
        // TODO
    }
    /**
     * metoda, która jest przypisana do przycisku zaloguj, która zawiera odpowiednie metody sprawdzające poprawność logowania
     */
    @FXML
    public void loginAction() {
        if(loginField.getText().equals("admin")){
        if (DB.Db_operations.login_test(loginField.getText(), passField.getText())) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("adminPanelFXML.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
            AdminPanelFXMLController adminPanelFXMLController = loader.getController();
            adminPanelFXMLController.setController(mainStackPaneController);
            mainStackPaneController.setScreen(anchorPane);
        } else {
            aA.errorMessage("Niepoprawne dane logowania", "Podaj poprawny login lub haslo");
        }}
        else{
            if (DB.Db_operations.login_test(loginField.getText(), passField.getText())) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("panelSprzedajacy.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PanelSprzedajacyController panelSprzedajacyController = loader.getController();
            panelSprzedajacyController.setController(mainStackPaneController);
            mainStackPaneController.setScreen(anchorPane);
        } else {
            aA.errorMessage("Niepoprawne dane logowania", "Podaj poprawny login lub haslo");
        }}
    }

    public void setController(MainStackPaneController mainStackPaneController) {
        this.mainStackPaneController = mainStackPaneController;
    }

}
