package io.github.cd871127.hodgepodge.cloud.auth.controller;

import io.github.cd871127.hodgepodge.cloud.auth.exception.UserException;
import io.github.cd871127.hodgepodge.cloud.auth.exception.UserExistException;
import io.github.cd871127.hodgepodge.cloud.auth.service.CipherService;
import io.github.cd871127.hodgepodge.cloud.auth.service.UserService;
import io.github.cd871127.hodgepodge.cloud.lib.user.UserInfo;
import io.github.cd871127.hodgepodge.cloud.lib.util.ResponseException;
import io.github.cd871127.hodgepodge.cloud.lib.web.server.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Base64;

import static io.github.cd871127.hodgepodge.cloud.auth.util.response.UserResponse.USER_ERROR;
import static io.github.cd871127.hodgepodge.cloud.auth.util.response.UserResponse.USER_EXIST;
import static io.github.cd871127.hodgepodge.cloud.lib.web.server.response.GeneralHodgepodgeResponse.FAILED;
import static io.github.cd871127.hodgepodge.cloud.lib.web.server.response.GeneralHodgepodgeResponse.SUCCESSFUL;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("")
    public ServerResponse<UserInfo> register(@RequestBody UserInfo userInfo) throws UserExistException, ResponseException {
        UserInfo resultUserInfo = userService.addUserInfo(userInfo);
        ServerResponse<UserInfo> serverResponse = new ServerResponse<>(SUCCESSFUL);
        serverResponse.setData(resultUserInfo);
        return serverResponse;
    }

    @PatchMapping("{userId}")
    public ServerResponse changePassword(@PathVariable String userId) {
//        ServerResponse serverResponse = cipherClient.publicKey(null, null);

//        userService.changePassword(userId, null, null);
        return null;
    }


    @ExceptionHandler(UserException.class)
    public ServerResponse userExceptionHandler(UserException userException) {
        ServerResponse serverResponse = new ServerResponse(FAILED);
        log.error(userException.getMessage());
        try {
            throw userException;
        } catch (UserExistException e) {
            serverResponse.setHodgepodgeResponse(USER_EXIST);
        } catch (UserException e) {
            serverResponse.setHodgepodgeResponse(USER_ERROR);
        }
        return serverResponse;
    }
}
