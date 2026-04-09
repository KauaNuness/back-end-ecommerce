package com.kau.ecommerce.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(Map.of(
                "cloud_name", "dwagsthsl",
                "api_key", "599781298262136",
                "api_secret", "RMAv6kosviMSC-0jbobglsL327o"
        ));
    }

}
