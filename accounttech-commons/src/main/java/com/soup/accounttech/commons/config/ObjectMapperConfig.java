package com.soup.accounttech.commons.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean(name = "objectMapperCamelCase")
    public ObjectMapper getObjectMapperCamel() {
        return this.getObjectMapper(JsonPropertiesFormat.CAMEL_CASE);
    }

    @Bean(name = "objectMapperLowerCase")
    public ObjectMapper getObjectMapperLower() {
        return this.getObjectMapper(JsonPropertiesFormat.LOWER_CASE);
    }

    private static enum JsonPropertiesFormat {
        CAMEL_CASE, LOWER_CASE;
    }

    private SimpleModule getModule() {
        // Register custom serializers
        SimpleModule module = new SimpleModule("DefaultModule", new Version(0, 0, 1, null));
        return module;
    }

    public ObjectMapper getObjectMapper(JsonPropertiesFormat jsonFormat) {
        ObjectMapper instance = new ObjectMapper();

        instance.registerModule(this.getModule());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        instance.setDateFormat(dateFormat);

        instance.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        instance.setSerializationInclusion(Inclusion.ALWAYS);

        switch (jsonFormat) {
        case CAMEL_CASE:
            break;
        case LOWER_CASE:
            instance.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            break;
        }

        return instance;
    }

}
