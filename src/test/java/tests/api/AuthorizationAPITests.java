package tests.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.RequestsUtils;

public class AuthorizationAPITests extends AbstractApiTest {

    RequestsUtils requestsUtils = new RequestsUtils();

    @Test
    @Tag("API")
    @DisplayName("Sign in user using password")
    public void testSignInWithOldPassword() {
        requestsUtils.signIn(userConfig.password());
    }
}
