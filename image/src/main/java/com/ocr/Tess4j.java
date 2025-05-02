package com.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;

/**
 * 官网样例
 */
public class Tess4j {
    public static void main(String[] args) throws TesseractException, IOException {
        // todo test file
        // 将图片保存为临时文件
        File tempFile = File.createTempFile("temp-", "test");
//        file.transferTo(tempFile);

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata"); // 训练数据路径
        tesseract.setLanguage("eng+chi_sim"); // 中英文识别

        String result = tesseract.doOCR(tempFile);
        tempFile.delete(); // 清理临时文件
        System.out.println(result);
    }
}
