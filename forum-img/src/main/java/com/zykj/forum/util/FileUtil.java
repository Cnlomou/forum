package com.zykj.forum.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileUtil {
    public static boolean del(String filePath){
        final File file = new File(filePath);
        return file.delete();
    }
    public static InputStream open(String filePath) throws FileNotFoundException {
        return new FileInputStream(filePath);
    }
}
