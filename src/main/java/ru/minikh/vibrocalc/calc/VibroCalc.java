package ru.minikh.vibrocalc.calc;

import java.util.HashMap;
import java.util.Map;

public class VibroCalc {

    private final static Double PI_2 =  2 * Math.PI;

    public Result calculate(Value value, Double freq) {

        Double pi2Freq = PI_2 * freq;

        Map<String, Value> valueMap = new HashMap<>();

        Double val = value.getValue() / (pi2Freq); // * 1000.0) / Math.sqrt(2.0);

        Value velocity = Value.builder()
                .value(val)
                .parameter(Parameter.V_mm_sec)
                .edIzm(EdIzm.RMS)
                .build();

        valueMap.put(velocity.getParameter().name(), velocity);

        return Result.builder().values(valueMap).build();
    }
}
