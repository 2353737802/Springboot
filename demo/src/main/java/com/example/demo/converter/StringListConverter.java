package com.example.demo.converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Converter(autoApply = true) // 自动应用到所有匹配字段
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "[]"; // 返回空数组而不是null
        }
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize string list to JSON", e);
            return "[]"; // 失败时返回安全值
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return mapper.readValue(dbData, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            log.error("Failed to deserialize JSON to string list: {}", dbData, e);
            return Collections.emptyList();
        }
    }
}