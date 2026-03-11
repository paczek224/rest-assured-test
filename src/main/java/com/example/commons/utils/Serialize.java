package com.example.commons.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Serialize {

    public static String toJson(Object response) {
        return toJson(response, true);
    }

    public static String toJson(Object object, boolean writeNulls) {
        String json;
        ObjectMapper mapper = new ObjectMapper();
        if (writeNulls) {
            mapper.addMixIn(object.getClass(), MixIn.class);
            mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        } else {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public static class MixIn {

    }
}
