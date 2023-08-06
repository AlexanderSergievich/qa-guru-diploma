package tests.api;

import models.requests.UserPatchRequestModel;
import models.requests.UserPostRequestModel;

public class AbstractApiTest extends TestBaseAPI {
    public static String token = "";
    public String authCookieKey = "Session-JWT";
    public UserPostRequestModel.Data postData = new UserPostRequestModel().new Data();
    public UserPostRequestModel.Data.Attributes postAttributes = new UserPostRequestModel().new Data().new Attributes();

    public UserPatchRequestModel.Data patchData = new UserPatchRequestModel().new Data();
    public UserPatchRequestModel.Data.Attributes patchAttributes = new UserPatchRequestModel().new Data().new Attributes();
}
