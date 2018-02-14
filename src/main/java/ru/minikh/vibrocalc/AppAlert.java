package ru.minikh.vibrocalc;

import javafx.scene.control.Alert;

public class AppAlert {

    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Виброкалькулятор");
        alert.setHeaderText("Ошибка");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
