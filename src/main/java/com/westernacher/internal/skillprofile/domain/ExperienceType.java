package com.westernacher.internal.skillprofile.domain;

import lombok.Getter;

@Getter
public class ExperienceType {
    private final int years;
    private final int months;
    public ExperienceType(int years, int months) {
        this.years = years;
        this.months = months;
    }
}
