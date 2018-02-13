package ru.minikh.vibrocalc;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.commons.lang3.StringUtils;
import ru.minikh.vibrocalc.calc.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final static String[] ED_IZM = {"СКЗ", "СЗ", "Пик", "Размах"};

    private VibroCalc vibroCalcByAcceleration = new VibroCalcByAcceleration();
    private VibroCalc vibroCalcByVelocity = new VibroCalcByVelocity();
    private VibroCalc vibroCalcByDisplacement = new VibroCalcByDisplacement();

    public TextField edAdb;
    public TextField edVdbMmSec;
    public TextField edVdbMSec;
    public TextField edAccelerationG;
    public TextField edAccelerationMsec2;
    public TextField edAccelerationMmSec2;
    public TextField edVelocityMsec;
    public TextField edVelocityMmSec;
    public TextField edDisplacementM;
    public TextField edDisplacementMm;

    public ComboBox accelerationGSelectEdIzm;
    public ComboBox accelerationMsec2SelectEdIzm;
    public ComboBox accelerationMmSec2SelectEdIzm;
    public ComboBox velocityMsecSelectEdIzm;
    public ComboBox velocityMmSecSelectEdIzm;
    public ComboBox displacementMSelectEdIzm;
    public ComboBox displacementMmSelectEdIzm;

    public TextField edFreqHz;
    public TextField edFreqCpm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accelerationGSelectEdIzm.getItems().addAll(ED_IZM);
        accelerationMsec2SelectEdIzm.getItems().addAll(ED_IZM);
        accelerationMmSec2SelectEdIzm.getItems().addAll(ED_IZM);
        velocityMsecSelectEdIzm.getItems().addAll(ED_IZM);
        velocityMmSecSelectEdIzm.getItems().addAll(ED_IZM);
        displacementMSelectEdIzm.getItems().addAll(ED_IZM);
        displacementMmSelectEdIzm.getItems().addAll(ED_IZM);

        accelerationGSelectEdIzm.getSelectionModel().select(3);
        accelerationMsec2SelectEdIzm.getSelectionModel().select(0);
        accelerationMmSec2SelectEdIzm.getSelectionModel().select(0);
        velocityMsecSelectEdIzm.getSelectionModel().select(0);
        velocityMmSecSelectEdIzm.getSelectionModel().select(0);
        displacementMSelectEdIzm.getSelectionModel().select(3);
        displacementMmSelectEdIzm.getSelectionModel().select(3);
    }

    private Double getFreq() {
        String text = edFreqHz.getText();

        return isNotNumeric(text) ? Double.parseDouble(text) : 0.0;
    }

    private boolean isNotNumeric(String text) {
        if (StringUtils.isNumeric(text)) return false;

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Вибро калькулятор");
        alert.setHeaderText("Ошибка");
        alert.setContentText("Введите число");
        alert.showAndWait();
        return true;
    }

    private void resetFreq() {
        edFreqHz.setText("0");
        edFreqCpm.setText("0");
    }

    private void resetResult() {
        edAdb.setText("0");
        edVdbMmSec.setText("0");
        edVdbMSec.setText("0");
        edAccelerationG.setText("0");
        edAccelerationMsec2.setText("0");
        edAccelerationMmSec2.setText("0");
        edVelocityMsec.setText("0");
        edVelocityMmSec.setText("0");
        edDisplacementM.setText("0");
        edDisplacementMm.setText("0");
    }

    public void onEditFreqHz(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = edFreqHz.getText();

        if (isNotNumeric(text)) return;

        Double freq = Double.parseDouble(text);

        resetResult();
        edFreqCpm.setText(String.valueOf(freq * 60));
    }

    public void onEditFreqCpm(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = edFreqCpm.getText();

        if (isNotNumeric(text)) return;

        Double freq = Double.parseDouble(text);

        resetResult();
        edFreqHz.setText(String.valueOf(freq / 60));
    }

    public void onEditA(KeyEvent keyEvent) {

    }

    public void btnResetResult(ActionEvent actionEvent) {
        resetResult();
        resetFreq();
    }

    public void onEditAdb(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = edAccelerationG.getText();

        if (isNotNumeric(text)) return;

        Double aDb = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(aDb)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.A_db)
                .build();

        Map<Parameter, EdIzm> parameters = new HashMap<>();
        parameters.put(Parameter.A_g, EdIzm.PEAK_TO_PEAK);
        parameters.put(Parameter.A_m_sec2, EdIzm.RMS);
        parameters.put(Parameter.A_mm_sec2, EdIzm.RMS);

        parameters.put(Parameter.V_m_sec, EdIzm.RMS);
        parameters.put(Parameter.V_mm_sec, EdIzm.RMS);
        parameters.put(Parameter.D_m, EdIzm.PEAK_TO_PEAK);
        parameters.put(Parameter.D_mm, EdIzm.PEAK_TO_PEAK);

        parameters.put(Parameter.A_db, EdIzm.NONE);
        parameters.put(Parameter.V_db_m_sec, EdIzm.NONE);
        parameters.put(Parameter.V_db_mm_sec, EdIzm.NONE);

        Result result = vibroCalcByAcceleration.calculate(acceleration, parameters, getFreq());

        System.out.println(result);
    }
}
