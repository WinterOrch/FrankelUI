package UI.component;

import UI.constant.UIConstants;

import javax.swing.*;

public class ExcuteButton extends JButton{
    private float strokeWidth;

    public ExcuteButton(String text, float stroke) {
        super(text);

        strokeWidth = stroke;
        initialize();
    }

    /**
     * 初始化: 无焦点渲染,背景透明，设置圆角边框
     */
    private void initialize(){

        this.setBorder(new RoundBorder(UIConstants.NAVI_BAR_BACK_COLOR, strokeWidth));
        this.setBackground(UIConstants.MAIN_BACK_COLOR);
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setFocusable(true);
        this.setForeground(UIConstants.NAVI_BAR_BACK_COLOR);
        this.setFont(UIConstants.FONT_BUTTON);

    }

    public void setDisabled() {
        this.setEnabled(false);
        this.setBorder(new RoundBorder(UIConstants.DISABLED_BUTTON_COLOR, strokeWidth));
    }

    public void setEnabled() {
        this.setEnabled(true);
        this.setBorder(new RoundBorder(UIConstants.NAVI_BAR_BACK_COLOR, strokeWidth));
    }
}
