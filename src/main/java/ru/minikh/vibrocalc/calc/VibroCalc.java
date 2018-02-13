package ru.minikh.vibrocalc.calc;

import java.util.Map;

public abstract class VibroCalc {

    final static Double _2_PI = 2 * Math.PI;
    final static Double AVG_TO_RMS_KOEFF = 1.1098901098901098901098901098901;
    final static Double G = 9.80665;
    final static Double KILO = 1000.0;

    public abstract Result calculate(Value value, Map<Parameter, EdIzm> parameters, Double freq);

    Double calc2PiFreq(Double freq) {
        return _2_PI * freq;
    }

    Value prepareResult(Map.Entry<Parameter, EdIzm> parameter,
                        Double rmsValue,
                        Value.ValueBuilder valueBuilder) {
        Double result;
        switch (parameter.getValue()) {
            case RMS:
            case NONE:
                result = rmsValue;
                valueBuilder
                        .value(result)
                        .edIzm(parameter.getValue());
                break;
            case AVG:
                result = rmsValue / AVG_TO_RMS_KOEFF;
                valueBuilder
                        .value(result)
                        .edIzm(parameter.getValue());
                break;
            case PEAK:
                result = rmsValue * Math.sqrt(2);
                valueBuilder
                        .value(result)
                        .edIzm(parameter.getValue());
                break;
            case PEAK_TO_PEAK:
                result = rmsValue * Math.sqrt(2) * 2;
                valueBuilder
                        .value(result)
                        .edIzm(parameter.getValue());
                break;
        }
        return valueBuilder.build();
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
