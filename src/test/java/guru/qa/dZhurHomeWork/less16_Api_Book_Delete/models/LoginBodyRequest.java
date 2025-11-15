package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginBodyRequest {
    private String userName;
    private String password;
}
