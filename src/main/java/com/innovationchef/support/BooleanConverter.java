package com.innovationchef.support;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean val) {
        return val ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return s.equals("Y");
    }
}
