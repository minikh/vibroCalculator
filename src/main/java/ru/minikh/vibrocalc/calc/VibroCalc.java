package ru.minikh.vibrocalc.calc;

import java.util.HashMap;
import java.util.Map;

public class VibroCalc {

    private final static Double PI_2 = 2 * Math.PI;
    private final static Double AVG_TO_RMS_KOEFF = 1.1098901098901098901098901098901;

    public Result calculate(Value value, Map<Parameter, EdIzm> parameters, Double freq) {

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

        Double pi2Freq = PI_2 * freq;

        Map<String, Value> valueMap = new HashMap<>();

        for (Map.Entry<Parameter, EdIzm> parameter : parameters.entrySet()) {
            switch (parameter.getKey()) {
                case V_mm_sec:
                    Double result;
                    Double velocityMmSecRms = rms / (pi2Freq);
                    Value.ValueBuilder valueBuilder = Value.builder()
                            .parameter(parameter.getKey());
                    switch (parameter.getValue()) {
                        case RMS:
                            result = velocityMmSecRms;
                            valueBuilder
                                    .value(result)
                                    .edIzm(parameter.getValue());
                            break;
                        case AVG:
                            result = velocityMmSecRms / AVG_TO_RMS_KOEFF;
                            valueBuilder
                                    .value(result)
                                    .edIzm(parameter.getValue());
                            break;
                        case PEAK:
                            result = velocityMmSecRms * Math.sqrt(2);
                            valueBuilder
                                    .value(result)
                                    .edIzm(parameter.getValue());
                            break;
                        case PEAK_TO_PEAK:
                            result = velocityMmSecRms * Math.sqrt(2) * 2;
                            valueBuilder
                                    .value(result)
                                    .edIzm(parameter.getValue());
                            break;
                    }
                    Value velocity = valueBuilder.build();
                    valueMap.put(velocity.getParameter().name(), velocity);
                    break;

                case V_m_sec:
                    Double velocityMSecRms = rms / (pi2Freq) / 1000;
                    valueBuilder = Value.builder()
                            .parameter(parameter.getKey());
                    switch (parameter.getValue()) {
                        case RMS:
                            result = velocityMSecRms;
                            valueBuilder
                                    .value(result)
                                    .edIzm(parameter.getValue());
                            break;
                        case AVG:
                            result = velocityMSecRms / AVG_TO_RMS_KOEFF;
                            valueBuilder
                                    .value(result)
                                    .edIzm(parameter.getValue());
                            break;
                        case PEAK:
                            result = velocityMSecRms * Math.sqrt(2);
                            valueBuilder
                                    .value(result)
                                    .edIzm(parameter.getValue());
                            break;
                        case PEAK_TO_PEAK:
                            result = velocityMSecRms * Math.sqrt(2) * 2;
                            valueBuilder
                                    .value(result)
                                    .edIzm(parameter.getValue());
                            break;
                    }
                    velocity = valueBuilder.build();
                    valueMap.put(velocity.getParameter().name(), velocity);
                    break;
            }
        }

        //Double velocityMmSec = rms / (pi2Freq); // * 1000.0) / Math.sqrt(2.0);
//        Value velocityMmSec = Value.builder()
//                .value(rms / (pi2Freq))
//                .parameter(Parameter.V_mm_sec)
//                .edIzm(EdIzm.RMS)
//                .build();
//        valueMap.put(velocityMmSec.getParameter().name(), velocityMmSec);

        return Result.builder().values(valueMap).build();
    }
}
