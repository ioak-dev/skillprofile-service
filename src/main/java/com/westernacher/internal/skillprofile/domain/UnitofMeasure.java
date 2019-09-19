package com.westernacher.internal.skillprofile.domain;

import lombok.Getter;

@Getter
public class UnitofMeasure {
    private final int years;
    private final int months;
    public UnitofMeasure(int years, int months) {
        this.years = years;
        this.months = months;
    }
}
