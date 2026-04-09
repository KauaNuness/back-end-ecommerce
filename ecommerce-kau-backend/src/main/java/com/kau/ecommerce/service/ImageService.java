package com.kau.ecommerce.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private Cloudinary cloudinary;

    public String upload(MultipartFile file){
        try{
            Map result = cloudinary.uploader().upload(file.getBytes(), Map.of());
            return result.get("url").toString();
        } catch (Exception e){
            throw new RuntimeException("Erro upload imagem");
        }
    }

}
