package UI.panel;

import UI.constant.UIConstants;
import UI.tools.image.Conver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PictureDemoPanel extends javax.swing.JPanel {

        private static final long serialVersionUID = 1L;
        public boolean isEmpty;
        private Image imageOrigin;
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
                isEmpty = true;
                imgWidth = 300;
                imgHeight = 200;
                image = ImageIO.read(UIConstants.PLACE_HOLDER);
                image = image.getScaledInstance(300,200, Image.SCALE_DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public BufferedImage getOrigin() {
            return Conver.toBufferedImage(imageOrigin);
        }

        public void setImagePath(String imgPath) {
            isEmpty = false;
            // 该方法不推荐使用，该方法是懒加载，图像并不加载到内存，当拿图像的宽和高时会返回-1；
            // image = Toolkit.getDefaultToolkit().getImage(imgPath);
            try {
                // 该方法会将图像加载到内存，从而拿到图像的详细信息。
                image = ImageIO.read(new FileInputStream(imgPath));
                imageOrigin = ImageIO.read(new FileInputStream(imgPath));
                image = image.getScaledInstance(300,200,Image.SCALE_DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setImgWidth(image.getWidth(this));
            setImgHeight(image.getHeight(this));
        }

        public void showImageDialog(Frame owner, String title) {
            if(!isEmpty) {
                JDialog imageDialog = new JDialog(owner, title);
                Container contentPane = imageDialog.getContentPane();
                JLabel picture = new JLabel();
                Icon icon = new ImageIcon(imageOrigin);
                picture.setIcon(icon);
                picture.setBounds(10, 10, icon.getIconWidth(),icon.getIconHeight());

                contentPane.add(picture);
                imageDialog.setSize(icon.getIconWidth(),icon.getIconHeight());
                imageDialog.setLocation(10,10);
                imageDialog.setResizable(false);
                imageDialog.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(owner,"Please select a picture first!");
            }
        }

        public void showHist(Frame owner, String title) {
            if(!isEmpty) {
                JDialog imageDialog = new JDialog(owner, title);
                Container contentPane = imageDialog.getContentPane();
                JLabel picture = new JLabel();
                Icon icon = new ImageIcon(Conver.getHist(this.getOrigin()));
                picture.setIcon(icon);
                picture.setBounds(10, 10, icon.getIconWidth(),icon.getIconHeight());

                contentPane.add(picture);
                imageDialog.setSize(icon.getIconWidth(),icon.getIconHeight());
                imageDialog.setLocation(10,10);
                imageDialog.setResizable(false);
                imageDialog.setVisible(true);
            }
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


