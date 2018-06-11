package UI.tools.watermarking;

import java.awt.image.BufferedImage;

/**
 * Prepare the picture for hash.
 * @author Frankel.Y
 * Created in 21:41 2018/6/7
 */
public class PictureHash {

    public static byte[] operate(BufferedImage originPicture) {
        int height = originPicture.getHeight();
        int width = originPicture.getWidth();

        byte[] content = new byte[4 + 3 * height * width];

        // Get Height and Width of The Original Picture
        content[0] = (byte)((width >>> 8) & 0xFF);
        content[1] = (byte)(width & 0xFF);
        content[2] = (byte)((height >>> 8) & 0xFF);
        content[3] = (byte)(height & 0xFF);

        // Get RGB of The Original Picture
        for(int i = originPicture.getMinX(); i < width; i++) {
            for(int j = originPicture.getMinY(); j < height; j++)    {
                int index = 4 + 3 * (height * i + j);

                Object data = originPicture.getRaster().getDataElements(i, j, null);

                content[index] = (byte)(originPicture.getColorModel().getRed(data) & 0xFE);
                content[index + 1] = (byte)(originPicture.getColorModel().getGreen(data) & 0xFE);
                content[index + 2] = (byte)(originPicture.getColorModel().getBlue(data) & 0xFE);
            }
        }

        return content;
    }


    /**
     * Generate to Hash For A Single ImageBrick
     * @param m  x For A ImageBrick In A Wall
     * @param n  y For A ImageBrick In A Wall
     * created in 20:21 2018/6/10
     */
    public static byte[] operateBrick(BufferedImage originPicture, int m, int n) {
        int height = originPicture.getHeight();
        int width = originPicture.getWidth();

        byte[] content = new byte[4 + 3 * 8 * 8];

        content[0] = (byte)((width >>> 8) & 0xFF);
        content[1] = (byte)(width & 0xFF);
        content[2] = (byte)((height >>> 8) & 0xFF);
        content[3] = (byte)(height & 0xFF);

        for (int p = 0; p < 8; p++) {
            for (int q = 0; q < 8; q++) {
                int index = 4 + 3 * (8 * p + q);

                int x = m * 8 + p;
                int y = n * 8 + q;
                if((x < width) && (y < height)) {
                    Object data = originPicture.getRaster().getDataElements(x, y, null);

                    content[index] = (byte)(originPicture.getColorModel().getRed(data) & 0xFE);
                    content[index + 1] = (byte)(originPicture.getColorModel().getGreen(data) & 0xFE);
                    content[index + 2] = (byte)(originPicture.getColorModel().getBlue(data) & 0xFE);
                }
                else{
                    content[index] = (byte)0;
                    content[index + 1] = (byte)0;
                    content[index + 2] = (byte)0;
                }
            }
        }

        return content;
    }
}