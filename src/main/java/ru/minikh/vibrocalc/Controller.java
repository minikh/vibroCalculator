package ru.minikh.vibrocalc;

import javafx.application.Platform;
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
    public Region row2;
    public Region row3;
    public Region row4;
    public Region row5;
    public Region row6;
    public Region row7;
    public Region row8;
    public Region row9;
    public Region row10;
    public Region row20;
    public Region row21;

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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                edFreqHz.requestFocus();
            }
        });

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

        row1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #54548b, #8383db);");
        row2.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #54548b, #8383db);");
        row3.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #54548b, #8383db);");

        row4.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #1a6969, #28a3a3);");
        row5.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #1a6969, #28a3a3);");
        row6.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #1a6969, #28a3a3);");

        row7.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #796030, #cca351);");
        row8.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #796030, #cca351);");

        row9.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #007900, #00cc00);");
        row10.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #007900, #00cc00);");

        row20.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #535353, #9a9a9a);");
        row21.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #535353, #9a9a9a);");
    }

    private Double getFreq() {
        String text = edFreqHz.getText();

        return isNotNumeric(text) ? 0.0 : Double.parseDouble(text);
    }

    private boolean isNotNumeric(String text) {
        if (StringUtils.isNumeric(text)) return false;

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Виброкалькулятор");
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

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
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

        Double value = Double.parseDouble(text);
        Value velocity = Value.builder()
                .value(value)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.V_db_mm_sec)
                .build();

        Result result = vibroCalcByVelocity.calculate(velocity, getFreq());

        applyResult(result, velocity);
    }

    public void onEditVdbMsec(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value velocity = Value.builder()
                .value(value)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.V_db_m_sec)
                .build();

        Result result = vibroCalcByVelocity.calculate(velocity, getFreq());

        applyResult(result, velocity);
    }

    public void onEditAccelerationG(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.A_g)
                .build();

        Result result = vibroCalcByAcceleration.calculate(acceleration, getFreq());

        applyResult(result, acceleration);
    }

    public void onEditAccelerationMsec2(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_m_sec2)
                .build();

        Result result = vibroCalcByAcceleration.calculate(acceleration, getFreq());

        applyResult(result, acceleration);
    }

    public void onEditAccelerationMmSec2(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_mm_sec2)
                .build();

        Result result = vibroCalcByAcceleration.calculate(acceleration, getFreq());

        applyResult(result, acceleration);
    }

    public void onEditVelocityMsec(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value velocity = Value.builder()
                .value(value)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.V_m_sec)
                .build();

        Result result = vibroCalcByVelocity.calculate(velocity, getFreq());

        applyResult(result, velocity);
    }

    public void onEditVelocityMmSec(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value velocity = Value.builder()
                .value(value)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.V_mm_sec)
                .build();

        Result result = vibroCalcByVelocity.calculate(velocity, getFreq());

        applyResult(result, velocity);
    }

    public void onEditDisplacementM(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value displacement = Value.builder()
                .value(value)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.D_m)
                .build();

        Result result = vibroCalcByDisplacement.calculate(displacement, getFreq());

        applyResult(result, displacement);
    }

    public void onEditDisplacementMm(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value displacement = Value.builder()
                .value(value)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.D_mm)
                .build();

        Result result = vibroCalcByDisplacement.calculate(displacement, getFreq());

        applyResult(result, displacement);
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
