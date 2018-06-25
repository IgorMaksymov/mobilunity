package org.maksymov.demo.exception;

import lombok.Builder;
import lombok.Data;
import org.maksymov.demo.ExceptionCode;

@Data
@Builder(builderClassName = "Builder")
public class AlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = -4448702213927164623L;

    private final ExceptionCode code;

    private final String description;

    public AlreadyExistException(final ExceptionCode code, final String description) {
        this.code = code;
        this.description = description;
    }
}
