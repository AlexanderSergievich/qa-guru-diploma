package tests.api.arzamas;

import models.requests.UserPatchRequestModel;
import models.responses.UserPatchResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.AbstractApiTestEnd2End;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.UserSpecs.PostResponseSpecification;
import static specs.UserSpecs.patchRequestSpecification;

public class UserProfileAPITests extends AbstractApiTestEnd2End {
    @Test
    @Tag("API")
    @DisplayName("Update newsletter distribution")
    public void testUpdateNewsDistribution(){
        step("Perform authorisation and get token", () -> {
            AuthorizationAPITests authorizationAPITests = new AuthorizationAPITests();
            authorizationAPITests.testSignIn(userConfig.getPassword());
            step("Perform update", () -> {
                patchData.setType("email_identities");
                patchAttributes.setEmail(userConfig.getEmail());
                patchAttributes.setNewsletter(true);

                UserPatchRequestModel constructedRequestModel = new UserPatchRequestModel();
                constructedRequestModel.setData(patchData);
                patchData.setAttributes(patchAttributes);

                UserPatchResponseModel userPatchResponseModel = given(patchRequestSpecification)
                        .body(constructedRequestModel)
                        .header("Session-jwt", token)
                        .when()
                        .patch("/api/v1/accounts/email_identities/update")
                        .then()
                        .spec(PostResponseSpecification)
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
    @Test
    @Tag("Support")
    @DisplayName("Update password to the old one")
    public void testUpdatePasswordToOld(){
        step("Perform authorisation and get token", () -> {
            AuthorizationAPITests authorizationAPITests = new AuthorizationAPITests();
            authorizationAPITests.testSignIn(userConfig.getNewPassword());
            step("Perform update", () -> {
                patchData.setType("email_identities");
                patchAttributes.setPassword(userConfig.getNewPassword());
                patchAttributes.setNew_password(userConfig.getPassword());
                patchAttributes.setNew_password_confirmation(userConfig.getPassword());
                patchAttributes.setEmail(userConfig.getEmail());

                UserPatchRequestModel constructedRequestModel = new UserPatchRequestModel();
                constructedRequestModel.setData(patchData);
                patchData.setAttributes(patchAttributes);

                UserPatchResponseModel userPatchResponseModel = given(patchRequestSpecification)
                        .body(constructedRequestModel)
                        .header("Session-jwt", token)
                        .when()
                        .patch("/api/v1/accounts/email_identities/update_password")
                        .then()
                        .spec(PostResponseSpecification)
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
