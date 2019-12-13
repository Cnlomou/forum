package com.zykj.forum.util;

import org.apache.tomcat.util.buf.HexUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUtil {
    public static ImageType iamgeType(InputStream inputStream) throws IOException {
        byte[] bytes=new byte[4];
        inputStream.read(bytes,0,bytes.length);
        String typeHex = HexUtils.toHexString(bytes).toUpperCase();
        switch (typeHex){
            case "FFD8FFE0":
                return ImageType.JPG;
            case "89504E47":
                return ImageType.PNG;
            case "47494638":
                return ImageType.GIF;
            case "49492A00":
                return ImageType.TIF;
            case "424D":
                return ImageType.BMP;
            default:
                return null;
        }
    }
    public static void imageCpy(OutputStream outputStream, InputStream inputStream, ImageType imageType) throws IOException {
        byte[] bytes=new byte[1024];
        int res=0;
        try {
            outputStream.write(imageType.getHex());
            while((res=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,res);
            }
        }catch (IOException ignored){
            throw ignored;
        }finally {
            outputStream.close();
            inputStream.close();
        }
    }


    public static enum ImageType{
        JPG(0xFFD8FFE0,"jpg"),
        PNG(0x89504E47,"png"),
        GIF(0x47494638,"gif"),
        TIF(0x49492A00,"tif"),
        BMP(0x424D,"bmp");
        private byte[] hex;
        private String name;
         ImageType(int hex,String name){
            this.hex=new byte[4];
            this.hex[0]|=(hex>>>24);
            this.hex[1]|=(hex>>>16);
            this.hex[2]|=(hex>>>8);
            this.hex[3]|=hex;
            this.name=name;
        }
        public byte[] getHex(){
             return this.hex;
        }
        public String getName(){
             return this.name;
        }
    }
}
