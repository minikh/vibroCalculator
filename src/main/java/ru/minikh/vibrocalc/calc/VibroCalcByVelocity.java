package ru.minikh.vibrocalc.calc;

import java.util.HashMap;
import java.util.Map;

public class VibroCalcByVelocity extends VibroCalc {

    public Result calculate(Value value, Map<Parameter, EdIzm> parameters, Double freq) {

        if (!(value.getParameter() == Parameter.V_db_m_sec
                || value.getParameter() == Parameter.V_db_mm_sec
                || value.getParameter() == Parameter.V_m_sec
                || value.getParameter() == Parameter.V_mm_sec)) {
            throw new RuntimeException("Не правильный параметр");
        }

        Double velocityRms = prepareValue(value);
        velocityRms = translationValue(value, velocityRms);

        Double _2piFreq = calc2PiFreq(freq);

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

    private Double translationValue(Value value, Double rms) {
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
}