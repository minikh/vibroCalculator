package ru.minikh.vibrocalc.calc;

import java.util.Map;

public abstract class VibroCalc {

    final static Double _2_PI = 2 * Math.PI;
    final static Double AVG_TO_RMS_KOEFF = 1.1098901098901098901098901098901;
    static Double G = 9.80665;
    static Double KILO = 1000.0;
    static Double ftToInch = 1.0;
    static Double inchToMil = 1.0;
    private Measures measures = Measures.METRIC;

    private Map<Parameter, EdIzm> parameters;

    public abstract Result calculate(Value value, Double freq);

    public void setParameters(Map<Parameter, EdIzm> parameters) {
        this.parameters = parameters;
    }

    Map<Parameter, EdIzm> getParameters() {
        if (parameters == null) throw new RuntimeException("Не заданы параметры");
        return parameters;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        switch (measures){
            case METRIC:
                KILO = 1000.0;
                ftToInch = 1.0;
                inchToMil = 1.0;
                G = 9.80665;
                break;
            case ENGLISH:
                KILO = 12.0;
                ftToInch = 12.0;
                inchToMil = 1000.0;
                G = 9.80665 / 0.3048;
                break;
        }
        this.measures = measures;
    }

    Double calc2PiFreq(Double freq) {
        return _2_PI * freq;
    }

    Value prepareResult(EdIzm parameter, Double rmsValue, Value.ValueBuilder valueBuilder) {
        Double result;
        switch (parameter) {
            case RMS:
            case NONE:
                result = rmsValue;
                valueBuilder
                        .value(result)
                        .edIzm(parameter);
                break;
            case AVG:
                result = rmsValue / AVG_TO_RMS_KOEFF;
                valueBuilder
                        .value(result)
                        .edIzm(parameter);
                break;
            case PEAK:
                result = rmsValue * Math.sqrt(2);
                valueBuilder
                        .value(result)
                        .edIzm(parameter);
                break;
            case PEAK_TO_PEAK:
                result = rmsValue * Math.sqrt(2) * 2;
                valueBuilder
                        .value(result)
                        .edIzm(parameter);
                break;
        }
        return valueBuilder.build();
    }

    public String recalculateValue(Value value, EdIzm currentEdIzm) {
        if (currentEdIzm == value.getEdIzm()) return String.valueOf(value.getValue());

        Double rms = prepareValue(value);

        Value.ValueBuilder valueBuilder = Value.builder()
                .parameter(value.getParameter());
        Value result = prepareResult(currentEdIzm, rms, valueBuilder);
        return String.valueOf(result.getValue());
    }

    Double prepareValue(Value value) {
        Double rms = 0.0;
        switch (value.getEdIzm()) {
            case RMS:
            case NONE:
                rms = value.getValue();
                break;
            case AVG:
                rms = value.getValue() * AVG_TO_RMS_KOEFF;
                break;
            case PEAK:
                rms = value.getValue() / Math.sqrt(2);
                break;
            case PEAK_TO_PEAK:
                rms = value.getValue() / 2 / Math.sqrt(2);
                break;
        }
        return rms;
    }
}
