package org.maksymov.demo.controller.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder(builderClassName = "Builder")
public class UserWithCredentials {

    @NotBlank
    @Size(min = 1, max = 255)
    private String userName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String password;

    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;

    @JsonCreator
    public UserWithCredentials(@JsonProperty(value = "userName")  final String userName,
                               @JsonProperty(value = "password")  final String password,
                               @JsonProperty(value = "firstName") final String firstName,
                               @JsonProperty(value = "lastName")  final String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
