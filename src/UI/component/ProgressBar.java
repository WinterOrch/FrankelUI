package UI.component;


import UI.constant.UIConstants;

import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;

public class ProgressBar extends JProgressBar{
    public ProgressBar(int min, int max) {
        super(min,max);

        this.setBorderPainted(false);
    }

    @Override
    public void setUI(ProgressBarUI ui) {
        // TODO Auto-generated method stub
        super.setUI(new ProgressUI(this, UIConstants.NAVI_BAR_BACK_COLOR));//这个颜色就是我们要设置的进度条颜色
    }
}
