package tests.api.arzamas;

import models.requests.UserPostRequestModel;
import models.responses.UserPostResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.AbstractApiTestEnd2End;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.UserSpecs.*;

public class AuthorizationAPITests extends AbstractApiTestEnd2End {


    @Test
    @Tag("API")
    @DisplayName("Sign in user using old password")
    public void testSignInWithOldPassword() {
        testSignIn(userConfig.getPassword());
    }

    @Test
    @Tag("Support")
    @DisplayName("Sign in user")
    public void testSignIn(String password) {

        postData.setType("email_identities");
        postAttributes.setEmail(userConfig.getEmail());

        if (password.equals(userConfig.getNewPassword())){
            postAttributes.setPassword(userConfig.getNewPassword());
        } else {
            postAttributes.setPassword(userConfig.getPassword());
        }

        UserPostRequestModel constructedRequestModel = new UserPostRequestModel();
        constructedRequestModel.setData(postData);
        postData.setAttributes(postAttributes);
        step("Perform authentication", () -> {
            UserPostResponseModel userPostResponseModel = given(SignInPostRequestSpecification)
                    .body(constructedRequestModel)
                    .when()
                    .post("/api/v1/accounts/email_identities/sign_in")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(UserPostResponseModel.class);
            step("Check response", () -> {
                assertNotNull(userPostResponseModel.getSession_jwt());
                assertNotNull("authorized", userPostResponseModel.getAction());
            });
            step("Perform authorisation", () ->{
                token = userPostResponseModel.getSession_jwt();
                testPerformAuthorisation();
            });
        });
    }
    @Test
    @Tag("Support")
    @DisplayName("Perform authorisation via jwt token")
    public void testPerformAuthorisation(){
        step("Perform authorisation", () -> {
            given(GetRequestSpecification)
                    .header(authCookieKey, token)
                    .when()
                    .get("/api/v1/account?include=subscriptions,phone_identity,facebook_identity,google_identity,vk_identity,apple_identity")
                    .then()
                    .spec(GetResponseSpecification);
        });
    }
    @Test
    @Tag("Support")
    @DisplayName("Sign in to verify password has been changed")
    public void testPasswordBeenChanged() {
        testSignIn(userConfig.getNewPassword());
    }
}
