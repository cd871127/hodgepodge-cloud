package io.github.cd871127.hodgepodge.cloud.cipher;

import io.github.cd871127.hodgepodge.cloud.cipher.properties.CipherProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({CipherProperties.class})
public class CipherApplication {
    public static void main(String[] args) {
        SpringApplication.run(CipherApplication.class, args);
    }
}
