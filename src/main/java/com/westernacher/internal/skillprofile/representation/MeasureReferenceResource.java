package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.MeasureReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MeasureReferenceResource {
    private String category;
    private String topic;
    private String newCategory;
    private String newTopic;

    public MeasureReference toMeasureReference() {
        MeasureReference measureReference = new MeasureReference();
        measureReference.setCategory(this.category);
        measureReference.setTopic(this.topic);

        if (this.newCategory != null && !this.newCategory.isEmpty()) {
            measureReference.setCategory(this.newCategory);
        }
        if (this.newTopic != null && !this.newTopic.isEmpty()) {
            measureReference.setTopic(this.newTopic);
        }
        return measureReference;
    }
}
