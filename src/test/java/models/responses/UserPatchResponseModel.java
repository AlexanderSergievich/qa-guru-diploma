package models.responses;

import lombok.Data;

@Data
public class UserPatchResponseModel {
    private String status;
    private String message;
    private String error;
    private String[] errors;
}
