package com.github.sidhant92.boolparser.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * @author Het Shah
 * @since 08/02/2023
 */
public class CommonsDataProvider {
    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }
}
