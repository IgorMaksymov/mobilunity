package org.maksymov.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.maksymov.demo.controller.payload.UserWithCredentials;
import org.maksymov.demo.dto.ErrorDTO;
import org.maksymov.demo.dto.UserProfileWithLoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertSame;
import static org.maksymov.demo.ExceptionCode.USER_ALREADY_EXISTS;

public class UserControllerIT extends AbstractIT {

    private static final String USER_API =  URL + "/api/user";
    private static final String DEFAULT_LOGIN = "TestLogin";
    private static final String DEFAULT_PASSWORD = "TestPassword";
    private static final String DEFAULT_FIRST_NAME = "Test First Name";
    private static final String DEFAULT_LAST_NAME = "Test Last Name";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @ExpectedDatabase(value = "classpath:dataset/user/createNewUser_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void shouldCreateNewUser() {
        final UserWithCredentials userProfileWithCredentials = prepareDefaultUser().build();
        final UserProfileWithLoginDTO expectedValue = new UserProfileWithLoginDTO(null, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_LOGIN);
        ResponseEntity<UserProfileWithLoginDTO> response = REST_TEMPLATE.postForEntity(USER_API, userProfileWithCredentials, UserProfileWithLoginDTO.class);
        response.getBody().setId(null);

        assertThat(response.getBody(), is(expectedValue));
        assertSame(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DatabaseSetup(value = "classpath:dataset/user/loginAlreadyExists_initial.xml")
    public void shouldReturn409IfLoginAlreadyExists() throws Exception {
        final UserWithCredentials userProfileWithCredentials = prepareDefaultUser().build();

        final String responseJson =
         given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userProfileWithCredentials)
         .when()
                .post(USER_API)
         .then()
                .statusCode(HttpStatus.CONFLICT.value())
         .extract()
                .response().asString();

        final ErrorDTO expectedValue = ErrorDTO.builder().code(USER_ALREADY_EXISTS).description("A user with the given username already exists").build();
        final ErrorDTO actualValue = objectMapper.readValue(responseJson, ErrorDTO.class);

        assertThat(actualValue, is(expectedValue));
    }

    private UserWithCredentials.Builder prepareDefaultUser() {
        return UserWithCredentials.builder().userName(DEFAULT_LOGIN).password(DEFAULT_PASSWORD).firstName(DEFAULT_FIRST_NAME).lastName(DEFAULT_LAST_NAME);
    }
}
