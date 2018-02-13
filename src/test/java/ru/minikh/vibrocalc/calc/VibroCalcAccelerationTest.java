package ru.minikh.vibrocalc.calc;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class VibroCalcAccelerationTest {

    @Test
    public void testCalc1A() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_m_sec2)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(159.155, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(0.159155, value.getValue(), 0.0000001);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(0.0716449, value.getValue(), 0.00000001);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(71.6449, value.getValue(), 0.00001);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(0.288418, value.getValue(), 0.00001);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(1.0, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(1000.0, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(100.17, value.getValue(), 0.001);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(144.036, value.getValue(), 0.001);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(164.036, value.getValue(), 0.001);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

    @Test
    public void testCalc2A() {
        Value acceleration = Value.builder()
                .value(2.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_m_sec2)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(318.31, value.getValue(), 0.001);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(0.31831, value.getValue(), 0.000001);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(0.14329, value.getValue(), 0.000001);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(143.29, value.getValue(), 0.001);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(0.576836, value.getValue(), 0.00001);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(2.0, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(2000.0, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(106.19, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(150.057, value.getValue(), 0.0001);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(170.057, value.getValue(), 0.0001);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

    @Test
    public void testCalc2A_mm_sec() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_mm_sec2)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(0.159155, value.getValue(), 0.000001);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(0.000159155, value.getValue(), 0.000000001);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(7.16449e-005, value.getValue(), 0.000000001);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(0.0716449, value.getValue(), 0.000001);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(0.000288418, value.getValue(), 0.00000001);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(0.001, value.getValue(), 0.001);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(1.0, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(40.1695, value.getValue(), 0.0001);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(84.0364, value.getValue(), 0.0001);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(104.036, value.getValue(), 0.001);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

    @Test
    public void testCalc2Ag() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.A_g)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(551.821, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(0.551821, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(0.248407, value.getValue(), 1.0e-005);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(248.407, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(1.0, value.getValue(), 0.00000001);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(3.46719, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(3467.19, value.getValue(), 1.0e-001);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(110.969, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(154.836, value.getValue(), 0.0001);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(174.836, value.getValue(), 0.0001);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

    @Test
    public void testCalc2AdB() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.A_db)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.A_db.name());
        assertEquals(1.0, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(1.10033e-005, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(0.0110033, value.getValue(), 1.0e-001);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(3.17355e-006, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(0.00175123, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(1.75123e-006, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(7.8833e-007, value.getValue(), 1.0e-005);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(0.00078833, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(44.8669, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(64.8669, value.getValue(), 1.0e-004);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

    @Test(expected = RuntimeException.class)
    public void shouldException() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.V_m_sec)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();

        vibroCalc.calculate(acceleration, 1.0);
    }
}