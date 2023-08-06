package utils;

import models.requests.UserPatchRequestModel;
import models.requests.UserPostRequestModel;
import models.responses.UserPatchResponseModel;
import models.responses.UserPostResponseModel;

import tests.api.AbstractApiTest;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static specs.UserSpecs.*;
import static specs.UserSpecs.getResponseSpecification;

public class RequestsUtils extends AbstractApiTest {

    public void signIn(String password) {

        postData.setType("email_identities");
        postAttributes.setEmail(userConfig.email());

        if (password.equals(userConfig.newPassword())) {
            postAttributes.setPassword(userConfig.newPassword());
        } else {
            postAttributes.setPassword(userConfig.password());
        }

        UserPostRequestModel constructedRequestModel = new UserPostRequestModel();
        constructedRequestModel.setData(postData);
        postData.setAttributes(postAttributes);

        step("Perform authentication", () -> {
            UserPostResponseModel userPostResponseModel = given(signInPostRequestSpecification)
                    .body(constructedRequestModel)
                    .when()
                    .post("/api/v1/accounts/email_identities/sign_in")
                    .then()
                    .spec(postResponseSpecification)
                    .extract().as(UserPostResponseModel.class);

            step("Check response", () -> {
                assertNotNull(userPostResponseModel.getSessionJwt());
                assertNotNull("authorized", userPostResponseModel.getAction());
            });

            step("Perform authorisation", () -> {
                token = userPostResponseModel.getSessionJwt();
                performAuthorisation();
            });
        });
    }

    public void performAuthorisation() {
        step("Perform authorisation", () -> {
            given(getRequestSpecification)
                    .header(authCookieKey, token)
                    .when()
                    .get("/api/v1/account?include=subscriptions,phone_identity,facebook_identity,google_identity,vk_identity,apple_identity")
                    .then()
                    .spec(getResponseSpecification);
        });
    }

    public void passwordBeenChanged() {
        signIn(userConfig.newPassword());
    }

    public void updatePasswordToOld() {
        step("Perform authorisation and get token", () -> {
            RequestsUtils requestsUtils = new RequestsUtils();
            requestsUtils.signIn(userConfig.newPassword());
            step("Perform update", () -> {
                patchData.setType("email_identities");
                patchAttributes.setPassword(userConfig.newPassword());
                patchAttributes.setNewPassword(userConfig.password());
                patchAttributes.setNewPasswordConfirmation(userConfig.password());
                patchAttributes.setEmail(userConfig.email());

                UserPatchRequestModel constructedRequestModel = new UserPatchRequestModel();
                constructedRequestModel.setData(patchData);
                patchData.setAttributes(patchAttributes);

                UserPatchResponseModel userPatchResponseModel = given(patchRequestSpecification)
                        .body(constructedRequestModel)
                        .header("Session-jwt", token)
                        .when()
                        .patch("/api/v1/accounts/email_identities/update_password")
                        .then()
                        .spec(postResponseSpecification)
                        .extract().as(UserPatchResponseModel.class);

                step("Check response", () -> {
                    assertEquals("success", userPatchResponseModel.getStatus());
                    assertEquals("Изменения сохранены", userPatchResponseModel.getMessage());
                    assertNull(userPatchResponseModel.getError());
                    assertArrayEquals(new String[]{}, userPatchResponseModel.getErrors());
                });
            });
        });
    }
}
