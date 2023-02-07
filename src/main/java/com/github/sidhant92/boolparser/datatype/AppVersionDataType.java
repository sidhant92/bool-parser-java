package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import org.apache.maven.artifact.versioning.ComparableVersion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class AppVersionDataType extends AbstractDataType<ComparableVersion> {
    private final ObjectMapper objectMapper;

    public AppVersionDataType(final ObjectMapper objectMapper) {
        super(ComparableVersion.class);
        this.objectMapper = objectMapper;
    }

    @Override
    public DataType getDataType() {
        return DataType.APP_VERSION;
    }

    @Override
    public boolean isValid(final Object value) {
        return super.defaultIsValid(value, objectMapper);
    }

    @Override
    public Optional<ComparableVersion> getValue(Object value) {
        return defaultGetValue(value, objectMapper);
    }
}
