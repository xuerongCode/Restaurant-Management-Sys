package com.rest.utils;

import org.junit.jupiter.api.Test;

/**
 * Created by xuero on 2017/7/18.
 */
public class FileUtile {

    private static String[] imageArray = {"bmp","dib","gif","jfif","jpe","jpeg",
                                            "jpg","png","tif","tiff","ico"};

    public static boolean isPicture(String fileName){
        if(fileName == null){
            return false;
        }

        String fileType = fileName.substring(fileName.lastIndexOf(".")+1);

        for(int i = 0;i<imageArray.length;i++){
            if(fileType.toLowerCase().equals(imageArray[i])){
                return true;
            }
        }
        return false;

    }

    @Test
    public void testIsPicture(){
        System.out.println(FileUtile.isPicture(""));
    }
}
