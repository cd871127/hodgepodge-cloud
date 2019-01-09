package io.github.cd871127.hodgepodge.cloud.cipher.service;

import io.github.cd871127.hodgepodge.cloud.cipher.exception.KeyIdExpiredException;
import io.github.cd871127.hodgepodge.cloud.cipher.mapper.CipherKeyMapper;
import io.github.cd871127.hodgepodge.cloud.lib.cipher.keypair.CipherKeyPair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommonService {
    @Resource
    private CipherKeyService cipherKeyService;

    @Resource
    private CipherKeyMapper cipherKeyMapper;

    public void persistentKey(String keyId) throws KeyIdExpiredException {
        CipherKeyPair cipherKeyPair = cipherKeyService.restoreCipherKeyPair(keyId);
        if (cipherKeyPair == null) {
            throw new KeyIdExpiredException();
        }
        cipherKeyService.insertCipherKeyPair(cipherKeyPair);
    }

    public Boolean deleteKeyPair(String keyId) {
        return cipherKeyMapper.deleteKeyPair(keyId);
    }
}
