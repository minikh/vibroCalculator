package ru.minikh.vibrocalc.calc;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class VibroCalcDisplacementTest {

    private Map<Parameter, EdIzm> parameters = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        parameters.put(Parameter.A_g, EdIzm.RMS);
        parameters.put(Parameter.A_m_sec2, EdIzm.RMS);
        parameters.put(Parameter.A_mm_sec2, EdIzm.RMS);

        parameters.put(Parameter.V_m_sec, EdIzm.RMS);
        parameters.put(Parameter.V_mm_sec, EdIzm.RMS);
        parameters.put(Parameter.D_m, EdIzm.PEAK_TO_PEAK);
        parameters.put(Parameter.D_mm, EdIzm.PEAK_TO_PEAK);

        parameters.put(Parameter.A_db, EdIzm.NONE);
        parameters.put(Parameter.V_db_m_sec, EdIzm.NONE);
        parameters.put(Parameter.V_db_mm_sec, EdIzm.NONE);
    }

    @Test
    public void testCalc1A() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.D_m)
                .build();

        VibroCalcByDisplacement vibroCalc = new VibroCalcByDisplacement();
        vibroCalc.setParameters(parameters);

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(2221.44, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(2.22144, value.getValue(), 1.0e-005);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(1.0, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(1000.0, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(1.42328, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(13.9577, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(13957.7, value.getValue(), 1.0e-001);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(123.066, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(166.933, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(186.933, value.getValue(), 1.0e-003);


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

        VibroCalcByDisplacement vibroCalc = new VibroCalcByDisplacement();
        vibroCalc.setParameters(parameters);

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
    public void testCalc2D_mm() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.PEAK_TO_PEAK)
                .parameter(Parameter.D_mm)
                .build();

        VibroCalcByDisplacement vibroCalc = new VibroCalcByDisplacement();
        vibroCalc.setParameters(parameters);

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(2.22144, value.getValue(), 1.0e-005);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(0.00222144, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(0.001, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(1, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(0.00142328, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(0.0139577, value.getValue(), 1.0e-007);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(13.9577, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(63.0658, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(106.933, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(126.933, value.getValue(), 1.0e-003);


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

        VibroCalcByDisplacement vibroCalc = new VibroCalcByDisplacement();
        vibroCalc.setParameters(parameters);

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
                .parameter(Parameter.V_m_sec)
                .build();

        VibroCalcByDisplacement vibroCalc = new VibroCalcByDisplacement();

        vibroCalc.calculate(acceleration, 1.0);
    }
}