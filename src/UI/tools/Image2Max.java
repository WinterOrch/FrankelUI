package UI.tools;

import java.awt.image.BufferedImage;

/**
 * BufferedImage与三维数组转化的工具类
 * @author Frankel.Y
 * Created in 16:05 2018/6/4
 * Modified by
 */
public class Image2Max {

    /**
     * 将BufferedImage转化为三维数组
     * created in 16:20 2018/6/4
     */
    public static double[][][] getBMPRGB(BufferedImage originImage)  {
        int green = 0;
        int red = 0;
        int blue = 0;
        int imageWidth = originImage.getWidth();
        int imageHeight = originImage.getHeight();

        double[][][] rgbMatrix = new double[imageWidth][imageHeight][3];

        for(int i = originImage.getMinX(); i < imageWidth; i++)   {
            for(int j = originImage.getMinY(); j < imageHeight; j++)    {
                Object data = originImage.getRaster().getDataElements(i, j, null);

                red = originImage.getColorModel().getRed(data);
                green = originImage.getColorModel().getGreen(data);
                blue = originImage.getColorModel().getBlue(data);

                rgbMatrix[i][j][0] = red;
                rgbMatrix[i][j][1] = green;
                rgbMatrix[i][j][2] = blue;
            }

        }

        return rgbMatrix;
    }
}
