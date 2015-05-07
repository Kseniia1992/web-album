package ua.ksolodovnik.webalbum.util;

import java.io.File;
import java.io.FileInputStream;

/**
 * This class contains method for saving an image.
 * It is used for testing.
 */
public class SaveImage {

    public static byte[] saveImg(){
        File file = new File("/home/ksolodovnik/Pictures/cats.jpg");
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bFile;
    }
}
