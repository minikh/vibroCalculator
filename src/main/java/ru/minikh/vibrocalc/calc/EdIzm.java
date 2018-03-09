package ru.minikh.vibrocalc.calc;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum EdIzm {
    RMS(1, "rms", "СКЗ"),
    AVG(2, "avg", "СЗ"),
    PEAK(3, "pk", "Пик"),
    PEAK_TO_PEAK(4, "pk-pk", "Размах"),
    NONE(5, "no", "нет");

    private Integer position;
    private String english;
    private String metric;

    EdIzm(Integer position, String english, String metric) {
        this.position = position;
        this.english = english;
        this.metric = metric;
    }

    public Integer getPosition() {
        return position;
    }

    public static EdIzm getEdIzm(String str) {
        for (EdIzm edIzm : EdIzm.values()) {
            if (edIzm.english.equals(str)) return edIzm;
        }

        for (EdIzm edIzm : EdIzm.values()) {
            if (edIzm.metric.equals(str)) return edIzm;
        }

        throw new RuntimeException("Нет такой единицы измерения: " + str);
    }



    public static Map<Integer, String> getEnglish() {
        return Arrays.stream(EdIzm.values())
                .filter(e -> e != NONE)
                .filter(e -> e != AVG)
                .collect(Collectors.toMap(EdIzm::getPosition, p -> p.english));
    }

    public static Map<Integer, String> getMetric() {
        return Arrays.stream(EdIzm.values())
                .filter(e -> e != NONE)
                .filter(e -> e != AVG)
                .collect(Collectors.toMap(EdIzm::getPosition, p -> p.metric));
    }
}
