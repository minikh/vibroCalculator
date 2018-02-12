package ru.minikh.vibrocalc.calc;

import java.util.HashMap;
import java.util.Map;

public class VibroCalcByVelocity {

    private final static Double _2_PI = 2 * Math.PI;
    private final static Double AVG_TO_RMS_KOEFF = 1.1098901098901098901098901098901;
    private final static Double G = 9.80665;
    private final static Double KILO = 1000.0;

    public Result calculate(Value value, Map<Parameter, EdIzm> parameters, Double freq) {

        if (!(value.getParameter() == Parameter.V_db_m_sec
                || value.getParameter() == Parameter.V_db_mm_sec
                || value.getParameter() == Parameter.V_m_sec
                || value.getParameter() == Parameter.V_mm_sec)) {
            throw new RuntimeException("Не правильный параметр");
        }

        Double velocityRms = prepareValue(value);

        Double _2piFreq = _2_PI * freq;

        Map<String, Value> valueMap = new HashMap<>();

        for (Map.Entry<Parameter, EdIzm> parameter : parameters.entrySet()) {
            Double result = null;
            switch (parameter.getKey()) {
                case A_g:
                    result = (velocityRms * _2piFreq) / G / KILO;
                    break;
                case A_m_sec2:
                    result = velocityRms * _2piFreq / KILO;
                    break;
                case A_mm_sec2:
                    result = velocityRms * _2piFreq;
                    break;
                case V_mm_sec:
                    result = velocityRms;
                    break;
                case V_m_sec:
                    result = velocityRms / KILO;
                    break;
                case D_m:
                    result = velocityRms / (_2piFreq * KILO);
                    break;
                case D_mm:
                    result = velocityRms / _2piFreq;
                    break;
                case A_db:
                    result = 20.0 * Math.log10(velocityRms * _2piFreq / KILO / (G * Math.pow(10, -6)));
                    break;
                case V_db_m_sec:
                    result = 20.0 * Math.log10(velocityRms / KILO / Math.pow(10, -8));
                    break;
                case V_db_mm_sec:
                    result = 20.0 * Math.log10(velocityRms / Math.pow(10, -6));
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

        switch (value.getParameter()) {
            case V_db_m_sec:
                rms *= G;
                break;
            case V_db_mm_sec:
                rms *= G;
                break;
            case V_m_sec:
                rms /= KILO;
                break;
            case V_mm_sec:
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
}
