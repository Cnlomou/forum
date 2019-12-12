package com.zykj.forum.service;

import com.zykj.forum.image.ImageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class ImageService {

    @Autowired
    ImageManager imageManager;
    @Autowired
    RestTemplate restTemplate;

    public void putImage(MultipartFile multipartFile) throws IOException {
        imageManager.save(multipartFile);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://", String.class);
    }

    public boolean getImage(HttpServletResponse response,String name) throws IOException {
            if(!imageManager.isHas(name))
                return false;
        try(
                OutputStream outputStream = response.getOutputStream();
        ) {
            InputStream inputStream = imageManager.openImage(name);
            byte[] bytes=new byte[1024];
            int res=0;
            while((res=inputStream.read(bytes))!=-1)
                outputStream.write(bytes,0,res);
            return true;
        } catch (IOException e) {
            throw e;
        }
    }
}
