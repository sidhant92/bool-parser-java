package com.github.sidhant92.boolparser.datatype;

import java.util.Optional;
import org.apache.maven.artifact.versioning.ComparableVersion;
import com.github.sidhant92.boolparser.constant.DataType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public class VersionDataType extends AbstractDataType<ComparableVersion> {
    public VersionDataType() {
        super(ComparableVersion.class);
    }

    @Override
    public DataType getDataType() {
        return DataType.VERSION;
    }

    @Override
    public boolean isValid(final Object value) {
        boolean isValid = super.defaultIsValid(value);
        if (!isValid) {
            try {
                new ComparableVersion(value.toString());
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValid(final Object value, final boolean useStrictValidation) {
        if (!useStrictValidation) {
            return isValid(value);
        }
        return super.defaultIsValid(value);
    }

    @Override
    public Optional<ComparableVersion> getValue(Object value) {
        final Optional<ComparableVersion> result = defaultGetValue(value);
        if (result.isPresent()) {
            return result;
        }
        try {
            return Optional.of(new ComparableVersion(value.toString()));
        } catch (final Exception ignored) {
        }
        return Optional.empty();
    }
}
