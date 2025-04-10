package com.uade.marketplace.data.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.marketplace.models.PaymentInfo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PaymentInfoConverter implements AttributeConverter<PaymentInfo, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(PaymentInfo paymentInfo) {
        try {
            return objectMapper.writeValueAsString(paymentInfo);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public PaymentInfo convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<PaymentInfo>() {});
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
