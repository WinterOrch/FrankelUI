package UI.panel;


import UI.constant.UIConstants;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

    public class PictureDemoPanel extends javax.swing.JPanel {

        private static final long serialVersionUID = 1L;
        private Image image;
        private int imgWidth;
        private int imgHeight;

        public int getImgWidth() {
            return imgWidth;
        }

        private void setImgWidth(int imgWidth) {
            this.imgWidth = imgWidth;

        }

        public int getImgHeight() {
            return imgHeight;
        }

        private void setImgHeight(int imgHeight) {
            this.imgHeight = imgHeight;
        }

        PictureDemoPanel(){
            try {
                imgWidth = 200;
                imgHeight = 200;
                image = ImageIO.read(UIConstants.PLACE_HOLDER);
                image = image.getScaledInstance(200,200, Image.SCALE_DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

        public void setImagePath(String imgPath) {
            // 该方法不推荐使用，该方法是懒加载，图像并不加载到内存，当拿图像的宽和高时会返回-1；
            // image = Toolkit.getDefaultToolkit().getImage(imgPath);
            try {
                // 该方法会将图像加载到内存，从而拿到图像的详细信息。
                image = ImageIO.read(new FileInputStream(imgPath));
                image = image.getScaledInstance(200,200,Image.SCALE_DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setImgWidth(image.getWidth(this));
            setImgHeight(image.getHeight(this));
        }

        @Override
        public void paintComponent(Graphics g1) {
            int x = 0;
            int y = 0;
            if (null == image) {
                return;
            }

            g1.drawImage(image, x, y, image.getWidth(this), image.getHeight(this),
                    this);
        }
    }


