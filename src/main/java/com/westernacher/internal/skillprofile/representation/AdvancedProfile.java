package com.westernacher.internal.skillprofile.representation;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class AdvancedProfile {
    
    private final Map<String, List<MeasureResource>> data = new HashMap<>();
    
    public void addunitOfMeasure(MeasureResource measureResource) {
        if (this.data.containsKey(measureResource.getCategory())) {
            this.data.get(measureResource.getCategory()).add(measureResource);
        } else {
            List<MeasureResource> list = new ArrayList<>();
            list.add(measureResource);
            this.data.put(measureResource.getCategory(), list);
        }
    }
    
}
