package org.maksymov.demo.service;

import org.maksymov.demo.controller.payload.UserWithCredentials;
import org.maksymov.demo.dto.UserProfileWithLoginDTO;

public interface UserService {

    UserProfileWithLoginDTO create(UserWithCredentials user);

}
