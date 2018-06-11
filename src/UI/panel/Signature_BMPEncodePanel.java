package UI.panel;

import UI.MainWindow;
import UI.component.ExcuteButton;
import UI.component.ProgressBar;
import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;
import UI.tools.encryption.RSA_Encryption;
import UI.tools.image.Conver;
import UI.tools.image.ImageWall;
import UI.tools.insert.MatrixEncoding;
import UI.tools.watermarking.PictureHash;
import UI.tools.watermarking.WMProperties;
import UI.tools.watermarking.Wong;
import org.apache.commons.codec.digest.DigestUtils;

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
class Signature_BMPEncodePanel extends JPanel {
    private JComboBox<String> inputHashCombox = new JComboBox<>();
    private JLabel inputHashLable = new JLabel();
    private JComboBox<String> inputEncryptCombox = new JComboBox<>();
    private JLabel inputEncyptLable = new JLabel();
    private PictureDemoPanel watermarkPanel = new PictureDemoPanel();
    private PictureDemoPanel picturePanel =  new PictureDemoPanel();

    private ExcuteButton confirmButton;
    private ExcuteButton saveButton;
    private JLabel inputWatermarkLabel = new JLabel();
    private JTextField inputWatermarkText = new JTextField();
    private JButton inputWatermarkButton = new JButton();

    private JLabel inputWatermarksecondLabel = new JLabel();
    private JTextField inputWatermarksecondText = new JTextField();
    private JButton inputWatermarksecondButton = new JButton();

    private JLabel inputPictureLabel = new JLabel();
    private JTextField inputPictureText = new JTextField();
    private JButton inputPictureButton = new JButton();
    private JLabel inputPasswordLable = new JLabel();
    private JTextField inputPasswordText = new JTextField();
    private JButton inputPasswordButton = new JButton();

    private JLabel inputPasswordsecondLable = new JLabel();
    private JTextField inputPasswordsecondText = new JTextField();
    private JButton inputPasswordsecondButton = new JButton();
    private JLabel tip;

    private ProgressBar progressBar;

    private JPopupMenu menuRightClick;
    private JMenuItem showImage;
    private JMenuItem showHist;

    // Flags
    private int pictureSelect;
    private boolean isPictureLoaded;
    private boolean isWatermarkLoaded;

    // File Name of The Loaded Photography
    private String fileNameWatermark;
    private String fileNamePicture;

