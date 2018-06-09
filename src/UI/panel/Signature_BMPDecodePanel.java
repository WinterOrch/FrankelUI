package UI.panel;

import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Signature_BMPDecodePanel extends JPanel{
    private JComboBox<String> inputHashCombox = new JComboBox<>();
    private JLabel inputHashLable = new JLabel();
    private JComboBox<String> inputEncryptCombox = new JComboBox<>();
    private JLabel inputEncyptLable = new JLabel();
    private  PictureDemoPanel EmbededwatermarkPanel = new PictureDemoPanel();
    private  PictureDemoPanel watermarkPanel =  new PictureDemoPanel();

    private JButton confirmButton = new JButton();
    private JLabel inputWatermarkButton = new JLabel();
    private JTextField inputEmbededWatermarkText = new JTextField();
    private JButton inputEmbededWatermarkButton = new JButton();
    private JLabel saveExtractedWatermarkLabel = new JLabel();
    private JTextField saveExtractedWatermarkText = new JTextField();
    private JButton saveExtractedWatermarkButton = new JButton();
    private JLabel inputPasswordLable = new JLabel();
    private JTextField inputPasswordText = new JTextField();
    private JButton inputPasswordButton = new JButton();

    /**
     * 构造
     * created in 22:20 2018/4/30
     */
    Signature_BMPDecodePanel(){
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

        inputWatermarkButton.setText((PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.WATERMARK")));
        inputEmbededWatermarkButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.SCAN"));
        saveExtractedWatermarkLabel.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.SAVEFILE.LABEL"));
        saveExtractedWatermarkButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.SCAN"));
        inputHashLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.HASH.LABLE"));
        inputEncyptLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.ENCRYPTION.LABLE"));
        inputHashCombox.setEditable(false);
        inputHashCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.HASH.FIRST"));
        inputHashCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.HASH.SECOND"));
        inputEncryptCombox.setEditable(false);
        inputEncryptCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.ENCRPT.FIRST"));
        inputEncryptCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.ENCRPT.SECOND"));
        inputPasswordLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.PASSWORD.LABLE"));
        inputPasswordButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.SCAN"));

        /**
         * 栽入图像
         *
         */


        //设置大小
        confirmButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputWatermarkButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputEmbededWatermarkText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputEmbededWatermarkButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        saveExtractedWatermarkLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        saveExtractedWatermarkText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        saveExtractedWatermarkButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputHashLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputHashCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        inputEncyptLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputEncryptCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        inputPasswordText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputPasswordLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPasswordButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        EmbededwatermarkPanel.setPreferredSize(new Dimension(200,200));
        watermarkPanel.setPreferredSize(new Dimension(200,200));


        //设置字体
        inputWatermarkButton.setFont(UIConstants.FONT_NORMAL);
        saveExtractedWatermarkLabel.setFont(UIConstants.FONT_NORMAL);
        inputHashCombox.setFont(UIConstants.FONT_NORMAL);
        confirmButton.setFont(UIConstants.FONT_BUTTON);

        //添加组件

        panelGridSetting.add(inputWatermarkButton);
        panelGridSetting.add(inputEmbededWatermarkText);
        panelGridSetting.add(inputEmbededWatermarkButton);

        panelGridSetting.add(saveExtractedWatermarkLabel);
        panelGridSetting.add(saveExtractedWatermarkText);
        panelGridSetting.add(saveExtractedWatermarkButton);
        panelGridSetting.add(inputHashLable);
        panelGridSetting.add(inputHashCombox);
        panelGridSetting.add(inputEncyptLable);
        panelGridSetting.add(inputEncryptCombox);

        panelGridSetting.add(inputPasswordLable);
        panelGridSetting.add(inputPasswordText);
        panelGridSetting.add(inputPasswordButton);

        secondPanelGridSetting.add(EmbededwatermarkPanel);
        secondPanelGridSetting.add(watermarkPanel);
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
        confirmButton.setText("Extract");
        return southPanel;
    }






    /**
     * 添加监听事件
     * created in 22:52 2018/4/30
     */
    private void addListener() {
        inputEmbededWatermarkButton.addActionListener(e -> {
            JFileChooser WatermarkFile = new JFileChooser();
            WatermarkFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK"));
            int returnWatermarkVal = WatermarkFile.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == returnWatermarkVal) {
                //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                File watermarkSpecificFile = WatermarkFile.getSelectedFile();
                inputEmbededWatermarkText.setText(watermarkSpecificFile.getAbsolutePath());
                EmbededwatermarkPanel.setImagePath(inputEmbededWatermarkText.getText());
                EmbededwatermarkPanel.updateUI();
            }
        });
        saveExtractedWatermarkButton.addActionListener(e -> {
            JFileChooser PictureFile = new JFileChooser();
            PictureFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PICTURE"));
            int returnPictureVal = PictureFile.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == returnPictureVal) {
                //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                File PictureSpecificFile = PictureFile.getSelectedFile();
                saveExtractedWatermarkText.setText(PictureSpecificFile.getAbsolutePath());
            }
            
            
        });
        inputPasswordButton.addActionListener(e->{
            JFileChooser PictureFile = new JFileChooser();
            PictureFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.RSA"));
            int returnPictureVal = PictureFile.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == returnPictureVal) {
                //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                File PictureSpecificFile = PictureFile.getSelectedFile();
                inputPasswordText.setText(PictureSpecificFile.getAbsolutePath());
        }});
        


    }



}
