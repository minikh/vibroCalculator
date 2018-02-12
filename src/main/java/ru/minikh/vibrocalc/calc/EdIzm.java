package ru.minikh.vibrocalc.calc;

public enum EdIzm {
    RMS("rms"),
    AVG("awg"),
    PEAK("peak"),
    PEAK_TO_PEAK("peak-to-peak");

    private String str;

    EdIzm(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
