package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginBodyResponse {
    String userId;
    String token;
    String userName;
    String expires;
}
