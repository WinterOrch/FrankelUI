package UI.panel;

import UI.MainWindow;
import UI.constant.PropertiesLocale;
import UI.constant.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Setting_OptionPanel extends JPanel {
    private JComboBox<String> languageCombox = new JComboBox<>(UIConstants.languageList);

    /**
     * 构造
     * created in 22:20 2018/4/30
     */
    public Setting_OptionPanel(){
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
        JLabel languageLabel = new JLabel(PropertiesLocale.getProperty("UI.SETTING.OPTION.LANGUAGE"));
        languageCombox.setEditable(false);
        if(PropertiesLocale.locale==1){
            languageCombox.setSelectedIndex(0);
        }
        else if(PropertiesLocale.locale==2){
            languageCombox.setSelectedIndex(1);
        }
        //设置大小
        languageLabel.setPreferredSize(UIConstants.LABLE_SIZE_ITEM);
        languageCombox.setPreferredSize(UIConstants.TEXT_FIELD_SIZE_ITEM);
        //设置字体
        languageLabel.setFont(UIConstants.FONT_NORMAL);
        languageCombox.setFont(UIConstants.FONT_NORMAL);
        //添加组件
        panelGridSetting.add(languageLabel);
        panelGridSetting.add(languageCombox);

        centerPanel.add(panelGridSetting);
        return centerPanel;
    }

    /**
     * 添加监听事件
     * created in 22:52 2018/4/30
     */
    private void addListener(){
        languageCombox.addItemListener(e -> {
            //语言变动
            if( e.getStateChange() == ItemEvent.SELECTED ){
                if(e.getSource() == languageCombox){
                    if((languageCombox.getSelectedIndex()==0)&&(PropertiesLocale.locale!=1)){
                        PropertiesLocale.changeLanguage("CN");
                        PropertiesLocale.initialize();
                    }
                    else if((languageCombox.getSelectedIndex()==1)&&(PropertiesLocale.locale!=2)){
                        PropertiesLocale.changeLanguage("EN");
                        PropertiesLocale.initialize();
                    }
                    MainWindow.freshLocale();
                }
            }
        });
        //TODO
    }

}
