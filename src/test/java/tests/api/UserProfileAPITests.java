package tests.api;

import models.requests.UserPatchRequestModel;
import models.responses.UserPatchResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.RequestsUtils;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.UserSpecs.postResponseSpecification;
import static specs.UserSpecs.patchRequestSpecification;

public class UserProfileAPITests extends AbstractApiTest {
    @Test
    @Tag("API")
    @DisplayName("Update newsletter distribution")
    public void testUpdateNewsDistribution() {
        step("Perform authorisation and get token", () -> {

            RequestsUtils requestsUtils = new RequestsUtils();
            requestsUtils.signIn(userConfig.password());

            step("Perform update", () -> {
                patchData.setType("email_identities");
                patchAttributes.setEmail(userConfig.email());
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
