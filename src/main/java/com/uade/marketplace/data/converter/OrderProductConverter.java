package com.uade.marketplace.data.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.marketplace.models.OrderProduct;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class OrderProductConverter implements AttributeConverter<List<OrderProduct>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(List<OrderProduct> orderProducts) {
        try {
            return objectMapper.writeValueAsString(orderProducts);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<OrderProduct> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
