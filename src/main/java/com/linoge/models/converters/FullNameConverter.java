package com.linoge.models.converters;

import com.linoge.models.shared.FullName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

/**
 * Created by Timo on 11.01.2017.
 * Convert FullName object in entity to database view with Separator and back.
 */
@Converter
public final class FullNameConverter implements AttributeConverter<FullName, String> {

    private static final String SPACE = " ";
    private static final String SEPARATOR = "|";
    private static final int SURNAME_INDEX = 0;
    private static final int FIRSTNAME_INDEX = 1;
    private static final int PATRONYMIC_INDEX = 2;

    public static String convertFullNameToString(FullName fullName) {
        return fullName.getSurName() + SPACE + fullName.getFirstName() + SPACE + fullName.getPatronymic();
    }

    public static FullName convertStringToFullName(String fullNameWithSpaces){
        return toFullName(Arrays.stream(fullNameWithSpaces.split(SPACE)).collect(toList()));
    }

    @Override
    public String convertToDatabaseColumn(FullName fullName) {
        return fullName.getSurName() + SEPARATOR + fullName.getFirstName() + SEPARATOR + fullName.getPatronymic();
    }

    @Override
    public FullName convertToEntityAttribute(String fullNameWithSeparator) {
        return toFullName(Arrays.stream(fullNameWithSeparator.split(Pattern.quote(SEPARATOR)))
                .collect(toList()));


    }

    private static FullName toFullName(List<String> fullNameData) {
        return FullName.builder()
                .surName(fullNameData.get(SURNAME_INDEX))
                .firstName(fullNameData.get(FIRSTNAME_INDEX))
                .patronymic(fullNameData.get(PATRONYMIC_INDEX))
                .build();
    }
}
