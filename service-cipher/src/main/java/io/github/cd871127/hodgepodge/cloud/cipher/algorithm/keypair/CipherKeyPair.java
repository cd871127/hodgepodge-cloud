package io.github.cd871127.hodgepodge.cloud.cipher.algorithm.keypair;

import io.github.cd871127.hodgepodge.cloud.cipher.algorithm.CipherAlgorithm;
import lombok.Data;

@Data
public class CipherKeyPair {

    private String keyId;
    private String publicKey;
    private String privateKey;
    private CipherAlgorithm cipherAlgorithm;

    public CipherKeyPair() {
    }

    public CipherKeyPair(String publicKey, String privateKey) {
        this();
        setPublicKey(publicKey);
        setPrivateKey(privateKey);
    }

}
