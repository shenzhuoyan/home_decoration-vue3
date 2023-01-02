package com.home_decoration.back_end.baiguo.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Data
class PicBody {
    private int errno; //0是成功，1是失败
    private String message;
    private HashMap<String,String> data;

    public static PicBody success(String url){
        PicBody picBody = new PicBody();
        picBody.errno=0;
        picBody.data=new HashMap<>();
        picBody.data.put("url",url);
        return picBody;
    }

    public static PicBody fail(){
        PicBody picBody = new PicBody();
        picBody.errno=1;
        picBody.message="图片存储失败";
        return picBody;
    }
}
@RestController
public class PushPicture {
    @Value("${file.uploadFolder}")
    String uploadFolder;

    /**
     *
     * @param picture 上传时需指定名字为picture
     * @return {
     *     errno, 0为成功，1为失败
     *     message, 失败时返回报错信息
     *     data:{
     *         url: 取图片的路径
     *     }
     * }
     */
    @PostMapping("/admin/picture/push")
    public PicBody upLoadPicture(MultipartFile picture){
        //System.out.println("收到图片");
        //先保存封面图
        String fileName = picture.getOriginalFilename();
        String dateTime = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-s").format(new Date());
        String pic = uploadFolder + dateTime + fileName;//实际存储的图片路径
        String aliaPic = "/picture/get/" + dateTime + fileName;//可以访问的虚拟文件路径
        File dest = new File(pic);
        try {
            picture.transferTo(dest);//存储图片
            return PicBody.success(aliaPic);

        } catch (IOException e) {
            System.out.println("文件保存失败"+pic);
            return PicBody.fail();
        }
    }
}
