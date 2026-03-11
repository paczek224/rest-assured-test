package com.example.commons.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class DataGeneratorUtils {

    public String randomAlphanumeric(int count) {
        return RandomStringUtils.secure().nextAlphanumeric(count);
    }
}
