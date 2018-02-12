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
        parameters.put(Parameter.A_g, EdIzm.PEAK_TO_PEAK);
        parameters.put(Parameter.A_m_sec2, EdIzm.RMS);
        parameters.put(Parameter.A_mm_sec2, EdIzm.RMS);

        parameters.put(Parameter.V_m_sec, EdIzm.RMS);
        parameters.put(Parameter.V_mm_sec, EdIzm.RMS);
        parameters.put(Parameter.D_m, EdIzm.PEAK_TO_PEAK);
        parameters.put(Parameter.D_mm, EdIzm.PEAK_TO_PEAK);

        parameters.put(Parameter.A_db, EdIzm.NONE);
        parameters.put(Parameter.V_db_m_sec, EdIzm.NONE);
        parameters.put(Parameter.V_db_mm_sec, EdIzm.NONE);

        Result result = vibroCalc.calculateByAcceleration(acceleration, parameters, 1.0);

        Value value = result.getValues().get(Parameter.V_mm_sec.name());
        assertEquals(159.155, value.getValue(), 0.0001);

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
//        assertEquals(100.17, value.getValue(), 0.001);

        value = result.getValues().get(Parameter.V_db_m_sec.name());
//        assertEquals(144.036, value.getValue(), 0.0001);


        for (Map.Entry<String, Value> valueEntry : result.getValues().entrySet()) {
            System.out.println(valueEntry.getKey() + ": \t" + valueEntry.getValue().getValue());
        }
    }
}