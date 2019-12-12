package com.zykj.forum.image;

import com.zykj.forum.util.ImageUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ImageManager {
    private ImageMonitor imageMonitor;
    private NameGenerator nameGenerator;
    private String path;
    public ImageManager(){
        this.imageMonitor=new ImageMonitor();
        this.nameGenerator=new UuidNameGenerator();
        this.path=Thread.currentThread().getContextClassLoader().getResource("").getPath()+'/';
    }

    public boolean save(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        ImageUtil.ImageType bufferedImage = verityFile(originalFilename, inputStream);
        String generaterName = nameGenerator.generater('.'+bufferedImage.getName());
        this.save(generaterName,inputStream,bufferedImage);
        return true;
    }

    public InputStream openImage(String name) throws FileNotFoundException {
        String path=this.path+name;
        return new FileInputStream(path);
    }

    private void save(String name, InputStream inputStream, ImageUtil.ImageType imageType) throws IOException {
        String newPath=path + name;
        while (isHas(newPath))
            newPath=this.nameGenerator.change(newPath);
        FileOutputStream fileOutputStream = new FileOutputStream(newPath);
        ImageUtil.imageCpy(fileOutputStream,inputStream,imageType);
    }

    public boolean isHas(String name){
        File file = new File(path + name);
        return file.exists();
    }

    public ImageMonitor getImageMonitor() {
        return imageMonitor;
    }

    public void setImageMonitor(ImageMonitor imageMonitor) {
        this.imageMonitor = imageMonitor;
    }

    public NameGenerator getNameGenerator() {
        return nameGenerator;
    }

    public void setNameGenerator(NameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private ImageUtil.ImageType verityFile(String fileName, InputStream inputStream) throws IOException {
       return imageMonitor.verityImage(fileName,inputStream);
    }
}
