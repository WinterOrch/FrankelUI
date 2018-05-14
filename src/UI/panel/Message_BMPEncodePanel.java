package UI.panel;

import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;

public class Message_BMPEncodePanel extends JPanel {
    private JComboBox<String> inputPictureCombox = new JComboBox<>();
    private JTextField inputSeedTextField = new JTextField();
    private JButton seedRandomButton;

    /**
     * 构造
     * created in 22:20 2018/4/30
     */
    public Message_BMPEncodePanel(){
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
        //TODO this.add(getDownPanel(), BorderLayout.SOUTH);
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

        //初始化组件
        JLabel inputPictureLabel = new JLabel(PropertiesLocale.getProperty("UI.MESSAGE.BMPENCODE.INPUT"));
        JLabel inputSeedLabel = new JLabel(PropertiesLocale.getProperty("UI.MESSAGE.BMPENCODE.SEED"));
        seedRandomButton = new JButton(PropertiesLocale.getProperty("UI.RANDOM"));
        inputPictureCombox.setEditable(false);
        inputPictureCombox.addItem(PropertiesLocale.getProperty("UI.MESSAGE.BMPENCODE.INPUT.DEFAULT"));
        inputPictureCombox.addItem(PropertiesLocale.getProperty("UI.MESSAGE.BMPENCODE.INPUT.CUSTOM"));
        inputPictureCombox.addItem(PropertiesLocale.getProperty("UI.MESSAGE.BMPENCODE.INPUT.RECENT"));

        //设置大小
        inputPictureLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputPictureCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        inputSeedLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        inputSeedTextField.setPreferredSize(UIConstants.REVERSED_TEXT_FIELD_SIZE_ITEM);
        seedRandomButton.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);

        //设置字体
        inputPictureLabel.setFont(UIConstants.FONT_NORMAL);
        inputSeedLabel.setFont(UIConstants.FONT_NORMAL);
        inputPictureCombox.setFont(UIConstants.FONT_NORMAL);
        inputSeedTextField.setFont(UIConstants.FONT_NORMAL);
        seedRandomButton.setFont(UIConstants.FONT_BUTTON);

        //添加组件
        panelGridSetting.add(inputPictureLabel);
        panelGridSetting.add(inputPictureCombox);
        panelGridSetting.add(inputSeedLabel);
        panelGridSetting.add(inputSeedTextField);
        panelGridSetting.add(seedRandomButton);

        centerPanel.add(panelGridSetting);
        return centerPanel;
    }

    /**
     * 添加监听事件
     * created in 22:52 2018/4/30
     */
    private void addListener(){

        //TODO
    }
}