    // Image Storage
    private static BufferedImage outputImage;
    private static BufferedImage bufferedImageFirstWatermark;
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
        isPictureLoaded = false;
        isWatermarkLoaded = false;
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
        menuRightClick = new JPopupMenu();
        showImage = new JMenuItem(PropertiesLocale.getProperty("UI.PICTUREPANEL.SHOWIMAGE"));
        menuRightClick.add(showImage);
        showHist = new JMenuItem(PropertiesLocale.getProperty("UI.PICTUREPANEL.SHOWHIST"));
        menuRightClick.add(showHist);
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
        inputWatermarkLabel.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK"));
        inputWatermarkButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.SCAN"));
        inputWatermarksecondLabel.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK"));
        inputWatermarksecondButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.SCAN"));

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
        inputEncryptCombox.addItem(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.THIRD"));
        inputPasswordLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.LABLE"));
        inputPasswordButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.BUTTON"));
        inputPasswordsecondLable.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.LABLE"));
        inputPasswordsecondButton.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.BUTTON"));

        progressBar = new ProgressBar(0,100);
        progressBar.setOrientation(JProgressBar.HORIZONTAL);

        if(Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.FIRST"))) {
            inputHashCombox.setSelectedIndex(0);
        }else if(Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.HASH.SECOND"))) {
            inputHashCombox.setSelectedIndex(1);
        }

        if(Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.FIRST"))) {
            inputEncryptCombox.setSelectedIndex(0);
            inputPasswordButton.setEnabled(false);
            inputPasswordsecondButton.setEnabled(false);
        }else if(Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.SECOND"))) {
            inputEncryptCombox.setSelectedIndex(1);
            inputPasswordButton.setEnabled(true);
            inputPasswordsecondButton.setEnabled(true);
        }else if(Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED")).equals(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.THIRD"))) {
            inputEncryptCombox.setSelectedIndex(2);
            inputPasswordButton.setEnabled(false);
            inputPasswordsecondButton.setEnabled(false);
        }


        //设置大小
        inputWatermarkLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputWatermarkText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputWatermarkButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);

        inputWatermarksecondLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputWatermarksecondText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputWatermarksecondButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);

        inputPictureLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);


        inputPictureText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputPictureButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputHashLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputHashCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        inputEncyptLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputEncryptCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);

        inputPasswordText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputPasswordLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPasswordButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);

        inputPasswordsecondText.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        inputPasswordsecondLable.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPasswordsecondButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);


        watermarkPanel.setPreferredSize(UIConstants.PICTURE_SIZE);
        picturePanel.setPreferredSize(UIConstants.PICTURE_SIZE);
        progressBar.setPreferredSize(UIConstants.PROGRESS_BAR_SIZE);

        //设置字体
        inputWatermarkLabel.setFont(UIConstants.FONT_NORMAL);
        inputWatermarkText.setFont(UIConstants.FONT_NORMAL);
        inputWatermarkButton.setFont(UIConstants.FONT_BUTTON);

        inputWatermarksecondLabel.setFont(UIConstants.FONT_NORMAL);
        inputWatermarksecondText.setFont(UIConstants.FONT_NORMAL);
        inputWatermarksecondButton.setFont(UIConstants.FONT_BUTTON);


        inputPictureLabel.setFont(UIConstants.FONT_NORMAL);
        inputEncryptCombox.setFont(UIConstants.FONT_NORMAL);
        inputEncyptLable.setFont(UIConstants.FONT_NORMAL);
        inputHashLable.setFont(UIConstants.FONT_NORMAL);
        inputHashCombox.setFont(UIConstants.FONT_NORMAL);

        inputPictureText.setFont(UIConstants.FONT_NORMAL);
        inputPictureButton.setFont(UIConstants.FONT_BUTTON);
        inputPasswordLable.setFont(UIConstants.FONT_NORMAL);
        inputPasswordText.setFont(UIConstants.FONT_NORMAL);
        inputPasswordButton.setFont(UIConstants.FONT_BUTTON);

        inputPasswordsecondLable.setFont(UIConstants.FONT_NORMAL);
        inputPasswordsecondText.setFont(UIConstants.FONT_NORMAL);
        inputPasswordsecondButton.setFont(UIConstants.FONT_BUTTON);

        //添加组件
        panelGridSetting.add(inputWatermarkLabel);
        panelGridSetting.add(inputWatermarkText);
        panelGridSetting.add(inputWatermarkButton);

        panelGridSetting.add(inputWatermarksecondLabel);
        panelGridSetting.add(inputWatermarksecondText);
        panelGridSetting.add(inputWatermarksecondButton);

        panelGridSetting.add(inputPictureLabel);
        panelGridSetting.add(inputPictureText);
        panelGridSetting.add(inputPictureButton);

        panelGridSetting.add(inputHashLable);
        panelGridSetting.add(inputHashCombox);
        panelGridSetting.add(inputEncyptLable);
        panelGridSetting.add(inputEncryptCombox);

        panelGridSetting.add(inputPasswordLable);
        panelGridSetting.add(inputPasswordText);
        panelGridSetting.add(inputPasswordButton);

        panelGridSetting.add(inputPasswordsecondLable);
        panelGridSetting.add(inputPasswordsecondText);
        panelGridSetting.add(inputPasswordsecondButton);

        secondPanelGridSetting.add(watermarkPanel);
        secondPanelGridSetting.add(picturePanel);
        secondPanelGridSetting.add(progressBar);
        centerPanel.add(panelGridSetting);
        centerPanel.add(secondPanelGridSetting);
        return centerPanel;
    }




    private JPanel getDownPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setBackground(UIConstants.MAIN_BACK_COLOR);
        southPanel.setLayout(new GridLayout(1,2));

        JPanel westPanel = new JPanel();
        westPanel.setOpaque(false);
        westPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        tip = new JLabel("");
        tip.setFont(UIConstants.FONT_NORMAL);
        westPanel.add(tip);

        JPanel eastPanel = new JPanel();
        eastPanel.setOpaque(false);
        eastPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));

        confirmButton = new ExcuteButton(PropertiesLocale.getProperty("UI.SIGNATURE.ACTIVATE"),2F);
        confirmButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);

        saveButton = new ExcuteButton(PropertiesLocale.getProperty("UI.SIGNATURE.SAVE"),2F);
        saveButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        saveButton.setEnabled(false);

        eastPanel.add(confirmButton);
        eastPanel.add(saveButton);

        southPanel.add(westPanel);
        southPanel.add(eastPanel);
        return southPanel;
    }

    /**
     * 添加监听事件
     * created in 22:52 2018/4/30
     */
    private void addListener() {
        watermarkPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pictureSelect = 0;
                //if(e.isPopupTrigger()) {
                if(watermarkPanel.isEmpty) {
                    showImage.setEnabled(false);
                    showHist.setEnabled(false);
                }else {
                    showImage.setEnabled(true);
                    showHist.setEnabled(true);
                }
                menuRightClick.show(e.getComponent(),e.getX(),e.getY());
                //}
            }
        });

        picturePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pictureSelect = 1;
                //if(e.isPopupTrigger()) {
                if(picturePanel.isEmpty) {
                    showImage.setEnabled(false);
                    showHist.setEnabled(false);
                }else {
                    showImage.setEnabled(true);
                    showHist.setEnabled(true);
                }
                menuRightClick.show(e.getComponent(),e.getX(),e.getY());
            }
            //}
        });

        showImage.addActionListener(e -> {
            if(pictureSelect==0) {
                watermarkPanel.showImageDialog(MainWindow.frame, fileNameWatermark);
            }else if(pictureSelect==1) {
                picturePanel.showImageDialog(MainWindow.frame, fileNamePicture);
            }
        });

        showHist.addActionListener(e -> {
            if(pictureSelect==0) {
                watermarkPanel.showHist(MainWindow.frame, fileNameWatermark);
            }else if(pictureSelect==1) {
                picturePanel.showHist(MainWindow.frame, fileNamePicture);
            }
        });

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
                fileNameWatermark = watermarkSpecificFile.getName();
                isWatermarkLoaded = true;
                tip.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK.SUCCESS"));
            }
        });

        inputWatermarksecondButton.addActionListener(e -> {
            JFileChooser WatermarkFile = new JFileChooser();
            WatermarkFile.setDialogTitle(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK"));
            int returnWatermarkVal = WatermarkFile.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == returnWatermarkVal) {
                //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
                File watermarkSpecificFile = WatermarkFile.getSelectedFile();
                inputWatermarksecondText.setText(watermarkSpecificFile.getAbsolutePath());
                bufferedImageFirstWatermark = watermarkPanel.getOrigin();
                watermarkPanel.setImagePath(inputWatermarksecondText.getText());
                watermarkPanel.updateUI();
                fileNameWatermark = watermarkSpecificFile.getName();
                isWatermarkLoaded = true;
                tip.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK.SUCCESS"));
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
                fileNamePicture = PictureSpecificFile.getName();
                isPictureLoaded = true;
                tip.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PICTURE.SUCCESS"));
            }
        });

        // Choose Hash Algorithm
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
                        inputPasswordsecondButton.setEnabled(false);
                    }
                    else if((inputEncryptCombox.getSelectedIndex()==1)){
                        WMProperties.changeAlgorithm("ENCRYP.EMBED",PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.SECOND"));
                        inputPasswordButton.setEnabled(true);
                        inputPasswordsecondButton.setEnabled(true);
                    }
                    else if((inputEncryptCombox.getSelectedIndex()==2)){
                        WMProperties.changeAlgorithm("ENCRYP.EMBED",PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.ENCRPT.THIRD"));
                        inputPasswordButton.setEnabled(false);
                        inputPasswordsecondButton.setEnabled(false);
                    }
                }
            }
        });

        inputPasswordButton.addActionListener(e -> {
            if(inputPasswordText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(MainWindow.frame,PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.WARNING"),
                        PropertiesLocale.getProperty("UI.MESSAGEDIALOG.TITLE"),JOptionPane.WARNING_MESSAGE);
            }else {
                RSA_Encryption.saveFile(inputPasswordText.getText().trim(),RSA_Encryption.TYPE_PRIVATE_KEY);
                tip.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.TIP"));
            }
        });


        inputPasswordsecondButton.addActionListener(e -> {
            if(inputPasswordsecondText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(MainWindow.frame,PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.WARNING"),
                        PropertiesLocale.getProperty("UI.MESSAGEDIALOG.TITLE"),JOptionPane.WARNING_MESSAGE);
            }else {
                RSA_Encryption.saveFile(inputPasswordsecondText.getText().trim(),RSA_Encryption.TYPE_PRIVATE_KEY);
                tip.setText(PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.TIP"));
            }
        });




        // Activate Button
        confirmButton.addActionListener(e -> {
            if(!isPictureLoaded) {
                JOptionPane.showMessageDialog(MainWindow.frame,PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PICTURE.WARNING"),
                        PropertiesLocale.getProperty("UI.MESSAGEDIALOG.TITLE"),JOptionPane.WARNING_MESSAGE);
            }else if(!isWatermarkLoaded) {
                JOptionPane.showMessageDialog(MainWindow.frame,PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.WATERMARK.WARNING"),
                        PropertiesLocale.getProperty("UI.MESSAGEDIALOG.TITLE"),JOptionPane.WARNING_MESSAGE);
            }else if(inputPasswordText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(MainWindow.frame,PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.WARNING"),
                        PropertiesLocale.getProperty("UI.MESSAGEDIALOG.TITLE"),JOptionPane.WARNING_MESSAGE);
            }else if(inputPasswordsecondText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(MainWindow.frame,PropertiesLocale.getProperty("UI.SIGNATURE.BMPENCODE.PASSWORD.WARNING"),
                        PropertiesLocale.getProperty("UI.MESSAGEDIALOG.TITLE"),JOptionPane.WARNING_MESSAGE);
            }else {
                new Thread(()->{
                    int hashType, encrptionType;

                    switch (Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED"))) {
                        case "SHA1": hashType = Wong.HASH_TYPE_SHA1;         break;
                        case "MD5": hashType = Wong.HASH_TYPE_MD5;           break;
                        default: hashType = Wong.HASH_TYPE_SHA1;//TODO
                    }

                    switch (Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED"))) {
                        case "AES": encrptionType = Wong.ENCRYP_TYPE_AES;       break;
                        case "RSA": encrptionType = Wong.ENCRYP_TYPE_RSA;       break;
                        case "DES": encrptionType = Wong.ENCRYP_TYPE_DES;       break;
                        default: encrptionType = Wong.ENCRYP_TYPE_DES;//TODO
                    }

                    String password = inputPasswordText.getText().trim();
                    String passwordsecond = inputPasswordsecondText.getText().trim();

                    BufferedImage carrierImage = picturePanel.getOrigin();

                    BufferedImage watermarkImage = watermarkPanel.getOrigin();


                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.CALHASH"));
                    progressBar.setValue(7);

                    BufferedImage watermarkFirst = Conver.compress(bufferedImageFirstWatermark);
                    BufferedImage watermarksecond = Conver.compress(watermarkImage);

                    watermarkFirst = Conver.binaryImage(watermarkFirst);
                    watermarksecond = Conver.binaryImage(watermarksecond);

                    int[][] binaryWatermark = Conver.binary2Matrix(watermarkFirst);
                    int[][] binarysecondWatermark = Conver.binary2Matrix(watermarksecond);

                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.WMBINARY"));
                    progressBar.setValue(20);
                    //binaryWatermark = ImageWall.fillPiece(carrierImage,binaryWatermark);

                    new ImageWall(carrierImage,binaryWatermark);

                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.WALLGENERATE"));
                    progressBar.setValue(33);


                    /*
                    BufferedImage justTest = Conver.matrix2BufferImage(ImageWall.matrixOutput(),carrierImage);

                    File newFile = new File(UI.tools.ToolConstants.CURRENT_DIR +
                            File.separator + "src" + File.separator + "延拓后水印图片.bmp");
                    try {
                        ImageIO.write((justTest), "bmp", newFile);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    */

                    //Insert Hash of A Whole Hash
                    /*byte[] pictureSummary = PictureHash.operate(carrierImage);

                    if(hashType == Wong.HASH_TYPE_SHA1) {
                        ImageWall.insertHash(DigestUtils.sha(pictureSummary));
                    }else if(hashType == Wong.HASH_TYPE_MD5) {
                        ImageWall.insertHash(DigestUtils.md5(pictureSummary));
                    }*/

                    //Insert Hash According to
                    if(hashType == Wong.HASH_TYPE_SHA1) {
                        ImageWall.insertIndividualHash(carrierImage, hashType);
                    }else if(hashType == Wong.HASH_TYPE_MD5) {
                        ImageWall.insertIndividualHash(carrierImage, hashType);
                    }
                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.HASHINSERT"));
                    progressBar.setValue(42);


                    /*
                    justTest = Conver.matrix2BufferImage(ImageWall.matrixOutput(),carrierImage);
                    newFile = new File(UI.tools.ToolConstants.CURRENT_DIR +
                            File.separator + "src" + File.separator + "异或后的矩阵.bmp");
                    try {
                        ImageIO.write((justTest), "bmp", newFile);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    */


                    if(encrptionType == Wong.ENCRYP_TYPE_AES) {
                        ImageWall.encrypt(password, ImageWall.ENCRYP_TYPE_AES);
                    }else if(encrptionType == Wong.ENCRYP_TYPE_RSA) {
                        ImageWall.encrypt(password, ImageWall.ENCRYP_TYPE_RSA);
                    }else if(encrptionType == Wong.ENCRYP_TYPE_DES) {
                        ImageWall.encrypt(password, ImageWall.ENCRYP_TYPE_DES);
                    }
                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.ENCRYINSERT"));
                    progressBar.setValue(55);

                    binaryWatermark = ImageWall.matrixOutput();

                    /*
                    justTest = Conver.matrix2BufferImage(ImageWall.matrixOutput(),carrierImage);
                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.MATRIXOUTPUT"));
                    progressBar.setValue(64);

                    newFile = new File(UI.tools.ToolConstants.CURRENT_DIR +
                            File.separator + "src" + File.separator + "最终矩阵.bmp");
                    try {
                        ImageIO.write((justTest), "bmp", newFile);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    */

                    // Second
                    new ImageWall(carrierImage,binarysecondWatermark);

                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.WALLGENERATE"));
                    progressBar.setValue(72);

                    //Insert Hash of A Whole Hash
                    /*byte[] pictureSummary = PictureHash.operate(carrierImage);

                    if(hashType == Wong.HASH_TYPE_SHA1) {
                        ImageWall.insertHash(DigestUtils.sha(pictureSummary));
                    }else if(hashType == Wong.HASH_TYPE_MD5) {
                        ImageWall.insertHash(DigestUtils.md5(pictureSummary));
                    }*/

                    //Insert Hash According to
                    if(hashType == Wong.HASH_TYPE_SHA1) {
                        ImageWall.insertIndividualHash(carrierImage, hashType);
                    }else if(hashType == Wong.HASH_TYPE_MD5) {
                        ImageWall.insertIndividualHash(carrierImage, hashType);
                    }
                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.HASHINSERT"));
                    progressBar.setValue(83);


                    if(encrptionType == Wong.ENCRYP_TYPE_AES) {
                        ImageWall.encrypt(passwordsecond, ImageWall.ENCRYP_TYPE_AES);
                    }else if(encrptionType == Wong.ENCRYP_TYPE_RSA) {
                        ImageWall.encrypt(passwordsecond, ImageWall.ENCRYP_TYPE_RSA);
                    }else if(encrptionType == Wong.ENCRYP_TYPE_DES) {
                        ImageWall.encrypt(passwordsecond, ImageWall.ENCRYP_TYPE_DES);
                    }
                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.ENCRYINSERT"));
                    progressBar.setValue(92);

                    binarysecondWatermark = ImageWall.matrixOutput();

                    outputImage = MatrixEncoding.embed(carrierImage,binaryWatermark,binarysecondWatermark);
                    tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.ENCODED"));
                    progressBar.setValue(100);

                    System.out.println(outputImage.getWidth());
                    System.out.println(outputImage.getHeight());



                    // Show Hist of Embeded Picture
                    JDialog imageDialog = new JDialog(MainWindow.frame, "Embeded");
                    Container contentPane = imageDialog.getContentPane();
                    JLabel picture = new JLabel();
                    Icon icon = new ImageIcon(Conver.getHist(outputImage));
                    picture.setIcon(icon);
                    picture.setBounds(10, 10, icon.getIconWidth(),icon.getIconHeight());

                    contentPane.add(picture);
                    imageDialog.setSize(icon.getIconWidth(),icon.getIconHeight());
                    imageDialog.setLocation(40,40);
                    imageDialog.setResizable(false);
                    imageDialog.setVisible(true);



                }).start();
                saveButton.setEnabled(true);
            }
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
                String fname = chooser.getName(file);

                if(!fname.contains( "." + extension )){
                    file = new File(chooser.getCurrentDirectory(),fname+"."+extension);
                }

                try {
                    ImageIO.write(outputImage, "bmp", file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                tip.setText(PropertiesLocale.getProperty("UI,SIGNATURE.BMPENCODE.PROGRESS.SAVED"));
            }
        }).start());
    }
}
