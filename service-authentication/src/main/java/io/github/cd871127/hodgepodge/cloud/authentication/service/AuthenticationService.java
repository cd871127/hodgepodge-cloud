package io.github.cd871127.hodgepodge.cloud.authentication.service;

import io.github.cd871127.hodgepodge.cloud.authentication.exception.AuthenticationException;
import io.github.cd871127.hodgepodge.cloud.authentication.mapper.AuthenticationMapper;
import com.github.cd871127.hodgepodge.cloud.cipher.crypto.RsaEncipher;
import io.github.cd871127.hodgepodge.cloud.lib.user.UserInfo;
import io.github.cd871127.hodgepodge.cloud.lib.util.Constant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthenticationService {

    @Resource
    private AuthenticationMapper authenticationMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public UserInfo register(UserInfo userInfo) throws Exception {
        if (authenticationMapper.isUserExist(userInfo.getUsername())) {
            throw new AuthenticationException("USER_EXIST");
        }
        //TODO verify if username exist and password valid
        authenticationMapper.register(userInfo);
        return login(userInfo.getUsername(), userInfo.getPassword());
    }

    public UserInfo login(String username, String password) {
        return null;
    }

    private String decodePassword(String password, String privateKeyStr) {
        RsaEncipher encipher = new RsaEncipher();
        return new String(encipher.decode(password.getBytes(Constant.CHAR_SET), encipher.stringToPrivateKey(privateKeyStr)), Constant.CHAR_SET);
    }
}
