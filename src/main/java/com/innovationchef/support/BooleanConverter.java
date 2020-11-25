package com.innovationchef.support;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, String> {

    private static String YES = "Y";
    private static String NO = "N";

    @Override
    public String convertToDatabaseColumn(Boolean val) {
        return val ? YES : NO;
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return s.equals(YES);
    }
}
