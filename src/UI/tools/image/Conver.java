package UI.tools.image;

import UI.MainWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static UI.tools.ToolConstants.CURRENT_DIR;

public class Conver {

    public static void binaryImage(BufferedImage image) {

        int width = Objects.requireNonNull(image).getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File(CURRENT_DIR + File.separator + "src" + File.separator + "2722425974762424028.bmp");
        try {
            ImageIO.write(grayImage, "bmp", newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void grayImage() throws IOException{
        BufferedImage image = ImageIO.read(MainWindow.class.getResource("/testPhoto/32.bmp"));

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File(System.getProperty("user.dir")+"/src/2722425974762424027.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }

    public static void main(String[] args) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(MainWindow.class.getResource("/testPhoto/24.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        binaryImage(image);
    }
}
