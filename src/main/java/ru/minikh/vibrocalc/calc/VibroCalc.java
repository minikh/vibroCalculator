package ru.minikh.vibrocalc.calc;

import java.util.HashMap;
import java.util.Map;

public class VibroCalc {

    private final static Double PI_2 = 2 * Math.PI;
    private final static Double AVG_TO_RMS_KOEFF = 1.1098901098901098901098901098901;

    public Result calculate(Value value, Map<Parameter, EdIzm> parameters, Double freq) {

        Double rms = prepareValue(value);

        Double pi2Freq = PI_2 * freq;

        Map<String, Value> valueMap = new HashMap<>();

        for (Map.Entry<Parameter, EdIzm> parameter : parameters.entrySet()) {
            switch (parameter.getKey()) {
                case V_mm_sec:
                    Double velocityMmSecRms = rms / pi2Freq;
                    Value.ValueBuilder valueBuilder = Value.builder()
                            .parameter(parameter.getKey());
                    Value velocity = prepareResult(parameter, velocityMmSecRms, valueBuilder);
                    valueMap.put(velocity.getParameter().name(), velocity);
                    break;

                case V_m_sec:
                    Double velocityMSecRms = rms / pi2Freq / 1000.0;
                    valueBuilder = Value.builder()
                            .parameter(parameter.getKey());
                    velocity = prepareResult(parameter, velocityMSecRms, valueBuilder);
                    valueMap.put(velocity.getParameter().name(), velocity);
                    break;
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
