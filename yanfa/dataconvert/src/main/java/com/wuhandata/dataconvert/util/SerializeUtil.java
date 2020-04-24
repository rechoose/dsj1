package com.wuhandata.dataconvert.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SerializeUtil {

    private static ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T toObject(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T toObject(String jsonStr, TypeReference<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
