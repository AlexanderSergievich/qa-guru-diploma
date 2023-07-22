package tests.api.arzamas;

import models.UserPostRequestModel;
import models.UserPostResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.AbstractApiTest;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.UserSpecs.PostResponseSpecification;
import static specs.UserSpecs.SignUpPostRequestSpecification;

public class UserCreationAPITests extends AbstractApiTest {
    @Test
    @Tag("API")
    @DisplayName("Sign up user without marketing distribution agreement")
    public String testSignUp(){
        postData.setType("email_identities");
        postAttributes.setEmail(faker.internet().emailAddress());
        postAttributes.setPassword(faker.internet().password());
        postAttributes.setName(faker.name().firstName() + " " + faker.name().lastName());
        postAttributes.setNewsletter(false);
        UserPostRequestModel constructedUserPostRequestModel = new UserPostRequestModel();
        constructedUserPostRequestModel.setData(postData);
        postData.setAttributes(postAttributes);
        step("Sign up with random user postData", () ->{
            UserPostResponseModel userPostResponseModel = given(SignUpPostRequestSpecification)
                    .body(constructedUserPostRequestModel)
                    .when()
                    .post("/api/v1/accounts/email_identities/sign_up")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(UserPostResponseModel.class);
            step("Check response", () -> {
                assertNotNull(userPostResponseModel.getSession_jwt());
                assertNotNull("authorized", userPostResponseModel.getAction());
            });
            step("Perform authorisation", () ->{
                token = userPostResponseModel.getSession_jwt();
                AuthorizationAPITests authorizationAPITests = new AuthorizationAPITests();
                authorizationAPITests.testPerformAuthorisation();
            });
        });
        return token;
    }
}
