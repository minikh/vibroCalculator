package ru.minikh.vibrocalc;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ComboBox adbSelectEdIzm;
    public ComboBox vdbMmSecSelectEdIzm;

    private final static String[] ED_IZM = {"СКЗ", "СЗ", "Пик", "Размах"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adbSelectEdIzm.getItems().addAll(ED_IZM);
        vdbMmSecSelectEdIzm.getItems().addAll(ED_IZM);


        adbSelectEdIzm.setAccessibleText("СКЗ");
        vdbMmSecSelectEdIzm.setAccessibleText("СКЗ");
    }

    public void onEditA(KeyEvent keyEvent) {

    }
}
