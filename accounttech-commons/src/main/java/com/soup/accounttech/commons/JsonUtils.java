package com.soup.accounttech.commons;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonUtils {
	
	@Autowired
    private ObjectMapper objectMapperLowerCase;

    @Autowired
    private ObjectMapper objectMapperCamelCase;

    public String toJson(Object object) {
        try {

            return this.objectMapperLowerCase.writeValueAsString(object);
        } catch (Exception e) {
            return null;
        }
    }

    public Object toObject(String json, Class<?> clazz) {
        try {

            Object object = this.objectMapperLowerCase.readValue(json, clazz);
            return object;
        } catch (Exception e) {
            return null;
        }
    }

    public Object toObjectCamelCase(String json, Class<?> clazz) {
        try {

            Object object = this.objectMapperCamelCase.readValue(json, clazz);
            return object;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T toObject(String json, TypeReference<T> valueTypeRef) {
        try {

            return this.objectMapperLowerCase.readValue(json, valueTypeRef);
        } catch (Exception e) {
            return null;
        }
    }
}
