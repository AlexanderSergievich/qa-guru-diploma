package models.requests;

import lombok.Data;

@Data
public class UserPostRequestModel {
    private Data data;

    @lombok.Data
    public class Data {
        private String type;
        private Attributes attributes;

        @lombok.Data
        public class Attributes {
            private String email;
            private String password;
            private String name;
            private boolean newsletter;
        }
    }
}

