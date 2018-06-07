package UI.tools.insert;

import java.awt.image.BufferedImage;

/**
 * Use matrix encoding algorithm to encode two bits information into RGB three channels.
 * @author Frankel.Y
 * Created in 21:02 2018/6/7
 */
public class MatrixEncoding {

    public BufferedImage embed( BufferedImage originImage, int[][] lowByte, int[][] highByte) {

        int imageWidth = originImage.getWidth();
        int imageHeight = originImage.getHeight();
        int red, green, blue;
        int temp1, temp2;
        int lowB, highB;

        //Copy the originImage
        BufferedImage outputImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB);
        outputImage.setData(originImage.getData());

        for(int i = outputImage.getMinX(); i < imageWidth; i++)   {
            for(int j = outputImage.getMinY(); j < imageHeight; j++)    {

                Object data = originImage.getRaster().getDataElements(i, j, null);

                red = originImage.getColorModel().getRed(data);
                green = originImage.getColorModel().getGreen(data);
                blue = originImage.getColorModel().getBlue(data);

                temp1 = (( red & 0x01 ) + ( blue & 0x01 )) & 0x01;
                temp2 = (( green & 0x01 ) + ( blue & 0x01 )) & 0x01;

                lowB = lowByte[j][i];
                highB = highByte[j][i];


                //Matrix Encode
                if(( temp1 == lowB) && ( temp2 == highByte[j][i] )) {
                    continue;
                } else if(( temp1 != lowB) && ( temp2 == highByte[j][i] )) {
                    if( lowB == 1 || lowB == 0 ) {
                        red = red + (( ~red ) & 0x01);
                    } else {
                        return null;
                    }
                } else if(( temp1 == lowB) && (temp2 != highB)) {
                    if( lowB == 1 || lowB == 0 ) {
                        green = green + (( ~green ) & 0x01);
                    } else {
                        return null;
                    }
                } else if(( temp1 != lowB) && ( temp2 != highB )) {
                    if( lowB == 1 || lowB == 0 ) {
                        blue = blue + (( ~blue ) & 0x01);
                    } else {
                        return null;
                    }
                }
                int argb = ( ( red & 0xFF ) << 16) + ( ( green & 0xFF ) << 8) +( ( blue & 0xFF ) << 16);
                outputImage.setRGB(i, j, argb);
            }
        }
        return outputImage;
    }
}
