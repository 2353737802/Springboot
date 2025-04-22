package com.example.demo.converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Collections;
import java.util.*;
@Slf4j
@Converter(autoApply = true)
public class JsonConverter implements AttributeConverter<Map<String, Object>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "{}";
        }
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize map to JSON", e);
            return "{}";
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptyMap();
        }
        try {
            return mapper.readValue(dbData, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            log.error("Failed to deserialize JSON to map: {}", dbData, e);
            return Collections.emptyMap();
        }
    }
}