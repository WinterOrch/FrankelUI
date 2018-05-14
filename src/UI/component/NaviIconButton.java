package UI.component;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Insets;

/**
 * Description 导航栏按钮的组建类
 * @author Frankel.Y
 *  23:27 2018/4/28
 */
public class NaviIconButton extends JButton{
    private ImageIcon buttonEnable, buttonReady;
    private String naviTip;

    /**
     * 构造方式
     * created in 23:35 2018/4/28
     * @param imageNormal 默认图标
     * @param imageReady 鼠标经过时图标
     * @param imageEnable 按下时图标
     * @param tip 说明文字
     */
    public NaviIconButton(ImageIcon imageNormal, ImageIcon imageEnable, ImageIcon imageReady, String tip) {

        super(imageNormal);

        this.buttonEnable = imageEnable;
        this.buttonReady = imageReady;
        this.naviTip = tip;

        initialize();
    }

    /**
     * 初始化: 无边，无焦点渲染，无内容区，边距0
     *         设置图标，设置提示字
     */
    private void initialize(){

        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusable(true);
        this.setMargin(new Insets(0, 0, 0, 0));

        this.setRolloverIcon(buttonReady);
        this.setSelectedIcon(buttonEnable);
        this.setPressedIcon(buttonEnable);
        //this.setDisabledIcon(buttonDisabled);

        if(!naviTip.isEmpty()){
            this.setToolTipText(naviTip);
        }
    }

}
