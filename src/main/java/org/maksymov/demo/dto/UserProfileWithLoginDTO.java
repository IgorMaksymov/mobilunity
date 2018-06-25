package org.maksymov.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserProfileWithLoginDTO extends UserProfileDTO {

    private String userName;

    public UserProfileWithLoginDTO(final Long id, final String firstName, final String lastName, final String userName) {
        super(id, firstName, lastName);
        this.userName = userName;
    }
}
