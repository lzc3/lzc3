package com.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageIoUtils {

    public static BufferedImage readFile(String path) {
        try {
            BufferedImage read = ImageIO.read(new File(path));
            return read;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存图片
     *
     * @param image BufferedImage
     * @param filePath 文件路径
     */
    public static void saveImageToFile(BufferedImage image, String filePath) {
        try {
            // 获取文件扩展名
            String formatName = filePath.substring(filePath.lastIndexOf(".") + 1);

            // 创建文件对象
            File output = new File(filePath);

            // 使用 ImageIO 保存图像
            ImageIO.write(image, formatName, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
