package com.flower.portfolio.external.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {
    Cloudinary cloudinary;

    public CloudinaryService(@Value("${cloudinary.cloud_name}") String cloudName,
                             @Value("${cloudinary.api_key}") String apiKey,
                             @Value("${cloudinary.api_secret}") String apiSecret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        cloudinary = new Cloudinary(config);
    }

    public List<Map<String,Object>> upload(MultipartFile[] multipartFiles)  {
        List<Map<String,Object>> results = new ArrayList<>();
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                File file = this.convert(multipartFile);
                Map<String, String> params = new HashMap<>();
                Map<String,Object> result = cloudinary.uploader().upload(file, params);
                //para no guardar las imagenes en el servidor
                file.delete();
                results.add(result);
            }
        } catch (IOException e) {
            //throw new NonExistingException("Ocurrio un error: "+e);
        }
        return results;
    }


    private File convert(MultipartFile multipartFile) throws IOException {
        File file=new File(multipartFile.getOriginalFilename());
        FileOutputStream fo= new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
