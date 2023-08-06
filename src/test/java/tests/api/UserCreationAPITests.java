package tests.api;

import models.requests.UserPostRequestModel;
import models.responses.UserPostResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.RequestsUtils;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.UserSpecs.postResponseSpecification;
import static specs.UserSpecs.signUpPostRequestSpecification;

public class UserCreationAPITests extends AbstractApiTest {

    @Test
    @Tag("API")
    @DisplayName("Sign up user without marketing distribution agreement")
    public void testSignUp(){
        postData.setType("email_identities");
        postAttributes.setEmail(faker.internet().emailAddress());
        postAttributes.setPassword(faker.internet().password());
        postAttributes.setName(faker.name().firstName() + " " + faker.name().lastName());
        postAttributes.setNewsletter(false);

        UserPostRequestModel constructedUserPostRequestModel = new UserPostRequestModel();
        constructedUserPostRequestModel.setData(postData);
        postData.setAttributes(postAttributes);

        step("Sign up with random user postData", () ->{
            UserPostResponseModel userPostResponseModel = given(signUpPostRequestSpecification)
                    .body(constructedUserPostRequestModel)
                    .when()
                    .post("/api/v1/accounts/email_identities/sign_up")
                    .then()
                    .spec(postResponseSpecification)
                    .extract().as(UserPostResponseModel.class);

            step("Check response", () -> {
                assertNotNull(userPostResponseModel.getSessionJwt());
                assertNotNull("authorized", userPostResponseModel.getAction());
            });

            step("Perform authorisation", () ->{
                token = userPostResponseModel.getSessionJwt();
                RequestsUtils requestsUtils = new RequestsUtils();
                requestsUtils.performAuthorisation();
            });
        });
    }
}
