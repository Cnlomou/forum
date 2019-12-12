package com.zykj.forum.image;

import com.zykj.forum.util.ImageUtil;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageMonitor {
    private static final String[] SUFFIX={".jpg",".png",".bmp"};

    public ImageUtil.ImageType verityImage(String fileName, InputStream inputStream) throws IOException {
        if(!this.verityName(fileName))
            throw new IllegalStateException("filename isn't image");
        ImageUtil.ImageType imageType = ImageUtil.iamgeType(inputStream);
        if(imageType==null)
            throw new IllegalStateException("image is unknown");
        return imageType;
    }

    private boolean verityName(String fileName){
        for (String suffix : SUFFIX) {
            if(fileName.endsWith(suffix))
                return true;
        }
        return false;
    }

}
