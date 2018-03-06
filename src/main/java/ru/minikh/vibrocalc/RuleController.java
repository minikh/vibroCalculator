package ru.minikh.vibrocalc;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RuleController implements Initializable {


    public Button btnCloseWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCloseWindow.getScene().getWindow();
        stage.close();
    }
}
