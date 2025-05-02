package com.imageTrans;

import com.github.hui.quick.plugin.image.wrapper.pixel.ImgPixelWrapper;
import com.github.hui.quick.plugin.image.wrapper.pixel.model.PixelStyleEnum;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.utils.ImageIoUtils.readFile;
import static com.utils.ImageIoUtils.saveImageToFile;

/**
 * 参照https://github.com/liuyueyi/quick-media开源项目进行图片处理
 */
public class ImageToStr {


    public static void main(String[] args) {
        // 获取当前目录代码
        String currentDir = System.getProperty("user.dir");
        System.out.println("当前工作目录: " + currentDir);

        String curResourcePrePath = "image/src/main/resources/";
        String filePath = curResourcePrePath + "豹女.jpg";

        ImgPixelWrapper build = ImgPixelWrapper.build()
                .setSourceImg(readFile(filePath))
                .setBlockSize(2)
//                .setBgColor(new Color(47, 47, 47))
//                .setFontColor(new Color(0,0,0))
                .setPixelType(CusPixelStyleEnum.CUS_CHAR_BLACK)
                .build();

        BufferedImage bufferedImg = build.asBufferedImg();
        saveImageToFile(bufferedImg, curResourcePrePath + "result.png");

        System.out.println();

    }

}
