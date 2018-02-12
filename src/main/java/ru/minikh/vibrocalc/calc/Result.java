package ru.minikh.vibrocalc.calc;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Result {
    private Map<String, Value> values;
}
