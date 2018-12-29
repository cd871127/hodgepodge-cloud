package io.github.cd871127.hodgepodge.cloud.auth.controller;

import io.github.cd871127.hodgepodge.cloud.auth.mapper.AuthMapper;
import io.github.cd871127.hodgepodge.cloud.auth.service.AuthService;
import io.github.cd871127.hodgepodge.cloud.lib.user.UserInfo;
import io.github.cd871127.hodgepodge.cloud.lib.web.server.response.ServerResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static io.github.cd871127.hodgepodge.cloud.lib.web.server.response.GeneralHodgepodgeResponse.SUCCESSFUL;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;



    @Resource
    private AuthMapper authMapper;

    @GetMapping("test")
    public String test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("1");
        userInfo.setUserId("1");
        userInfo.setPasswordKeyId("1");
        userInfo.setUsername("1");
        authMapper.insertUserInfo(userInfo);
        authMapper.updateUserInfo(userInfo);
        return "ok";
    }
}
