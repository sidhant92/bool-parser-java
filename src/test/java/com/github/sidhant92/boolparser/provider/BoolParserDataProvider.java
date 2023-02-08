package com.github.sidhant92.boolparser.provider;

import java.io.File;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;

/**
 * @author Het Shah
 * @since 08/02/2023
 */
public class BoolParserDataProvider {
    public static Map<String, Object> getData() {
        final ObjectMapper objectMapper = CommonsDataProvider.getObjectMapper();
        return Try.of(() -> objectMapper.readValue(new File("src/test/resources/data.json"), new TypeReference<Map<String, Object>>() {}))
                  .getOrElseGet(ex -> null);
    }
}
