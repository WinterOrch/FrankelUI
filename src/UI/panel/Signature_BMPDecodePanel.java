package UI.panel;

import UI.MainWindow;
import UI.component.ExcuteButton;
import UI.component.ProgressBar;
import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;
import UI.tools.ToolConstants;
import UI.tools.watermarking.WMProperties;
import UI.tools.watermarking.Wong;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("Duplicates")
public class Signature_BMPDecodePanel extends JPanel{
    private JComboBox<String> inputHashCombox = new JComboBox<>();
    private JLabel inputHashLable = new JLabel();
    private JComboBox<String> inputEncryptCombox = new JComboBox<>();
    private JLabel inputEncyptLable = new JLabel();
    public static PictureDemoPanel EmbededwatermarkPanel = new PictureDemoPanel();
    public static PictureDemoPanel watermarkPanel =  new PictureDemoPanel();
    private File watermarkSpecificFile = null;
    private File PasswordSpecificFile = null;
    private File saveExtractedWatermarkSpecificFile = null;
    private ExcuteButton confirmButton;
    private ExcuteButton saveButton;
    private JLabel inputWatermarkButton = new JLabel();
    private JTextField inputEmbededWatermarkText = new JTextField();
    private JButton inputEmbededWatermarkButton = new JButton();

    private JLabel inputPasswordLable = new JLabel();
    private JTextField inputPasswordText = new JTextField();
    private JButton inputPasswordButton = new JButton();

    private JLabel inputPasswordLable2 = new JLabel();
    private JTextField inputPasswordText2 = new JTextField();
    private JButton inputPasswordButton2 = new JButton();

    public static ProgressBar progressBar;

    private JPopupMenu menuRightClick;
    private JMenuItem showImage;
    private JMenuItem showHist;
    private JMenuItem compare;

    private String fileNameWatermark;
    private int pictureSelect;
    private boolean isExtracted;

