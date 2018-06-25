package org.maksymov.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class UserProfileDTO {

    private static final long serialVersionUID = 3527558017111717932L;

    private Long id;

    private String firstName;

    private String lastName;

}
