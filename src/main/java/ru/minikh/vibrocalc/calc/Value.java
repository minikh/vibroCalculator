package ru.minikh.vibrocalc.calc;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Value {
    private Double value;
    private EdIzm edIzm;
    private Parameter parameter;
}
