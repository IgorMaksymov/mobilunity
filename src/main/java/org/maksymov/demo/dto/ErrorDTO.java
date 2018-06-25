package org.maksymov.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.maksymov.demo.ExceptionCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class ErrorDTO {

    private ExceptionCode code;

    private String description;
}
