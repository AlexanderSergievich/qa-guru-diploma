package models;

import lombok.Data;
@Data
public class LoginResponseModel {
    public String session_jwt;
    public String action;

}
