package com.ocr;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;

import java.io.IOException;

import static org.bytedeco.leptonica.global.leptonica.pixDestroy;
import static org.bytedeco.leptonica.global.leptonica.pixRead;

/**
 * OCR简单测试
 */
public class BasicExample {

    public static void main(String[] args) throws IOException {
        BytePointer outText;

        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init("C:\\lzc\\app\\proTools\\ai\\temp\\tessdata", "chi_sim") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }

        // Open input image with leptonica library
        //  "image.png"

//        MultipartFile multipartFile = null;
//        byte[] imageBytes = multipartFile.getBytes();
//        PIX image2 = pixReadMem(imageBytes, imageBytes.length);

        PIX image = pixRead(args.length > 0 ? args[0] : "C:\\lzc\\img\\ocr\\image.png");
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        System.out.println("OCR output:\n" + outText.getString());

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
    }
}
