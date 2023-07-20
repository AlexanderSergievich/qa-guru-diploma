package models;

import lombok.Data;
@Data
public class UserPostRequestModel {
        private Data data;
        @lombok.Data
        public class Data{
                public String type;
                private Attributes attributes;
                @lombok.Data
                public class Attributes{
                        public String email;
                        public String password;
                        public String name;
                        public boolean newsletter;
                }
        }
}

