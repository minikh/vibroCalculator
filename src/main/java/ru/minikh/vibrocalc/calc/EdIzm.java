package ru.minikh.vibrocalc.calc;

public enum EdIzm {
    RMS("СКЗ"),
    AVG("СЗ"),
    PEAK("Пик"),
    PEAK_TO_PEAK("Размах"),
    NONE("нет");

    private String str;

    EdIzm(String str) {
        this.str = str;
    }

    public static EdIzm getEdIzm(String str) {
        for (EdIzm edIzm : EdIzm.values()) {
            if (edIzm.str.equals(str)) return edIzm;
        }

        throw new RuntimeException("Нет такой единицы измерения: " + str);
    }

    public String getStr() {
        return str;
    }
}
