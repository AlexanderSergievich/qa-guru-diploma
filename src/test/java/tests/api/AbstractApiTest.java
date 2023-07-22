package tests.api;

import models.UserPatchRequestModel;
import models.UserPostRequestModel;
import tests.TestBase;

public class AbstractApiTest extends TestBase {
    public static String token = "";
    public String authCookieKey = "Session-JWT";
    public String url = "https://radio.arzamas.academy";
    public UserPostRequestModel.Data postData = new UserPostRequestModel().new Data();
    public UserPostRequestModel.Data.Attributes postAttributes = new UserPostRequestModel().new Data().new Attributes();

    public UserPatchRequestModel.Data patchData = new UserPatchRequestModel().new Data();
    public UserPatchRequestModel.Data.Attributes patchAttributes = new UserPatchRequestModel().new Data().new Attributes();
}
