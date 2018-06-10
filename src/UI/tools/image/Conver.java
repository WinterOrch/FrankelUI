package UI.tools.image;

import UI.MainWindow;
import UI.tools.ToolConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;


public class Conver {

    public static final int BINARY_WHITE = -1;
    public static final int BINARY_BLACK = -16777216;

    public static BufferedImage compress( BufferedImage originImage ) {

        int size = ToolConstants.WATERMARK_SIZE;
        int width = originImage.getWidth();
        int height = originImage.getHeight();

        // Calculate Standard Dimension
        if( width > height ) {
            if( width > size ) {
                height = height * size / width;
                width = size;
            }
        } else {
            if( height > size ) {
                width = width * size / height;
                height = size;
            }
        }

        // Compressing
        Image big = originImage.getScaledInstance(width, height,Image.SCALE_DEFAULT);
        BufferedImage inputbig = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        inputbig.getGraphics().drawImage(big, 0, 0, width, height, null);

        return inputbig;
    }

    /**
     * 将图像转为二值图像
     * created in 13:17 2018/6/6
     */

    /**
     * 可能存在i,j的问题
     * @param image
     * @return
     */
    public static BufferedImage binaryImage( BufferedImage image ) {

        int width = Objects.requireNonNull(image).getWidth();
        int height = image.getHeight();

        BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                int rgb = image.getRGB(i, j);
                binaryImage.setRGB(i, j, rgb);
            }
        }

        return binaryImage;
    }

    public static BufferedImage matrix2BufferImage(int[][]  matrix, BufferedImage oringinalPicture){
        int width;
        int height;
        width = oringinalPicture.getWidth();
        height = oringinalPicture.getHeight();
        BufferedImage  outputimage= new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < height ; i++){
            for (int j = 0; j < width;j++){
                if (matrix[i][j] == 0)
                    outputimage.setRGB(j,i,BINARY_WHITE);
                else
                    outputimage.setRGB(j,i,BINARY_BLACK);
            }
        }
        return outputimage;
    }


    public static int[][] binary2Matrix(BufferedImage binaryImage) {
        int[][] matrix = new int[binaryImage.getHeight()][binaryImage.getWidth()];

        int imageWidth = binaryImage.getWidth();
        int imageHeight = binaryImage.getHeight();

        for(int i = binaryImage.getMinX(); i < imageWidth; i++) {
            for(int j = binaryImage.getMinY(); j < imageHeight; j++) {
                matrix[j][i] = (~binaryImage.getRGB(i,j)) & 0x01;//TODO 修改
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(MainWindow.class.getResource("/testPhoto/55e736d12f2eb9384eade4fed6628535e4dd6ffe.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage binaryImage = binaryImage(compress(Objects.requireNonNull(image)));
        /*for(int i=0;i<binaryImage.getHeight();i++) {
            for(int j=0;j<binaryImage.getWidth();j++) {
                //System.out.print(t[i][j]+" ");
                binaryImage.setRGB(j,i,BINARY_WHITE);
            }
            //System.out.println("\n");
        }*/

        File newFile = new File(UI.tools.ToolConstants.CURRENT_DIR +
                File.separator + "src" + File.separator + "2722425974762424027.bmp");
        try {
            ImageIO.write((binaryImage), "bmp", newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
        //boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
           /* if (hasAlpha) {
             transparency = Transparency.BITMASK;
             }*/

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
            /*if (hasAlpha) {
             type = BufferedImage.TYPE_INT_ARGB;
             }*/
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

    private static int[][] hist(BufferedImage image){
        int red;
        int green;
        int blue;

        int[][] hist = new int[3][256];

        for(int i =0; i < 3; i++) {
            for(int j = 0; j < 256; j++) {
                hist[i][j] = 0;
            }
        }

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        for(int i = image.getMinX(); i < imageWidth; i++) {
            for(int j = image.getMinY(); j < imageHeight; j++) {
                Object data = image.getRaster().getDataElements(i, j, null);

                red = image.getColorModel().getRed(data);
                green = image.getColorModel().getGreen(data);
                blue = image.getColorModel().getBlue(data);

                hist[0][red]++;
                hist[1][green]++;
                hist[2][blue]++;
            }
        }

        return hist;
    }

    public static BufferedImage getHist(BufferedImage image){
        int[][] intensity = hist(image);
        int size = 300;
        BufferedImage pic = new BufferedImage(size,size, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = pic.createGraphics();
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0, 0, size, size);
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(5, 250, 265, 250);
        g2d.drawLine(5, 250, 5, 5);

        int max = 0;
        g2d.setPaint(Color.RED);
        for(int i = 0; i < 256; i++) {
            if(intensity[0][i] > max)
                max = intensity[0][i];
        }
        float rate = 200.0f / ((float)max);
        int offset = 2;
        for(int i=0; i<intensity[0].length; i++) {
            int frequency = (int)(intensity[0][i] * rate);
            g2d.drawLine(5 + offset + i, 250, 5 + offset + i, 250 - frequency);
        }


        g2d.setPaint(Color.GREEN);
        for(int i = 0; i < 256; i++) {
            if(intensity[1][i] > max)
                max = intensity[1][i];
        }
        rate = 200.0f / ((float)max);
        offset = 2;
        for(int i=0; i<intensity[1].length; i++) {
            int frequency = (int)(intensity[1][i] * rate);
            g2d.drawLine(5 + offset + i, 250, 5 + offset + i, 250 - frequency);
        }

        g2d.setPaint(Color.BLUE);
        for(int i = 0; i < 256; i++) {
            if(intensity[2][i] > max)
                max = intensity[2][i];
        }
        rate = 200.0f / ((float)max);
        offset = 2;
        for(int i=0; i<intensity[2].length; i++) {
            int frequency = (int)(intensity[2][i] * rate);
            g2d.drawLine(5 + offset + i, 250, 5 + offset + i, 250 - frequency);
        }

        return pic;
    }
}
