package com.westernacher.internal.skillprofile.representation;

import com.westernacher.internal.skillprofile.domain.User;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class MeasureRepresentation {

    private String category;
    private String topic;
    private String value;

    public static List<MeasureRepresentation> toMeasureRepresentation(User user) {
        user.setFirstName("assasas");

        List<MeasureRepresentation> measureRepresentationList = new ArrayList<>();

        Class<? extends Object> c       = user.getClass();
        Field[]                 fields  = c.getDeclaredFields();
        Method[]                methods = c.getMethods();
        for (Method method:methods)
        {
            System.out.println("Public method found: " +  method.toString());
        }
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            try {
                System.out.println(name);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        MeasureRepresentation.builder()
                             .category(user.getFirstName())
                             .build();
        return null;
    }
}
