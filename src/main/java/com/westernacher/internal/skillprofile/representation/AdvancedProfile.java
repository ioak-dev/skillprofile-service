package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.UnitofMeasure;
import com.westernacher.internal.skillprofile.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Builder
@Data
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

    public static AdvancedProfile toAdvancedProfile(User user) {
        return AdvancedProfile.builder().build();
    }







}
