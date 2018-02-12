package ru.minikh.vibrocalc.calc;

import java.util.HashMap;
import java.util.Map;

public class VibroCalcByDisplacement extends VibroCalc {

    public Result calculate(Value value, Map<Parameter, EdIzm> parameters, Double freq) {

        if (!(value.getParameter() == Parameter.D_mm
                || value.getParameter() == Parameter.D_m)) {
            throw new RuntimeException("Не правильный параметр");
        }

        Double displacementRms = prepareValue(value);
        displacementRms = translationValue(value, displacementRms);

        Double _2piFreq = calc2PiFreq(freq);

        Map<String, Value> valueMap = new HashMap<>();

        for (Map.Entry<Parameter, EdIzm> parameter : parameters.entrySet()) {
            Double result = null;
            switch (parameter.getKey()) {
                case A_g:
                    result = displacementRms * _2piFreq * _2piFreq / G;
                    break;
                case A_m_sec2:
                    result = displacementRms * _2piFreq * _2piFreq ;
                    break;
                case A_mm_sec2:
                    result = displacementRms * _2piFreq * _2piFreq * KILO;
                    break;
                case V_mm_sec:
                    result = displacementRms * _2piFreq * KILO;
                    break;
                case V_m_sec:
                    result = displacementRms * _2piFreq;
                    break;
                case D_m:
                    result = displacementRms;
                    break;
                case D_mm:
                    result = displacementRms * KILO;
                    break;
                case A_db:
                    result = 20.0 * Math.log10(displacementRms * _2piFreq * _2piFreq / (G * Math.pow(10, -6)));
                    break;
                case V_db_m_sec:
                    result = 20.0 * Math.log10(displacementRms * _2piFreq / Math.pow(10, -8));
                    break;
                case V_db_mm_sec:
                    result = 20.0 * Math.log10(displacementRms * _2piFreq * KILO / Math.pow(10, -6));
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
            case D_m:
                break;
            case D_mm:
                rms *= KILO;
                break;
        }

        return rms;
    }
}
