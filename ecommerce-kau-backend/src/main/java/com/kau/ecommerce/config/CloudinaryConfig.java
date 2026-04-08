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
                "cloud_name", "SEU_CLOUD",
                "api_key", "SUA KEY",
                "api_secret", "SEU_SECRET"
        ));
    }

}
