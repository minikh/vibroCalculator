package ru.minikh.vibrocalc.calc;

import org.junit.Test;

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

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(value.getValue(), 0.159155, 0.0000001);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(value.getValue(), 0.000159155, 0.0000001);

    }
}