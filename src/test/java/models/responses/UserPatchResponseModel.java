package models.responses;

import lombok.Data;
@Data
public class UserPatchResponseModel {
    public String status;
    public String message;
    public String error;
    public String[] errors;
}
