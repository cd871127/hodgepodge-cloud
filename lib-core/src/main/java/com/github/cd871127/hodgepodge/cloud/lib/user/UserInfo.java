package com.github.cd871127.hodgepodge.cloud.lib.user;

import lombok.Data;

@Data
public class UserInfo {
    private String userId;
    private String password;
    private String phone;
    private String eMail;
    private String token;
}
