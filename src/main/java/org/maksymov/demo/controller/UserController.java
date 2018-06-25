package org.maksymov.demo.controller;

import lombok.RequiredArgsConstructor;
import org.maksymov.demo.controller.payload.UserWithCredentials;
import org.maksymov.demo.dto.UserProfileWithLoginDTO;
import org.maksymov.demo.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequiredArgsConstructor
public class UserController  {

    @NotNull
    private final UserService userService;

    @PostMapping("/api/user")
    public UserProfileWithLoginDTO create(@Valid @RequestBody final UserWithCredentials userWithCredentials) {
        return userService.create(userWithCredentials);
    }

}
