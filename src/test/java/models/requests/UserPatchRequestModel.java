package models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserPatchRequestModel {
    private UserPatchRequestModel.Data data;

    @lombok.Data
    public class Data {
        private String type;
        private UserPatchRequestModel.Data.Attributes attributes;

        @lombok.Data
        public class Attributes {
            private String email;
            private String password;
            @JsonProperty("new_password")
            private String newPassword;
            @JsonProperty("new_password_confirmation")
            private String newPasswordConfirmation;
            private boolean newsletter;
        }
    }
}
