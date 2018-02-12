package ru.minikh.vibrocalc.calc;

import java.util.HashMap;
import java.util.Map;

public class VibroCalc {

    private final static Double _2_PI = 2 * Math.PI;
    private final static Double AVG_TO_RMS_KOEFF = 1.1098901098901098901098901098901;

    public Result calculateByAcceleration(Value value, Map<Parameter, EdIzm> parameters, Double freq) {

        Double accelerationRms = prepareValue(value);

        Double _2piFreq = _2_PI * freq;

        Map<String, Value> valueMap = new HashMap<>();

        for (Map.Entry<Parameter, EdIzm> parameter : parameters.entrySet()) {
            Double result = null;
            switch (parameter.getKey()) {
                case V_mm_sec:
                    result = accelerationRms / _2piFreq;
                    break;
                case V_m_sec:
                    result = accelerationRms / _2piFreq / 1000.0;
                    break;
                case D_m:
                    result = accelerationRms / (_2piFreq * 1000.0) / (_2piFreq * 1000.0 * 2);
                    break;
                case D_mm:
                    result = accelerationRms / (_2piFreq * 1000.0) / (_2piFreq * 1000.0 * 2);
                    break;
            }

            if (result != null) {
                Value.ValueBuilder valueBuilder = Value.builder()
                        .parameter(parameter.getKey());
                Value resultValue = prepareResult(parameter, result, valueBuilder);
                valueMap.put(resultValue.getParameter().name(), resultValue);
            }
        }

        return Result.builder().values(valueMap).build();
    }

    private Double prepareValue(Value value) {
        Double rms = 0.0;
        switch (value.getEdIzm()) {
            case RMS:
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

    private Value prepareResult(Map.Entry<Parameter, EdIzm> parameter,
                                Double rmsValue,
                                Value.ValueBuilder valueBuilder) {
        Double result;
        switch (parameter.getValue()) {
            case RMS:
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
}
