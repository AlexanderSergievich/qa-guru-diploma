package models.responses;

import lombok.Data;
@Data
public class UserPostResponseModel {
    public String session_jwt;
    public String action;

}
