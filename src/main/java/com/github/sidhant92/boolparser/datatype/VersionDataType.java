package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import org.apache.maven.artifact.versioning.ComparableVersion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class VersionDataType extends AbstractDataType<ComparableVersion> {
    private final ObjectMapper objectMapper;

    public VersionDataType(final ObjectMapper objectMapper) {
        super(ComparableVersion.class);
        this.objectMapper = objectMapper;
    }

    @Override
    public DataType getDataType() {
        return DataType.VERSION;
    }

    @Override
    public boolean isValid(final Object value) {
        return super.defaultIsValid(value, objectMapper);
    }

    @Override
    public boolean isValid(final Object value, final boolean useStrictValidation) {
        return super.defaultIsValid(value, objectMapper, useStrictValidation);
    }

    @Override
    public Optional<ComparableVersion> getValue(Object value) {
        return defaultGetValue(value, objectMapper);
    }
}
