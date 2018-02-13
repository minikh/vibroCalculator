package ru.minikh.vibrocalc.calc;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class VibroCalcVelocityTest {

    @Test
    public void testCalc1V() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.V_mm_sec)
                .build();

        VibroCalcByVelocity vibroCalc = new VibroCalcByVelocity();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(1.0, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(0.001, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(0.000450158, value.getValue(), 1.0e-009);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(0.450158, value.getValue(), 1.0e-006);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(0.00181218, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(0.00628319, value.getValue(), 1.0e-008);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(6.28319, value.getValue(), 1.0e-005);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(56.1331, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(100.0, value.getValue(), 1.0e-006);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(120.0, value.getValue(), 1.0e-006);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

//    @Test
    public void testCalc2A() {
        Value acceleration = Value.builder()
                .value(2.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_m_sec2)
                .build();

        VibroCalcByVelocity vibroCalc = new VibroCalcByVelocity();

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
//        assertEquals(170.057, value.getValue(), 0.0001);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
//        assertEquals(170.057, value.getValue(), 0.0001);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

//    @Test
    public void testCalc2A_mm_sec() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_mm_sec2)
                .build();

        VibroCalcByVelocity vibroCalc = new VibroCalcByVelocity();

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
//        assertEquals(84.0364, value.getValue(), 0.0001);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
//        assertEquals(104.036, value.getValue(), 0.0001);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

//    @Test
    public void testCalc2Ag() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.A_g)
                .build();

        VibroCalcByVelocity vibroCalc = new VibroCalcByVelocity();

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
//        assertEquals(154.836, value.getValue(), 0.0001);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
//        assertEquals(174.836, value.getValue(), 0.0001);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

    @Test(expected = RuntimeException.class)
    public void shouldException() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.A_g)
                .build();

        VibroCalcByVelocity vibroCalc = new VibroCalcByVelocity();

        vibroCalc.calculate(acceleration, 1.0);
    }
}