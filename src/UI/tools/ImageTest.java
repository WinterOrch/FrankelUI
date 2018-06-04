package UI.tools;

import UI.MainWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageTest {

    public static void main(String[] args) {

        BufferedImage bili = null;
        try {
            bili = ImageIO.read(MainWindow.class.getResource("/testPhoto/32.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Width: "+Objects.requireNonNull(bili).getWidth());
        System.out.println("Height: "+Objects.requireNonNull(bili).getHeight());
    }


    /**
     * 读取一张图片的RGB值
     */
    public void getImagePixel(String image) {

        int[] rgb = new int[3];
        File file = new File(image);
        BufferedImage bi = null;
        try {

            bi = ImageIO.read(file);
        } catch (IOException e) {

            e.printStackTrace();
        }
        int width = Objects.requireNonNull(bi).getWidth();
        int height = bi.getHeight();
        int minX = bi.getMinX();
        int minY = bi.getMinY();
        for(int y = minY; y < height; y++) {
            for(int x = minX; x < width; x++) {
                //获取包含这个像素的颜色信息的值, int型
                int pixel = bi.getRGB(x, y);
                //从pixel中获取rgb的值
                rgb[0] = (pixel & 0xff0000) >> 16; //r
                rgb[1] = (pixel & 0xff00) >> 8; //g
                rgb[2] = (pixel & 0xff); //b
                System.out.print("("+rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
            }
            System.out.println();
        }

    }
}
