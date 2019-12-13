package com.zykj.forum.controller;

import com.zykj.forum.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@RestController
public class ImageController {

    @Resource
    ImageService imageService;

    @PostMapping("/putimage")
    public String putImage(@RequestParam(name = "image") MultipartFile multipartFile,
                           HttpSession session) throws IOException {

        imageService.putImage(multipartFile);
        return "ok";
    }
    @GetMapping("/image/{name}")
    public void getImage(@PathVariable(name = "name") String filename,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("image/*");
        response.setHeader("Connection","keep-alive");
        response.setIntHeader("expires",1000*60);       //设置一分钟的缓存
        response.setHeader("cache-control","public,max-age=60000");
        if(!imageService.getImage(response, filename))
            response.setStatus(404);

    }
}
