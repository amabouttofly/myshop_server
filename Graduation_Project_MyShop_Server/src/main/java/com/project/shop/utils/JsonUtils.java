package com.project.shop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    static public ObjectMapper mapper=new ObjectMapper();

    static public String getJsonString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
