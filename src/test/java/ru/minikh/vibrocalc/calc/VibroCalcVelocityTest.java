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
                .parameter(Parameter.V_m_sec)
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

    @Test
    public void testCalc2V_m_sec() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.V_m_sec)
                .build();

        VibroCalcByVelocity vibroCalc = new VibroCalcByVelocity();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(1000, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(1.0, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(0.450158, value.getValue(), 1.0e-006);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(450.158, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(1.81218, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(6.28319, value.getValue(), 1.0e-005);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(6283.19, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(116.133, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(160, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(180, value.getValue(), 1.0e-007);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

    @Test
    public void testCalc2VdbMmSec() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.V_db_mm_sec)
                .build();

        VibroCalcByVelocity vibroCalc = new VibroCalcByVelocity();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(1.12202e-006, value.getValue(), 1.0e-0011);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(1.12202e-009, value.getValue(), 1.0e-014);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(5.05086e-010, value.getValue(), 1.0e-0015);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(5.05086e-007, value.getValue(), 1.0e-0012);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(2.0333e-009, value.getValue(), 1.0e-0013);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(7.04985e-009, value.getValue(), 1.0e-0014);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(7.04985e-006, value.getValue(), 1.0e-0011);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(-62.8669, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(-19, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(1, value.getValue(), 1.0e-004);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

    @Test
    public void testCalc2VdbMSec() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.V_db_m_sec)
                .build();

        VibroCalcByVelocity vibroCalc = new VibroCalcByVelocity();

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(1.12202e-005, value.getValue(), 1.0e-0010);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(1.12202e-008, value.getValue(), 1.0e-013);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(5.05086e-009, value.getValue(), 1.0e-0014);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(5.05086e-006, value.getValue(), 1.0e-0011);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(2.0333e-008, value.getValue(), 1.0e-0012);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(7.04985e-008, value.getValue(), 1.0e-0013);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(7.04985e-005, value.getValue(), 1.0e-0010);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(-42.8669, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(1, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(21, value.getValue(), 1.0e-004);


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