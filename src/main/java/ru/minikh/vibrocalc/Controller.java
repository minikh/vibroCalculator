package ru.minikh.vibrocalc;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.minikh.vibrocalc.calc.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

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

    public RadioButton isEnglish;
    public RadioButton isMetric;

    public Label aDbLabel;
    public Label vDbMmSecLabel;
    public Label vDbMSecLabel;
    public Label AccelerationGLabel;
    public Label AccelerationMSec2Label;
    public Label AccelerationMmSec2Label;
    public Label velocityMSecLabel;
    public Label velocityMmSecLabel;
    public Label displacementMLabel;
    public Label displacementMmLabel;
    public Label freqHzLabel;
    public Label freqCpmLabel;

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

    private EdIzm accelerationGSelectEdIzmLastValue;
    private EdIzm accelerationMsec2SelectEdIzmLastValue;
    private EdIzm accelerationMmSec2SelectEdIzmLastValue;
    private EdIzm velocityMsecSelectEdIzmLastValue;
    private EdIzm velocityMmSecSelectEdIzmLastValue;
    private EdIzm displacementMSelectEdIzmLastValue;
    private EdIzm displacementMmSelectEdIzmLastValue;

    private KeyEvent lastKeyEvent;

    private final static Map<Integer, String> english = EdIzm.getEnglish();
    private final static Map<Integer, String> metric = EdIzm.getMetric();

    int accelerationGSelectEdIzmIndex = 0;
    int accelerationMsec2SelectEdIzmIndex = 0;
    int accelerationMmSec2SelectEdIzmIndex = 0;
    int velocityMsecSelectEdIzmIndex = 0;
    int velocityMmSecSelectEdIzmIndex = 0;
    int displacementMSelectEdIzmIndex = 2;
    int displacementMmSelectEdIzmIndex = 2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> edFreqHz.requestFocus());

        clear();
        setMetric();
        setSelectedEdIzm();

        accelerationGSelectEdIzmLastValue = EdIzm.getEdIzm((String) accelerationGSelectEdIzm.getSelectionModel().getSelectedItem());
        accelerationMsec2SelectEdIzmLastValue = EdIzm.getEdIzm((String) accelerationMsec2SelectEdIzm.getSelectionModel().getSelectedItem());
        accelerationMmSec2SelectEdIzmLastValue = EdIzm.getEdIzm((String) accelerationMmSec2SelectEdIzm.getSelectionModel().getSelectedItem());
        velocityMsecSelectEdIzmLastValue = EdIzm.getEdIzm((String) velocityMsecSelectEdIzm.getSelectionModel().getSelectedItem());
        velocityMmSecSelectEdIzmLastValue = EdIzm.getEdIzm((String) velocityMmSecSelectEdIzm.getSelectionModel().getSelectedItem());
        displacementMSelectEdIzmLastValue = EdIzm.getEdIzm((String) displacementMSelectEdIzm.getSelectionModel().getSelectedItem());
        displacementMmSelectEdIzmLastValue = EdIzm.getEdIzm((String) displacementMmSelectEdIzm.getSelectionModel().getSelectedItem());

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
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException e) {
            AppAlert.showErrorAlert("Введите число");
            return true;
        }

        return false;
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

        if (freq > 100_000) {
            AppAlert.showErrorAlert("Введите число от 0 до 100 000");
            edFreqHz.selectAll();
            return;
        }

        resetResult();
        edFreqCpm.setText(String.valueOf(freq * 60));
    }

    public void onEditFreqCpm(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = edFreqCpm.getText();

        if (isNotNumeric(text)) return;

        Double freq = Double.parseDouble(text);

        if (freq > 6_000_000) {
            AppAlert.showErrorAlert("Введите число от 0 до 6 000 000");
            edFreqCpm.selectAll();
            return;
        }

        resetResult();
        edFreqHz.setText(String.valueOf(freq / 60));
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

        vibroCalcByAcceleration.setParameters(getSelectedParameters());
        Result result = vibroCalcByAcceleration.calculate(acceleration, getFreq());

        applyResult(result, acceleration);

        lastKeyEvent = keyEvent;
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

        vibroCalcByVelocity.setParameters(getSelectedParameters());
        Result result = vibroCalcByVelocity.calculate(velocity, getFreq());

        applyResult(result, velocity);

        lastKeyEvent = keyEvent;
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

        vibroCalcByVelocity.setParameters(getSelectedParameters());
        Result result = vibroCalcByVelocity.calculate(velocity, getFreq());

        applyResult(result, velocity);

        lastKeyEvent = keyEvent;
    }

    public void onChangeAccelerationGEdIzm(Event event) {
        String text = edAccelerationG.getText();

        if (isNotNumeric(text)) return;

        EdIzm edIzm = EdIzm.getEdIzm((String) accelerationGSelectEdIzm.getSelectionModel().getSelectedItem());

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(accelerationGSelectEdIzmLastValue)
                .parameter(Parameter.A_g)
                .build();

        edAccelerationG.setText(vibroCalcByAcceleration.recalculateValue(acceleration, edIzm));
        accelerationGSelectEdIzmLastValue = edIzm;
    }

    public void onEditAccelerationG(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;
        if (checkFreqByZero()) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(EdIzm.getEdIzm((String) accelerationGSelectEdIzm.getSelectionModel().getSelectedItem()))
                .parameter(Parameter.A_g)
                .build();

        vibroCalcByAcceleration.setParameters(getSelectedParameters());
        Result result = vibroCalcByAcceleration.calculate(acceleration, getFreq());

        applyResult(result, acceleration);

        lastKeyEvent = keyEvent;
    }

    private boolean checkFreqByZero() {
        try {
            double freqHz = Double.parseDouble(edFreqHz.getText());
            double freqCpm = Double.parseDouble(edFreqCpm.getText());

            if (freqCpm == 0.0 || freqHz == 0.0) return true;
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public void onChangeAccelerationMsec2EdIzm(Event event) {
        String text = edAccelerationMsec2.getText();

        if (isNotNumeric(text)) return;

        EdIzm edIzm = EdIzm.getEdIzm((String) accelerationMsec2SelectEdIzm.getSelectionModel().getSelectedItem());

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(accelerationMsec2SelectEdIzmLastValue)
                .parameter(Parameter.A_m_sec2)
                .build();

        edAccelerationMsec2.setText(vibroCalcByAcceleration.recalculateValue(acceleration, edIzm));
        accelerationMsec2SelectEdIzmLastValue = edIzm;
    }

    public void onEditAccelerationMsec2(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(EdIzm.getEdIzm((String) accelerationMsec2SelectEdIzm.getSelectionModel().getSelectedItem()))
                .parameter(Parameter.A_m_sec2)
                .build();

        vibroCalcByAcceleration.setParameters(getSelectedParameters());
        Result result = vibroCalcByAcceleration.calculate(acceleration, getFreq());

        applyResult(result, acceleration);

        lastKeyEvent = keyEvent;
    }

    public void onChangeAccelerationMmSec2EdIzm(Event event) {
        String text = edAccelerationMmSec2.getText();

        if (isNotNumeric(text)) return;

        EdIzm edIzm = EdIzm.getEdIzm((String) accelerationMmSec2SelectEdIzm.getSelectionModel().getSelectedItem());

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(accelerationMmSec2SelectEdIzmLastValue)
                .parameter(Parameter.A_mm_sec2)
                .build();

        edAccelerationMmSec2.setText(vibroCalcByAcceleration.recalculateValue(acceleration, edIzm));
        accelerationMmSec2SelectEdIzmLastValue = edIzm;
    }

    public void onEditAccelerationMmSec2(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value acceleration = Value.builder()
                .value(value)
                .edIzm(EdIzm.getEdIzm((String) accelerationMmSec2SelectEdIzm.getSelectionModel().getSelectedItem()))
                .parameter(Parameter.A_mm_sec2)
                .build();

        vibroCalcByAcceleration.setParameters(getSelectedParameters());
        Result result = vibroCalcByAcceleration.calculate(acceleration, getFreq());

        applyResult(result, acceleration);

        lastKeyEvent = keyEvent;
    }

    public void onChangeVelocityMsecEdIzm(Event event) {
        String text = edVelocityMsec.getText();

        if (isNotNumeric(text)) return;

        EdIzm edIzm = EdIzm.getEdIzm((String) velocityMsecSelectEdIzm.getSelectionModel().getSelectedItem());

        Double value = Double.parseDouble(text);
        Value velocity = Value.builder()
                .value(value)
                .edIzm(velocityMsecSelectEdIzmLastValue)
                .parameter(Parameter.V_m_sec)
                .build();

        edVelocityMsec.setText(vibroCalcByVelocity.recalculateValue(velocity, edIzm));
        velocityMsecSelectEdIzmLastValue = edIzm;
    }

    public void onEditVelocityMsec(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value velocity = Value.builder()
                .value(value)
                .edIzm(EdIzm.getEdIzm((String) velocityMsecSelectEdIzm.getSelectionModel().getSelectedItem()))
                .parameter(Parameter.V_m_sec)
                .build();

        vibroCalcByVelocity.setParameters(getSelectedParameters());
        Result result = vibroCalcByVelocity.calculate(velocity, getFreq());

        applyResult(result, velocity);

        lastKeyEvent = keyEvent;
    }

    public void onChangeVelocityMmSecEdIzm(Event event) {
        String text = edVelocityMmSec.getText();

        if (isNotNumeric(text)) return;

        EdIzm edIzm = EdIzm.getEdIzm((String) velocityMmSecSelectEdIzm.getSelectionModel().getSelectedItem());

        Double value = Double.parseDouble(text);
        Value velocity = Value.builder()
                .value(value)
                .edIzm(velocityMmSecSelectEdIzmLastValue)
                .parameter(Parameter.V_mm_sec)
                .build();

        edVelocityMmSec.setText(vibroCalcByVelocity.recalculateValue(velocity, edIzm));
        velocityMmSecSelectEdIzmLastValue = edIzm;
    }

    public void onEditVelocityMmSec(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value velocity = Value.builder()
                .value(value)
                .edIzm(EdIzm.getEdIzm((String) velocityMmSecSelectEdIzm.getSelectionModel().getSelectedItem()))
                .parameter(Parameter.V_mm_sec)
                .build();

        vibroCalcByVelocity.setParameters(getSelectedParameters());
        Result result = vibroCalcByVelocity.calculate(velocity, getFreq());

        applyResult(result, velocity);

        lastKeyEvent = keyEvent;
    }

    public void onChangeDisplacementMEdIzm(Event event) {
        String text = edDisplacementM.getText();

        if (isNotNumeric(text)) return;

        EdIzm edIzm = EdIzm.getEdIzm((String) displacementMSelectEdIzm.getSelectionModel().getSelectedItem());

        Double value = Double.parseDouble(text);
        Value displacement = Value.builder()
                .value(value)
                .edIzm(displacementMSelectEdIzmLastValue)
                .parameter(Parameter.D_m)
                .build();

        edDisplacementM.setText(vibroCalcByDisplacement.recalculateValue(displacement, edIzm));
        displacementMSelectEdIzmLastValue = edIzm;
    }

    public void onEditDisplacementM(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value displacement = Value.builder()
                .value(value)
                .edIzm(EdIzm.getEdIzm((String) displacementMSelectEdIzm.getSelectionModel().getSelectedItem()))
                .parameter(Parameter.D_m)
                .build();

        vibroCalcByDisplacement.setParameters(getSelectedParameters());
        Result result = vibroCalcByDisplacement.calculate(displacement, getFreq());

        applyResult(result, displacement);

        lastKeyEvent = keyEvent;
    }

    public void onChangeDisplacementMmEdIzm(Event event) {
        String text = edDisplacementMm.getText();

        if (isNotNumeric(text)) return;

        EdIzm edIzm = EdIzm.getEdIzm((String) displacementMmSelectEdIzm.getSelectionModel().getSelectedItem());

        Double value = Double.parseDouble(text);
        Value displacement = Value.builder()
                .value(value)
                .edIzm(displacementMmSelectEdIzmLastValue)
                .parameter(Parameter.D_mm)
                .build();

        edDisplacementMm.setText(vibroCalcByDisplacement.recalculateValue(displacement, edIzm));
        displacementMmSelectEdIzmLastValue = edIzm;
    }

    public void onEditDisplacementMm(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;

        String text = ((TextField) keyEvent.getSource()).getText();

        if (isNotNumeric(text)) return;

        Double value = Double.parseDouble(text);
        Value displacement = Value.builder()
                .value(value)
                .edIzm(EdIzm.getEdIzm((String) displacementMmSelectEdIzm.getSelectionModel().getSelectedItem()))
                .parameter(Parameter.D_mm)
                .build();

        vibroCalcByDisplacement.setParameters(getSelectedParameters());
        Result result = vibroCalcByDisplacement.calculate(displacement, getFreq());

        applyResult(result, displacement);

        lastKeyEvent = keyEvent;
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
        try {
            java.awt.Desktop.getDesktop().browse(new URI("www.vibrtest.ru"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Map<Parameter, EdIzm> getSelectedParameters() {
        Map<Parameter, EdIzm> parameters = new HashMap<>();

        parameters.put(Parameter.A_g, EdIzm.getEdIzm((String) accelerationGSelectEdIzm.getSelectionModel().getSelectedItem()));
        parameters.put(Parameter.A_m_sec2, EdIzm.getEdIzm((String) accelerationMsec2SelectEdIzm.getSelectionModel().getSelectedItem()));
        parameters.put(Parameter.A_mm_sec2, EdIzm.getEdIzm((String) accelerationMmSec2SelectEdIzm.getSelectionModel().getSelectedItem()));

        parameters.put(Parameter.V_m_sec, EdIzm.getEdIzm((String) velocityMsecSelectEdIzm.getSelectionModel().getSelectedItem()));
        parameters.put(Parameter.V_mm_sec, EdIzm.getEdIzm((String) velocityMmSecSelectEdIzm.getSelectionModel().getSelectedItem()));

        parameters.put(Parameter.D_m, EdIzm.getEdIzm((String) displacementMSelectEdIzm.getSelectionModel().getSelectedItem()));
        parameters.put(Parameter.D_mm, EdIzm.getEdIzm((String) displacementMmSelectEdIzm.getSelectionModel().getSelectedItem()));

        parameters.put(Parameter.A_db, EdIzm.NONE);
        parameters.put(Parameter.V_db_m_sec, EdIzm.NONE);
        parameters.put(Parameter.V_db_mm_sec, EdIzm.NONE);

        return parameters;
    }

    public void setEnglish(MouseEvent mouseEvent) {
        getSelectedEdIzm();
        clear();

        if (isEnglish.isSelected()) {
            vibroCalcByAcceleration.setMeasures(Measures.ENGLISH);
            vibroCalcByVelocity.setMeasures(Measures.ENGLISH);
            vibroCalcByDisplacement.setMeasures(Measures.ENGLISH);

            freqHzLabel.setText("Frequency [Hz]");
            freqCpmLabel.setText("Frequency [cpm]");
            vDbMmSecLabel.setText("VdB re 1x10e-6 inch/sec");
            vDbMSecLabel.setText("VdB re 1x10e-8 ft/sec");
            AccelerationGLabel.setText("Acceleration, g");
            AccelerationMSec2Label.setText("Acceleration, ft/sec2");
            AccelerationMmSec2Label.setText("Acceleration, inch/sec2");
            velocityMSecLabel.setText("Velocity, ft/sec");
            velocityMmSecLabel.setText("Velocity, inch/sec");
            displacementMLabel.setText("Displacement, inch");
            displacementMmLabel.setText("Displacement, mils");

            accelerationGSelectEdIzm.getItems().clear();
            accelerationMsec2SelectEdIzm.getItems().clear();
            accelerationMmSec2SelectEdIzm.getItems().clear();
            velocityMsecSelectEdIzm.getItems().clear();
            velocityMmSecSelectEdIzm.getItems().clear();
            displacementMSelectEdIzm.getItems().clear();
            displacementMmSelectEdIzm.getItems().clear();

            setEnglish();
        } else {
            vibroCalcByAcceleration.setMeasures(Measures.METRIC);
            vibroCalcByVelocity.setMeasures(Measures.METRIC);
            vibroCalcByDisplacement.setMeasures(Measures.METRIC);

            freqHzLabel.setText("Частота, Гц");
            freqCpmLabel.setText("Частота, об/мин");
            vDbMmSecLabel.setText("VdB re 1x10e-6 мм/сек");
            vDbMSecLabel.setText("VdB re 1x10e-8 м/с");
            AccelerationGLabel.setText("Виброускорение, g");
            AccelerationMSec2Label.setText("Виброускорение, м/с2");
            AccelerationMmSec2Label.setText("Виброускорение, мм/с2");
            velocityMSecLabel.setText("Виброскорость, м/с");
            velocityMmSecLabel.setText("Виброскорость, мм/с");
            displacementMLabel.setText("Виброперемещение, м");
            displacementMmLabel.setText("Виброперемещение, мм");

            setMetric();
        }

        setSelectedEdIzm();

        if (lastKeyEvent != null) {
            Event.fireEvent(lastKeyEvent.getTarget(), lastKeyEvent);
        }
    }

    private void clear() {
        accelerationGSelectEdIzm.getItems().clear();
        accelerationMsec2SelectEdIzm.getItems().clear();
        accelerationMmSec2SelectEdIzm.getItems().clear();
        velocityMsecSelectEdIzm.getItems().clear();
        velocityMmSecSelectEdIzm.getItems().clear();
        displacementMSelectEdIzm.getItems().clear();
        displacementMmSelectEdIzm.getItems().clear();
    }

    private void setEnglish() {
        setEdIzm(english.values());
    }

    private void setMetric() {
        setEdIzm(metric.values());
    }

    private void setEdIzm(Collection<String> values) {
        accelerationGSelectEdIzm.getItems().addAll(values);
        accelerationMsec2SelectEdIzm.getItems().addAll(values);
        accelerationMmSec2SelectEdIzm.getItems().addAll(values);
        velocityMsecSelectEdIzm.getItems().addAll(values);
        velocityMmSecSelectEdIzm.getItems().addAll(values);
        displacementMSelectEdIzm.getItems().addAll(values);
        displacementMmSelectEdIzm.getItems().addAll(values);
    }

    private void getSelectedEdIzm() {
        accelerationGSelectEdIzmIndex = accelerationGSelectEdIzm.getSelectionModel().getSelectedIndex();
        accelerationMsec2SelectEdIzmIndex = accelerationMsec2SelectEdIzm.getSelectionModel().getSelectedIndex();
        accelerationMmSec2SelectEdIzmIndex = accelerationMmSec2SelectEdIzm.getSelectionModel().getSelectedIndex();
        velocityMsecSelectEdIzmIndex = velocityMsecSelectEdIzm.getSelectionModel().getSelectedIndex();
        velocityMmSecSelectEdIzmIndex = velocityMmSecSelectEdIzm.getSelectionModel().getSelectedIndex();
        displacementMSelectEdIzmIndex = displacementMSelectEdIzm.getSelectionModel().getSelectedIndex();
        displacementMmSelectEdIzmIndex = displacementMmSelectEdIzm.getSelectionModel().getSelectedIndex();
    }

    private void setSelectedEdIzm() {
        accelerationGSelectEdIzm.getSelectionModel().select(accelerationGSelectEdIzmIndex);
        accelerationMsec2SelectEdIzm.getSelectionModel().select(accelerationMsec2SelectEdIzmIndex);
        accelerationMmSec2SelectEdIzm.getSelectionModel().select(accelerationMmSec2SelectEdIzmIndex);
        velocityMsecSelectEdIzm.getSelectionModel().select(velocityMsecSelectEdIzmIndex);
        velocityMmSecSelectEdIzm.getSelectionModel().select(velocityMmSecSelectEdIzmIndex);
        displacementMSelectEdIzm.getSelectionModel().select(displacementMSelectEdIzmIndex);
        displacementMmSelectEdIzm.getSelectionModel().select(displacementMmSelectEdIzmIndex);
    }

    public void edOnClick(MouseEvent mouseEvent) {
        ((TextField) mouseEvent.getSource()).selectAll();
    }

    public void btnRule(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/rule-window.fxml"));
            Scene secondScene = new Scene(root, 500, 250);
            Stage newWindow = new Stage();
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setTitle("Инструкция");
            newWindow.setResizable(false);
            newWindow.setScene(secondScene);

            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        StackPane secondaryLayout = new StackPane();
//        secondaryLayout.getChildren().add(secondLabel);


    }
}
