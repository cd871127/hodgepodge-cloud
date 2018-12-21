package io.github.cd871127.hodgepodge.cloud.authentication.controller;

import com.github.cd871127.hodgepodge.cloud.lib.web.server.response.CommonResponseInfo;
import io.github.cd871127.hodgepodge.cloud.authentication.service.AuthenticationService;
import io.github.cd871127.hodgepodge.cloud.lib.user.UserInfo;
import io.github.cd871127.hodgepodge.cloud.lib.web.AbstractController;
import io.github.cd871127.hodgepodge.cloud.lib.web.server.response.ServerResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RequestMapping("/authentication")
@RestController
public class AuthenticationController extends AbstractController {

    @Resource
    private AuthenticationService authenticationService;

    /**
     * add new user
     *
     * @param userInfo include username & password
     * @return userInfo include valid token and info from parameter
     */
    @RequestMapping(value = "register", method = POST)
    public ServerResponse<UserInfo> register(@RequestBody UserInfo userInfo) throws Exception {
        ServerResponse<UserInfo> serverResponse = new ServerResponse<>(CommonResponseInfo.SUCCESSFUL);
        authenticationService.register(userInfo);
        return serverResponse;
    }

    /**
     * user login controller
     *
     * @param username
     * @param password encoded password
     * @return
     */
    @RequestMapping(value = "info/{username}", method = GET)
    public ServerResponse<UserInfo> login(@PathVariable String username, @RequestParam String password) {
        ServerResponse<UserInfo> serverResponse = new ServerResponse<>(CommonResponseInfo.SUCCESSFUL);
        serverResponse.setData(authenticationService.login(username, password));
        return serverResponse;
    }

    @RequestMapping(value = "info/{username}", method = PATCH)
    public ServerResponse<UserInfo> modify(@PathVariable String username, @RequestParam String password) {
        ServerResponse<UserInfo> serverResponse = new ServerResponse<>(CommonResponseInfo.SUCCESSFUL);
        serverResponse.setData(authenticationService.login(username, password));
        return serverResponse;
    }


}
