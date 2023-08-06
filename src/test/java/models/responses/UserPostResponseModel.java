package models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserPostResponseModel {
    @JsonProperty("session_jwt")
    private String sessionJwt;
    private String action;
}
