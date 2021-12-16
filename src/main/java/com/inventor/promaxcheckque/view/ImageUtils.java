package com.inventor.promaxcheckque.view;

import java.io.ByteArrayOutputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtils{

    public ImageView createIcon(String iconURL, int width, int height) {
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(iconURL, width,height, false, false));
        return imageView;
    }

    public static byte[] Img2ByteArray(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);

        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", bos);
        } finally {
            try {
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bos.toByteArray();
    }

    public static Image byteArray2Image(byte[] imgData) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imgData);
        return new Image(bais);
    }

}
