package UI.panel;

import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class Signature_BMPEncodePanel extends JPanel {
    private JComboBox<String> inputHashCombox = new JComboBox<>();
    private JLabel inputHashLable = new JLabel();
    private JComboBox<String> inputEncryptCombox = new JComboBox<>();
    private JLabel inputEncyptLable = new JLabel();
    private  PictureDemoPanel watermarkPanel = new PictureDemoPanel();
    private  PictureDemoPanel picturePanel =  new PictureDemoPanel();

    private JButton confirmButton = new JButton();
    private JLabel inputWatermarkLabel = new JLabel();
    private JTextField inputWatermarkText = new JTextField();
    private JButton inputWatermarkButton = new JButton();
    private JLabel inputPictureLabel = new JLabel();
    private JTextField inputPictureText = new JTextField();
    private JButton inputPictureButton = new JButton();
    private JLabel inputPasswordLable = new JLabel();
    private JTextField inputPasswordText = new JTextField();
    private JButton inputPasswordButton = new JButton();
    private JLabel saveWatermarkedDirectoryLabel = new JLabel();
    private JTextField saveWatermarkedDirectoryText = new JTextField();
    private JButton saveWatermarkedDirectoryButton = new JButton();
    /**
     * 构造
     * created in 22:20 2018/4/30
     */
    Signature_BMPEncodePanel(){
        initialize();
        addComponents();
        addListener();
    }

    /**
     * 初始化
     * created in 22:21 2018/4/30
     */
    private void initialize(){
        this.setBackground(UIConstants.MAIN_BACK_COLOR);
        this.setLayout(new BorderLayout());
    }

    /**
     * 添加组件
     * created in 22:22 2018/4/30
     */
    private void addComponents(){
        this.add(getCenterPanel(), BorderLayout.CENTER);
        this.add(getDownPanel(), BorderLayout.SOUTH);
    }

    /**
     * 生成主界面
     * created in 22:23 2018/4/30
     */
    private JPanel getCenterPanel(){
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(UIConstants.MAIN_BACK_COLOR);
        centerPanel.setLayout(new GridLayout(2, 1));

        //设置Grid
        JPanel panelGridSetting = new JPanel();
        panelGridSetting.setBackground(UIConstants.MAIN_BACK_COLOR);
        panelGridSetting.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 0));
        JPanel secondPanelGridSetting = new JPanel();
        secondPanelGridSetting.setBackground(UIConstants.MAIN_BACK_COLOR);


        //初始化组件

        inputWatermarkLabel.setText((PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK")));
        inputWatermarkButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.SCAN"));
        inputPictureLabel.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PICTURE"));
        inputPictureButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.SCAN"));
        inputHashLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.LABLE"));
        inputEncyptLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRYPTION.LABLE"));
        inputHashCombox.setEditable(false);
        inputHashCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.FIRST"));
        inputHashCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.SECOND"));
        inputEncryptCombox.setEditable(false);
        inputEncryptCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.FIRST"));
        inputEncryptCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.SECOND"));
        inputPasswordLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.LABLE"));
        inputPasswordButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.BUTTON"));
        saveWatermarkedDirectoryLabel.setText((PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.SAVEFILE.LABLE")));
        saveWatermarkedDirectoryButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.SAVEFILE.BUTTON"));
        saveWatermarkedDirectoryButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.SCAN"));

        /**
         * 栽入图像
         *
         */


        //设置大小
        confirmButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputWatermarkLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputWatermarkText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputWatermarkButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPictureLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);

        saveWatermarkedDirectoryText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        saveWatermarkedDirectoryLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        saveWatermarkedDirectoryButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);


        inputPictureText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputPictureButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputHashLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputHashCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        inputEncyptLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputEncryptCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        inputPasswordText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputPasswordLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPasswordButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        watermarkPanel.setPreferredSize(new Dimension(200,200));
        picturePanel.setPreferredSize(new Dimension(200,200));


        //设置字体
        inputWatermarkLabel.setFont(UIConstants.FONT_NORMAL);
        inputPictureLabel.setFont(UIConstants.FONT_NORMAL);
        inputHashCombox.setFont(UIConstants.FONT_NORMAL);
        confirmButton.setFont(UIConstants.FONT_BUTTON);

        //添加组件

        panelGridSetting.add(inputWatermarkLabel);
        panelGridSetting.add(inputWatermarkText);
        panelGridSetting.add(inputWatermarkButton);

        panelGridSetting.add(inputPictureLabel);
        panelGridSetting.add(inputPictureText);
        panelGridSetting.add(inputPictureButton);

        panelGridSetting.add(saveWatermarkedDirectoryLabel);
        panelGridSetting.add(saveWatermarkedDirectoryText);
        panelGridSetting.add(saveWatermarkedDirectoryButton);

        panelGridSetting.add(inputHashLable);
        panelGridSetting.add(inputHashCombox);
        panelGridSetting.add(inputEncyptLable);
        panelGridSetting.add(inputEncryptCombox);

       panelGridSetting.add(inputPasswordLable);
        panelGridSetting.add(inputPasswordText);
       panelGridSetting.add(inputPasswordButton);

        secondPanelGridSetting.add(watermarkPanel);
        secondPanelGridSetting.add(picturePanel);
        centerPanel.add(panelGridSetting);
        centerPanel.add(secondPanelGridSetting);
        return centerPanel;
    }




    private JPanel getDownPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setBackground(UIConstants.MAIN_BACK_COLOR);
       southPanel.add(confirmButton);
        confirmButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);

        southPanel.setLayout(new FlowLayout());

        southPanel.add(confirmButton);
        confirmButton.setText("Start");
        return southPanel;
    }






    /**
     * 添加监听事件
     * created in 22:52 2018/4/30
     */
    private void addListener() {
        inputWatermarkButton.addActionListener(e -> {
                    JFileChooser WatermarkFile = new JFileChooser();
                    WatermarkFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK"));
                    int returnWatermarkVal = WatermarkFile.showOpenDialog(null);
                    if (JFileChooser.APPROVE_OPTION == returnWatermarkVal) {
                        //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                        File watermarkSpecificFile = WatermarkFile.getSelectedFile();
                        inputWatermarkText.setText(watermarkSpecificFile.getAbsolutePath());
                        watermarkPanel.setImagePath(inputWatermarkText.getText());
                        watermarkPanel.updateUI();
                    }
                });
            inputPictureButton.addActionListener(e -> {
                JFileChooser PictureFile = new JFileChooser();
                PictureFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PICTURE"));
                int returnPictureVal = PictureFile.showOpenDialog(null);
                if (JFileChooser.APPROVE_OPTION == returnPictureVal) {
                    //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                    File PictureSpecificFile = PictureFile.getSelectedFile();
                    inputPictureText.setText(PictureSpecificFile.getAbsolutePath());
                    picturePanel.setImagePath(inputPictureText.getText());
                    picturePanel.setPreferredSize(new Dimension(picturePanel.getImgWidth(), picturePanel
                            .getImgHeight()));
                    picturePanel.updateUI();
                }
            });
            saveWatermarkedDirectoryButton.addActionListener(e->{
                JFileChooser PictureFile = new JFileChooser();
                PictureFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.SAVEFILE.TEXT"));
                int returnPictureVal = PictureFile.showOpenDialog(null);
                if (JFileChooser.APPROVE_OPTION == returnPictureVal) {
                    //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                    File PictureSpecificFile = PictureFile.getSelectedFile();
                    saveWatermarkedDirectoryText.setText(PictureSpecificFile.getAbsolutePath());
                }
            });

    }


}
