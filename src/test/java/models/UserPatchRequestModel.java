package models;

import lombok.Data;
@Data
public class UserPatchRequestModel {
    private UserPatchRequestModel.Data data;
    @lombok.Data
    public class Data{
        public String type;
        private UserPatchRequestModel.Data.Attributes attributes;
        @lombok.Data
        public class Attributes{
            public String email;
            public String password;
            public String new_password;
            public String new_password_confirmation;
            public boolean newsletter;
        }
    }
}
