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
            bili = ImageIO.read(MainWindow.class.getResource("/testPhoto/24.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object data = Objects.requireNonNull(bili).getRaster().getDataElements(0, 0, null);

        int red = bili.getColorModel().getRed(data);
        int green = bili.getColorModel().getGreen(data);
        int blue = bili.getColorModel().getBlue(data);

        System.out.println(red);
        System.out.println(green);
        System.out.println(blue);

        BufferedImage outputImage = new BufferedImage(bili.getWidth(),bili.getHeight(),BufferedImage.TYPE_INT_RGB);
        outputImage.setData(bili.getData());

        red = (bili.getRGB(0,0) >> 16) & 0xFF;
        green = (bili.getRGB(0,0) >> 8) & 0xFF;
        blue = (bili.getRGB(0, 0)) & 0xFF;

        int argb = bili.getRGB(0,0)&0xFF000000 +(( red & 0xFF ) << 16) +  (( green & 0xFF) << 8) +(blue & 0xFF);
        System.out.println(argb);
        bili.setRGB(0,0,argb);
        System.out.println(bili.getRGB(0,0));
        System.out.println(outputImage.getRGB(0,0));

        System.out.println("Width: "+Objects.requireNonNull(bili).getWidth());
        System.out.println("Height: "+Objects.requireNonNull(bili).getHeight());
        /*
        int red = 222;
        int green = 123;
        int blue = 34;
        int argb = (( red & 0xFF ) << 16) + ( ( green & 0xFF ) << 8) +((blue & 0xFF));*/
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
