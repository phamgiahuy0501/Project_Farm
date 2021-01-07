/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kazen
 */
public class Ground extends MyLabel{
    int type;
    long timeFinish;
    final JLabel timeLabel = new JLabel();
    
    public Ground() {
        super();
        timeFinish = 0;
        type = 0;
    }

    public Ground(int type, long timeFinish, JLabel label, Point point) {
        super(label, point);
        this.type = type;
        this.timeFinish = timeFinish;
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        addTimeLable(timeLabel);
    }    
    
    public void hideTimeLabel() {
        timeLabel.setVisible(false);
        label.revalidate();
        label.repaint();
    }
    
    public void updateTimeLabel(float percent) {
        timeLabel.setText(String.format("%.2f", percent) + "%");
        timeLabel.repaint();
    }
    
    public void showTimeLabel() {
        timeLabel.setVisible(true);
        label.revalidate();
        label.repaint();
    }
    
    public long getTimeFinish() {
        return timeFinish;
    }
    
    public int getType() {
        return type;
    }
    
    public void setEmpty() {
        type = 0;
        timeFinish = 0;
    }
}
