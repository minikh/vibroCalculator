package ru.minikh.vibrocalc.calc;

import java.util.HashMap;
import java.util.Map;

public class VibroCalcByAcceleration extends VibroCalc {

    @Override
    public Result calculate(Value value, Map<Parameter, EdIzm> parameters, Double freq) {

        if (!(value.getParameter() == Parameter.A_g
                || value.getParameter() == Parameter.A_db
                || value.getParameter() == Parameter.A_mm_sec2
                || value.getParameter() == Parameter.A_m_sec2)) {
            throw new RuntimeException("Не правильный параметр");
        }

        Double accelerationRms = prepareValue(value);
        accelerationRms = translationValue(value, accelerationRms);

        Double _2piFreq = calc2PiFreq(freq);

        Map<String, Value> valueMap = new HashMap<>();

        for (Map.Entry<Parameter, EdIzm> parameter : parameters.entrySet()) {
            Double result = null;
            switch (parameter.getKey()) {
                case A_g:
                    result = accelerationRms / G;
                    break;
                case A_m_sec2:
                    result = accelerationRms;
                    break;
                case A_mm_sec2:
                    result = accelerationRms * KILO;
                    break;
                case V_mm_sec:
                    result = accelerationRms / _2piFreq * KILO;
                    break;
                case V_m_sec:
                    result = accelerationRms / _2piFreq;
                    break;
                case D_m:
                    result = accelerationRms / (_2piFreq) / (_2piFreq);
                    break;
                case D_mm:
                    result = accelerationRms / (_2piFreq / KILO) / (_2piFreq);
                    break;
                case A_db:
                    result = 20.0 * Math.log10(accelerationRms / (G * Math.pow(10, -6)));
                    break;
                case V_db_m_sec:
                    result = 20.0 * Math.log10(accelerationRms / _2piFreq / Math.pow(10, -8));
                    break;
                case V_db_mm_sec:
                    result = 20.0 * Math.log10(accelerationRms * KILO / _2piFreq / Math.pow(10, -6));
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
            case A_db:
                rms = Math.pow(10, rms / 20.0) * (G * Math.pow(10, -6));
                break;
            case A_g:
                rms *= G;
                break;
            case A_mm_sec2:
                rms /= KILO;
                break;
            case A_m_sec2:
                break;
        }

        return rms;
    }
}
