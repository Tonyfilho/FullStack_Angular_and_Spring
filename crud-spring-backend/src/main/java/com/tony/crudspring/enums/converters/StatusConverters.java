package com.tony.crudspring.enums.converters;

import java.util.stream.Stream;

import com.tony.crudspring.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverters implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }

        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        /**
         * Iqualmente o RXJs, temos o Stream.of() q cria streams e com isto podemos usar
         * os metodos de Streams
         */
        /**
         * Iqualmente ao RXJS fazemos um filtro, pois receberemos uma list e pegaremos o
         * 1º valor
         */
        if (value == null) {
            return null;
        }
        return Stream.of(Status.values()).filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        // .orElseThrow(e -> new IllegalAccessException(e));

    }

}
