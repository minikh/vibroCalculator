package ru.minikh.vibrocalc.calc;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class VibroCalcAccelerationEnglishTest {

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
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_m_sec2)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();
        vibroCalc.setMeasures(Measures.ENGLISH);
        vibroCalc.setParameters(parameters);

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(1.90986, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.V_m_sec.name());
        assertEquals(0.159155, value.getValue(), 0.0000001);

        value = result.getValues().get(Parameter.D_m.name());
        assertEquals(0.859739, value.getValue(), 1.0e-005);

        value = result.getValues().get(Parameter.D_mm.name());
        assertEquals(859.739, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(0.0310808, value.getValue(), 0.00001);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(1.0, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(12.0, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(89.8499, value.getValue(), 0.001);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(133.717, value.getValue(), 0.001);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(153.717, value.getValue(), 0.001);


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

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();
        vibroCalc.setParameters(parameters);

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
        assertEquals(0.000101971, value.getValue(), 0.00000001);

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

//    @Test
    public void testCalc2Ag() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.RMS)
                .parameter(Parameter.A_g)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();
        vibroCalc.setMeasures(Measures.ENGLISH);
        vibroCalc.setParameters(parameters);

        Result result = vibroCalc.calculate(acceleration, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
//        assertEquals(61.4481, value.getValue(), 0.01);

        value = result.getValues().get(Parameter.V_m_sec.name());
//        assertEquals(5.12068, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.D_m.name());
//        assertEquals(27.6614, value.getValue(), 1.0e-005);

        value = result.getValues().get(Parameter.D_mm.name());
//        assertEquals(27661.4, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.A_g.name());
        assertEquals(1.0, value.getValue(), 0.00000001);

        value = result.getValues().get(Parameter.A_m_sec2.name());
        assertEquals(32.1742, value.getValue(), 1.0e-004);

        value = result.getValues().get(Parameter.A_mm_sec2.name());
        assertEquals(386.09, value.getValue(), 1.0e-001);

        value = result.getValues().get(Parameter.A_db.name());
        assertEquals(120, value.getValue(), 1.0e-003);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
        assertEquals(163.867, value.getValue(), 1.0e-002);

        value = result.getValues().get(Parameter.V_db_mm_sec.name());
        assertEquals(183.867, value.getValue(), 1.0e-002);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }

//    @Test
    public void testCalc2AdB() {
        Value acceleration = Value.builder()
                .value(1.0)
                .edIzm(EdIzm.NONE)
                .parameter(Parameter.A_db)
                .build();

        VibroCalcByAcceleration vibroCalc = new VibroCalcByAcceleration();
        vibroCalc.setParameters(parameters);

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