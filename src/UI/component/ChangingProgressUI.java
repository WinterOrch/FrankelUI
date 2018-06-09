package UI.component;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ChangingProgressUI extends BasicProgressBarUI {

    private JProgressBar jProgressBar;
    private int progressvalue;
    private Color targetColor;
    private Color initialColor;

    ChangingProgressUI(JProgressBar jProgressBar,Color iniColor, Color forecolor) {
        this.jProgressBar = jProgressBar;
        this.initialColor = iniColor;
        this.targetColor = forecolor;
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {

        progressvalue = this.jProgressBar.getValue();

        if(progressvalue<20){
            this.jProgressBar.setForeground(Color.BLUE);
        }
        else if(progressvalue<40){
            this.jProgressBar.setForeground(Color.YELLOW);
        }
        else if(progressvalue<60){
            this.jProgressBar.setForeground(Color.RED);
        }
        else if(progressvalue<80){
            this.jProgressBar.setForeground(Color.GREEN);
        }
        else{
            this.jProgressBar.setForeground(Color.CYAN);
        }

        super.paintDeterminate(g, c);
    }

}