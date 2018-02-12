package ru.minikh.vibrocalc.calc;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class VibroCalcTest {

    @Test
    public void testCalc() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_m_sec2)
                .build();

        VibroCalc vibroCalc = new VibroCalc();

        Map<Parameter, EdIzm> parameters = new HashMap<>();
        parameters.put(Parameter.V_m_sec, EdIzm.RMS);
        parameters.put(Parameter.V_mm_sec, EdIzm.RMS);

        Result result = vibroCalc.calculateByAcceleration(acceleration, parameters, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(value.getValue(), 0.159155, 0.0000001);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(value.getValue(), 0.000159155, 0.0000000001);

    }
}