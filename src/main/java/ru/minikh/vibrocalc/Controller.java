package ru.minikh.vibrocalc;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.apache.commons.lang3.StringUtils;
import ru.minikh.vibrocalc.calc.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final static String[] ED_IZM = {"СКЗ", "СЗ", "Пик", "Размах"};
    public Region row1;

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

        row1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #8f57dc, #7d2edc);");
    }

    private Double getFreq() {
        String text = edFreqHz.getText();

        return isNotNumeric(text) ? 0.0 : Double.parseDouble(text);
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

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double aDb = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(aDb)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.A_db)
                .build();

        Result result = vibroCalcByAcceleration.calculate(acceleration, getFreq());

        applyResult(result, acceleration);
    }

    public void onEditVdbMmSec(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double aDb = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(aDb)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.V_db_mm_sec)
                .build();

        Result result = vibroCalcByVelocity.calculate(acceleration, getFreq());

        applyResult(result, acceleration);
    }

    public void onEditVdbMsec(KeyEvent keyEvent) {

    }

    public void onEditAccelerationG(KeyEvent keyEvent) {

    }

    public void onEditAccelerationMsec2(KeyEvent keyEvent) {

    }

    public void onEditAccelerationMmSec2(KeyEvent keyEvent) {

    }

    public void onEditVelocityMsec(KeyEvent keyEvent) {

    }

    public void onEditVelocityMmSec(KeyEvent keyEvent) {

    }

    public void onEditDisplacementM(KeyEvent keyEvent) {

    }

    public void onEditDisplacementMm(KeyEvent keyEvent) {

    }

    private void applyResult(Result result, Value value) {
        if (value.getParameter() != Parameter.A_db) {
            edAdb.setText(String.valueOf(result.getValues().get(Parameter.A_db.name()).getValue()));
        }

        if (value.getParameter() != Parameter.V_db_mm_sec) {
            edVdbMmSec.setText(String.valueOf(result.getValues().get(Parameter.V_db_mm_sec.name()).getValue()));
        }

        if (value.getParameter() != Parameter.V_db_m_sec) {
            edVdbMSec.setText(String.valueOf(result.getValues().get(Parameter.V_db_m_sec.name()).getValue()));
        }

        if (value.getParameter() != Parameter.A_g) {
            edAccelerationG.setText(String.valueOf(result.getValues().get(Parameter.A_g.name()).getValue()));
        }

        if (value.getParameter() != Parameter.A_m_sec2) {
            edAccelerationMsec2.setText(String.valueOf(result.getValues().get(Parameter.A_m_sec2.name()).getValue()));
        }

        if (value.getParameter() != Parameter.A_mm_sec2) {
            edAccelerationMmSec2.setText(String.valueOf(result.getValues().get(Parameter.A_mm_sec2.name()).getValue()));
        }

        if (value.getParameter() != Parameter.V_m_sec) {
            edVelocityMsec.setText(String.valueOf(result.getValues().get(Parameter.V_m_sec.name()).getValue()));
        }

        if (value.getParameter() != Parameter.V_mm_sec) {
            edVelocityMmSec.setText(String.valueOf(result.getValues().get(Parameter.V_mm_sec.name()).getValue()));
        }

        if (value.getParameter() != Parameter.D_m) {
            edDisplacementM.setText(String.valueOf(result.getValues().get(Parameter.D_m.name()).getValue()));
        }

        if (value.getParameter() != Parameter.D_mm) {
            edDisplacementMm.setText(String.valueOf(result.getValues().get(Parameter.D_mm.name()).getValue()));
        }
    }

    public void openUrl(MouseEvent mouseEvent) {
//        getHostServices().showDocument(url);
        try {
            java.awt.Desktop.getDesktop().browse(new URI("www.vibrtest.ru"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
