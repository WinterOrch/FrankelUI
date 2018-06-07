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

                content[index] = (byte)(originPicture.getColorModel().getRed(data) & 0xFF);
                content[index + 1] = (byte)(originPicture.getColorModel().getGreen(data) & 0xFF);
                content[index + 2] = (byte)(originPicture.getColorModel().getBlue(data) & 0xFF);
            }
        }

        return content;
    }

}