    private static BufferedImage embededPicture;
    public static BufferedImage resultPictureOne;
    public static BufferedImage resultPictureTwo;
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
        isExtracted = false;
    }

    /**
     * 添加组件
     * created in 22:22 2018/4/30
     */
    private void addComponents(){
        this.add(getCenterPanel(), BorderLayout.CENTER);
        this.add(getDownPanel(), BorderLayout.SOUTH);
        menuRightClick = new JPopupMenu();
        showImage = new JMenuItem(PropertiesLocale.getProperty("UI.PICTUREPANEL.SHOWIMAGE"));
        menuRightClick.add(showImage);
        showHist = new JMenuItem(PropertiesLocale.getProperty("UI.PICTUREPANEL.SHOWHIST"));
        menuRightClick.add(showHist);
        compare = new JMenuItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.COMPARE"));
        menuRightClick.add(compare);
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
        panelGridSetting.setLayout(new FlowLayout(FlowLayout.LEFT, UIConstants.MAIN_H_GAP, 7));
        JPanel secondPanelGridSetting = new JPanel();
        secondPanelGridSetting.setBackground(UIConstants.MAIN_BACK_COLOR);
        secondPanelGridSetting.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));

        //初始化组件

        inputWatermarkButton.setText((PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.WATERMARK")));
        inputEmbededWatermarkButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.SCAN"));

        inputHashLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.HASH.LABLE"));
        inputEncyptLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.ENCRYPTION.LABLE"));
        inputHashCombox.setEditable(false);
        inputHashCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.HASH.FIRST"));
        inputHashCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.HASH.SECOND"));
        inputEncryptCombox.setEditable(false);
        inputEncryptCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.ENCRPT.FIRST"));
        inputEncryptCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.ENCRPT.SECOND"));
        inputEncryptCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.ENCRPT.THIRD"));
        inputPasswordLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.PASSWORD.LABLE"));
        inputPasswordLable2.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.PASSWORD2.LABLE"));
        inputPasswordButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.SCAN"));
        inputPasswordButton2.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.SCAN"));

        progressBar = new ProgressBar(0,100);
        progressBar.setOrientation(JProgressBar.HORIZONTAL);

        //设置大小   confirmButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputWatermarkButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputEmbededWatermarkText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputEmbededWatermarkButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);

        inputHashLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputHashCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        inputEncyptLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputEncryptCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        inputPasswordText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputPasswordLable2.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPasswordText2.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputPasswordButton2.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPasswordLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPasswordButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        watermarkPanel.setPreferredSize(UIConstants.PICTURE_SIZE);
        EmbededwatermarkPanel.setPreferredSize(UIConstants.PICTURE_SIZE);
        progressBar.setPreferredSize(UIConstants.PROGRESS_BAR_SIZE);

        if(Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.FIRST"))) {
            inputHashCombox.setSelectedIndex(0);
        }else if(Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.SECOND"))) {
            inputHashCombox.setSelectedIndex(1);
        }

        if(Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.FIRST"))) {
            inputEncryptCombox.setSelectedIndex(0);
            inputPasswordButton.setEnabled(false);
            inputPasswordButton2.setEnabled(false);
        }else if(Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.SECOND"))) {
            inputEncryptCombox.setSelectedIndex(1);
            inputPasswordButton.setEnabled(true);
            inputPasswordButton2.setEnabled(true);
        }else if(Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.THIRD"))) {
            inputEncryptCombox.setSelectedIndex(2);
            inputPasswordButton.setEnabled(false);
            inputPasswordButton2.setEnabled(false);
        }
        //设置字体
        inputWatermarkButton.setFont(UIConstants.FONT_BUTTON);
        inputPasswordButton2.setFont(UIConstants.FONT_BUTTON);
        inputHashCombox.setFont(UIConstants.FONT_NORMAL);
        inputHashLable.setFont(UIConstants.FONT_NORMAL);
        inputEncryptCombox.setFont(UIConstants.FONT_NORMAL);
        inputEncyptLable.setFont(UIConstants.FONT_NORMAL);
        inputWatermarkButton.setFont(UIConstants.FONT_NORMAL);
        inputEmbededWatermarkButton.setFont(UIConstants.FONT_BUTTON);
        inputPasswordLable.setFont(UIConstants.FONT_NORMAL);
        inputPasswordLable2.setFont(UIConstants.FONT_NORMAL);
        inputPasswordButton.setFont(UIConstants.FONT_BUTTON);


        //添加组件
        panelGridSetting.add(inputWatermarkButton);
        panelGridSetting.add(inputEmbededWatermarkText);
        panelGridSetting.add(inputEmbededWatermarkButton);

        panelGridSetting.add(inputHashLable);
        panelGridSetting.add(inputHashCombox);
        panelGridSetting.add(inputEncyptLable);
        panelGridSetting.add(inputEncryptCombox);

        panelGridSetting.add(inputPasswordLable);
        panelGridSetting.add(inputPasswordText);
        panelGridSetting.add(inputPasswordButton);

        panelGridSetting.add(inputPasswordLable2);
        panelGridSetting.add(inputPasswordText2);
        panelGridSetting.add(inputPasswordButton2);

        secondPanelGridSetting.add(EmbededwatermarkPanel);
        secondPanelGridSetting.add(watermarkPanel);
        secondPanelGridSetting.add(progressBar);
        centerPanel.add(panelGridSetting);
        centerPanel.add(secondPanelGridSetting);
        return centerPanel;
    }




    private JPanel getDownPanel() {
        JPanel southPanel = new JPanel();
        confirmButton = new ExcuteButton(PropertiesLocale.getProperty("UI.SIGNATURE.EXTRACT"),2F);
        saveButton = new ExcuteButton(PropertiesLocale.getProperty("UI.SIGNATURE.SAVE"),2F);
        southPanel.setBackground(UIConstants.MAIN_BACK_COLOR);

        confirmButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        confirmButton.setFont(UIConstants.FONT_BUTTON);
        saveButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        saveButton.setFont(UIConstants.FONT_BUTTON);
        saveButton.setEnabled(false);
        southPanel.setBackground(UIConstants.MAIN_BACK_COLOR);
        southPanel.setLayout(new GridLayout(1,2));
        JPanel westPanel = new JPanel();
        westPanel.setOpaque(false);
        westPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JPanel eastPanel = new JPanel();
        eastPanel.setOpaque(false);
        eastPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));

        eastPanel.add(confirmButton);
        eastPanel.add(saveButton);
        southPanel.setLayout(new GridLayout(1,2));
        southPanel.add(westPanel);
        southPanel.add(eastPanel);
        return southPanel;
    }



    /**
     * 添加监听事件
     * created in 22:52 2018/4/30
     */
    private void addListener() {
        EmbededwatermarkPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pictureSelect = 0;
                //if(e.isPopupTrigger()) {
                if(EmbededwatermarkPanel.isEmpty) {
                    showImage.setEnabled(false);
                    showHist.setEnabled(false);
                }else {
                    showImage.setEnabled(true);
                    showHist.setEnabled(true);
                }
                if(isExtracted) {
                    compare.setEnabled(true);
                }else {
                    compare.setEnabled(false);
                }
                menuRightClick.show(e.getComponent(),e.getX(),e.getY());
                //}
            }
        });

        watermarkPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pictureSelect = 1;
                //if(e.isPopupTrigger()) {
                if(watermarkPanel.isEmpty) {
                    showImage.setEnabled(false);
                    showHist.setEnabled(false);
                    compare.setEnabled(false);
                }else {
                    showImage.setEnabled(true);
                    showHist.setEnabled(true);
                }
                if(isExtracted) {
                    compare.setEnabled(true);
                }else {
                    compare.setEnabled(false);
                }
                menuRightClick.show(e.getComponent(),e.getX(),e.getY());
            }
            //}
        });

        showImage.addActionListener(e -> {
            if(pictureSelect==0) {
                EmbededwatermarkPanel.showImageDialog(MainWindow.frame, fileNameWatermark);
            }else if(pictureSelect==1) {
                watermarkPanel.showImageDialog(MainWindow.frame, "Extracted");
            }
        });

        showHist.addActionListener(e -> {
            if(pictureSelect==0) {
                EmbededwatermarkPanel.showHist(MainWindow.frame, fileNameWatermark);
            }else if(pictureSelect==1) {
                watermarkPanel.showHist(MainWindow.frame, "Extracted");
            }
        });

        compare.addActionListener(e -> {
            if(pictureSelect==0) {
                EmbededwatermarkPanel.showCompare(MainWindow.frame, "Compare_1",embededPicture);
            }else if(pictureSelect==1) {
                watermarkPanel.showCompare(MainWindow.frame, "Compare_2",embededPicture);
            }
        });

        inputEmbededWatermarkButton.addActionListener(e -> {
            JFileChooser WatermarkFile = new JFileChooser();
            WatermarkFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK"));
            int returnWatermarkVal = WatermarkFile.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == returnWatermarkVal) {
                //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                watermarkSpecificFile = WatermarkFile.getSelectedFile();
                fileNameWatermark = watermarkSpecificFile.getName();
                inputEmbededWatermarkText.setText(watermarkSpecificFile.getAbsolutePath());
                EmbededwatermarkPanel.setImagePath(inputEmbededWatermarkText.getText());
                EmbededwatermarkPanel.updateUI();
            }
        });

        inputPasswordButton.addActionListener(e->{
            JFileChooser PictureFile = new JFileChooser();
            PictureFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.RSA"));
            int returnPictureVal = PictureFile.showOpenDialog(null);

            String extension = ToolConstants.PRIVATE_KEY_FILE;
            //私钥后缀名过滤器
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Private Key File(*." + extension + ")", extension);

            PictureFile.setFileFilter(filter);

            if (JFileChooser.APPROVE_OPTION == returnPictureVal) {
                //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                PasswordSpecificFile = PictureFile.getSelectedFile();
                inputPasswordText.setText(PasswordSpecificFile.getAbsolutePath());
            }});

        inputPasswordButton2.addActionListener(e->{
            JFileChooser PictureFile = new JFileChooser();
            PictureFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPDECODE.RSA"));
            int returnPictureVal = PictureFile.showOpenDialog(null);

            String extension = ToolConstants.PRIVATE_KEY_FILE;
            //私钥后缀名过滤器
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Private Key File(*." + extension + ")", extension);

            PictureFile.setFileFilter(filter);

            if (JFileChooser.APPROVE_OPTION == returnPictureVal) {
                //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                PasswordSpecificFile = PictureFile.getSelectedFile();
                inputPasswordText2.setText(PasswordSpecificFile.getAbsolutePath());
            }});

        inputHashCombox.addItemListener(e -> {
            //算法变动
            if( e.getStateChange() == ItemEvent.SELECTED ){
                if(e.getSource() == inputHashCombox){
                    if((inputHashCombox.getSelectedIndex()==0)){
                        WMProperties.changeAlgorithm("HASH.EMBED",PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.FIRST"));
                    }
                    else if((inputHashCombox.getSelectedIndex()==1)){
                        WMProperties.changeAlgorithm("HASH.EMBED",PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.SECOND"));
                    }
                }
            }
        });

        // Choose Encryption Algorithm
        inputEncryptCombox.addItemListener(e -> {
            //算法变动
            if( e.getStateChange() == ItemEvent.SELECTED ){
                if(e.getSource() == inputEncryptCombox){
                    if((inputEncryptCombox.getSelectedIndex()==0)){
                        WMProperties.changeAlgorithm("ENCRYP.EMBED",PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.FIRST"));
                        inputPasswordButton.setEnabled(false);
                        inputPasswordButton2.setEnabled(false);
                    }
                    else if((inputEncryptCombox.getSelectedIndex()==1)){
                        WMProperties.changeAlgorithm("ENCRYP.EMBED",PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.SECOND"));
                        inputPasswordButton.setEnabled(true);
                        inputPasswordButton2.setEnabled(true);
                    }
                    else if((inputEncryptCombox.getSelectedIndex()==2)){
                        WMProperties.changeAlgorithm("ENCRYP.EMBED",PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.THIRD"));
                        inputPasswordButton.setEnabled(false);
                        inputPasswordButton2.setEnabled(false);
                    }
                }
            }
        });

        confirmButton.addActionListener(e -> {
            BufferedImage origin;
            try {
                origin = ImageIO.read(watermarkSpecificFile);

                embededPicture = origin;

                if (Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED")).equals("SHA1"))
                    if (Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED")).equals("DES")) {
                        Wong.decodePictureFirst(origin,inputPasswordText.getText(),Wong.HASH_TYPE_SHA1,Wong.ENCRYP_TYPE_DES,null,null);
                        Wong.decodePictureSecond(origin,inputPasswordText2.getText(),Wong.HASH_TYPE_SHA1,Wong.ENCRYP_TYPE_DES,null,null);
                    }
                if (Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED")).equals("MD5"))
                    if (Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED")).equals("DES")) {
                        Wong.decodePictureFirst(origin,inputPasswordText.getText(),Wong.HASH_TYPE_MD5,Wong.ENCRYP_TYPE_DES,null,null);
                        Wong.decodePictureSecond(origin,inputPasswordText2.getText(),Wong.HASH_TYPE_MD5,Wong.ENCRYP_TYPE_DES,null,null);
                    }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            EmbededwatermarkPanel.setFrontImage(resultPictureOne);
            watermarkPanel.setFrontImage(resultPictureTwo);
            saveButton.setEnabled(true);
            isExtracted = true;
        });

        saveButton.addActionListener(e -> new Thread(() -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter;
            String extension = "bmp";
            filter = new FileNameExtensionFilter(
                    "." + extension, extension);
            chooser.setFileFilter(filter);

            int option = chooser.showSaveDialog(null);
            if(option==JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                File file2;
                String fname = chooser.getName(file);

                //if(!fname.contains( "." + extension )){
                    file = new File(chooser.getCurrentDirectory(),fname + "_1" + "."+extension);
                    file2 = new File(chooser.getCurrentDirectory(),fname + "_2" + "."+extension);
                //}

                try {
                    ImageIO.write(resultPictureOne, "bmp", file);
                    ImageIO.write(resultPictureTwo, "bmp", file2);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }).start());
    }
}