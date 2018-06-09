package UI.tools.image;

import UI.MainWindow;
import UI.tools.ToolConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
            image = ImageIO.read(MainWindow.class.getResource("/testPhoto/24.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage binaryImage = binaryImage(compress(Objects.requireNonNull(image)));
        for(int i=0;i<binaryImage.getHeight();i++) {
            for(int j=0;j<binaryImage.getWidth();j++) {
                //System.out.print(t[i][j]+" ");
                binaryImage.setRGB(j,i,BINARY_WHITE);
            }
            //System.out.println("\n");
        }

        File newFile = new File(UI.tools.ToolConstants.CURRENT_DIR +
                File.separator + "src" + File.separator + "2722425974762424027.bmp");
        try {
            ImageIO.write((binaryImage), "bmp", newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
