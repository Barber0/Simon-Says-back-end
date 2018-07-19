package com.fang.alpha.utils;

import com.fang.alpha.bean.Response;
import com.fang.alpha.exception.FileException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static final int VideoType = 0;
    public static final int ImgType = 1;

    public static final String Video = "video";
    public static final String Img = "img";

    public static String upload(MultipartFile file,String storePath,int fileType){
        if (file.isEmpty()) return "";
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        System.out.println(fileName);
        if (!checkFileType(suffix,fileType)) throw new FileException(FileException.WrongFileType,"不允许上传该类型文件");
        fileName = DigestUtils.md5DigestAsHex(fileName.getBytes())+"."+suffix;
        System.out.println(fileName);
        int size = (int) file.getSize();
        File dest = new File(storePath+fileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return "/"+fileName;
        }catch (IllegalStateException e){
            e.printStackTrace();
            return "";
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    private static boolean checkFileType(String suffix,int type){
        if (type==ImgType){
            switch (suffix.toLowerCase()){
                case "jpg":
                    return true;
                case "jpeg":
                    return true;
                case "png":
                    return true;
                case "gif":
                    return true;

                default:
                    return false;
            }
        }else if (type==VideoType){
            switch (suffix.toLowerCase()){
                case "mp4":
                    return true;
                case "avi":
                    return true;
                case "mkv":
                    return true;

                    default:
                        return false;
            }
        }else return false;
    }
}
