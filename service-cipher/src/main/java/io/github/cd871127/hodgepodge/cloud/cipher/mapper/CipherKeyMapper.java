package io.github.cd871127.hodgepodge.cloud.cipher.mapper;

import io.github.cd871127.hodgepodge.cloud.cipher.algorithm.CipherAlgorithm;
import io.github.cd871127.hodgepodge.cloud.cipher.algorithm.keypair.CipherKeyPair;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CipherKeyMapper {

    @Select("select key_id from SECRET_KEY_INFO where CIPHER_ALGORITHM=#{cipherAlgorithm}")
    List<String> selectAllKeyId(CipherAlgorithm cipherAlgorithm);

    @Select("select key_id,public_key,private_key,cipher_algorithm from SECRET_KEY_INFO where key_id=#{keyId}")
    CipherKeyPair selectKeyPairByKeyId(String keyId);


    @Insert("insert into SECRET_KEY_INFO(KEY_ID, PUBLIC_KEY, PRIVATE_KEY,CIPHER_ALGORITHM) value(#{keyId},#{publicKey},#{privateKey},#{cipherAlgorithm})")
    int insertCipherKeyPair(CipherKeyPair cipherKeyPair);
}